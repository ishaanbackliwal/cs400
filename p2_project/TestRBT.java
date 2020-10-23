import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test if a Red-Black tree 
// extension of rbt is correct.  Mostly check node color and position

//@SuppressWarnings("rawtypes")
public class TestRBT  {

    protected RBT<Integer,String> rbt;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
         rbt = new RBT<Integer,String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /** 
     * CASE 123 Insert three values in sorted order and then check 
     * the root, left, and right keys to see if RBT rebalancing 
     * occurred.
     * 
     */
    @Test
    void testRBT_001_insert_sorted_order_simple() {
        try {
            rbt.insert(10, "10");
            Assert.assertTrue(rbt.rootIsBlack());
            
            rbt.insert(20, "20");
            Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20)) ;
            Assert.assertEquals(rbt.colorOf(20),RBT.RED);
            
            rbt.insert(30, "30");  // SHOULD CAUSE REBALANCING
            Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));
            rbt.print();
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child
            Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
            Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
            Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

            rbt.print();
            
        } catch (Exception e) {
            //e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 321 Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    @Test
    void testRBT_002_insert_reversed_sorted_order_simple() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    	try {
    		rbt.insert(30, "30");
            Assert.assertTrue(rbt.rootIsBlack());
            
            rbt.insert(20, "20");
            Assert.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(20)) ;
            Assert.assertEquals(rbt.colorOf(20),RBT.RED);
            
            rbt.insert(10, "10");  // SHOULD CAUSE REBALANCING
            Assert.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child
            Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
            Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
            Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

            rbt.print();
    	}
    	catch (Exception e) {
            //e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }        
    }

    /** 
     * CASE 132 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred correctly.
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    @Test
    void testRBT_003_insert_smallest_largest_middle_order_simple() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    	try {
    		rbt.insert(10, "10");
            Assert.assertTrue(rbt.rootIsBlack());
            
            rbt.insert(30, "30");
            Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(30)) ;
            Assert.assertEquals(rbt.colorOf(30),RBT.RED);
            
            rbt.insert(20, "20");  // SHOULD CAUSE REBALANCING
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child
            Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
            Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
            Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

            rbt.print();
    	}
    	catch (Exception e) {
            //e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
        
    }

    /** 
     * CASE 312 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred correctly.
     */
    @Test
    void testRBT_004_insert_largest_smallest_middle_order_simple() {
    	try {
    		rbt.insert(30, "30");
            Assert.assertTrue(rbt.rootIsBlack());
            
            rbt.insert(10, "10");
            Assert.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(10)) ;
            Assert.assertEquals(rbt.colorOf(10),RBT.RED);
            
            rbt.insert(20, "20");  // SHOULD CAUSE REBALANCING
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child
            Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
            Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
            Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

            rbt.print();
    	}
    	catch (Exception e) {
            //e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }
    /**
     * Tests whether inserting a lot of nodes and getting them works
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    @Test
    void testRBT_005_insert_many_then_get_all() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    	for(int i = 0; i < 244; i++) {
    		rbt.insert(i, "V" + i);
    	}
    	try {
    		for(int i = 0; i < 244; i++) {
        		rbt.get(i);
        	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("getting all items stores in RBT threw an exception when it should not have");
    		
    	}
    }
    /**
     * Tests whether inserting a duplicate key throws a DuplicateKeyException
     */
    @Test
    void testRBT_006_inserting_duplicate_key_throws_DuplicateKeyException() {
    	try {
    		rbt.insert(10, "V");
    		rbt.insert(10, "V");
    		fail("DuplicateKeyException was not thrown after inserting a duplicate key.");
    	}
    	catch(DuplicateKeyException e) {
    		//nothing is done since if this exception is thrown, program is running as expected 
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests whether inserting a null key throws an IllegalNullKeyException
     */
    @Test
    void testRBT_007_inserting_null_key_throws_IllegalNullKeyException() {
    	try {
    		rbt.insert(null, "V");
    		fail("IllegalNullKeyException was not thrown after inserting a null key.");
    	}
    	catch(IllegalNullKeyException e) {
    		//nothing is done since if this exception is thrown, program is running as expected 
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests whether inserting one item with a certain key and then using the get
     * method on that key returns the correct value
     */
    @Test
    void tstrbt_008_insert_get_one_item_returns_right_val() {
    	try {
    		rbt.insert(10, "V");
    		if(rbt.get(10).compareTo("V") != 0)
    			fail("get was supposed to return 'V' but returned '" + rbt.get(10) + "' instead.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checks whether getting a value after inserting amny in the rbt changes
     * the value of numKeys
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
    @Test
    void testRBT_009_get_does_not_change_numKeys() throws IllegalNullKeyException, DuplicateKeyException {
    	try {
    		for(int i = 0; i < 123; i++)
        		rbt.insert(i, "V" + i);
    		int initialNum = rbt.numKeys();
    		rbt.get(24);
    		if(rbt.numKeys() != initialNum)
    			fail("Getting a value changed numKeys even though it should not.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checks whether getting a value from the rbt does not remove that key
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
    @Test
    void testRBT_010_get_does_not_remove() throws IllegalNullKeyException, DuplicateKeyException {
    	try {
    		for(int i = 0; i < 123; i++)
        		rbt.insert(i, "V" + i);
    		rbt.get(24);
    		if(!rbt.contains(24))
    			fail("Getting a value removed a key even though it should not.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in balanced order produces the right 
     * post order traversal
     */
    @Test
    void testRBT_011_check_postOrder_balanced_insert() {
    	try {
    		rbt.insert(20, "1");
    		rbt.insert(10, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(30);
    		expected.add(20);
    		
    		List<Integer> real = rbt.getPostOrderTraversal();
    		System.out.println("Post Order :" + real);
    		System.out.println("Expected   :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in balanced order produces the right 
     * level order traversal
     */
    @Test
    void testRBT_012_check_levelOrder_balanced_insert() {
    	try {
    		rbt.insert(20, "1");
    		rbt.insert(10, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(20);
    		expected.add(10);
    		expected.add(30);
    		
    		List<Integer> real = rbt.getLevelOrderTraversal();
    		System.out.println("Level Order :" + real);
    		System.out.println("Expected    :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in balanced order produces the right 
     * pre order traversal
     */
    @Test
    void testRBT_013_check_preOrder_balanced_insert() {
    	try {
    		rbt.insert(20, "1");
    		rbt.insert(10, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(20);
    		expected.add(10);
    		expected.add(30);
    		
    		List<Integer> real = rbt.getPreOrderTraversal();
    		System.out.println("Pre Order :" + real);
    		System.out.println("Expected  :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in balanced order produces the right 
     * in order traversal
     */
    @Test
    void testRBT_014_check_inOrder_balanced_insert() {
    	try {
    		rbt.insert(20, "1");
    		rbt.insert(10, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(20);
    		expected.add(30);
    		
    		List<Integer> real = rbt.getInOrderTraversal();
    		System.out.println("In Order  :" + real);
    		System.out.println("Expected  :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in an unbalanced order produces the right 
     * in order traversal
     */
    @Test
    void testRBT_015_check_inOrder_unbalanced_insert() {
    	try {
    		rbt.insert(10, "1");
    		rbt.insert(20, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(20);
    		expected.add(30);
    		
    		List<Integer> real = rbt.getInOrderTraversal();
    		System.out.println("In Order  :" + real);
    		System.out.println("Expected  :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in an unbalanced order produces the right 
     * pre order traversal
     */
    @Test
    void testBST_016_check_preOrder_unbalanced_insert() {
    	try {
    		rbt.insert(10, "1");
    		rbt.insert(20, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(20);
    		expected.add(30);
    		
    		List<Integer> real = rbt.getPreOrderTraversal();
    		System.out.println("Pre Order :" + real);
    		System.out.println("Expected  :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in an unbalanced order produces the right 
     * post order traversal
     */
    @Test
    void testBST_017_check_postOrder_unbalanced_insert() {
    	try {
    		rbt.insert(10, "1");
    		rbt.insert(20, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(30);
    		expected.add(10);
    		expected.add(20);
    		
    		List<Integer> real = rbt.getPostOrderTraversal();
    		System.out.println("Post Order :" + real);
    		System.out.println("Expected   :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checking that inserting 3 values in an unbalanced order produces the right 
     * level order traversal
     */
    @Test
    void testBST_018_check_levelOrder_unbalanced_insert() {
    	try {
    		rbt.insert(10, "1");
    		rbt.insert(20, "2");
    		rbt.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(20);
    		expected.add(10);
    		expected.add(30);
    		
    		List<Integer> real = rbt.getLevelOrderTraversal();
    		System.out.println("Level Order :" + real);
    		System.out.println("Expected    :" + expected);
    		
    		Assert.assertEquals(real, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests the height when the tree is empty
     */
    @Test
    void testRBT_019_height_zero_when_tree_is_empty() {
    	try {
    		if(rbt.getHeight() != 0)
    			fail("Tree is empty and thus height should be 0, but it is actually: " + rbt.getHeight());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests if when the root is the sole node in the tree, the height is one
     */
    @Test
    void testRBT_020_height_is_one_when_root_is_sole_node() {
    	try {
    		rbt.insert(10, "root");
    		if(rbt.getHeight() != 1)
    			fail("Root is the sole node in the RBT, and height should be 1, but it is not.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests whether getHeight returns 3 for a full balanced tree with a height of 3 
     */
    @Test
    void testRBT_021_height_for_balanced_insert_order() {
    	try {
    		rbt.insert(50, "root");
    		rbt.insert(25, "25");
    		rbt.insert(75, "75");
    		rbt.insert(15, "15");
    		rbt.insert(35, "35");
    		rbt.insert(65, "65");
    		rbt.insert(85, "85");
    		if(rbt.getHeight() != 3)
    			fail("Height should be 3, but instead is: " + rbt.getHeight());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests whether getHeight returns 3 for an balanced tree with a height of 3
     */
    @Test
    void testRBT_022_height_for_unbalanced_insert_order() {
    	try {
    		rbt.insert(10, "root");
    		rbt.insert(20, "20");
    		rbt.insert(30, "30");
    		rbt.insert(40, "40");
    		rbt.insert(50, "50");
    		rbt.insert(60, "60");
    		rbt.insert(70, "70");
    		if(rbt.getHeight() != 3)
    			fail("Height should be 3, but instead is: " + rbt.getHeight());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    // TODO: Add your own tests
    
    // Add tests to make sure that rebalancing occurs even if the 
    // tree is larger.   Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    // Does delete work?  Does the tree maintain balance when a key is deleted?
    // If delete is not implemented, does calling it throw an UnsupportedOperationException

} // copyright Deb Deppeler, all rights reserved, DO NOT SHARE