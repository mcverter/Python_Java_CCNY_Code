/* Name: Mitchell Verter
 * For:  Algorithms
 * File:  ContinuousKnapsack.java
 * 
 * Objects:  (1) Continuous Knapsack
 *            
 *  This file contains the classes and the code
 *  necessary for executing our solution to the
 *  given continuous knapsack problem
 */

package Algorithms.CCNY.ContinuousKnapsack;

public class ContinuousKnapsack {

	// the following values are taken from 
	// the assignment
	double KnapsackCapacity = 25;
	final int NUMBER_OF_CHOICES = 5;
	double [] amount = {12, 6, 17, 32, 23};
	double [] weight = {18, 15, 28, 50, 47};

	// for calculating the density of each item
	double [] density = new double [NUMBER_OF_CHOICES];

	// for keeping track of what has been selected
	double [] selection = new double [NUMBER_OF_CHOICES];

	/* 
	 * METHOD: CONSTRUCTOR ContinuousKnapsack()
	 * POSTCONDITIONS: The density array is initialized 
	 */
	ContinuousKnapsack()
	{
		for (int i = 0; i< NUMBER_OF_CHOICES; i++)
		{
			density[i] = weight[i]/amount[i];
		}
	}

	/* 
	 * METHOD: void packKnapsack()
	 * POSTCONDITIONS: Determines which the most dense
	 *       item is and fills up the bag from that item
	 *       until the knapsack is completely filled 
	 */
	void packKnapsack()
	{
		while (KnapsackCapacity > 0)
		{
			int selected = findHighestDensity();
			fillFromSelection(selected);
		}
	}

	/* METHOD: int findHighestDensity()
	 * POSTCONDITIONS: Finds the index of item
	 *     which is most dense and which has not 
	 *     been already used up 
	 */
	int findHighestDensity()
	{

		double lowDensity = 1000;
		int lowIndex = -1;

		for(int i = 0; i < NUMBER_OF_CHOICES; i++)
		{
			if ((density[i] <= lowDensity) &&
					(amount[i] > 0))
			{
				lowIndex = i;
				lowDensity = density[lowIndex];
			}
		}
		return lowIndex;
	}

	/* METHOD: int findHighestDensity()
	 * POSTCONDITIONS: Fills the knapsack with contents
	 *     from the item at the given index.  
	 */
	void fillFromSelection(int selected)
	{
		double amt = amount[selected];
		double amountToTake =
				Math.min(amt, KnapsackCapacity);
		selection[selected] = amountToTake; 
		amount[selected] -= amountToTake;
		KnapsackCapacity -= amountToTake;
	}


	/* METHOD: void printResults()
	 * POSTCONDITIONS: Outputs the answer to the 
	 *    continuous knapsack problem 
	 */
	void printResults ()
	{
		double total=0;
		
		System.out.println("We have selected the following:");
		
		for(int i = 0; i < NUMBER_OF_CHOICES; i++)
		{
			System.out.printf("Item Number %d:  Volume: %.4f Weight: %.4f\n", 
					i, selection[i], selection[i] * density[i]);
			total += (selection[i] * density[i]);
		}

		System.out.printf("The total weight is %.4f.\n", total);
	}

	/* METHOD: void main (String args[])
	 * POSTCONDITIONS: Creates a new ContinuousKnapsack,
	 *       packs it, and prints the result.  
	 */
	public static void main (String args[])
	{
		ContinuousKnapsack ck = new ContinuousKnapsack();
		ck.packKnapsack();
		ck.printResults();
	}
}
