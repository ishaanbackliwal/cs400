import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * CS400 Spring 2020 (COVID-19) Final Exam Assignment
 * 
 * ========================================================== 
 * Date:  Monday, May 4th, 2020
 * Student: Ishaan Backliwal
 * Lecture: MW 001
 * NETID:   backliwal
 * CSLOGIN: ishaan
 * 
 * TODO ENTER NOTES TO GRADER HERE:
 * 
 * 
 * 
 * 
 * 
 * ==========================================================

 * DESCRIPTION:
 * 
 * THIS IS A DATA PROCESSING PROGRAM with custom Data Structure
 * 
 * It reads the name of a data file from the command line args.
 *     java Main data.csv 
 *     
 * It then reads the data from the named data file and creates an instance of DS
 * 
 * It does not write data to file.  
 *
 * The results.txt is created in this way: 
 * RUN PROGRAM, COPY OUTPUT, AND PASTE INTO A TEXT FILE
 * Then, save the file with the name: results.txt
 * 
 * CAUTION: IT IS ACADEMIC MISCONDUCT TO EDIT YOUR PROGRAM'S OUTPUT 
 * in the results.txt file that is submitted.
 * 
 * DO NOT LEAVE EXTRANEOUS PRINT STATEMENTS IF YOU CAN HELP IT!
 * 
 * WE WILL ALSO TEST YOUR CODE AND CAPTURE CONSOLE OUTPUT TO COMPARE TO results.txt
 * 
 * @author deppeler ALL RIGHTS RESERVED, FOR STUDENT USE DURING EXAM ONLY.
 */
public class Main {

	/** DO NOT EDIT -- DS is required for instance methods. */
	DS ds;
	
	/** default no-arg constructor, used only by main */
	public Main() { }

	/** Add other fields here as needed -- may not need any */

	/** 
	 * TODO Read and parse specified file to build a data structure. 
	 * Must read each line and add data entries to the DS.
	 * 
	 * @param filename the name of the data file.
	 * @return instance of DS that contains all of the data
	 */
	public static DS parseData(String filename) {
		DS dataStructure = new DS();
		ArrayList<String> fileInfo = new ArrayList<String>();
		// PRO-TIP Use scanner connected to a file to read lines of text.
		Scanner scnr = null;
		// If data file does not have correctly formatted data,
		// it is okay for your program to crash due exceptions.
		// If data.csv is correct, your project should not crash.
		try {
			scnr = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scnr.nextLine(); // first line
		while (scnr.hasNextLine()) {
			dataStructure.add(DailyStateDataEntry.parse(scnr.nextLine()));
		}
		return dataStructure;
	}

	/**
	 * TODO Returns a list of the states with most total cases in descending order.
	 * 
	 * Creates a multi-line string that has all states in decreasing order 
	 * based on the number of total cases for that state.  
	 * 
	 * For example: generateTopCasesReport(4) would get the following String
	 * 
	 * New York,5796756,282999
	 * New Jersey,1930397,83432
	 * Massachusetts,831374,33172
	 * California,775044,25381
	 * 
	 * The order between two states with same number of cases does not matter.
	 * 
	 * PRO-TIP: get each state's StateSummary from ds use insertion sort
	 * to keep a sorted list as you get each state's summary object.
	 * 
	 * Or, you can get them first and then use Collections.sort with a Comparator.
	 * 
	 * @param numOfStates is the maximum number of states to list.
	 * If numStates is greater than the number of states, display all states.
	 * 
	 * @return a String that a list of the number of states with the most total cases.
	 */
	public String generateTopCasesReport(int numOfStates) {

		/**  PRO-TIP make sure that StateSummary works first */

		// Recommended algorithm (not required)
		// 1. get state names from ds
		String[] names = ds.getStateNamesInSortedOrder();
		// 2. for each state, get the StateSummary and add to a list
		ArrayList<StateSummary> summaries = new ArrayList<StateSummary>();
		for(int i = 0; i < names.length; i++)
			summaries.add(ds.getStateSummaryFor(names[i]));
		// 3. create a Comparator that will sort in descending order by total cases
		Comparator<StateSummary> comparator = new Comparator<StateSummary>() {
			public int compare(StateSummary summary1, StateSummary summary2) {
				return summary2.getTotalCases() - summary1.getTotalCases();
			}
		};
		// 4. use Collections.sort with list and the comparator
		Collections.sort(summaries, comparator);
		// 5. limit list to specified number of states
		ArrayList<StateSummary> shortened = new ArrayList<StateSummary>();
		for(int j = 0; j < numOfStates; j++)
			shortened.add(summaries.get(j));
		// 6. create a results string with one line per state
		String report = "";
		for(int k = 0; k < shortened.size(); k++)
			report = report.concat(shortened.get(k).toString() + "\n");
		return report;  
		
	}

	/** DO NOT EDIT -- PROVIDED TO STUDENTS AND TAS FOR CONVENIENCE */
	public String generateTopCasesReport() {
		return generateTopCasesReport(
				this.ds.getStateNamesInSortedOrder().length);
	}

	/** 
	 * DO NOT EDIT -- THIS IS THE OUTPUT WE EXPECT TO SEE AND GRADE.
	 * Parses data, Creates several reports, Displays results to the screen.
	 */
	public void processDataAndOutputResultsToConsole(String filename) {
	
		// Parse the data to an internal DS,		
		ds = parseData(filename);
	
		// Use DS instance to generate sample report
		String[] states = ds.getStateNamesInSortedOrder();
		StateSummary sampleReport = ds.getStateSummaryFor(states[0]); 
		System.out.println(sampleReport);
	
		// Use DS to generate several different top case reports
		String report = generateTopCasesReport(1);
		System.out.println("Top One\n" + report);
	
		report = generateTopCasesReport(3);
		System.out.println("Top Three\n" + report);
	
		report = generateTopCasesReport(5);
		System.out.println("Top Five\n" + report);
	
		report = generateTopCasesReport(10);
		System.out.println("Top Ten\n" + report);
	
		report = generateTopCasesReport();
		System.out.println("All States\n" + report);
	}

	/** DO NOT EDIT THIS METHOD */
	public static void main (String [] args) {
	
		// ==============================================
		// Display usage information if filename is not given as program argument
		// ==============================================
		if (args.length < 1) {
			System.out.println(
					"Expected name of a data file with the following format:\n"
					+ "recordID,state,cases,deaths\n\n"
					+ "Usage: java Main <data.csv> ");
			System.exit(0);
	
		} 
	
		// ==============================================
		// Create Main instance and process reports
		// ==============================================
	
		// Get name of data file from args[0]
		String filename = args[0];
	
		// Create instance and run the start() method
		Main main = new Main();
		main.processDataAndOutputResultsToConsole(filename);
	
	} 

}
