package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ...
    	int[] arrx = new int[points.length];
	int[] arry = new int[points.length];
    	
    	
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "selection sort";
		for(int i = 0; i< pts.length;i++) {
		    arrx[i] = pts[i].getX();
		    arry[i] = pts[i].getY();
		}
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
	    for(int i = 0; i< points.length;i++) {
		int minIndexX = i;
		int minIndexY = i;
		
		for(int j = i+1; j< points.length;j++) {
		    if(arrx[minIndexX]> arrx[j]) {
			minIndexX = j;
		    }
		    if(arry[minIndexY]> arry[j]) {
			minIndexY = j;
		    }
		}
		 int tempX = arrx[minIndexX];
		 int tempY = arry[minIndexY];
		 arrx[minIndexX] = arrx[i];
		 arrx[i] = tempX;
		 arry[minIndexY] = arry[i];
		 arry[i] = tempY;
		 
	    }
	   
	   
		
	}	
}
