import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Due Date:   04/09/2020
 * Authors:    Ishaan Backliwal
 * Email:      backliwal@wisc.edu
 * 
 * Course:     CS400
 * Semester:   Spring 2020
 * Lecture:    001
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {
    
    private Graph graph;
    
    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
        graph = new Graph();
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
        Package[] pack = null;
        Object object = new JSONParser().parse(new FileReader(jsonFilepath));
        JSONObject jsonObject = (JSONObject) object;
        JSONArray packages = (JSONArray) jsonObject.get("packages");
        // array of packages from JSON file
        pack = new Package[packages.size()];
        for(int i = 0; i< packages.size(); i++) {
        	JSONObject jsonPack = (JSONObject) packages.get(i);
        	// name of the current package
        	String packName = (String) jsonPack.get("name");
        	// list of dependencies of current package
        	JSONArray dependencies = (JSONArray) jsonPack.get("dependencies");
        	// convert dependencies to array of strings
        	String[] arr = new String[dependencies.size()];
        	for (int j = 0; j < arr.length; j++) {
        		arr[j] = (String) dependencies.get(j);
        	}
        	Package currentPack = new Package(packName, arr);
			pack[i] = currentPack;
        }
        // add vertices and edges needed
        for (int i = 0; i < pack.length; i++) {
			graph.addVertex(pack[i].getName());
			for (int j = 0; j < pack[i].getDependencies().length; j++)
				graph.addEdge(pack[i].getName(), pack[i].getDependencies()[j]);
		}
        
    }
    
    /**
     * Helper method to get all packages in the graph.
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
        return graph.getAllVertices();
    }
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
    	Set<String> allPackages = graph.getAllVertices();
    	// if 'pkg' does not exist, throw exception
    	if (!allPackages.contains(pkg))
    		throw new PackageNotFoundException();
    	LinkedList<String> visited = new LinkedList<String>();
    	installHelp(visited, pkg, new LinkedList<String>());
    	// add to original package
    	visited.add(pkg);
    	return visited;
    }
    /**
     * Helper method for getIntallationOrder()
     * @param visited
     * @param current
     * @param previous
     * @throws CycleException 
     */
    private void installHelp(LinkedList<String> visited, String current, LinkedList<String> previous) throws CycleException {
    	LinkedList<String> added = new LinkedList<String>();
    	// iterates through
    	for (String x : graph.getAdjacentVerticesOf(current))
    		// add to beginning of the list if not yet visited
    		if (!visited.contains(x)) {
    			visited.add(0, x);
    			added.add(x);
    		} 
    		else if (!previous.contains(x))
    			throw new CycleException();
    		else {
    			int index = visited.indexOf(x);
    			visited.remove(index);
    			visited.add(0, x);
    		}
    	// recursive
    	for (String y : added)
    		installHelp(visited, y, added);
    }
    
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
    	// already installed packages
    	List<String> alreadyInstalled = this.getInstallationOrder(installedPkg);
    	// to-install packages
		List<String> install = this.getInstallationOrder(newPkg);
		// if alreadyInstalled contains newPkg
		if (!alreadyInstalled.contains(newPkg)) {
			// take out packages already in alreadyInstalled out of install
			for (String vertex : alreadyInstalled)
				install.remove(vertex);
			return install;
		}
		// if installed does not contain new package
		if (alreadyInstalled.contains(newPkg)) {
			for (String vertex : install)
				// removes any package from alreadyInstalled that is also in install
				alreadyInstalled.remove(vertex);
			alreadyInstalled.add(0, newPkg);
			return alreadyInstalled;
		} 
		else {
			return install;
		}
    }
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
        // max dependency package
		String dependenciesMax = getPackageWithMaxDependencies();
		Set<String> allPackages = getAllPackages();
		List<String> order = new LinkedList<String>();
		try {
			// sets order of install to the install order of dependenciesMax
			order = getInstallationOrder(dependenciesMax);
			allPackages.remove(dependenciesMax);
			// adds packages not in list into correct spot
			for (String x : allPackages) {
				// if vert is not in the list
				if (!order.contains(x)) {
					// install order of the current package
					List<String> order2 = getInstallationOrder(x);
					for (int i = 0; i < order2.size(); i++) {
						if (!order.contains(order2.get(i)))
							order.add(order2.get(i));
					}
				}
			}
		} 
		catch (PackageNotFoundException e) {
			// PackageNotFoundException should NOT be thrown
		}
		return order;
    }
    
    /**
     * Find and return the name of the package with the maximum number of dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
		Set<String> allPackages = this.getAllPackages();
		// this will represent the longest path in the graph
		List<String> longest = new LinkedList<String>();
		List<String> temp;
		String mostDependencies = "";
		// compares all paths for each package to previous longest
		for (String pkg : allPackages) {
			try {
				temp = this.getInstallationOrder(pkg);
				if (temp.size() > longest.size()) {
					longest = temp;
					mostDependencies = pkg;
				}
			} 
			catch (PackageNotFoundException e) {}
		}
		return mostDependencies;
    }

    public static void main (String [] args) {
        System.out.println("PackageManager.main()");
    }
    
}
