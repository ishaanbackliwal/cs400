import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * TODO Stores (key,value) pair for each (State,List<DailyStateDataEntry>).
 * 
 * This class also provides additional data functionality.
 * @author deppeler ALL RIGHTS RESERVED, FOR STUDENT USE DURING EXAM ONLY.
 */
public class DS <K extends Comparable<K>, V> {

	/** TODO add private fields for your data structure here */
	private int tableSize;
	private double loadFactorThreshold;
	private LinkedList<KVPair<K, V>>[] ht;
	private int numKeys;
	/** PRO-TIP: use Hashtable or TreeMap  */
	
	/** PRO-TIP: store as (State,List<DailyStateDataEntry>) pairs. */
	/**
	 * Class for key value pair
	 * @author ishaan backliwal
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private class KVPair<K extends Comparable<K>, V> {
		private String key;
		private ArrayList<DailyStateDataEntry> value;
		
		/**
		 * Constructor for key value pair
		 * @param key
		 * @param value
		 */
		private KVPair(String key, ArrayList<DailyStateDataEntry> value) {
			this.key = key;
			this.value = value;
		}
		/**
		 * Gets key of the key value pair
		 * @return key
		 */
		private String getKey() {
			return key;
		}
		/**
		 * Gets value  of the key value pair
		 * @return value
		 */
		private ArrayList<DailyStateDataEntry> getValue() {
			return value;
		}
	}
	/** TODO initial internal data structure field(s) here */
	@SuppressWarnings("unchecked")
	public DS() {
		tableSize = 11;
		ht = (LinkedList<DS<K, V>.KVPair<K, V>>[]) new LinkedList<?>[tableSize];
		loadFactorThreshold = 0.76;
	}

	/** TODO Add a daily entry to the list for a given state */
	public void add(DailyStateDataEntry dataEntry) {
		/** 
		 * PRO-TIP get state name from record
		 * If not found, add to internal ds
		 * Then, add this data entry to the correct list for the state.
		 */
		String stateName = dataEntry.getStateName();
		int currentPosition = hashCode(dataEntry);
		// create pair for the case that the state does not exist
		ArrayList<DailyStateDataEntry> list = new ArrayList<DailyStateDataEntry>();
		list.add(dataEntry);
		KVPair<K, V> pair = new KVPair<K, V>(stateName, list);
		
		boolean stateExists = false;
		// if table is empty, insert pair
		if(ht[currentPosition] == null) {
			ht[currentPosition] = new LinkedList<KVPair<K,V>>();
			ht[currentPosition].add(pair);
		}
		// either replace existing state's list or add new KV pair
		else {
			for(int i = 0; i < ht[currentPosition].size(); i++) {
				// if state exists, replace list
				if(ht[currentPosition].get(i).getKey().compareTo(stateName) == 0) {
					stateExists = true;
					ht[currentPosition].get(i).getValue().add(dataEntry);
				}
			}
			// if state does not already exist, add it
			if(!stateExists)
				ht[currentPosition].add(pair);
		}
		numKeys++;
		// resize if load factor is greater than or equal to the threshold
		if(getLoadFactor() >= loadFactorThreshold)
			resize();
	}
	/**
	 * Resizes the table if needed
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		// store original table
		LinkedList<KVPair<K, V>>[] original = ht;
		int originalSize = tableSize;
		// new table size
		tableSize = (tableSize * 2) + 1;
		// reset number of keys to 0 in order to restart insert process in new table
		numKeys = 0;
		// reset hash table to new size
		ht = (LinkedList<DS<K, V>.KVPair<K, V>>[]) new LinkedList<?>[tableSize];
		// inserts all KV pairs from original table into the new one
		for(int i = 0; i < originalSize; i++)
			if(original[i] != null)
				for(int j = 0; j < original[i].size(); j++)
					for(int k = 0; k < original[i].get(j).getValue().size(); k++)
						add(original[i].get(j).getValue().get(k));
	}
	/**
	 * @return load factor for the tree by dividing the number of keys by the
	 * table size
	 */
	public double getLoadFactor() {
		return (double)numKeys / (double)tableSize;
	}
	/**
	 * Gets a hash index through the hash function
	 * @param dataEntry to use and input into the function in order to get the hash index
	 * @return hash index
	 * @throws IllegalNullKeyException if key is null
	 */
	private int hashCode(DailyStateDataEntry dataEntry) {
		if (dataEntry == null)
			throw new NullPointerException();
		return Math.abs(dataEntry.hashCode()) % tableSize;
	}
	
	/** TODO Return a summary of all the records for specified State */
	public StateSummary getStateSummaryFor(String stateName) {
		// get list of records for the desired state
		ArrayList<DailyStateDataEntry> list = new ArrayList<DailyStateDataEntry>();
		for(int i = 0; i < ht.length; i++) {
			if(ht[i] != null)
				for(int j = 0; j < ht[i].size(); j++)
					// if stateName matches, add record to list
					if(ht[i].get(j).getKey().compareTo(stateName) == 0)
						list = ht[i].get(j).getValue();
		}
		// return state summary
		return new StateSummary(stateName, list);
	}

	/** TODO Return an array of all state names in sorted order. */
	public String[] getStateNamesInSortedOrder() {
		// names of all the states in the DS
		ArrayList<String> names = new ArrayList<String>();
		// find state names and add them to the array list
		for(int i = 0; i < ht.length; i++) {
			if(ht[i] != null)
				for(int j = 0; j < ht[i].size(); j++)
					// check for duplicates
					if(!names.contains(ht[i].get(j).getKey()))
						names.add(ht[i].get(j).getKey());
		}
		// sort names
		Collections.sort(names);
		// add sorted names to array to return
		String[] sortedNames = new String[names.size()];
		for(int k = 0; k < names.size(); k++)
			sortedNames[k] = names.get(k);
		return sortedNames;
	}
	
	/** 
	 * Mini-test code of StateSummary class. 
	 * STUDENTS MAY EDIT for their own use.
	 */
	public static void main(String [] args) {
		DS ds = Main.parseData("test.csv");
		String [] names = ds.getStateNamesInSortedOrder();
		for (String name : names) {
			System.out.print(name+",");
		}
		System.out.println();
		System.out.println(ds.getStateSummaryFor("Dane"));
		System.out.println(ds.getStateSummaryFor("Green"));
		System.out.println(ds.getStateSummaryFor("Milwaukee"));
	}

}
