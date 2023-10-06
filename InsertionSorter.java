package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.InputMismatchException;


/**
 *  
 * @author Vinicius Malaman Soares 
 */

/**
 * 
 * This class implements insertion sort.   
 *@author Vinicius Malaman Soares 
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts 
	 * @author Vinicius Malaman Soares  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "InsertionSort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.
	 * @author Vinicius Malaman Soares   
	 */
	@Override 
	public void sort()
	{
		for(int i = 0; i< points.length;i++) {
		    Point key = points[i];
		    int j = i-1;
		    
		    while(j>-1 && pointComparator.compare(points[j], key)>0) {
			points[j+1] = points[j];
			j--;
			
		    }
		    points[j+1] =key;
		} 
	}		
}
