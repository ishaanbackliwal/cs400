import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@SuppressWarnings("rawtypes")
public class TestBST {

    protected STADT<Integer,String> bst;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
         bst = new BST<Integer,String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }
    /** 
     * CASE 123 Insert three values in sorted order and then check 
     * the root, left, and right keys to see if insert worked 
     * correctly.
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     */
    @Test
    void testBST_001_insert_sorted_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("insert to right child of root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 30 changed root");

            if (!bst.getKeyOfRightChildOf(20).equals(30)) 
                fail("inserting 30 as right child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }

    /** 
     * CASE 321 Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if insert 
     * worked in the other direction.
     */
    @Test
    void testBST_002_insert_reversed_sorted_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("insert to left child of root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfLeftChildOf(20).equals(10)) 
                fail("inserting 10 as left child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }

    /** 
     * CASE 132 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_003_insert_smallest_largest_middle_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyOfRightChildOf(10).equals(30)) 
                fail("insert to right child of root does not work");
            Assert.assertEquals(bst.getKeyOfRightChildOf(10),Integer.valueOf(30));
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 20 changed root");

            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("inserting 20 as left child of 30");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }

    /** 
     * CASE 312 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_004_insert_largest_smallest_middle_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");

            // the tree should have 30 at the root
            // and 10 as its left child and 20 as 10's right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }
    /**
     * Tests if getting each traversal of an empty tree still gets a traversal 
     * that is empty
     */
    @Test
    void testBST_005_empty_traversal() {
    	try {
    		List<Integer> inOrder = bst.getInOrderTraversal();
    		List<Integer> preOrder = bst.getPreOrderTraversal();
    		List<Integer> postOrder = bst.getPostOrderTraversal();
    		List<Integer> levelOrder = bst.getLevelOrderTraversal();
    		List<Integer> expectedOutput = new ArrayList<Integer>();
        	
        	System.out.println("In Order:         " + inOrder);
        	System.out.println("Pre Order:        " + preOrder);
        	System.out.println("Post Order:       " + postOrder);
        	System.out.println("Level Order:      " + levelOrder);
        	System.out.println("Expected Output:  " + expectedOutput);
        	
        	Assert.assertEquals(inOrder, expectedOutput);
        	Assert.assertEquals(preOrder, expectedOutput);
        	Assert.assertEquals(postOrder, expectedOutput);
        	Assert.assertEquals(levelOrder, expectedOutput);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Testing each traversal type for a single insert in the tree
     */
    @Test
    void testBST_006_single_insert_traversal_order() {
    	try {
    		bst.insert(10, "10");
    		List<Integer> inOrder = bst.getInOrderTraversal();
    		List<Integer> preOrder = bst.getPreOrderTraversal();
    		List<Integer> postOrder = bst.getPostOrderTraversal();
    		List<Integer> levelOrder = bst.getLevelOrderTraversal();
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		
        	System.out.println("In Order:         " + inOrder);
        	System.out.println("Pre Order:        " + preOrder);
        	System.out.println("Post Order:       " + postOrder);
        	System.out.println("Level Order:      " + levelOrder);
        	System.out.println("Expected Output:  " + expected);
        	
        	Assert.assertEquals(inOrder, expected);
        	Assert.assertEquals(preOrder, expected);
        	Assert.assertEquals(postOrder, expected);
        	Assert.assertEquals(levelOrder, expected);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Insert 524 items and check if numKeys has the appropriate value (524)
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
    @Test
    void testBST_007_check_numKeys_after_multiple_inserts() throws IllegalNullKeyException, DuplicateKeyException {
    	try {
    		for(int i = 0; i < 524; i++) {
        		bst.insert(i, "V" + i);
        	}
        	
        	if(bst.numKeys() != 524)
        		fail("numKeys was expected to be 524 after 524 insertions, but was not.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests whether inserting a duplicate key throws a DuplicateKeyException
     */
    @Test
    void testBST_008_inserting_duplicate_key_throws_DuplicateKeyException() {
    	try {
    		bst.insert(10, "V");
    		bst.insert(10, "V");
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
    void testBST_009_inserting_null_key_throws_IllegalNullKeyException() {
    	try {
    		bst.insert(null, "V");
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
     * Tests if reinserting an item after deleting it does not throw a DuplicateKeyExepction
     */
    @Test
    void testBST_010_reinsert_deleted_item_no_throw_DuplicateKeyException() {
    	try {
    		bst.insert(10, "V");
    		bst.remove(10);
    		bst.insert(10, "V");
    	}
    	catch(DuplicateKeyException e) {
    		fail("DuplicateKeyException was incorrectly thrown after re-inserting a deleted item.");
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
    void tstBST_011_insert_get_one_item_returns_right_val() {
    	try {
    		bst.insert(10, "V");
    		if(bst.get(10).compareTo("V") != 0)
    			fail("get was supposed to return 'V' but returned '" + bst.get(10) + "' instead.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checks whether getting a value after inserting many in the BST changes
     * the value of numKeys
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
    @Test
    void testBST_012_get_does_not_change_numKeys() throws IllegalNullKeyException, DuplicateKeyException {
    	try {
    		for(int i = 0; i < 123; i++)
        		bst.insert(i, "V" + i);
    		int initialNum = bst.numKeys();
    		bst.get(24);
    		if(bst.numKeys() != initialNum)
    			fail("Getting a value changed numKeys even though it should not.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Checks whether getting a value from the BST does not remove that key
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
    @Test
    void testBST_013_get_does_not_remove() throws IllegalNullKeyException, DuplicateKeyException {
    	try {
    		for(int i = 0; i < 123; i++)
        		bst.insert(i, "V" + i);
    		bst.get(24);
    		if(!bst.contains(24))
    			fail("Getting a value removed a key even though it should not.");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests if using get with a null key throws an IllegalNullKeyException
     */
    @Test
    void testBST_014_get_null_key_throws_IllegalNullkeyException() {
    	try {
    		bst.get(null);
    		fail("IllegalNullKeyException was not thrown after getting a null key.");
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
     * Tests if using get with a nonexistent throws a KeyNotFoundException
     */
    @Test
    void testBST_015_get_nonexistent_key_throws_KeyNotFoundException() {
    	try {
    		bst.insert(10, "10");
    		bst.insert(20, "20");
    		bst.insert(30, "30");
    		bst.get(40);
    		fail("KeyNotFoundException was not thrown after trying to get a nonexistent key.");
    	}
    	catch(KeyNotFoundException e) {
    		//nothing is done since if this exception is thrown, program is running as expected 
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests if using get with an empty tree throws a KeyNotFoundException
     */
    @Test
    void testBST_015_get_from_empty_tree_throws_KeyNotFoundException() {
    	try {
    		bst.get(40);
    		fail("KeyNotFoundException was not thrown after trying to get from an empty tree.");
    	}
    	catch(KeyNotFoundException e) {
    		//nothing is done since if this exception is thrown, program is running as expected 
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests if using contains with a null key throws an IllegalNullKeyException
     */
    @Test
    void testBST_016_contains_null_key_throws_IllegalNullkeyException() {
    	try {
    		bst.contains(null);
    		fail("IllegalNullKeyException was not thrown after using contains with a null key.");
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
     * Checking that inserting 3 values in balanced order produces the right 
     * post order traversal
     */
    @Test
    void testBST_017_check_postOrder_balanced_insert() {
    	try {
    		bst.insert(20, "1");
    		bst.insert(10, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(30);
    		expected.add(20);
    		
    		List<Integer> real = bst.getPostOrderTraversal();
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
    void testBST_018_check_levelOrder_balanced_insert() {
    	try {
    		bst.insert(20, "1");
    		bst.insert(10, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(20);
    		expected.add(10);
    		expected.add(30);
    		
    		List<Integer> real = bst.getLevelOrderTraversal();
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
    void testBST_019_check_preOrder_balanced_insert() {
    	try {
    		bst.insert(20, "1");
    		bst.insert(10, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(20);
    		expected.add(10);
    		expected.add(30);
    		
    		List<Integer> real = bst.getPreOrderTraversal();
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
    void testBST_020_check_inOrder_balanced_insert() {
    	try {
    		bst.insert(20, "1");
    		bst.insert(10, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(20);
    		expected.add(30);
    		
    		List<Integer> real = bst.getInOrderTraversal();
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
    void testBST_021_check_inOrder_unbalanced_insert() {
    	try {
    		bst.insert(10, "1");
    		bst.insert(20, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(20);
    		expected.add(30);
    		
    		List<Integer> real = bst.getInOrderTraversal();
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
    void testBST_022_check_preOrder_unbalanced_insert() {
    	try {
    		bst.insert(10, "1");
    		bst.insert(20, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(20);
    		expected.add(30);
    		
    		List<Integer> real = bst.getPreOrderTraversal();
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
    void testBST_022_check_postOrder_unbalanced_insert() {
    	try {
    		bst.insert(10, "1");
    		bst.insert(20, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(30);
    		expected.add(20);
    		expected.add(10);
    		
    		List<Integer> real = bst.getPostOrderTraversal();
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
    void testBST_023_check_levelOrder_unbalanced_insert() {
    	try {
    		bst.insert(10, "1");
    		bst.insert(20, "2");
    		bst.insert(30, "3");
    		
    		List<Integer> expected = new ArrayList<Integer>();
    		expected.add(10);
    		expected.add(20);
    		expected.add(30);
    		
    		List<Integer> real = bst.getLevelOrderTraversal();
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
    void testBST_024_height_zero_when_tree_is_empty() {
    	try {
    		if(bst.getHeight() != 0)
    			fail("Tree is empty and thus height should be 0, but it is actually: " + bst.getHeight());
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
    void testBST_025_height_is_one_when_root_is_sole_node() {
    	try {
    		bst.insert(10, "root");
    		if(bst.getHeight() != 1)
    			fail("Root is the sole node in the BST, and height should be 1, but it is not.");
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
    void testBST_026_height_for_full_balanced_tree() {
    	try {
    		bst.insert(50, "root");
    		bst.insert(25, "25");
    		bst.insert(75, "75");
    		bst.insert(15, "15");
    		bst.insert(35, "35");
    		bst.insert(65, "65");
    		bst.insert(85, "85");
    		if(bst.getHeight() != 3)
    			fail("Height should be 3, but instead is: " + bst.getHeight());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests whether getHeight returns 7 for an unbalanced tree with a height of 7
     */
    @Test
    void testBST_027_height_for_unbalanced_tree() {
    	try {
    		bst.insert(10, "root");
    		bst.insert(20, "20");
    		bst.insert(30, "30");
    		bst.insert(40, "40");
    		bst.insert(50, "50");
    		bst.insert(60, "60");
    		bst.insert(70, "70");
    		if(bst.getHeight() != 7)
    			fail("Height should be 7, but instead is: " + bst.getHeight());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("Unexpected exception: " + e.getMessage());
    	}
    }
    /**
     * Tests if getKeyOfRightChildOf throws an IllegalNullKeyException when passed a null key
     */
    @Test
	void testBST_028_getKeyOfRightChildOf_passed_null_key_throws_IllegalNullKeyException() {
		try {
			bst.getKeyOfRightChildOf(null);
			fail("IllegalNullKeyException was not thrown after a null key was passed to getKeyOfRightChildOf");
		}
		catch (IllegalNullKeyException e) {
			//If this is thrown, the program is running properly
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}
    /**
     * Tests if getKeyOfLeftChildOf throws an IllegalNullKeyException when passed a null key
     */
    @Test
	void testBST_029_getKeyOfLeftChildOf_passed_null_key_throws_IllegalNullKeyException() {
		try {
			bst.getKeyOfLeftChildOf(null);
			fail("IllegalNullKeyException was not thrown after a null key was passed to getKeyOfLefttChildOf");
		}
		catch (IllegalNullKeyException e) {
			//If this is thrown, the program is running properly
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}
    /**
     * Tests whether getKeyOfRightChildOf returns the correct key
     */
    @Test
    void testBST_030_getKeyOfRightChildOf_returns_right_key() {
    	try {
    		bst.insert(20, "20");
    		bst.insert(10, "10");
    		bst.insert(30, "30");
			if(bst.getKeyOfRightChildOf(20) != 30)
				fail("getKeyOfRightChildOf returned " + bst.getKeyOfRightChildOf(20) + " instead of 30.");
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
    }
    /**
     * Tests whether getKeyOfLeftChildOf returns the correct key
     */
    @Test
    void testBST_031_getKeyOfLeftChildOf_returns_right_key() {
    	try {
    		bst.insert(20, "20");
    		bst.insert(10, "10");
    		bst.insert(30, "30");
			if(bst.getKeyOfLeftChildOf(20) != 10)
				fail("getKeyOfLeftChildOf returned " + bst.getKeyOfRightChildOf(20) + " instead of 10.");
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
    }
    /**
     * Tests whether getKeyOfRightChildOf returns null when the right child is null
     */
    @Test
    void testBST_032_getKeyOfRightChildOf_returns_null_when_right_child_is_null() {
    	try {
    		bst.insert(20, "20");
    		bst.insert(10, "10");
			if(bst.getKeyOfRightChildOf(20) != null)
				fail("getKeyOfRightChildOf returned " + bst.getKeyOfRightChildOf(20) + " instead of null.");
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
    }
    /**
     * Tests whether getKeyOfLeftChildOf returns the correct key
     */
    @Test
    void testBST_033_getKeyOfLeftChildOf_returns_null_when_left_child_is_null() {
    	try {
    		bst.insert(20, "20");
    		bst.insert(30, "30");
			if(bst.getKeyOfLeftChildOf(20) != null)
				fail("getKeyOfLeftChildOf returned " + bst.getKeyOfRightChildOf(20) + " instead of null.");
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
    }
    /**
     * Tests is remove operation actually removes the key passed
     */
    @Test
    void testBST_034_remove_removes_correct_node() {
    	try {
    		bst.insert(20, "20");
    		bst.insert(10, "10");
    		bst.insert(30, "30");
    		bst.remove(30);
			if(bst.contains(30))
				fail("remove did not remove passed node form tree");
			
			List<Integer> expected = new ArrayList<Integer>();
			expected.add(10);
			expected.add(20);
			if(!expected.equals(bst.getInOrderTraversal()))
				fail("remove did not remove passed node form tree");
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
    }
    /**
     * Tests if removing a key decrements numKeys
     */
    @Test
    void testBST_035_remove_decrements_numKeys() {
    	try {
    		bst.insert(20, "20");
    		bst.insert(10, "10");
    		bst.insert(30, "30");
    		bst.remove(30);
			if(bst.numKeys() != 2)
				fail("remove did not decrement numKeys");
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
    }
    /**
     * Tests if passing remove a null key throws an IllegalNullKeyException
     */
    @Test
    void testBST_036_remove_throws_IllegalNullKeyException_when_passed_null_key() {
    	try {
    		bst.remove(null);
			fail("remove did not throw IllegalNullKeyException when passed a null key");
		} 
    	catch(IllegalNullKeyException e) {
    		//if this exception is thrown, program is running properly
    	}
		catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
    }
}
