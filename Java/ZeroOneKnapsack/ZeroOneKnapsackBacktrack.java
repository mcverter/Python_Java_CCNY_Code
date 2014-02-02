/* Name: Mitchell Verter
 * For:  Algorithms
 * File:  ZeroOneKnapsackBacktrack.java
 * 
 * Objects:  (1) ZeroKnapsackBacktrack
 *           (2) BackKnapSackItem
 *           
 *  This file contains the classes and the code
 *  necessary for executing Question #31, in which
 *  we are asked to develop code that provides a
 *  backtracking programming algorithm for the 0-1 knapsack problem
 */


package Algorithms.CCNY.ZeroOneKnapsack;
import java.util.Arrays;

import Algorithms.CCNY.Statistician.Statistician;

public class ZeroOneKnapsackBacktrack 
{
	final int MAXWEIGHT = 12;
	
	int maxProfit = 0;
	int currentWeight = 0;

	
	BackKnapSackItem [] items = 
		{  
			new BackKnapSackItem(20,2,10),
			new BackKnapSackItem(30,5,6),
			new BackKnapSackItem(35,7,5),
			new BackKnapSackItem(12,3,4),
			new BackKnapSackItem(3,1,3),
		};
	
	boolean [] tempSet = new boolean [items.length];
	boolean [] finalSet = new boolean [items.length];
		
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void setItems(BackKnapSackItem [] items)
	{
		this.items = items;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	boolean lastItem(int currentItem)
	{
		return (currentItem == (items.length - 1));
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void printSet (boolean [] set)
	{
		int currentWeight = 0;
		int currentPrice = 0;
		
		int totalWeight = 0;
		int totalPrice = 0;

		BackKnapSackItem item;
		
		for (int i = 0; i<set.length; i++)
		{
			if (set[i])
			{
				item = items[i];
				currentWeight = item.getWeight();
				currentPrice = item.getPrice();
				System.out.printf("\tIncluding #%d, weight %d, price %d.  \n", 
						i, currentWeight, currentPrice);
				totalWeight += currentWeight;
				totalPrice += currentPrice;
			}
		}
		
		System.out.printf("The total weight is %d.  The total price is %d\n\n", totalWeight, totalPrice);		
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	boolean isPromising (int currentIndex, int currentProfit, int currentWeight)
	{

		int maxTestWeight;
		int maxTestValue;

		int nextIndex;
		BackKnapSackItem nextItem;

		if (currentWeight >= MAXWEIGHT)
		{
			return false;
		}
		else 
		{
		
			maxTestValue = currentProfit;
			maxTestWeight = currentWeight;

			nextIndex = currentIndex +1;
			
			while ((nextIndex < items.length)
					&& (maxTestWeight + items[nextIndex].getWeight()) < MAXWEIGHT)
			{
				nextItem = items[nextIndex];
				maxTestWeight += nextItem.getWeight();
				maxTestValue += nextItem.getPrice();
				nextIndex++;
			}
			if (nextIndex < items.length)
			{
				nextItem = items[nextIndex];
				maxTestValue = maxTestValue + 
						(MAXWEIGHT-maxTestWeight) *(nextItem.getDensity());
			}
			
			return (maxTestValue > maxProfit);
		}
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	public void knapsack (int currentIndex, int currentProfit, int currentWeight)
	{
		if ((currentWeight <= MAXWEIGHT) && 
				(currentProfit > maxProfit))
		{
			maxProfit = currentProfit;
			finalSet = tempSet.clone();
			System.out.print("Here is the current maximum set: \n");
			printFinalSet();			
		}
		
		if (isPromising(currentIndex, currentProfit, currentWeight) && 
				!lastItem(currentIndex))
		{
			System.out.print("This set is promising: \n");
			printTempSet();	
			int nextIndex = currentIndex+1;
			BackKnapSackItem nextItem = items [nextIndex];

			tempSet[nextIndex] = true;	
			knapsack(nextIndex, 
					currentProfit + nextItem.getPrice(),
					currentWeight + nextItem.getWeight());
			
			tempSet [nextIndex] = false;
			knapsack(nextIndex, currentProfit, currentWeight);
		}
		
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void printFinalSet()
	{
		printSet(finalSet);
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void printTempSet()
	{
		printSet(tempSet);
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	ZeroOneKnapsackBacktrack()
	{
		Arrays.sort(items);
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	public static void main(String args[])
	{
		ZeroOneKnapsackBacktrack zokb = new ZeroOneKnapsackBacktrack();
	 	zokb.knapsack(-1, 0, 0);
	
		System.out.print("The final configuration is : \n");
		zokb.printFinalSet();
	}
}

class BackKnapSackItem implements Comparable <BackKnapSackItem>
{
	int mPrice, mWeight, mDensity;
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	BackKnapSackItem(int price, int weight, int density)
	{
		mPrice = price;
		mWeight = weight;
		mDensity = density;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	BackKnapSackItem (int price, int weight)
	{
		mPrice = price;
		mWeight = weight;
		mDensity = mPrice/mWeight;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	int getPrice()
	{
		return mPrice;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	int getWeight()
	{
		return mWeight;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	int getDensity()
	{
		return mDensity;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	public int compareTo(BackKnapSackItem other)
	{
		return other.mDensity - mDensity;
	}
}

