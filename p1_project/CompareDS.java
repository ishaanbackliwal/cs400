/**
 * CompareDS.java
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
 * CompareDS - TODO Describe purpose of this user-defined type
 * @author backliwal ishaanbackliwal
 *
 */
public class CompareDS {
	
	/**
	 * Main method that runs operations on data structure implementations and calculates
	 * their run times
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("CompareDS.main Compares work time for: DS_My and DS_Brian");
		System.out.println("Description: Each trial inserts and gets a total of "
						 + "\n100, 1000, 10000, and 100000 items respectivley and "
						 + "\ncalulates the time it took for these operations to complete.\n");
		int count = 100;	// number of items the loops are going to insert and get
		// loop will run four times, first for 100 items, then 1000, then 10000, and finally 100000
		for(int j = 0; j < 4; j++) {
			DS_My dsMy = new DS_My();
			DS_Brian dsBrian = new DS_Brian();
			
			System.out.println("DS_My is doing work for " + count + " values");
			// log the start time (in nanoseconds)
			long startTimeMy = System.nanoTime();
			// insert 'count' number of items into DS_My
			for(int i = 0; i < count; i++) {
				dsMy.insert(i, "one");
			}
			// retrieve 'count' number of items from DS_My
			for(int i = 0; i < count; i++) {
				dsMy.get(i);
			}
			// log end time
			long endTimeMy = System.nanoTime();
			// calculate total time spent on operations
			long totalTimeMy = endTimeMy - startTimeMy;
			System.out.println("It took " +  totalTimeMy + "ns to process " + count + " items");
			
			
			System.out.println("DS_Brian is doing work for " + count + " values");
			// log the start time (in nanoseconds)
			long startTimeBrian = System.nanoTime();
			// insert 'count' number of items into DS_Brian
			for(int i = 0; i < count; i++) {
				dsBrian.insert(i, "one");
			}
			// retrieve 'count' number of items from DS_Brian
			for(int i = 0; i < count; i++) {
				dsBrian.get(i);
			}
			// log end time
			long endTimeBrian = System.nanoTime();
			// calculate total time spent on operations
			long totalTimeBrian = endTimeBrian - startTimeBrian;
			System.out.println("It took " +  totalTimeBrian + "ns to process " + count + " items\n");
			// change count to the next iterations version (by multiplying by 10)
			count = count * 10;
		}
	}

}
