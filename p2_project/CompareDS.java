/**
 * CompareDS.java created by ishaanbackliwalFeb 28, 2020
 *
 * Author: Ishaan Backliwal
 * Date: @date
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * List Collaborators: name, email@wisc.edu, lecture number
 * 
 * Other Credits: 
 * 
 * Known Bugs: 
 */

/**
 * CompareDS - TODO Describe purpose of this user-defined type
 * @author backliwal ishaanbackliwal
 *
 */
public class CompareDS {

	/**
	 * @param args
	 * @throws DuplicateKeyException 
	 * @throws IllegalNullKeyException 
	 * @throws KeyNotFoundException 
	 */
	public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		System.out.println("CompareDS.main Compares work time for: BST and RBT");
		System.out.println("Description: Each trial inserts and gets a total of "
						 + "\n100, 1000, 10000, and 100000 items respectivley and "
						 + "\ncalulates the time it took for these operations to complete.\n");
		int count = 100;	// number of items the loops are going to insert and get
		// loop will run four times, first for 100 items, then 1000, then 10000, and finally 100000
		for(int j = 0; j < 4; j++) {
			BST<Integer, String> bst = new BST<Integer, String>();
			RBT<Integer, String> rbt = new RBT<Integer, String>();
			
			System.out.println("BST is doing work for " + count + " values");
			// log the start time (in nanoseconds)
			long startTimeBST = System.nanoTime();
			// insert 'count' number of items into BST
			for(int i = 0; i < count; i++) {
				bst.insert(i, "one");
			}
			// retrieve 'count' number of items from BST
			for(int i = 0; i < count; i++) {
				bst.get(i);
			}
			// log end time
			long endTimeBST = System.nanoTime();
			// calculate total time spent on operations
			long totalTimeBST = endTimeBST - startTimeBST;
			System.out.println("It took " +  totalTimeBST + "ns to process " + count + " items");
			
			
			System.out.println("RBT is doing work for " + count + " values");
			// log the start time (in nanoseconds)
			long startTimeRBT = System.nanoTime();
			// insert 'count' number of items into RBT
			for(int i = 0; i < count; i++) {
				rbt.insert(i, "one");
			}
			// retrieve 'count' number of items from RBT
			for(int i = 0; i < count; i++) {
				rbt.get(i);
			}
			// log end time
			long endTimeRBT = System.nanoTime();
			// calculate total time spent on operations
			long totalTimeRBT = endTimeRBT - startTimeRBT;
			System.out.println("It took " +  totalTimeRBT + "ns to process " + count + " items\n");
			// change count to the next iterations version (by multiplying by 10)
			count = count * 10;
		}

	}

}
