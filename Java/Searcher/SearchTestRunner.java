/* Name: Mitchell Verter
 * For:  Algorithms
 * File: SearchTestRunner.java
 * 
 * Objects:  (1) SearchTestRunner
 *            
 *  This file contains the classes and the code
 *  necessary for testing the search algorithms
 *  designed in the file Searcher.java
 */



package Algorithms.CCNY.Searcher;
import java.util.Random;
import java.util.Arrays;
import Algorithms.CCNY.Statistician.Statistician;;

public class SearchTestRunner 
{
	// Constants for initializing data
	final static int DEFAULT_SIZE = 1000;
	final int MAX_VALUE = 10000;

	// constants for running tests
	final int NUMBER_OF_SEARCHES = 1000;
	final static int MAX_SEARCH_VALUE = 10500;
	
	// for initializing arrays
	Random numberGenerator;
	boolean [] usedValues;

	//  data set
	int allValues [];

	// For testing the various search algorithms
	SequentialSearcher unOrderedSequential;	
	OrderedSearcher orderedSequential;
	TenSearcher orderedTen;
	InterchangePreviousSearcher interchangePrevious;
	InterchangeTopSearcher interchangeTop;

	// for keeping a running total of the number of searches
	int unOrderedSequentialTotal, orderedSequentialTotal, orderedTenTotal,
	interchangePreviousTotal, interchangeTopTotal;

	// for keeping statistics on the algorithms -- per trial
	Statistician unorderedStats, orderedStats, orderedTenStats, 
		interchangePreviousStats, interchangeTopStats;
	
	// for keeping statistics on the algorithms -- per search
	Statistician unorderedPerStats, orderedPerStats, orderedTenPerStats, 
	interchangePreviousPerStats, interchangeTopPerStats;


	/* 
	 * METHOD:  Constructor
	 * POSTCONDITIONS: 
	 */
	SearchTestRunner()
	{
		numberGenerator = new Random(System.currentTimeMillis());
		initializeValuesArray();

		unOrderedSequential = new SequentialSearcher(allValues.clone());;		
		orderedSequential = new OrderedSearcher(allValues.clone());
		orderedTen = new TenSearcher(allValues.clone());
		interchangePrevious = new InterchangePreviousSearcher
					(allValues.clone());
		interchangeTop = new InterchangeTopSearcher
					(allValues.clone());



		unOrderedSequentialTotal = orderedSequentialTotal = orderedTenTotal =
				interchangePreviousTotal = interchangeTopTotal = 0;

		unorderedStats = new Statistician();
		orderedStats = new Statistician();
		orderedTenStats = new Statistician(); 
		interchangePreviousStats = new Statistician();
		interchangeTopStats = new Statistician();


		unorderedPerStats = new Statistician();
		orderedPerStats = new Statistician();
		orderedTenPerStats = new Statistician(); 
		interchangePreviousPerStats = new Statistician();
		interchangeTopPerStats = new Statistician();

	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void printArrays()
	{
		System.out.println("UnOrdered:");
		unOrderedSequential.printArray();
		System.out.println("Ordered:");
		orderedSequential.printArray();
		System.out.println("Ten:");
		orderedTen.printArray();
		System.out.println("Previous:");
		interchangePrevious.printArray();
		System.out.println("Top:");
		interchangeTop.printArray();

	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */

	void initializeValuesArray ()
	{
		allValues = new int[DEFAULT_SIZE];

		boolean [] usedValues = new boolean [MAX_VALUE];

		boolean newKeyFound;
		int generatedNumber;
		for (int i = 0; i < DEFAULT_SIZE; i++)
		{
			newKeyFound = false;

			while (newKeyFound != true)
			{
				generatedNumber = numberGenerator.nextInt(MAX_VALUE);	
				if(usedValues[generatedNumber] != true)
				{
					newKeyFound = true;

					usedValues[generatedNumber] = true;
					allValues [i] = generatedNumber;
				}
			}      // unsorted array initialized

		}
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	void runTests()
	{
		int key, numSearch;	

		for (int i=0; i < NUMBER_OF_SEARCHES; i++)
		{
			key = numberGenerator.nextInt(MAX_SEARCH_VALUE);

			numSearch = unOrderedSequential.searchForValue(key);
			unorderedPerStats.add(numSearch);
			unOrderedSequentialTotal += numSearch;

			numSearch = orderedSequential.searchForValue(key);
			orderedPerStats.add(numSearch);
			orderedSequentialTotal += numSearch;
			
			numSearch = orderedTen.searchForValue(key);
			orderedTenPerStats.add(numSearch);
			orderedTenTotal += numSearch;

			numSearch = interchangePrevious.searchForValue(key); 
			interchangePreviousPerStats.add(numSearch);
			interchangePreviousTotal += numSearch; 

			numSearch = interchangeTop.searchForValue(key);
			interchangeTopPerStats.add(numSearch);
			interchangeTopTotal += numSearch;

			

		}

		unorderedStats.add(unOrderedSequentialTotal);
		orderedStats.add(orderedSequentialTotal);
		orderedTenStats.add(orderedTenTotal); 
		interchangePreviousStats.add(interchangePreviousTotal);
		interchangeTopStats.add(interchangeTopTotal);

	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	public void resetCounters()
	{
		unOrderedSequentialTotal = orderedSequentialTotal =orderedTenTotal =
				interchangePreviousTotal= interchangeTopTotal = 0;
		
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	public void printAllStats()
	{
		Statistician.printHeader();
		System.out.println("Unordered Sequential: "); 
		unorderedStats.printRow();
		System.out.println("Ordered Sequential: "); 
		orderedStats.printRow();
		System.out.println("Search by Ten: ");
		orderedTenStats.printRow();
		System.out.println("Switch with Previous "); 
		interchangePreviousStats.printRow();
		System.out.println("Switch with Top ");
		interchangeTopStats.printRow();
		

		System.out.println("\n\n==========PER STATS=========\n");
		Statistician.printHeader();
		System.out.println("Unordered Sequential: "); 
		unorderedPerStats.printRow();
		System.out.println("Ordered Sequential: "); 
		orderedPerStats.printRow();
		System.out.println("Search by Ten: ");
		orderedTenPerStats.printRow();
		System.out.println("Switch with Previous "); 
		interchangePreviousPerStats.printRow();
		System.out.println("Switch with Top ");
		interchangeTopPerStats.printRow();

	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS: 
	 */
	public static void main(String args[])
	{


		SearchTestRunner str = new SearchTestRunner();
		for (int i=0; i < 100; i++)
		{
			str.resetCounters();
			str.runTests();
		}
		str.printAllStats();
	}
}



