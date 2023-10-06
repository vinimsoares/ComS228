package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Vinicius Malaman Soares 

 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *@author Vinicius Malaman Soares 
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 * @author Vinicius Malaman Soares 
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "MergeSort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * @author Vinicius Malaman Soares 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points); 
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 * @author Vinicius Malaman Soares 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int size = pts.length;
		if(size <= 1) {
		    return;
		}else {
		int half = size/2;
		Point[] leftSide = new Point[half];
		Point[] rightSide = new Point[size - half];
		
		for(int i =0; i< half; i++) {
		    leftSide[i] = pts[i];
		}
		for(int i = 0;i<rightSide.length;i++ ) {
		    rightSide[i] = pts[i+half];
		}
		
		mergeSortRec(leftSide);
		mergeSortRec(rightSide);
		
		merge(pts,leftSide,rightSide);
		}
	}

	
	// Other private methods if needed ...
	private void merge(Point[] arr, Point[] left, Point[] right) {
	    int leftMid = left.length;
	    int rightMid = right.length;
	    int i =0;
	    int k = 0;
	    int j = 0;
	    while(i<leftMid && j <rightMid) {
		if(pointComparator.compare(left[i], right[j])<0) {
		    arr[k] = left[i];
		    i++;
		}else {
		    arr[k] = right[j];
		    j++;
		}
		k++;
	    }
	    while(i< leftMid) {
		arr[k] = left[i];
		i++;
		k++;
	    }
	    while(j < rightMid) {
		arr[k] = right[j];
		j++;
		k++;
	    }
	}

}
