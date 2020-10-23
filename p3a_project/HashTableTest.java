import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * HashTableTest.java
 *
 * Author: Ishaan Backliwal
 * Due Date: 03/12/2020
 * Project Name: P3a
 * Email: backliwal@wisc.edu
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * List Collaborators: n/a
 * 
 * Other Credits: javadocs
 * 
 * Known Bugs: n/a
 */

/**
 * This is a JUnit test class to test the implementation of HashTable
 * @author ishaan backliwal
 */
public class HashTableTest{

    private HashTableADT<Integer, String> ht;
    
    /**
     * Code that runs before every test method
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    	ht = new HashTable<Integer, String>();
    }

    /**
     * Code that runs after every test method
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    	ht = null;
    }

    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    /**
     * Tests if inserting a large number of items results in the right number of keys
     */
    @Test
    public void test002_insert_many_KVPairs(){
    	try {
    		int num = 12345;
        	for(int i = 0; i < num; i++)
        		ht.insert(i, "V" + i);
        	if(ht.numKeys() != num)
        		fail("numeber of keys was " + ht.numKeys() + " instead of " + num);
    	}
    	catch(Exception e) {
    		fail("this test should not have thrown an exception but it did.");
    	}
    }
    /**
     * This test tries to remove a nonexistent key which should return false
     */
    @Test
    public void test003_remove_nonexistent_key() {
    	try {
    		ht.insert(1, "1");
    		ht.insert(2, "2");
    		ht.insert(3, "3");
    		boolean condition = ht.remove(4);
    		if(condition)
    			fail("remove method returned true when trying to remove nonexistent key");
    	}
    	catch(Exception e) {
    		fail("this test should not have thrown an exception but it did.");
    	}
    }
    /**
     * This test inserts many items and tries to remove all of them which should
     * result in numKeys = 0
     */
    @Test
    public void test004_insert_and_remove_many_items() {
    	try {
    		int num = 12345;
        	for(int i = 0; i < num; i++)
        		ht.insert(i, "V" + i);
        	for(int i = 0; i < num; i++)
        		ht.remove(i);
        	if(ht.numKeys() != 0)
        		fail("numeber of keys was " + ht.numKeys() + " instead of 0");
    	}
    	catch(Exception e) {
    		fail("this test should not have thrown an exception but it did.");
    	}
    }
    /**
     * Compares the expected and actual load factor of the hash table
     */
    @Test
    public void test005_correct_load_factor() {
    	try {
    		int tableSize = 20;
        	int laodFactorThreshold = 10;
        	int numKeys = 10;
        	double loadFactor = (double)numKeys / (double)tableSize;
        	ht =  new HashTable<Integer, String>(tableSize, laodFactorThreshold);
        	for(int i = 0; i < numKeys; i++)
        		ht.insert(i, "V" + i);
        	if(loadFactor != ht.getLoadFactor())
        		fail("load factor should have been " + loadFactor + 
        				" but instead it was " + ht.getLoadFactor());
    	}
    	catch(Exception e) {
    		fail("this test should not have thrown an exception but it did.");
    	}
    }
    /**
     * Tests whether getting a null key throws an IllegalNullKeyException
     */
    @Test
    public void test006_get_null_key_throws_IllegalNullKeyException() {
    	try {
    		ht.insert(1, "1");
    		ht.insert(2, "2");
    		ht.insert(3, "3");
    		ht.get(null);
    		fail("getting a null key did not throw an exception");
    	}
    	catch(IllegalNullKeyException e) {
    		//pass
    	} 
    	catch (KeyNotFoundException e) {
    		fail("this test should not have thrown a KeyNotFoundException");
		}
    }
    /**
     * Tests whether the getCapai=city method returns the correct capacity
     */
    @Test
    public void test007_correct_capacity() {
    	try {
    		int tableSize = 20;
        	int laodFactorThreshold = 10;
        	ht =  new HashTable<Integer, String>(tableSize, laodFactorThreshold);
        	if(ht.getCapacity() != tableSize)
        		fail("getCapacity() should have returned " + tableSize + " but "
        				+ "instead returned " + ht.getCapacity());
    	}
    	catch(Exception e) {
    		fail("this test should not have thrown an exception but it did.");
    	}
    }
    /**
     * Tests whether getting a nonexistent key throws a KeyNotFoundException
     */
    @Test
    public void test008_get_nonexistent_key_throws_KeyNotFoundException() {
    	try {
    		ht.insert(1, "1");
    		ht.insert(2, "2");
    		ht.insert(3, "3");
    		ht.get(4);
    		fail("getting a nonesistent key did not throw an exception");
    	}
    	catch(IllegalNullKeyException e) {
    		fail("this test should not have thrown a IllegalNullKeyException");
    	} 
    	catch (KeyNotFoundException e) {
    		//pass
		}
    }
    /**
     * Tests if inserting a null key throws an IllegalNullKeyException
     */
    @Test
    public void test009_insert_null_key_throws_IllegalNullKeyException() {
    	try {
    		ht.insert(1, "1");
    		ht.insert(2, "2");
    		ht.insert(3, "3");
    		ht.insert(null, "4");
    		fail("inserting a null key did not throw an exception");
    	}
    	catch(IllegalNullKeyException e) {
    		//pass
    	}
    }
    /**
     * Tests whether get changes numKeys variable or not
     */
    @Test
    public void test010_get_does_not_change_numKeys() {
    	try {
    		int numKeys = 1234;
    		for(int i = 0; i < numKeys; i++)
        		ht.insert(i, "V" + i);
    		ht.get(1);
    		ht.get(12);
    		ht.get(123);
    		if(ht.numKeys() != numKeys)
    			fail("Getting a value changed numKeys even though it should not.");
    	}
    	catch(Exception e) {
    		fail("this test should not have thrown an exception but it did.");
    	}
    }
    /**
     * Tests whether remove decrements numKeys by the right amount
     */
    @Test
    public void test011_remove_decrements_numKeys() {
    	try {
    		int numKeys = 1234;
    		for(int i = 0; i < numKeys; i++)
        		ht.insert(i, "V" + i);
    		ht.remove(1);
    		ht.remove(12);
    		ht.remove(123);
    		if(ht.numKeys() != numKeys - 3)
    			fail("Remove did not correctly decrement numKeys by exactly three "
    					+ "after three removes");
    	}
    	catch(Exception e) {
    		fail("this test should not have thrown an exception but it did.");
    	}
    }
    
}
