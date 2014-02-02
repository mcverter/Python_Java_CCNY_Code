/* Name: Mitchell Verter
 * For:  Algorithms
 * File: Statistician.java
 * 
 * Objects:  (1) Statistician extends ArrayList
 *            
 *  This file contains the classes and the code
 *  necessary for keeping track of the results produced
 *  by experiments, and for calculating the mean and
 *  standard deviation of these results
 */


package Algorithms.CCNY.Statistician;
import java.util.ArrayList;

public class Statistician extends ArrayList <Number>
{
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	public static void printHeader()
	{
		System.out.println ("MEAN  \t\t\tStandard Deviation ");
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	public void printRow()
	{
		System.out.printf("%10.1f \t  %10.1f\t \n", //%s\n", 
				calculateMean(), calculateStandardDeviation()); //, allValues());
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	String allValues()
	{
		String result="";
		for (int i = 0; i < size(); i++)
		{
			Number value = get(i);
			result += value + "\t";
		}
		return result;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	double calculateMean()
	{
		double total = 0;
		for (int i = 0; i < size(); i++)
		{
			Number value = get(i);
			total += value.doubleValue();
		}
		return total/size();		
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */

/* 	Step 1: Find the mean.
 
	Step 2: Subtract the mean from each value and square the differences.
	Step 3: Find the sum of the squares.
	Step 4: Divide the sum by the number of data values minus one.
	Step 5: Take the square root of the answer.
*/
	double calculateStandardDeviation()
	{
		double mean = calculateMean();
		double sumOfSquareDifferences = 0;

		double value, difference;
		for (int i = 0; i < size(); i++)
		{
			value = get(i).doubleValue();
			difference = value - mean;
			sumOfSquareDifferences += Math.pow(difference, 2);
		}
		return Math.sqrt (sumOfSquareDifferences/(size()-1));
	}
}
