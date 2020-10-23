/**
 * DataStructureADTTest.java
 *
 * Author: Ishaan Backliwal
 * Due Date: 02/13/2020
 * Project Name: P1
 * Email: backliwal@wisc.edu
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * List Collaborators: Prof. Deb Deppler
 * 
 * Other Credits: javadocs
 * 
 * Known Bugs: n/a
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 
 * DataStructureADTTest - This class runs JUnit tests in order to verify the 
 * validity of various ADT implementations
 * 
 * @author ishaan backliwal and deb deppler
 *
 * @param <T>
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
    
    private T ds;
    
    protected abstract T createInstance();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        ds = createInstance();
    }

    @AfterEach
    void tearDown() throws Exception {
        ds = null;
    }

    /**
     * Tests whether size = 0 when nothing is inserted
     */
    @Test
    void test00_empty_ds_size() {
        if (ds.size() != 0)
        fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    
    // TODO: review tests 01 - 04
    /**
     * Tests insert method by inserting one key value pair into the data 
     * structure and then confirming that size() is 1
     */
    @Test
    void test01_insert_one() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.size()==1);
    }
    /**
     * Tests whether size is 0 after inserting and removing same object
     */
    @Test
    void test02_insert_remove_one_size_0() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.remove(key)); // remove the key
        if (ds.size() != 0)
            fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    /**
     * Tests whether duplicate exception is thrown when trying to add two key 
     * value pairs with the same key
     */
    @Test
    void test03_duplicate_exception_thrown() {
        String key = "1";
        String value = "one";
        ds.insert("1", "one");
        ds.insert("2", "two");
        try { 
            ds.insert(key, value); 
            fail("duplicate exception not thrown");
        }
        catch (RuntimeException re) { }
        assert (ds.size()==2);
    }
    /**
     * Tests if trying to remove a key that does not exists in the data 
     * structure returns false      
     */
    @Test
    void test04_remove_returns_false_when_key_not_present() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (!ds.remove("2")); // remove non-existent key is false
        assert (ds.remove(key));  // remove existing key is true
        if (ds.get(key)!=null)
            fail("get("+key+ ") returned " + ds.get(key) + " which should have been removed");
    }
    /**
     * Tests whether remove method removes first key after entering two in
     */
    @Test
    void test05_insert_remove_one() {
        ds.insert("1", "one");
        ds.insert("2", "two");
        ds.remove("1");
        if(ds.contains("1"))
        	fail("remove method did not remove key");
    }
    /**
     * Tests whether after 3 inserts, size is correct
     */
    @Test
    void test06_insert_many_size() {
    	ds.insert("1", "one"); 
        ds.insert("2", "two"); 
        ds.insert("3", "three");
        if (ds.size() != 3)
        	fail("data structure should have size = 3, but instead has size = " + ds.size());
    }
    /**
     * Tests whether two items with same values can be added to data structure
     */
    @Test
    void test07_duplicate_values() {
    	String value = "same";
        ds.insert("1", value); 
        ds.insert("2", value);
        if(ds.size() != 2)
        	fail("data structure should have size = 2, but instead has a size = " + ds.size());
    }
    /**
     * Tests whether inserting null key throws an exception
     */
    @Test
    void test08_insert_null_key_throws_exception() {
    	boolean exceptionThrown = false;
    	try {
    		ds.insert(null, "two");
    	}
    	catch(IllegalArgumentException e) {
    		exceptionThrown = true;
    	}
    	if(!exceptionThrown)	
    		fail("IllegalArgumentException not thrown when null key was inserted to data structure");
    }
    /**
     * Tests whether a key with a null value is accepted into the data structure
     */
    @Test
    void test09_key_with_null_value_accepted() {
    	ds.insert("1", "one");
    	ds.insert("2", null);
    	ds.insert("3", "three");
    	if(!ds.contains("2"))
    		fail("data structure did not accept ket with a null value");
    }
    /**
     * Tests whether trying to remove a null key throws an exception
     */
    @Test
    void test10_remove_null_key_throws_exception() {
    	ds.insert("1", "one");
    	boolean exceptionThrown = false;
    	try {
    		ds.remove(null);
    	}
    	catch(IllegalArgumentException e) {
    		exceptionThrown = true;
    	}
    	if(!exceptionThrown)
    		fail("IllegalArgumentException not thrown when null key was told to be removed from data structure");
    	
    }
    /**
     * Tests whether removing a nonexistent key returns false or not
     */
    @Test
    void test11_remove_nonexsistent_key_returns_false() {
    	ds.insert("1", "one");
    	ds.insert("2", "two");
    	ds.insert("3", "three");
    	if(ds.remove("4"))
    		fail("attempting to remove nonexistent key did not result in false");
    }
    /**
     * Tests whether trying to remove a null key does not change the size of the 
     * data structure
     */
    @Test
    void test12_remove_null_key_does_not_change_size() {
    	ds.insert("1", "one");
    	int initialSize = ds.size();
    	try {
    		ds.remove(null);
    	}
    	catch(IllegalArgumentException e) {
    		if(initialSize != ds.size())
    			fail("attempting to remove null key changed size when it was not supposed to");
    	}
    	if(ds.size() != 1)
    		fail("IllegalArgumentException not thrown when null key was told to be removed from data structure");
    }
    /**
     * Tests whether contains returns false of passed a key that does not exist
     */
    @Test
    void test13_contains_returns_false_if_key_does_not_exist() {
    	ds.insert("1", "one");
    	ds.insert("2", "two");
    	ds.insert("3", "three");
    	if(ds.contains("4"))
    		fail("contains should have returned false as key does not exist, yet it returned true");
    }
    /**
     * Tests whether inserting 500 key value pairs and running contains on all 
     * of them works as expected
     */
    @Test
    void test14_insert_500_key_value_pairs_test_contains_on_all() {
    	for(int i = 0; i < 500; i++)
    		ds.insert("Key: " + i, "Value: " + i);
    	for(int i = 0; i < 500; i++) {
    		if(!ds.contains("Key: " + i))
    			fail("data structure does not contain the appropriate set of key value pairs");
    	}
    }
    /**
     * Tests whether one can re-add a key after removing it form the data structure
     */
    @Test
    void test15_can_readd_deleted_key() {
    	ds.insert("1", "one");
    	ds.remove("1");
    	try {
    		ds.insert("1", "one");
    	}
    	catch(RuntimeException e) {
    		fail("Could not re-insert key after it was removed, RuntimeException thrown");
    	}
    }
    /**
     * Tests whether the data structure can store 1000 items
     */
    @Test
    void test16_can_store_1000_items() {
    	for(int i = 0; i < 1000; i++) {
    		ds.insert(Integer.toString(i), "value: " + i);
    	}
    	if(ds.size() != 1000)
    		fail("could not store 1000 items, instead stored " + ds.size());
    }
    /**
     * Tests whether trying to get a null key works or not
     */
    @Test
    void test17_getting_null_key_throws_exception() {
    	ds.insert("1", "one");
    	ds.insert("2", "two");
    	ds.insert("3", "three");
    	boolean exceptionThrown = false;
    	try {
    		ds.get(null);
    	}
    	catch(IllegalArgumentException e) {
    		exceptionThrown = true;
    	}
    	if(!exceptionThrown)
    		fail("getting a null key did not throw an IllegalArgumentException");
    }
    /**
     * Tests whether get method returns a correct value
     */
    @Test
    void test18_get_returns_right_item() {
    	ds.insert("1", "one");
    	ds.insert("2", "two");
    	Object three = ds.get("3");
    	boolean condition = false;
    	if(three == null)
    		condition = true;
    	Object one = ds.get("1");
    	if(one != null)
    		condition = true;
    	else
    		condition = false;
    	if(!condition)
    		fail("get method does not return the correct things when called upon");
    }

}
