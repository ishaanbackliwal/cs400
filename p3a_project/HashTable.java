import java.util.LinkedList;
/**
 * HashTable.java
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
//
// Describe the collision resolution scheme you have chosen identify your scheme 
// as open addressing or bucket:
//   --> I decided to use buckets of linked lists in order to handle collisions
//
// Explain your hashing algorithm here:
// 	 --> The hashing algorithm in this program uses the hash code of the key
// 		 and divides that by the table size
//

public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
	private int tableSize;
	private double loadFactorThreshold;
	private LinkedList<KVPair<K, V>>[] ht;
	private int numKeys;
	
	/**
	 * Class for key value pair
	 * @author ishaan backliwal
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private class KVPair<K extends Comparable<K>, V> {
		private K key;
		private V value;
		
		/**
		 * Constructor for key value pair
		 * @param key
		 * @param value
		 */
		private KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		/**
		 * Gets key of the key value pair
		 * @return key
		 */
		private K getKey() {
			return key;
		}
		/**
		 * Gets value  of the key value pair
		 * @return value
		 */
		private V getValue() {
			return value;
		}
	}
	/**
	 * No argument constructor
	 */
	@SuppressWarnings("unchecked")
	public HashTable() {
		tableSize = 7;
		ht = (LinkedList<HashTable<K, V>.KVPair<K, V>>[]) new LinkedList<?>[tableSize];
		loadFactorThreshold = 0.7;
	}

	/**
	 * Two argument constructor initializes the hash table
	 * @param initialCapacity is the initial capacity of the hash table
	 * @param loadFactorThreshold is the load factor threshold of the hash table
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		tableSize = initialCapacity;
		this.loadFactorThreshold = loadFactorThreshold;
		// initializes hash table
		ht = (LinkedList<HashTable<K, V>.KVPair<K, V>>[]) new LinkedList<?>[tableSize];
		numKeys = 0;
	}

	/**
	 * Inserts a key value pair in the hash table
	 * @param key is the key of the key value pair to be inserted
	 * @param value is the value of the key value pair to be inserted
	 * @throws IllegalNullKeyException if key passed is equal to null
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException  {
		int currentPosition = hashCode(key);
		KVPair<K, V> pair = new KVPair<K, V>(key, value);
		boolean keyExists = false;
		// if table is empty, insert pair
		if(ht[currentPosition] == null) {
			ht[currentPosition] = new LinkedList<KVPair<K,V>>();
			ht[currentPosition].add(pair);
		}
		// either replace existing key's value or add new KV pair
		else {
			for(int i = 0; i < ht[currentPosition].size(); i++) {
				// if key exists, replace value
				if(ht[currentPosition].get(i).getKey().compareTo(key) == 0) {
					keyExists = true;
					ht[currentPosition].get(i).value = value;
				}
			}
			// if key does not already exist, add it
			if(!keyExists)
				ht[currentPosition].add(pair);
		}
		numKeys++;
		// resize if load factor is greater than or equal to the threshold
		if(getLoadFactor() >= loadFactorThreshold)
			resize();
	}

	/**
	 * Resizes the table if needed
	 * @throws DuplicateKeyException 
	 * @throws IllegalNullKeyException 
	 */
	@SuppressWarnings("unchecked")
	private void resize() throws IllegalNullKeyException {
		// store original table
		LinkedList<KVPair<K, V>>[] original = ht;
		int originalSize = tableSize;
		// new table size
		tableSize = (tableSize * 2) + 1;
		// reset number of keys to 0 in order to restart insert process in new table
		numKeys = 0;
		// reset hash table to new size
		ht = (LinkedList<HashTable<K, V>.KVPair<K, V>>[]) new LinkedList<?>[tableSize];
		// inserts all KV pairs from original table into the new one
		for(int i = 0; i < originalSize; i++)
			if(original[i] != null)
				for(int j = 0; j < original[i].size(); j++)
					insert(original[i].get(j).getKey(), original[i].get(j).getValue());
	}

	/**
	 * Gets a hash index through the hash function
	 * @param key to use and input into the function in order to get the hash index
	 * @return hash index
	 * @throws IllegalNullKeyException if key is null
	 */
	private int hashCode(K key) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		return Math.abs(key.hashCode()) % tableSize;
	}

	/**
	 * Removes a key value pair from the hash table
	 * @param key is the key of the key value pair to be removed
	 * @return true if pair was removed, false otherwise
	 * @throws IllegalNullKeyException if key passed is null
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		if(key == null)
			throw new IllegalNullKeyException();
		int currentPosition = hashCode(key);
		// if key not found, return false
		if(ht[currentPosition] == null)
			return false;
		// remove pair and return true
		for(int i = 0; i < ht[currentPosition].size(); i++) {
			if (key.compareTo(ht[currentPosition].get(i).getKey()) == 0) {
				ht[currentPosition].remove(i);
				numKeys--;
				return true;
			}
		}
		// no pair was removed if this return statement is reached
		return false;
	}

	/**
	 * Gets the value of the key the method is passed
	 * @param key is the key to find in the table
	 * @returns the value of the key to be found in the table
	 * @throws KeyNotFoundException if key requested is not in the table
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if(key == null)
			throw new IllegalNullKeyException();
		int currentPosition = hashCode(key);
		// if the table is empty, throw exception
		if(ht[currentPosition] == null)
			throw new KeyNotFoundException();
		// find a the key and return the value if found
		for(int i = 0; i < ht[currentPosition].size(); i++) {
			if(ht[currentPosition].get(i).getKey().compareTo(key) == 0)
				return ht[currentPosition].get(i).getValue();
		}
		// if key is not fund, throw exception
		throw new KeyNotFoundException();
	}

	/**
	 * @return the number of keys in the table
	 */
	@Override
	public int numKeys() {
		return numKeys;
	}

	/**
	 * @return the load factor threshold for this hash table
	 */
	@Override
	public double getLoadFactorThreshold() {
		return loadFactorThreshold;
	}

	/**
	 * @return load factor for the tree by dividing the number of keys by the
	 * table size
	 */
	@Override
	public double getLoadFactor() {
		return (double)numKeys / (double)tableSize;
	}

	/**
	 * @return max capacity of hash table
	 */
	@Override
	public int getCapacity() {
		return tableSize;
	}
	
	/**
	 * @return the collision resolution scheme used for this hash table
	 */
	// 1 OPEN ADDRESSING: linear probe
	// 2 OPEN ADDRESSING: quadratic probe
	// 3 OPEN ADDRESSING: double hashing
	// 4 CHAINED BUCKET: array of arrays
	// 5 CHAINED BUCKET: array of linked nodes
	// 6 CHAINED BUCKET: array of search trees
	// 7 CHAINED BUCKET: linked nodes of arrays
	// 8 CHAINED BUCKET: linked nodes of linked node
	// 9 CHAINED BUCKET: linked nodes of search trees
	@Override
	public int getCollisionResolution() {
		return 5;
	}	
}
