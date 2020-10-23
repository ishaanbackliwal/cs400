import java.util.ArrayList;
import java.util.List;

// DO IMPLEMENT A BINARY SEARCH TREE IN THIS CLASS

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface 
 * that are required solely to support gray-box testing 
 * of the internal tree structure.  They must be implemented
 * as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public class BST<K extends Comparable<K>, V> implements STADT<K,V> {
	/**
	 * BSTNode - Inner class that implements a nodes for the outer BST class
	 * @author ishaan backliwal
	 *
	 * @param <K>
	 * @param <V>
	 */
	private class BSTNode<K, V>{
		
		K key;
		V value;
		BSTNode<K, V> left;
		BSTNode<K, V> right;
		
		/**
		 * Two argument constructor that initializes key and value of node 
		 * and sets left and right child to null
		 * @param key is the key of the node
		 * @param value is the value of the node
		 */
		private BSTNode(K key, V value){
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
		private BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild){
			this.key = key;
			this.value = value;
			right = rightChild;
			left = leftChild;
		}
	}
	
	public BSTNode<K, V> root;	// root of BST
	public int numKeys;	// number of keys in BST
	/**
	 * No argument constructor that initializes values
	 */
	public BST() {
		root = null;
		numKeys = 0;
	}
	/**
     * Returns the key that is in the root node of this BST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    public K getKeyAtRoot() {
        if(root == null) {
        	return null;
        }
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
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(finder(root, key) == null)
    		throw new KeyNotFoundException();
    	BSTNode<K, V> find = finder(root, key);
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
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(finder(root, key) == null)
    		throw new KeyNotFoundException();
    	BSTNode<K, V> find = finder(root, key);
    	if(find.right == null)
    		return null;
    	return find.right.key;
    }
    
    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    public int getHeight() {
        // if root is the only node in the BST, return height of 1
    	if(numKeys == 1)
        	return 1;
    	// otherwise run helper method
    	else
    		return helpHeight(root);
    }
    /**
     * Helper method to find height of the BST
     * @param current is the current root the program is on in the BST
     * @return height of the BST
     */
    private int helpHeight(BSTNode<K, V> current) {
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
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    public List<K> getInOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	helpInOrder(root, list);
    	return list;
    }
    /**
     * Helper method to get the in-order traversal and add it to a list of keys
     * @param current node the program is at within the BST
     * @param list is the array list of keys
     */
    private void helpInOrder(BSTNode<K, V> current, List<K> list) {
    	if(current != null) {
    		helpInOrder(current.left, list);
    		list.add(current.key);
    		helpInOrder(current.right, list);
    	}
    }
    
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    public List<K> getPreOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	helpPreOrder(root, list);
    	return list;
    }
    /**
     * Helper method to get the pre-order traversal and add it to a list of keys
     * @param current node the program is at within the BST
     * @param list is the array list of keys
     */
    private void helpPreOrder(BSTNode<K, V> current, List<K> list) {
    	if(current != null) {
    		list.add(current.key);
    		helpPreOrder(current.left, list);
    		helpPreOrder(current.right, list);
    	}
    }

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    public List<K> getPostOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	helpPostOrder(root, list);
    	return list;
    }
    /**
     * Helper method to get the post-order traversal and add it to a list of keys
     * @param current node the program is at within the BST
     * @param list is the array list of keys
     */
    private void helpPostOrder(BSTNode<K, V> current, List<K> list) {
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
    public List<K> getLevelOrderTraversal() {
    	List<K> list = new ArrayList<K>();
    	int level = this.getHeight();
    	// run the helper for each level
    	for(int i = 1; i <= level; i++)
    		helpLevelOrder(root, list, i);
    	return list;
    }
    /**
     * Helper method for level order traversal
     * @param current is the current node the program is at within the BST
     * @param list is the list of keys in the appropriate traversal order
     * @param level is the level of the BST the program is at
     */
    private void helpLevelOrder(BSTNode<K, V> current, List<K> list, int level) {
    	// if the current node is null, end recursion
    	if(current == null)
    		return;
    	// if the current level is the root, add the root to the tree
    	else if(level == 1)
    		list.add(current.key);
    	// if the current level value is greater than one (lower than root), 
    	//recurse left and right 
    	else if(level > 1) {
    		helpLevelOrder(current.left, list, level - 1);
    		helpLevelOrder(current.right, list, level - 1);
    	}
    		
    }
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        // if key passed is null, throw exception
    	if(key == null)
        	throw new IllegalNullKeyException();
    	// if BST is empty, set root to node passed
    	if(root == null)
        	root = new BSTNode<K, V>(key, value);
    	// else run insert helper to find location for new node
    	else
    		insertHelp(root, new BSTNode<K, V>(key, value));
    	// increment record of the number of keys in the BST
    	numKeys++;
    }
    /**
     * Helper method for insert method
     * @param current is the current node being tested
     * @param insert is the node being inserted into the BST
     * @throws DuplicateKeyException when key of node being inserted already exists in the BST
     */
    private void insertHelp(BSTNode<K, V> current, BSTNode<K, V> insert) throws DuplicateKeyException {
    	// if key passed already exists, throw exception
    	if(current.key.compareTo(insert.key) == 0)
    		throw new DuplicateKeyException();
    	// recursive case - going left if key is less than current root node
    	if(current.key.compareTo(insert.key) > 0) {
    		// if the left node of the current node is null, set that to the new node
    		if(current.left == null)
    			current.left = insert;
    		// else recurse back and go further down the tree
    		else
    			insertHelp(current.left, insert);
    	}
    	// recursive case - going right if key is greater than current root node
    	else if(current.key.compareTo(insert.key) < 0) {
    		// if the right node of the current node is null, set that to the new node
    		if(current.right == null)
    			current.right = insert;
    		// else recurse back and go further down the tree
    		else
    			insertHelp(current.right, insert);
    	}
    }
 
    /** 
     * If key is found, remove the key,value pair from the data structure 
     * and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure, return false.
     * If key is null, throw IllegalNullKeyException
     */
    public boolean remove(K key) throws IllegalNullKeyException {
    	// if key passed is null, throw exception
    	if(key == null)
        	throw new IllegalNullKeyException();
    	// if tree is empty, remove operation cannot be conducted
    	if(root == null) {
    		return false;
    	}
    	// if key does not exist in BST, return false
    	if(!this.contains(key))
    		return false;
    	// new tree without removed node
    	root = helpRemove(root, key);
    	numKeys--;
    	return true;
    }
    /**
     * Helper method for the remove operation of the BST
     * @param current is the current node the program is at in the BST
     * @param key is the key of the node being removed
     * @return new tree with removed node
     * @throws IllegalNullKeyException
     */
    private BSTNode<K, V> helpRemove(BSTNode<K, V> current, K key) throws IllegalNullKeyException { 
    	// if node being removed is lesser than current, recurse left
    	if(current.key.compareTo(key) > 0)
    		current.left = helpRemove(current.left, key);
    	// if node being removed is greater than current, recurse right
    	else if(current.key.compareTo(key) < 0)
    		current.right = helpRemove(current.right, key);
    	// if node to be removed is found
    	else {
    		// when node does not have left child, return right child
    		if(current.left == null)
    			return current.right;
    		// when node does not have right child, return left child
    		if(current.right == null)
    			return current.left;
    		// in-order successor
    		BSTNode<K, V> child = current.right;
    		// find in-order successor
    		while(child.left != null) {
    			child = child.left;
    		}
    		// set the key and value of node being remove to that of in-order successor
    		current.key = child.key;
    		current.value = child.value;
    		// delete successor (in-order)
    		current.right = helpRemove(current.right, current.key);
    	}
		return current;
    }

    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(finder(root, key) == null)
        	throw new KeyNotFoundException();
    	return finder(root, key).value;
    }

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    public boolean contains(K key) throws IllegalNullKeyException { 
        if(finder(root, key) == null)
        	return false;
        return true;
    }

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    public int numKeys() {
        return numKeys;
    }
    /**
     * Helper method that finds a node in the BST
     * @param current is the current node being tested
     * @param key is the key trying to be found
     * @return the node when found or null if not found
     * @throws IllegalNullKeyException if key passed is null
     */
    public BSTNode<K, V> finder(BSTNode<K, V> current, K key) throws IllegalNullKeyException{
		// if key passed is null, throw exception
    	if(key == null)
			throw new IllegalNullKeyException();
    	// if the key does not exist in the BST, return null
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
		// if node not found in the BST, return null
    }
    
    /**
     * Print the tree. 
     *
     * For our testing purposes of your print method: 
     * all keys that we insert in the tree will have 
     * a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display a binary search in any of a variety of ways, 
     * but we must see a tree that we can identify left and right children 
     * of each node
     *
     * For example: 
     
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.

       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.

     */
    public void print() {
    	for(int i = 1; i <= this.getHeight(); i++) {
    		printHelper(root, i);
    		System.out.println();
    	}
    }
    
    private void printHelper(BSTNode<K, V> current, int level) {
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
}

// copyrighted material, students do not have permission to post on public sites
//  deppeler@cs.wisc.edu
