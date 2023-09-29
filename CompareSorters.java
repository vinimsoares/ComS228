package edu.iastate.cs228.hw2;

/**
 *  
 * @author
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
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
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
		generateRandomPoints(50, rand);
		Point point1 = new Point(-1,2);
		Point point2 = new Point(3,3);
		Point point3 = new Point(1,0);
		Point point4 = new Point(0,1);
		Point point5 = new Point();
		Point[] test = new Point[] {point1,point2,point3,point4} ;
		SelectionSorter testSelec = new SelectionSorter(test);
		testSelec.sort();
		System.out.println(Arrays.toString(test));
		
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
		
	}
	
	
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
	    	if(numPts<1) {
	    	    throw new IllegalArgumentException();
	    	}
	    	Point[] points = new Point[numPts];
	    	for(int i = 0; i< numPts-1;i++) {
	    	int numx = rand.nextInt(101)-50;
	    	int numy = rand.nextInt(101)-50;
	    	Point tempPoint = new Point(numx, numy);
	    	points[i] = tempPoint;
	    	}
	
	    	
		return points; 
		
	}
	
}
