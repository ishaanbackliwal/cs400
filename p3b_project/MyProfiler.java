/**
 * MyProfiler.java
 *
 * Author: Ishaan Backliwal
 * Due Date: 03/26/2020
 * Project Name: P3b
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
// Used as the data structure to test our hash table against Tree Map
import java.util.TreeMap;

public class MyProfiler<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;

	/**
	 * Constructor that initializes both the hash table and tree map
	 */
	public MyProfiler() {
		hashtable = new HashTable<K, V>();
		treemap = new TreeMap<K, V>();
	}
	/**
	 * Inserts into both the tree map and hash table
	 * @param key is the key to be inserted into both ADTs
	 * @param value is the value of the key being inserted
	 * @throws IllegalNullKeyException
	 */
	public void insert(K key, V value) throws IllegalNullKeyException {
		hashtable.insert(key, value);
		treemap.put(key, value);
	}
	/**
	 * Gets value of the key passed to the method from both ADTs
	 * @param key is the key of the value being retrieved
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	public void retrieve(K key) throws IllegalNullKeyException, KeyNotFoundException {
		hashtable.get(key);
		treemap.get(key);
	}
	/**
	 * Tests my hash table against tree map
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int numElements = Integer.parseInt(args[0]);
			MyProfiler<String, String> profile = new MyProfiler<String, String>();
			for(int i = 0; i < numElements; i++)
				profile.insert("K" + i, "V" + i);
			for(int i = 0; i < numElements; i++)
				profile.retrieve("K" + i);
			String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Usage: java MyProfiler <number_of_elements>");
			System.exit(1);
		}
	}
}
