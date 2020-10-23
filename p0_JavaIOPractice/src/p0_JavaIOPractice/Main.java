/**
 * Main.java created by ishaanbackliwal Jan 29, 2020
 *
 * Author: Ishaan Backliwal
 * Date: 02/03/2020
 * Project Name: P0 Java IO Practice
 * Email: backliwal@wisc.edu
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * Program Description: Tennis simulator runs a 10-point tennis match. In order to use the program, 
 * the user must input a file with the names, records, and ranks of two different tennis players. 
 * Then the user can simulate a 10-point match.Once the match is finished, the user may output a 
 * new file that contains the updated statistics of each player.
 * 
 * List Collaborators: n/a
 * 
 * Other Credits: javadocs
 * 
 * Known Bugs: n/a
 */
package p0_JavaIOPractice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Main - This class runs the 'tennis simulator' defined within it
 * 
 * @author backliwal ishaanbackliwal
 */
public class Main {

	private static final Scanner scnr = new Scanner(System.in);	// user text input
	private Scanner fileScanner;	// file input
	private final String title;	// identification of program creator
	private String fileName;	// input file name
	private String p1Name;		// player 1's name
	private String p1Record;	// player 1's win record (ex. 12-3)
	private int p1Wins;			// player 1's # of wins
	private int p1Losses;		// player 1's # of losses
	private int p1Rank;			// player 1's rank
	private String p2Name;		// player 2's name
	private String p2Record;	// player 2's win record (ex. 12-3)
	private int p2Wins;			// player 2's # of wins
	private int p2Losses;		// player 2's # of losses
	private int p2Rank;			// player 2's rank
	private int p1Score;		// player 1's score
	private int p2Score;		// player 2's score
	private boolean outputCondition;  // if output file has been created or not
	
	/**
	 * No argument constructor that initializes various variables
	 * 
	 * @throws FileNotFoundException
	 */
	private Main() throws FileNotFoundException {
		title = "Author: Ishaan Backliwal \nEmail: backliwal@wisc.edu "
				+ "\nLecture #: 001";
		fileName = "N/A";
		outputCondition = false;
	}
	/**
	 * Runs the program
	 * 
	 * @param args
	 * 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Main program = new Main();
		System.out.println(program.title + "\n");
		// run the program
		program.welcomeMenu(1);
	}
	
	/**
	 * Main menu of the tennis simulator that gives options on what the user wishes to do
	 * 
	 * @param intro is the decision variable that decides weather or not the 
	 * introduction message is displayed (works like binary)
	 * 
	 * @throws FileNotFoundException thrown by fileSumbission() method
	 */
	private void welcomeMenu(int intro) throws FileNotFoundException {
		// welcome message
		if(intro == 1) {
			System.out.println("---------------------------------------------");
			System.out.println("      Welcome to TENNIS SIMULATOR 2020!      ");
			System.out.println("---------------------------------------------");
			System.out.println("This program simulates a 10-point tennis match."
					+ "\nTo begin, submit a file with player stats.");
		}
		System.out.println("***File in the system: " + fileName + "***");
		if(outputCondition) {
			System.out.println("***File 'output.txt' has been created***");
		}
		System.out.println("\n(1) File formatting info\n(2) Sumbit player stats file"
				+ "\n(3) Print new stats file\n(4) Start match\n(5) Quit");
		String mainInput = scnr.nextLine();
		if(mainInput.compareTo("1") == 0) {
			// if user picks 1, go to file formatting info
			formattingInfo();
		}
		else if(mainInput.compareTo("2") == 0) {
			// if user picks 2, go to file submission portal
			fileSubmission();
		}
		else if(mainInput.compareTo("3") == 0) {
			// if user picks 3, go to file output portal
			printFile();
		}
		else if(mainInput.compareTo("4") == 0) {
			// if user picks 4, go to simulation runner
			runSim(1);
		}
		else if(mainInput.compareTo("5") == 0) {
			// if user picks 5, go to goodbye message
			quit();
		}
		else {
			System.out.println("INVALID INPUT: Please try again.");
			welcomeMenu(0);
		}
	}
	
	/**
	 * Displays how the file to be submitted to the program should be formatted 
	 * in order for proper usage of the program
	 * 
	 * @throws FileNotFoundException
	 */
	private void formattingInfo() throws FileNotFoundException {
		System.out.println("----------------------------------------------------"
				+ "------------------------------------------");
		System.out.println("(Press ENTER key to return to main menu)");
		System.out.println("The following is how your file should be formatted "
				+ "in order for the program to run properly:");
		System.out.println("\nP1 Name: FIRST LAST\n" + 
				"P1 Record: MATHES WON - MATCHES LOST\n" + 
				"P1 Ranking: INTEGER VALUE RANKING\n" + 
				"\n" + 
				"P2 Name: FIRST LAST\n" + 
				"P2 Record: MATHES WON - MATCHES LOST\n" + 
				"P2 Ranking: INTEGER VALUE RANKING");
		System.out.println("----------------------------------------------------"
				+ "------------------------------------------");
		String formattingInput = scnr.nextLine();
		if(formattingInput.compareTo("") == 0) {
			welcomeMenu(1);
		}
		else {
			System.out.println("INVALID INPUT: Please try again.");
			formattingInfo();
		}
	}
	
	/**
	 * Portal to submit the input file in order to properly run the program
	 * 
	 * @throws FileNotFoundException if file submitted dpoes not match any files in the directory
	 */
	private void fileSubmission() throws FileNotFoundException {
		System.out.println("--------------------------------");
		System.out.println("     File Sumbission Portal");
		System.out.println("--------------------------------");
		System.out.println("Input the file name:");
		System.out.println("(ex: 'input.txt')");
		fileName = scnr.nextLine();
		try {
			fileScanner = new Scanner(new File(fileName));
			
			// gathering player 1 info
			p1Name = fileScanner.nextLine().substring(9);
			p1Record = fileScanner.nextLine();
			p1Wins = Integer.parseInt(p1Record.substring(11, p1Record.indexOf("-")));
			p1Losses = Integer.parseInt(p1Record.substring(p1Record.indexOf("-") + 1));
			p1Rank = Integer.parseInt(fileScanner.nextLine().substring(12));
			
			// gathering player 2 info
			fileScanner.nextLine();
			p2Name = fileScanner.nextLine().substring(9);
			p2Record = fileScanner.nextLine();
			p2Wins = Integer.parseInt(p2Record.substring(11, p2Record.indexOf("-")));
			p2Losses = Integer.parseInt(p2Record.substring(p2Record.indexOf("-") + 1));
			p2Rank = Integer.parseInt(fileScanner.nextLine().substring(12));
			
			welcomeMenu(1);
		}
		catch(FileNotFoundException e) {
			System.out.println("FILE NOT FOUND: Please enter a valid file name.");
			fileSubmission();
		}
	}
	
	/**
	 * Creates an output file when run with updated statistics for each player
	 * 
	 * @throws FileNotFoundException
	 */
	private void printFile() throws FileNotFoundException {
		// if no input file is in the system, no output file can be created
		if(fileName.compareTo("N/A") == 0) {
			System.out.println("No file in system.\nPress ENTER to go to file sumbission menu.");
			String goToFileSumbit = scnr.nextLine();
			if(goToFileSumbit.compareTo("") == 0) {
				fileSubmission();
			}
		}
		else {
			// writing output file with the appropriate information
			PrintWriter output = new PrintWriter(new File("./output.txt"));
			output.println("P1 Name: " + p1Name);
			output.println("P1 Record: " + p1Wins + "-" + p1Losses);
			output.println("P1 Ranking: " + p1Rank);
			output.println("");
			output.println("P2 Name: " + p2Name);
			output.println("P2 Record: " + p2Wins + "-" + p2Losses);
			output.println("P2 Ranking: " + p2Rank);
			output.close();
			outputCondition = true;
			
			// file creation confirmation message
			System.out.println("------------------------------------------------");
			System.out.println("File 'output.txt' has successfully been created!");
			System.out.println("     (press ENTER to return to main menu)");
			System.out.println("------------------------------------------------");
			String fileOutputDone = scnr.nextLine();
			if(fileOutputDone.compareTo("") == 0) {
				welcomeMenu(1);
			}
		}
	}
	
	/**
	 * Runs tennis simulator which gives the user the option to assign each player a certain amount of points
	 * First player to 10 points wins the math and is given a congratulations message
	 * 
	 * @param intro is the decision variable that decides weather or not the introduction message is displayed (works like binary)
	 * @throws FileNotFoundException
	 */
	private void runSim(int intro) throws FileNotFoundException {
		// if no input file is in the system, simulation cannot be run
		if(fileName.compareTo("N/A") == 0) {
			System.out.println("No file in system.\nEnter 1 to go to file sumbission menu.");
			String noFile = scnr.nextLine();
			if(noFile.compareTo("1") == 0) {
				fileSubmission();
			}
		}
		else {
			// if either player's score is 10, the simulation is over and the match has been won
			// check for this condition first as to not display intro message if game is over
			if(p1Score == 10 || p2Score == 10) {
				System.out.println("---------------------------------------------");
				System.out.println("---------------------------------------------");
				System.out.println("---------------------------------------------");
				System.out.println("CONGRAGULATIONS!!!");
				
				// procedure if player 1 wins the match
				if(p1Score == 10) {
					System.out.println(p1Name + " has won!");
					p1Wins++;
					p2Losses++;
					p2Rank++;
					if(p1Rank != 1) {
						p1Rank--;
					}
				}
				
				// procedure if player 2 wins the match
				if(p2Score == 10) {
					System.out.println(p2Name + " has won!");
					p2Wins++;
					p1Losses++;
					p1Rank++;
					if(p2Rank != 1) {
						p2Rank--;
					}
				}
				System.out.println("(press ENTER to return to main menu)");
				System.out.println("---------------------------------------------");
				System.out.println("---------------------------------------------");
				System.out.println("---------------------------------------------");
				String someoneWon = scnr.nextLine();
				if(someoneWon.compareTo("") == 0) {
					welcomeMenu(1);
				}
				else {
					welcomeMenu(1);
				}
			}
			// if neither player has reached a score of 10, run this
			else {
				// if into condition is 1/yes, display introduction header
				if(intro == 1) {
					System.out.println("---------------------------------------------");
					System.out.println("            SIMULATION STARTED");
					System.out.println("---------------------------------------------");
				}
				// if outputCondition is true, display message in order to inform user of existing output file
				if(outputCondition) {
					System.out.println("  Any changes made will overwrite existing");
					System.out.println("           'output.txt' file.");
					System.out.println("---------------------------------------------");
				}
				// scoring and user input options
				System.out.println("~ ~ ~ S C O R E ~ ~ ~");
				System.out.println(p1Name.substring(p1Name.indexOf(" ") + 1) + ": " + p1Score);
				System.out.println(p2Name.substring(p2Name.indexOf(" ") + 1) + ": " + p2Score);
				System.out.println("---------------------------------------------");
				System.out.println("\n(1) Point " + p1Name.substring(p1Name.indexOf(" ") + 1));
				System.out.println("(2) Point " + p2Name.substring(p2Name.indexOf(" ") + 1));
				System.out.println("(3) Quit to Main Menu");
				String scoreChanger = scnr.nextLine();
				if(scoreChanger.compareTo("1") == 0) {
					// if user picks 1, increase player 1's score
					p1Score++;
					runSim(0);
				}
				else if(scoreChanger.compareTo("2") == 0) {
					// if user picks 2, increase player 2's score
					p2Score++;
					runSim(0);
				}
				else if(scoreChanger.compareTo("3") == 0) {
					// if user picks 3, return to main menu
					welcomeMenu(1);
				}
				else {
					System.out.println("INVALID INPUT: Please try again.");
					runSim(0);
				}
			}
		}
	}
	
	/**
	 * Message that is displayed when user wishes to stop the program from running
	 * 
	 * @throws FileNotFoundException
	 */
	private void quit() throws FileNotFoundException {
		System.out.println("---------------------------------------------");
		System.out.println("Thank you for playing TENNIS SIMULATOR 2020" + 
						 "\n             Have a nice day!");
		System.out.println("---------------------------------------------");
	}
}
