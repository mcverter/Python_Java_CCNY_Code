/* Name: Mitchell Verter
 * For:  Algorithms
 * File: SortTester.java
 * 
 * Objects:  (1) SortTester
 *            
 *  This file contains the classes and the code
 *  necessary for testing the sort algorithms
 *  designed in the file Sorter.java
 */



package Algorithms.CCNY.Sorter;
import Algorithms.CCNY.Statistician.Statistician;
import java.util.Random;
import java.util.Arrays;

public class SortTester 
{
	static final int DEFAULT_SIZE = 10000;
	static final int MAX_VALUE = 100000;
	boolean [] usedValues;
	int allValues [];
	Random numberGenerator;

	InsertionSorter inserter;
	MergeSorter merger;
	QuickSorter quicker;
	ShellSorter sheller;
	HeapSorter heaper;
	MergeSorterWithInsertion mergerPlus;
	QuickSorterWithInsertion quickerPlus;

	Statistician inserterOp, mergerOp, quickerOp, shellerOp, heaperOp,
	mergerPlusOp, quickerPlusOp;

	Statistician inserterSwap, mergerSwap, quickerSwap, shellerSwap, heaperSwap,
	mergerPlusSwap, quickerPlusSwap;

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	public void printArray(int [] myArray)
	{
		for (int i : myArray)
		{
			System.out.print(i + " ");
		}
		System.out.println();
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	SortTester()
	{
		inserterOp = new Statistician();
		mergerOp = new Statistician();
		quickerOp = new Statistician();
		shellerOp = new Statistician();
		heaperOp = new Statistician();
		mergerPlusOp = new Statistician();
		quickerPlusOp = new Statistician();
		
		inserterSwap = new Statistician();
		mergerSwap= new Statistician();
		quickerSwap= new Statistician();
		shellerSwap = new Statistician();
		heaperSwap = new Statistician();
		mergerPlusSwap = new Statistician();
		quickerPlusSwap= new Statistician();

		
		numberGenerator = new Random(System.currentTimeMillis());

		initializeValuesArray();
		
		inserter = new InsertionSorter();
		heaper = new HeapSorter();
		sheller = new ShellSorter();
		quicker = new QuickSorter();
		quickerPlus = new QuickSorterWithInsertion();
		merger = new MergeSorter();
		mergerPlus = new MergeSorterWithInsertion();		
		
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void runTests()
	{
		initializeValuesArray();
		int[] insertArray = allValues.clone();
		int [] heapArray = allValues.clone();
		int [] shellArray = allValues.clone();
		int [] quickArray = allValues.clone();
		int [] quickerPlusArray = allValues.clone();
		int [] mergeArray = allValues.clone();
		int [] mergerPlusArray = allValues.clone();
		
		inserter.sort(insertArray);
		heaper.sort(heapArray);
		sheller.sort(shellArray);
		quicker.sort(quickArray);
		quickerPlus.sort(quickerPlusArray);
		merger.sort(mergeArray);
		mergerPlus.sort(mergerPlusArray);

		inserterOp.add(inserter.getComparisons()); 
		mergerOp.add(merger.getComparisons());  
		quickerOp.add(quicker.getComparisons());  
		shellerOp.add(sheller.getComparisons());  
		heaperOp.add(heaper.getComparisons());  
		mergerPlusOp.add(mergerPlus.getComparisons());  
		quickerPlusOp.add(quickerPlus.getComparisons());  
		
		inserterSwap.add(inserter.getSwaps()); 
		mergerSwap.add(merger.getSwaps());
		quickerSwap.add(quicker.getSwaps());
		shellerSwap.add(sheller.getSwaps()); 
		heaperSwap.add(heaper.getSwaps()); 
		mergerPlusSwap.add(mergerPlus.getSwaps()); 
		quickerPlusSwap.add(quickerPlus.getSwaps());		
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void printTwoArrays(int [] A, int [] B)
	{
		for (int i = 1; i<A.length; i++)
		{
			System.out.printf("i=%d.\tA[%d]=%d.\tB[%d]=%d.\n", i, i, A[i], i, B[i]);
		}
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	boolean compareArrays(int [] A, int [] B)
	{
		boolean same;
		for (int i = 1; i<A.length; i++)
		{
			if (A[i] != B[i])
			{
				same = false;
				return same;
			}
		}
		same = true	;
		return same;
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
	public void printAllStats()
	{
		Statistician.printHeader();

		System.out.println("Merge Sort: [Comparisons, Swaps]");
		mergerOp.printRow();
		mergerSwap.printRow();		
		
		System.out.println("Merge Sort Plus Insertion: [Comparisons, Swaps]");
		mergerPlusOp.printRow();
		mergerPlusSwap.printRow();
	

		System.out.println("Insertion Sort: [Comparisons, Swaps]");
		inserterOp.printRow();
		inserterSwap.printRow();

		System.out.println("Shell Sort: [Comparisons, Swaps]");
		shellerOp.printRow();
		shellerSwap.printRow();
				
		System.out.println("Heap Sort: [Comparisons, Swaps]");
		heaperOp.printRow();
		heaperSwap.printRow();	

		System.out.println("Quick Sort: [Comparisons, Swaps]");
		quickerOp.printRow();
		quickerSwap.printRow();
		
		System.out.println("Quick Sort Plus Insertion: [Comparisons, Swaps]");
		quickerPlusOp.printRow();		
		quickerPlusSwap.printRow();
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	public void resetAllCounters()
	{
		inserter.resetCounters();
		heaper.resetCounters();
		sheller.resetCounters();
		quicker.resetCounters();
		quickerPlus.resetCounters();
		merger.resetCounters();
		mergerPlus.resetCounters();		
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	public static void main(String [] args)
	{
		SortTester st = new SortTester();
		for (int i=0; i<100;i++)
		{
			st.runTests();
			st.resetAllCounters();
		}
		st.printAllStats();
	}
}
