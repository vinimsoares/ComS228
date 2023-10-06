package edu.iastate.cs228.hw2;

import java.io.File;

/**
 *  
 * @author Vinicius Malaman Soares 
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.filechooser.FileNameExtensionFilter;


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 * @author Vinicius Malaman Soares 
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		PointScanner[] scanners = new PointScanner[4]; 
		Random rand = new Random();
		Scanner scrn = new Scanner(System.in);
		int key = 0;
		int trialNumber = 1;
		int size = 0;
		Point[] points;
		while(key != 3) {
	System.out.println("Performance of Four Sorting Algorithms in Point Scanning");
	System.out.println("keys: 1(Random Integer) 2(file input) 3(exit)");
	System.out.println("Trial "+ trialNumber+":");
	key = scrn.nextInt();
		Scanner scrn2 = new Scanner(System.in);
	
		
		if(key == 1) {
		    System.out.println("Enter number of random points: ");
		    size = scrn.nextInt();
		    points = new Point[size];
		    points = generateRandomPoints(size, rand);
		    scanners[0] =  new PointScanner(points, Algorithm.InsertionSort);
		    scanners[1] = new PointScanner(points, Algorithm.MergeSort);
		    scanners[2] = new PointScanner(points, Algorithm.QuickSort);
		    scanners[3] = new PointScanner(points, Algorithm.SelectionSort);
		    
		}if(key == 2) {
		   	
			System.out.println("Points from a file");
			System.out.println("File name: ");
			String fileName = scrn2.nextLine();
			scanners[0] = new PointScanner(fileName, Algorithm.InsertionSort);
			scanners[1] = new PointScanner(fileName, Algorithm.MergeSort);
			scanners[2] = new PointScanner(fileName, Algorithm.QuickSort);
			scanners[3] = new PointScanner(fileName, Algorithm.SelectionSort);
		    
		    
		    }
		   
		
		
		
		System.out.println();
		    System.out.println("Algorithm      Size     Time(ns)");
		    System.out.println("--------------------------------------");
		    for(int i = 0;i< 4; i++) {
			scanners[i].scan();
			System.out.println(scanners[i].stats());
		    }
		    trialNumber++;
		    System.out.println("--------------------------------------");
		
		}
		
		
		}
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 	
	    	
	    	Point[] points = new Point[numPts];
	    	for(int i = 0; i< numPts;i++) {
	    	int numx = rand.nextInt(101)-50;
	    	int numy = rand.nextInt(101)-50;
	    	Point tempPoint = new Point(numx, numy);
	    	points[i] = tempPoint;
	    	}
	
	    	
		return points; 
		
	}
	
}
