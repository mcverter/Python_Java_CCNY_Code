/* Name: Mitchell Verter
 * For:  Algorithms
 * File:  ZeroOneKnapsackDynamic.java
 * 
 * Objects:  (1) ZeroKnapsackDynamic
 *           (2) DynKnapSackItem
 *           
 *  This file contains the classes and the code
 *  necessary for executing Question #32, in which
 *  we are asked to develop code that provides a
 *  dynamic programming algorithm for the 0-1 knapsack problem
 */

package Algorithms.CCNY.ZeroOneKnapsack;
import Algorithms.CCNY.Statistician.Statistician;

public class ZeroOneKnapsackDynamic 
{

	/* Items array intialized to specifications 
	 * given in question #30
	 */
	DynKnapSackItem [] items = 
		{  
			new DynKnapSackItem(0,0),
			new DynKnapSackItem(20,2),
			new DynKnapSackItem(30,5),
			new DynKnapSackItem(35,7),
			new DynKnapSackItem(12,3),
			new DynKnapSackItem(3,1)
		};

	
	// maximum weight of the sack
	int MAXWEIGHT = 12;

	
	// for keeping track of the price calculated thus far.
	int globalMaxPrice = 0;
	
	// Array for memoizing prices
	int [][] tempPrices = new int [items.length] [MAXWEIGHT+1];
	
	boolean [] keep = new boolean [items.length];
	
	/* METHOD:  void setItems(DynKnapSackItem [] items)
	 * PRECONDITIONS:
	 * POSTCONDITIONS: used to override the item list
	 */
	void setItems(DynKnapSackItem [] items)
	{
		this.items = items;
	}

	/* 
	 * METHOD: 	void initializeTopRow()
	 * POSTCONDITIONS: Initializes first row to 0s.
	 */
	void initializeTopRow()
	{
		for (int j = 0; j<MAXWEIGHT; j++)
		{
			tempPrices[0][j] = 0;
		}
	}
	
	/* 
	 * METHOD: 	void initializeFirstColumn()
	 * POSTCONDITIONS: initializes first column to 0s
	 */
	void initializeFirstColumn()
	{
		for (int i = 0; i< tempPrices.length; i++)
		{
			tempPrices[i][0] = 0;
		}
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void calculateMaxPrice (int currentItem, int totalPossibleWeight)
	{

		// V[i,w] = max V[i-1, w], vi + V [i-1, w-wi]
		DynKnapSackItem dksi = items[currentItem];
		int itemWeight = dksi.getWeight();
		int itemPrice = dksi.getPrice();
		int price = 0;
		
		if (itemWeight > totalPossibleWeight)
		{
			price = tempPrices[currentItem-1][totalPossibleWeight];
		}
		else
		{
			price = Math.max(
					tempPrices[currentItem-1][totalPossibleWeight],
					itemPrice + tempPrices
						[currentItem-1][totalPossibleWeight - itemWeight]);
		}
		tempPrices [currentItem][totalPossibleWeight] = price;
		
		if (price > globalMaxPrice)
		{
			globalMaxPrice = price;
			System.out.println("Global Maximum Price is now " + globalMaxPrice);
		}
	
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void calculateAllPrices()
	{
		for (int i = 1; i < tempPrices.length; i++)
		{
			for (int j = 1; j <= MAXWEIGHT; j++)
			{
				calculateMaxPrice(i, j);
			}
		}
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void printAllPrices()
	{
		for (int i = 0; i < tempPrices.length; i++)
		{
			for (int j = 0; j <= MAXWEIGHT; j++)
			{
				System.out.printf("%2d  ", tempPrices[i][j]);
			}
			System.out.println();
		}
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	public static void main(String[] args)
	{
		ZeroOneKnapsackDynamic zoksd = new ZeroOneKnapsackDynamic();

		zoksd.initializeTopRow();
		zoksd.calculateAllPrices();		
		zoksd.printAllPrices();
		
	}
}

class DynKnapSackItem  
{
	int mPrice, mWeight;
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	DynKnapSackItem(int price, int weight)
	{
		mPrice = price;
		mWeight = weight;
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

}

