/**
 * DS_My.java
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
 * List Collaborators: n/a
 * 
 * Other Credits: javadocs
 * 
 * Known Bugs: n/a
 */

/**
 * Implementation of a data structure
 * 
 * @author ishaanbackliwal
 */
public class DS_My implements DataStructureADT {
	
	private int size; // length of linked list
	Node head; // head node

	/**
	 * Linked-node (inner) class
	 * 
	 * @author ishaanbackliwal
	 *
	 * @param <K> is the comparable key
	 * @param <V> is the value that will be stored
	 */
	private class Node<K extends Comparable<K>, V> {
    	
		private K key;	// key of each node
    	private V value;	// value of the node
    	private Node next;	// next node in the linked list
    	
    	/**
    	 * Creates a node
    	 * @param key is the key of this particular node
    	 * @param value is the value of this particular note
    	 */
		private Node(K key, V value) {
			// TODO Auto-generated constructor stub
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		/**
		 * Getter method for the next node
		 * @return next node
		 */
		private Node next() {
			return this.next;
		}
		
		/**
		 * Setter method for the next node
		 * @param next is the node to be set as next
		 */
		private void setNext(Node next) {
			this.next = next;
		}
    }
	/**
	 * Constructs DS_My class and initializes instance variables 
	 */
    public DS_My() {
        size = 0;
        head = null;
    }

	/**
	 * Inserts node with given key and value into data structure
	 * 
	 * @param key is the key of the new node
	 * @param value is the value of the new node
	 * 
	 * @throws IllegalArgumentException when key is null
	 * @throws RuntimeException when key already exists
	 */
	@Override
	public void insert(Comparable key, Object value) {
		// if the key is null, throw IllegalArgumentException
		if(key == null)
			throw new IllegalArgumentException("null key");
		// if key already exists, throw RuntimeExcpetion
		if(this.contains(key))
			throw new RuntimeException("duplicate key");
		// if the list is empty, make the head the new node
		if(head == null) {
			head = new Node(key, value);
			// increment size
			size++;
		}
		else {
			Node temp = head;
			// move to last node
			while(temp.next() != null) {
				temp = temp.next();
			}
			//set node after last node to a new node with given key and value
			temp.setNext(new Node(key, value));
			// increment size
			size++;
		}
	}

	/**
	 * Removes node from data structure if it exists
	 * 
	 * @param key is the key of the node to be removed
	 * @return true if remove successful, false otherwise
	 * 
	 * @throws IllegalArgumentException if key passed is null
	 */
	@Override
	public boolean remove(Comparable key) {
		// TODO Auto-generated method stub
		// if the key passed is null, throws an IllegalArgumentException
		if(key == null) {
			throw new IllegalArgumentException("null key");
		}
		// if the data structure is empty, method fails to remove
		if(head == null) {
			return false;
		}
		// if the data structure does not contain the key, the method fails to remove
		if(!this.contains(key)) {
			return false;
		}
		Node temp = head;	// temporary place holder
		int nodeIndex = 0;	// index of what node the program is in the data structure
		boolean found = false;	// whether or not the key to be removed is found
		// loop to find index of key
		while(temp.next() != null && !found) {
			// if key found, exit loop by setting found to true
			if(temp.key.equals(key)) {
				found = true;
			}
			// if not found yet, increment index and temporary node position
			else {
				nodeIndex++;
				temp = temp.next();
			}
		}
		// if key is head of data structure, remove head
		if(nodeIndex == 0) {
			head = head.next();
		}
		else {
			temp = head;
			// go to node before the one being removed
			for(int i = 0; i < nodeIndex - 1; i++) {
				temp = temp.next();
			}
			// remove node 
			temp.setNext(temp.next().next());
		}
		// decrement size
		size--;
		return true;
	}

	/**
	 * Gets the value of the node with the matching key
	 * 
	 * @param key is the key being found
	 * @return value of the key found
	 * 
	 * @throws IllegalArgumentException when found key is null
	 */
	@Override
	public Object get(Comparable key) {
		Node temp = head;
		boolean found = false;
		// if key is null, throw IllegalArgumentException
		if(key == null) {
			throw new IllegalArgumentException("null key");
		}
		// if data structure is empty, return null
		if(head == null) {
			return null;
		}
		// if head is the right key
		if(head.key.equals(key)) {
			return head.value;
		}
		// loop to find the node with the right key
		else {
			while(temp.next() != null && !found) {
				temp = temp.next();
				// case where key is found
				if(temp.key.equals(key)) {
					// set boolean to true in order to exit loop
					found = true;
				}
			}
		}
		// if key not found, return null
		if(!found)
			return null;
		// value of found node
		return temp.value;
	}

	/**
	 * Checks whether a certain key is in the data structure
	 * 
	 * @param key is the key being found in the data structure
	 * @return true if key is found, false if key is null or not found
	 */
	@Override
	public boolean contains(Comparable key) {
		Node temp = head;
		// if data structure is empty, return false
		if(head == null) {
			return false;
		}
		// if key is null, return false
		if(key == null) {
			return false;
		}
		while(temp.next() != null) {
			// if key is found, return true
			if(temp.key.equals(key)) {
				return true;
			}
			else {
				temp = temp.next();
			}
		}
		if(temp.key.equals(key)) {
			return true;
		}
		return false;
	}

	/**
	 * Getter method for the size of the data structure
	 * 
	 * @return the size of the data structure
	 */
	@Override
	public int size() {
		return size;
	}
}                            
    
























