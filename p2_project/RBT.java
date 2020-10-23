import java.util.ArrayList;
import java.util.List;


/**
 * Implements a generic Red-Black tree.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS
 * DO NOT ADD ANY PACKAGE LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove.
 * If you do not override this operation,
 * you may still have the method in your type, 
 * and have the method throw new UnsupportedOperationException.
 * See https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K,V>{
	/**
	 * RBTNode - Inner class that implements a nodes for the outer RBT class
	 * @author ishaan backliwal
	 *
	 * @param <K>
	 * @param <V>
	 */
	private class RBTNode<K, V> {
		
		K key;
		V value;
		RBTNode<K, V> left;
		RBTNode<K, V> right;
		RBTNode<K, V> parent;
		RBTNode<K, V> grandparent;
		int color;
		
		/**
		 * Two argument constructor that initializes key and value of node 
		 * and sets left and right child to null
		 * @param key is the key of the node
		 * @param value is the value of the node
		 */
		private RBTNode(K key, V value){
			this(key, value, null, null);
		}
		/**
		 * Four argument constructor that initializes key, value, left child, 
		 * and right child of the node
		 * @param key
		 * @param value
		 * @param leftChild
		 * @param rightChild
		 */
		private RBTNode(K key, V value, RBTNode<K, V> leftChild, RBTNode<K, V> rightChild){
			this.key = key;
			this.value = value;
			grandparent = null;
			parent = null;
			right = rightChild;
			left = leftChild;
			color = RED;
		}
	}
    // USE AND DO NOT EDIT THESE CONSTANTS
    public static final int RED = 0;
    public static final int BLACK = 1;
    public RBTNode<K, V> root;	// root of RBT
	public int numKeys;	// number of keys in RBT
    
    // TODO: define a default no-arg constructor
    public RBT() {
    	root = null;
    	numKeys = 0;
    }

    /**
     * Returns the color of the node that contains the specified key.
     * Returns RBT.RED if the node is red, and RBT.BLACK if the node is black.
     * Returns -1 if the node is not found.
     * @param 
     * @return
     * @throws IllegalNullKeyException 
     */
    public int colorOf(K key) throws IllegalNullKeyException {
		RBTNode<K, V> found = finder(root, key);
		if(found == null)
			return -1;
    	return found.color;
    }

    /**
     * Returns true if the color of the root is black.
     * If root is null, returns BLACK.
     * @return true if root is black, else returns false (for red)
     * @throws IllegalNullKeyException 
     */
    public boolean rootIsBlack() throws IllegalNullKeyException {
        return this.colorOf(getKeyAtRoot()) == BLACK;
    }

    /**
     * Returns true if the node that contains this key is RED.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is RED,
     * else return false if key is found and the node's color is BLACK.
     */
    public boolean isRed(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null)
        	throw new IllegalNullKeyException();
        if(!this.contains(key))
        	throw new KeyNotFoundException();
    	return this.colorOf(key) == RED;
    }

    /**
     * Returns true if the node that contains this key is BLACK.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is BLACK,
     * else return false if key is found and the node's color is RED.
     */
    public boolean isBlack(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(key == null)
        	throw new IllegalNullKeyException();
        if(!this.contains(key))
        	throw new KeyNotFoundException();
    	return this.colorOf(key) == BLACK;
    }
    /**
     * Returns the key that is in the root node of this RBT.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    @Override
    public K getKeyAtRoot() {
        return root.key;
    }
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this RBT
     */
    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(finder(root, key) == null)
    		throw new KeyNotFoundException();
    	RBTNode<K, V> find = finder(root, key);
    	if(find.left == null)
    		return null;
    	return find.left.key;
    }
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this RBT
     */
    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(finder(root, key) == null)
    		throw new KeyNotFoundException();
    	RBTNode<K, V> find = finder(root, key);
    	if(find.right == null)
    		return null;
    	return find.right.key;
    }
    /**
     * Returns the height of this RBT.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A RBT with no keys, has a height of zero (0).
     * A RBT with one key, has a height of one (1).
     * A RBT with two keys, has a height of two (2).
     * A RBT with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this RED BLACK TREE
     */
    @Override
    public int getHeight() {
    	// if root is the only node in the RBT, return height of 1
    	if(numKeys == 1)
        	return 1;
    	// otherwise run helper method
    	else
    		return helpHeight(root);
    }
    /**
     * Helper method to find height of the RBT
     * @param current is the current root the program is on in the RBT
     * @return height of the RBT
     */
    private int helpHeight(RBTNode<K, V> current) {
    	// if current does not exist, return 0 for its height addition
    	if(current == null)
        	return 0;
    	// depth of left subtree
    	int depthL = helpHeight(current.left);
    	// depth of right subtree
    	int depthR = helpHeight(current.right);
    	// if left subtree depth is greater than right's, return left's depth
    	if(depthL > depthR)
    		return depthL + 1;
    	// otherwise return right's depth
    	else
    		return depthR + 1;
    }
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of red black trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    @Override
    public List<K> getInOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	helpInOrder(root, list);
    	return list;
    }
    /**
     * Helper method to get the in-order traversal and add it to a list of keys
     * @param current node the program is at within the RBT
     * @param list is the array list of keys
     */
    private void helpInOrder(RBTNode<K, V> current, List<K> list) {
    	if(current != null) {
    		helpInOrder(current.left, list);
    		list.add(current.key);
    		helpInOrder(current.right, list);
    	}
    }
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of red black trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    @Override
    public List<K> getPreOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	helpPreOrder(root, list);
    	return list;
    }
    /**
     * Helper method to get the pre-order traversal and add it to a list of keys
     * @param current node the program is at within the RBT
     * @param list is the array list of keys
     */
    private void helpPreOrder(RBTNode<K, V> current, List<K> list) {
    	if(current != null) {
    		list.add(current.key);
    		helpPreOrder(current.left, list);
    		helpPreOrder(current.right, list);
    	}
    }
    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of red black trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    @Override
    public List<K> getPostOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	helpPostOrder(root, list);
    	return list;
    }
    /**
     * Helper method to get the post-order traversal and add it to a list of keys
     * @param current node the program is at within the RBT
     * @param list is the array list of keys
     */
    private void helpPostOrder(RBTNode<K, V> current, List<K> list) {
    	if(current != null) {
    		helpPostOrder(current.left, list);
    		helpPostOrder(current.right, list);
    		list.add(current.key);
    	}
    }
    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    @Override
    public List<K> getLevelOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	int level = this.getHeight();
    	// run the helper for each level
    	for(int i = 0; i < level + 1; i++)
    		helpLevelOrder(root, list, level);
    	return list;
    }
    /**
     * Helper method for level order traversal
     * @param current is the current node the program is at within the RBT
     * @param list is the list of keys in the appropriate traversal order
     * @param level is the level of the RBT the program is at
     */
    private void helpLevelOrder(RBTNode<K, V> current, List<K> list, int level) {
    	// if the current node is null, end recursion
    	if(current == null)
    		return;
    	// if the current level is the root, add the root to the tree
    	else if(level == 1)
    		list.add(current.key);
    	// if the current level value is greater than one (lower than root), 
    	//recurse left and right 
    	else if(level > 1) {
    		helpLevelOrder(current.left, list, level--);
    		helpLevelOrder(current.right, list, level--);
    	}
    		
    }
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        if(key == null)
        	throw new IllegalNullKeyException();
    	if(root == null) {
        	root = new RBTNode<K, V>(key, value);
        	root.color = BLACK;
    	}
    	else {
    		RBTNode<K, V> insert = new RBTNode<K, V>(key, value);
    		insert.color = RED;
    		insertHelp(root, insert);
    	}
    	numKeys++;
        
    }
    /**
     * Helper method for insert method
     * @param current is the current node being tested
     * @param insert is the node being inserted into the RBT
     * @throws DuplicateKeyException when key of node being inserted already exists in the RBT
     */
    private RBTNode<K, V> insertHelp(RBTNode<K, V> current, RBTNode<K, V> insert) throws DuplicateKeyException, IllegalNullKeyException {
    	// if key passed already exists, throw exception
    	if(current.key.compareTo(insert.key) == 0)
    		throw new DuplicateKeyException();
    	// recursive case - going left if key is less than current root node
    	if(current.key.compareTo(insert.key) > 0) {
    		// if the left node of the current node is null, set that to the new node
    		if(current.left == null) {
    			current.left = insert;
    			current.left.parent = current;
    			current.left.grandparent = current.parent;
    			if(numKeys > 2 && current.color == RED)
    				fixInsert(insert);
    		}
    		// else recurse back and go further down the tree
    		else {
    			current = insertHelp(current.left, insert);
    		}
    	}
    	// recursive case - going right if key is greater than current root node
    	else if(current.key.compareTo(insert.key) < 0) {
    		// if the right node of the current node is null, set that to the new node
    		if(current.right == null) {
    			current.right = insert;
    			current.right.parent = current;
    			current.right.grandparent = current.parent;
    			if(numKeys > 2 && current.color == RED)
    				fixInsert(insert);
    		}
    		// else recurse back and go further down the tree
    		else {
    			current = insertHelp(current.right, insert);
    		}
    	}
    	root.color = BLACK;
    	return current;
    }
    /**
     * Finds the parent node of the node currently being used
     * @param current is the current node
     * @param key is the key of the node of which the parent needs to be found
     * @return current
     * @throws IllegalNullKeyException
     */
    private RBTNode<K,V> findParent(RBTNode<K,V> current, K key) throws IllegalNullKeyException{
    	// if the key does not exist in the RBT, return null
    	if(current == null)
    		return null;
    	// if current is the node being searched for, return that node
    	if(current.left != null) {
    		if(current.left.key.compareTo(key) == 0)
    			return current;
    	}
    	if(current.right != null) {
    		if(current.right.key.compareTo(key) == 0)
    			return current;
    	}
		// recurse left
		else if(current.key.compareTo(key) > 0)
			return findParent(current.left, key);
		// recurse right
		else
			return findParent(current.right, key);
    	return current;
    }
    /**
     * Decides which fix is needed after a red property violation
     * @param insert
     * @throws IllegalNullKeyException
     */
    private void fixInsert(RBTNode<K,V> insert) throws IllegalNullKeyException{
    	RBTNode<K, V> parent = findParent(root, insert.key);
    	RBTNode<K, V> grandParent = findParent(root, parent.key);
    	
    	// Find sibling
    	RBTNode<K, V> sibling = null;
    	if(grandParent.left.equals(insert))
    		sibling = grandParent.right;
    	else
    		sibling = grandParent.left;
    	
    	// RECOLORING
    	if(sibling.color == RED) {
    		parent.color = BLACK;
    		sibling.color = BLACK;
    		grandParent.color = RED;
    		fixInsert(grandParent);
    	}
    	else {
    		TNR(insert, parent, grandParent);
    	}
//    	// TNR Left-Right or Right
//    	else if(parent.key.compareTo(grandParent.left.key) == 0){
//    		if(insert.key.compareTo(parent.right.key) == 0)
//    			parent = TNRLeft(parent);
//    		else
//    			grandParent = rotateRight(grandParent);
//    	}
//    	// TNR Right-Left or Left
//    	else if(parent.key.compareTo(grandParent.right.key) == 0){
//    		if(insert.key.compareTo(parent.left.key) == 0)
//    			parent = TNRRight(parent);
//    		else
//    			grandParent = rotateLeft(grandParent);
//    	}
//    	root.color = BLACK;
    }
    /**
     * Runs a tri node restructuring
     * @param child
     * @param parent
     * @param grandparent
     * @throws IllegalNullKeyException
     */
    private void TNR(RBTNode<K, V> child, RBTNode<K, V> parent, RBTNode<K, V> grandparent) throws IllegalNullKeyException {
    	if(grandparent.left != null && grandparent.left.equals(parent) && parent.left != null && parent.left.equals(child)) {
    		if(grandparent.parent == null)
    			root = parent;
    		else {
    			if(grandparent.parent.left.equals(grandparent)) {
    				grandparent = findParent(root, grandparent.key);
    				grandparent.parent.left = parent;
    			}
    			if(grandparent.parent.right.equals(grandparent)) {
    				grandparent = findParent(root, grandparent.key);
    				grandparent.parent.right = parent;
    			}
    		}
    		grandparent.left = null;
        	grandparent.color = RED;
        	grandparent.parent = parent;
        	parent.left = child;
        	parent.right = grandparent;
        	parent.color = BLACK;
        	child.parent = parent;
    	}
    	if(grandparent.left != null && grandparent.left.equals(parent) && parent.left != null && parent.left.equals(child)) {
    		if(grandparent.parent == null)
    			root = parent;
    		else {
    			if(grandparent.parent.left.equals(grandparent)) {
    				grandparent = findParent(root, grandparent.key);
    				grandparent.parent.left = child;
    			}
    			if(grandparent.parent.right.equals(grandparent)) {
    				grandparent = findParent(root, grandparent.key);
    				grandparent.parent.right = child;
    			}
    		}
    		grandparent.left = null;
        	grandparent.color = RED;
        	grandparent.parent = child;
        	
        	parent.right = null;
        	parent.parent = child;
        	parent.color = RED;
        	
        	child.left = parent;
        	child.right = grandparent;
        	child.color = BLACK;
    	}
    	
    }
//    private RBTNode<K, V> rotateRight(RBTNode<K, V> grandparent){
//    	RBTNode<K, V> temp = grandparent.left;
//    	grandparent.left = temp.right;
//    	temp.right = grandparent;
//    	temp.color = temp.right.color;
//    	temp.right.color = RED;
//    	return temp;
//    }
//    private RBTNode<K, V> rotateLeft(RBTNode<K, V> grandparent){
//    	RBTNode<K, V> temp = grandparent.right;
//    	grandparent.right = temp.left;
//    	temp.left = grandparent;
//    	temp.color = temp.left.color;
//    	temp.left.color = RED;
//    	return temp;
//    }
//    private RBTNode<K, V> TNRRight(RBTNode<K, V> parent){
//    	RBTNode<K, V> temp = parent.left.right;
//    	parent.left.right = null;
//    	temp.left = parent.left;
//    	parent.left = null;
//    	temp.right = parent;
//    	temp.color = parent.color;
//    	temp.right.color = RED;
//    	return temp;
//    }
//    private RBTNode<K, V> TNRLeft(RBTNode<K, V> parent){
//    	RBTNode<K, V> temp = parent.right.left;
//    	parent.right.left = null;
//    	temp.right = parent.right;
//    	parent.right = null;
//    	temp.left = parent;
//    	temp.color = parent.color;
//    	temp.left.color = RED;
//    	return temp;
//    }
    /**
     * Unsupported remove method
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        throw new UnsupportedOperationException();
    }
    /**
     * Gets value of key being searched for
     * @throw KeyNotFoundException when key not found in RBT
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(finder(root, key) == null)
        	throw new KeyNotFoundException();
    	return finder(root, key).value;
    }
    /**
     * Returns true if the RBT contains the key, and false otherwise
     */
    @Override
    public boolean contains(K key) throws IllegalNullKeyException {
    	if(finder(root, key) == null)
        	return false;
        return true;
    }
    /**
     * The number of keys in the RBT is returned
     */
    @Override
    public int numKeys() {
        return numKeys;
    }
    /**
     * Finds a node in the RBT
     * @param current
     * @param key
     * @return
     * @throws IllegalNullKeyException
     */
    private RBTNode<K, V> finder(RBTNode<K, V> current, K key) throws IllegalNullKeyException{
		// if key passed is null, throw exception
    	if(key == null)
			throw new IllegalNullKeyException();
    	// if the key does not exist in the RBT, return null
    	if(current == null)
    		return null;
    	// if current is the node being searched for, return that node
		if(current.key.compareTo(key) == 0)
			return current;		
		// recurse left
		else if(current.key.compareTo(key) > 0)
			return finder(current.left, key);
		// recurse right
		else
			return finder(current.right, key);
    }
    /**
     * Prints the RBT
     */
    @Override
    public void print() {
    	for(int i = 1; i <= this.getHeight(); i++) {
    		printHelper(root, i);
    		System.out.println();
    	}
    }
    /**
     * Helper method for print
     * @param current
     * @param level
     */
    private void printHelper(RBTNode<K, V> current, int level) {
    	 if(current == null) {
    		 System.out.print("(X) ");
    		 return;
    	 }
    	 else if (level == 1) { 
             System.out.print(current.key + " ");
         }
         else if (level > 1) 
         { 
             printHelper(current.left, level-1); 
             printHelper(current.right, level-1);
         }
    }


    // TODO: override the insert method so that it rebalances 
    //       according to red-black tree insert algorithm.


    // TODO [OPTIONAL] you may override print() to include
    //      color R-red or B-black.
    
}