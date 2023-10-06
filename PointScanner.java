package edu.iastate.cs228.hw2;

import java.io.File;


/**
 * 
 * @author Vinicius Malaman Soares 
 *
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0) {
		    throw new IllegalArgumentException();
		}else {
		    points = new Point[pts.length];
		    for(int i = 0;i< pts.length;i++) {
			points[i] = pts[i];
		    }
		    sortingAlgorithm = algo;
		}
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		sortingAlgorithm = algo;
		
		try{
		    File file = new File(inputFileName);
		    Scanner scrn = new Scanner(file);
		    int length = 0;
		    while(scrn.hasNext()) {
			scrn.nextInt();
			length++;
		    }
		    scrn.close();
		    if(length %2 != 0) {
			throw new InputMismatchException();
		    }
		    points = new Point[length/2];
		    Scanner scrn2 = new Scanner(file);
		    int i =0;
		    while(scrn2.hasNext()) {
			int x = scrn2.nextInt();
			int y = scrn2.nextInt();
			points[i] = new Point(x,y);
			i++;
		    }
		    scrn2.close();
		}catch(FileNotFoundException e) {
		   throw new FileNotFoundException();
		}
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @throws FileNotFoundException
	 * @return
	 */
	public void scan()throws FileNotFoundException
	{
		// TODO  
		AbstractSorter aSorter = null; 
		long start= 0;
		long end = 0;
		long totalx = 0;
		long totaly = 0;
		int x = 0;
		int y = 0;
		
		// create an o\bject to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
		if(sortingAlgorithm.equals(Algorithm.SelectionSort)) {
		    aSorter = new SelectionSorter(points);
		}else if(sortingAlgorithm.equals(Algorithm.InsertionSort)) {
		    aSorter = new InsertionSorter(points);
		}else if(sortingAlgorithm.equals(Algorithm.MergeSort)) {
		    aSorter = new MergeSorter(points);
		}if(sortingAlgorithm.equals(Algorithm.QuickSort)) {
		    aSorter = new QuickSorter(points);
		}
		aSorter.setComparator(0);
		start = System.nanoTime();
		aSorter.sort();
		end = System.nanoTime();
		totalx = end - start;
		
		x = aSorter.getMedian().getX();
		
		aSorter.setComparator(1);
		start = System.nanoTime();
		aSorter.sort();
		end = System.nanoTime();
		totaly = end-start;
		
		y = aSorter.getMedian().getY();
		scanTime = totalx +totaly;
		
		medianCoordinatePoint = new Point(x,y);
		writeMCPToFile();
		
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
	    String str ="";
	    str += sortingAlgorithm.name();
	    str+="\t";
	    str+= points.length;
	    str+= "\t";
	    str+= scanTime;
	    return str; 
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{	
	    	String str ="MCP: ";
	    	str +="("+medianCoordinatePoint.getX()+","+ medianCoordinatePoint.getY() +")";
		return str; 
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		FileOutputStream output = new FileOutputStream("output"+sortingAlgorithm+".txt");
		PrintWriter print = new PrintWriter(output);
		print.println(this);
		print.close();
			
	}	

	

		
}
