import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Filename:   PackageManagerTests.java
 * Project:    p4
 * Due Date:   04/09/2020
 * Authors:    Ishaan Backliwal
 * Email:      backliwal@wisc.edu
 * 
 * Course:     CS400
 * Semester:   Spring 2020
 * Lecture:    001
 * 
 */

/**
 * PackageManagerTests tests the PackageManager Class using JUnit tests
 * @author ishaan backliwal
 *
 */
  class PackageManagerTests {
	
	  PackageManager pkgM;
	
	  @BeforeEach
	  void setUp() throws Exception {
	   	  pkgM = new PackageManager();
	  }

	  /**
	   * @throws java.lang.Exception
	   */
	  @AfterEach
	  void tearDown() throws Exception {
		  pkgM = null;
	  }
	  /**
	   * Tests PackageManager constructor
	   */
	  @Test
	  void test001_pkgs_to_install() {
	    try {
	      pkgM.constructGraph("valid.json");
	      String test = pkgM.toString();
	      if (test.equals(null))
	        fail("package manager constructor exhibitng incorrect functinality");
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception was thrown when it should not have");
	    }
	  }
	  /**
	   * Tests getInstallationOrderForAllPackages
	   */
	  @Test
	  void test002_getInstallationOrderForAllPackages() throws FileNotFoundException, IOException, ParseException {
	    try {
	      pkgM.constructGraph("valid.json");
	      String installOrder = pkgM.getInstallationOrderForAllPackages().toString();
	      if (!installOrder.equals("[D, C, B, A, E]"))
	        fail("install order is wrong, the expected was [D, C, B, A, E] but the actual is " + installOrder);
	    } 
	    catch (CycleException e) {
	      e.printStackTrace();
	      fail("CycleException thrown when it should not have");
	    } 
	    catch (Exception e) {
          e.printStackTrace();
          fail("unexcpected exception thrown when it should not have");
	    }
	  }
	  /*
	   * Tests toInstall()
	   */
	  @Test
	  void test003_toInstall() {
		  try {
			  pkgM.constructGraph("valid.json");
			  List<String> expected = new LinkedList<String>();
			  expected.add("A");
			  List<String> actual = pkgM.toInstall("A", "B"); // gets actual list
			  if(!actual.equals(expected)) {
				  fail("actual list does equal expected, the expected was " + expected + " but the actual is " + actual);
			  }
		  }
		  catch(Exception e) {
			  e.printStackTrace();
			  fail("unexpected exception was thrown when it should not have");
		  }
	  }
	  /**
	   * Tests listing of json file packages correctly
	   */
	  @Test
	  void test004_listAllPackages() throws CycleException {
	    try {
	      pkgM.constructGraph("valid.json");
	      String packages = pkgM.getAllPackages().toString();
	      if (!packages.equals("[A, B, C, D, E]"))
	        fail("the install order is wrong, the excepted was [A, B, C, D, E] but the actual is " + packages);
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception thrown when it should not have");
	    }
	  }
	  /**
	   * Tests if CycleException thrown when supposed to using getInstallationOrder()
	   */
	  @Test
	  void test005_cyclic_json_getInstallationOrder_for_specific_Package() throws FileNotFoundException, IOException, ParseException {
	    try {
	      pkgM.constructGraph("cycleTest.json");
	      pkgM.getInstallationOrder("A");
	      fail("CycleException not thrown when it should have");
	    } 
	    catch (CycleException e) {
	      // pass
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception was thrown when it should not have");
	    }
	  }
	  /**
	   * Tests PackageManager when given a nonexistent file
	   */
	  @Test
	  void test006_exception_if_json_file_is_notexistent() {
	    try {
	      pkgM.constructGraph("FileDNE.json");
	      String tryFail = pkgM.getPackageWithMaxDependencies().toString();
	      if (!tryFail.equals(null))
	        fail("This file does not exist, 'tryFail' should be null");
	      fail("FileNotFoundException was not thrown when it should have been");
	    } 
	    catch (FileNotFoundException e) {
	      // pass
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception was thrown when it should not have been");
	    }
	  }

	  /**
	   * Tests if getInstallationOrderForAllPackages() throws CycleException when supposed to
	   */
	  @Test
	  void test007_CycleException_thrown_for_getInstallationOrderForAllPackages() throws FileNotFoundException, IOException, ParseException, PackageNotFoundException {
	    try {
	      pkgM.constructGraph("cycleTest.json");
	      String installOrder = pkgM.getInstallationOrderForAllPackages().toString();
	      fail("installa order did not throw exception when it should have");
	    } 
	    catch (CycleException e) {
	      // pass
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception was thrown when it should not have");
	    }
	  }
	  /**
	   * Tests whether install roder is correct or not
	   */
	  @Test
	  void test008_correct_installation_order() {
	    try {
	      pkgM.constructGraph("valid.json");
	      String installationOrder = pkgM.getInstallationOrder("A").toString();
	      if (!installationOrder.equals("[D, C, B, A]"))
	        fail("installation order was incorrect, expected was [D, C, B, A] but actual is " + installationOrder);
	    } 
	    catch (CycleException e) {
	      e.printStackTrace();
	      fail("CycleException thrown when not expected");
	    } 
	    catch (PackageNotFoundException e) {
	      e.printStackTrace();
	      fail("PackageNotFoundException thrown when not expected");
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception thrown when it should not have");
	    }
	  }
	  /**
	   * Tests if packages with max dependencies are returned when supposed to
	   */
	  @Test
	  void test009_get_max_pakages_to_install() {
	    try {
	      pkgM.constructGraph("valid.json");
	      String max = pkgM.getPackageWithMaxDependencies().toString();
	      if (!max.equals("A"))
	        fail("failed to get package with the max dependencies");
	    } catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception was thrown when it should not have");
	    }
	  }
	  /**
	   * Tests whether cycles are seen and handled the right way
	   */
	  @Test
	  void test010_installation_order_throws_CycleEception() {
	    try {
	      pkgM.constructGraph("cycleTest.json");
	      String installOrder = pkgM.getInstallationOrder("A").toString();
	      fail("installation order did not throw CycleException as expected");
	    } 
	    catch (CycleException e) {
	      // pass
	    } 
	    catch (PackageNotFoundException e) {
	      e.printStackTrace();
	      fail("PackageNotFoundException thrown when it should not have");
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      fail("unexcpected exception thrown when it should not have");
	    }
	  }
  }
