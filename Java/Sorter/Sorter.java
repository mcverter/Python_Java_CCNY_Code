/* Name: Mitchell Verter
 * For:  Algorithms
 * File:  Sorter.java
 * 
 * Objects:
 *   (1) Sorter
 *   (2) MergeSorter
 *   (3) InsertionSorter
 *   (4) MergeSorterWithInsertion
 *   (5) QuickSorter
 *   (6) QuickSorterWithInsertion
 *   (7) ShellSorter
 *   (8) HeapSorter
 *            
 *  This file contains the classes and the code
 *  necessary for comparing various sort algorithms.
 */


package Algorithms.CCNY.Sorter;
import java.util.Random;

public abstract class Sorter {
	
	// for keeping track of the number of operations
	int numComparisons;
	int numSwaps;
	
	/* 
	 * METHOD: void resetCounters() 
	 * POSTCONDITIONS:  resets counters
	 *    for a new trial
	 */
	public void resetCounters()
	{
		numComparisons=numSwaps=0;
	}
	
	/* 
	 * METHOD: 	abstract void sort(int [] array);
	 * POSTCONDITIONS:  each subclass defines 
	 *     how to perform a sort
	 */
	abstract void sort(int [] array);
	
	/* 
	 * METHOD: 	public void printArray(int [] myArray)
	 * POSTCONDITIONS:  for debug prints
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
	 * METHOD: 	int getSwaps()
	 * POSTCONDITIONS:  returns the number of swaps
	 */
	int getSwaps()
	{
		return numSwaps;
	}
	
	/* 
	 * METHOD: 	int getComparisons()
	 * POSTCONDITIONS:  returns the number of comparisons
	 */
	int getComparisons()
	{
		return numComparisons;
	}
	
	/* 
	 * METHOD: 	void swap (int [] array, int i, int j)
	 * POSTCONDITIONS:  exhanges two values within the array
	 *     increments numSwaps by 2.
	 */
	void swap (int [] array, int i, int j)
	{
		int val = array[i];
		array[i] = array[j];
		array[j] = val;
		numSwaps += 2;
	}

}

class MergeSorter extends Sorter
{
	/* 
	 * METHOD: 	void merge(int [] array, int start, int middle, int end)
	 * POSTCONDITIONS:  
	 */
	void merge(int [] array, int start, int middle, int end)
	{		
		int n1 = middle - start +1;
		int n2 = end - middle;


		int [] startArray = new int[n1 + 1];
		int [] endArray = new int[n2 + 1];


		int i, j;
		i = j = 0;
		for (; i<n1; i++)
		{
			startArray[i] = array[start+i];
		}
		startArray[n1] = 1000001;
		for (; j<n2; j++)
		{
			endArray[j] = array[middle+j+1];
		}
		endArray[n2] = 1000001;

		i = j = 0;
		for (int k = start; k <= end; k++)
		{
			if (startArray[i] <= endArray[j])
			{
				array[k] = startArray[i];
				i++;
			}
			else 
			{
				array[k] = endArray[j];
				j++;
			}
			numComparisons++;
			numSwaps++;
		}
	}

	/* 
	 * METHOD: void mergeSort (int [] array, int start, int end)
	 * POSTCONDITIONS:  
	 */
	void mergeSort (int [] array, int start, int end)
	{
		if (start < end)
		{
			int middle = (start + end)/2;
			mergeSort(array, start, middle);
			mergeSort(array, middle+1, end);
			merge(array, start, middle, end);
		}
	}	
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void sort(int[] array)
	{
		mergeSort (array, 0, array.length -1);
	}
} 

class InsertionSorter extends Sorter
{
	
	/* 
	 * METHOD: 	void insertionSort (int[] array, int begin, int end, int interval)
	 * POSTCONDITIONS:  
	 */
	void insertionSort (int[] array, int begin, int end, int interval)

	//	<T extends Number> void insertionSort (T[] array, int begin, int end, int interval)
	{
		int i, j;
		int value;

		i = begin+interval;
		while (i<= end)
		{
			value = array[i];
			j = i - interval;
			while (j >= begin && array[j] >  value)
			{
				array[j+interval] = array[j];
				j -= interval;
				numComparisons++;
				numSwaps++;
			}		
			array[j+interval] = value;
			i += interval;
			numComparisons++;
			numSwaps++;
		}
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void insertionSort (double[] array,  int begin, int end, int interval)

	//	<T extends Number> void insertionSort (T[] array, int begin, int end, int interval)
	{
		int i, j;
		double value;		
		i = begin+interval;
		while (i<= end)
		{
			value = array[i];

			j = i - interval;
			while (j >= begin && array[j] <  value)
			{
				array[j+interval] = array[j];
				j -= interval;
				numComparisons++;
				numSwaps++;
			}		
			array[j+interval] = value;
			i += interval;
			numComparisons++;
			numSwaps++;
		}
	}

	void insertionSort (int[] array, int begin, int end)
	//	<T extends Number> void insertionSort (T[] array, int begin, int end)
	{
		this.insertionSort(array, begin, end, 1);
	}

	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void insertionSort (int [] array, int interval)
//	<T extends Number> void insertionSort (T [] array, int interval)
	{
		insertionSort ( array,  0,  array.length-1, interval);

	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	//	<T extends Number> void insertionSort (T [] array)
	void insertionSort (int [] array)
	{
		insertionSort ( array, 0, array.length-1, 1);
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void sort(int[] array)
	{
		insertionSort(array);
	}
}

class MergeSorterWithInsertion extends MergeSorter
{
	InsertionSorter is;

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	int getSwaps()
	{
		return super.getSwaps() + is.getSwaps();
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	int getComparisons()
	{
		return super.getComparisons() + is.getComparisons();
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	public void resetCounters()
	{	
		super.resetCounters();
		is.resetCounters();
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	void mergeSort (int [] array, int start, int end)
	{
		if (end-start <20)
		{
			is.insertionSort(array, start, end);
		}

		else
		{	
			super.mergeSort(array, start, end);
		}
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	MergeSorterWithInsertion()
	{
		is = new InsertionSorter();
	}
}

class QuickSorter extends Sorter
{

	double [] medianFinder = new double[5];
	int medianFinderCount; // last medianCounter element
	final int ENCODING_NUM = 
			(int) Math.pow(10, Math.ceil((Math.log10(SortTester.DEFAULT_SIZE)) + 1));
	static final Random rng = new Random();
	InsertionSorter is = new InsertionSorter();

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	public void resetCounters()
	{	
		super.resetCounters();
		is.resetCounters();
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	int getSwaps()
	{
		return numSwaps + is.getSwaps();
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	int getComparisons()
	{
		return numComparisons + is.getComparisons();
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	double encodeIndex(int [] array, int index)
	{
		double retValue = array[index] + ((double)index / ENCODING_NUM);
		return retValue;
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	int decodeIndex(double encodedValue)
	{
		double decoded = ((encodedValue - Math.floor(encodedValue)) * ENCODING_NUM);
		return (int) Math.round(decoded);
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	boolean insertIntoMedianFinder(double selection)
	{
		int i;
		for (i = 0; i< medianFinderCount ; i++)
		{
			numComparisons++;
			if (medianFinder[i] == selection)
			{
				return false;
			}
		}
		medianFinder[medianFinderCount++] = selection;
		return true;
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	int choosePartition (int [] array, int start, int end)
	{
		int length = (end - start)  + 1;
		int numFound = 0;
		int nextIndex;

		double selection;

		medianFinderCount = 0;

		if (length > 5)
		{
			numComparisons++;
			while (numFound < 5)
			{
				selection = encodeIndex(array,
						start + rng.nextInt(length));
				if (insertIntoMedianFinder(selection))
				{
					numFound++;
				}
			}
			is.insertionSort(medianFinder, 0, 4, 1);
			return decodeIndex(medianFinder[2]);
		}
		else 
		{
			for (int i = 0; i<length; i++)
			{
				selection = encodeIndex(array, start + i);
				insertIntoMedianFinder(selection);
			}
			is.insertionSort(medianFinder, 0, length-1, 1);
			int median = (length/2 + length%2)-1;
			return decodeIndex(medianFinder[median]);
		}

	}


	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	int partition (int [] array, int start, int end)
	{
		int partitionValue = choosePartition(array, start, end);
		swap(array, partitionValue, end);

		int x = array[end];
		int i = start - 1;

		for (int j = start; j < end ; j++)
		{
			if (array[j] <= x)
			{
				i++;
				if (j != i)
				{
					swap (array, i, j);
				}
				numComparisons++;
			}
			numComparisons++;
		}
		swap (array, i+1, end);
		return i+1;
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void quickSort (int [] array, int start, int end)
	{
		if (start < end)
		{	
			int partitionLocation = partition(array, start, end);
			quickSort (array, start, partitionLocation - 1);
			quickSort (array, partitionLocation + 1, end);		
		}
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void sort(int[] array)
	{
		quickSort(array, 0, array.length - 1);
	}
}

class QuickSorterWithInsertion extends QuickSorter
{
	InsertionSorter is;

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	public void resetCounters()
	{	
		super.resetCounters();
		is.resetCounters();
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	int getSwaps()
	{
		return numSwaps + is.getSwaps();
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	int getComparisons()
	{
		return numComparisons + is.getComparisons();
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	@Override
	void quickSort (int [] array, int start, int end)
	{
		if (end-start <= 20)
		{
			is.insertionSort(array, start, end);
		}
		else
		{	
			super.quickSort(array, start, end);
		}
	}
	
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	QuickSorterWithInsertion()
	{
		is = new InsertionSorter();
	}
}

class ShellSorter extends InsertionSorter
{
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */

	void shellSort(int[] array)
	{
		// KNUTH: gaps = (3^k - 1) / 2, not greater than  N / 3
		// I had a hard time finding general exponentiation functions
		// in the java library 
		// so I am using the natural log E instead.
		int k = (int) Math.floor
				(Math.log(2*(array.length/Math.E)+1)/Math.log(3));		
		int interval;

		while (k > 0)
		{
			interval = (int) (Math.exp(k)) / 2;
			insertionSort(array, interval);		
			k--;

		}
	}

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void sort(int[] array)
	{
		shellSort(array);
	}
}

class HeapSorter extends Sorter
{
	int heapSize;

	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	int parent(int index)
	{
		return (index/2);
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	int left (int index)
	{
		return 2*index;
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	int right(int index)
	{
		return (2*index) + 1;
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void maxHeapify (int [] array, int index)
	{
		int left = left(index);
		int right = right(index);
		int largest;

		if ((left < heapSize) &&
				(array[left]>array[index]))
		{
			largest = left;
		}
		else 
		{
			largest = index;
		}
		numComparisons++;
		if ((right < heapSize) &&
				(array[right]>array[largest]))
		{
			largest = right;
		}
		numComparisons++;
		if (largest != index)
		{
			swap(array, index, largest);
			maxHeapify (array, largest);
		}
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void buildMaxHeap (int [] array)
	{
		heapSize = array.length;

		for (int i = (array.length-1)/2;
				i >=0; i--)
		{
			maxHeapify(array, i);
		}
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void heapSort(int [] array)
	{
		buildMaxHeap(array);

		for (int index = array.length-1; index > 0; index--)
		{
			swap (array, 0, index);
			heapSize--;
			maxHeapify(array, 0);
		}
	}
	/* 
	 * METHOD: 
	 * POSTCONDITIONS:  
	 */
	void sort(int[] array)
	{
		heapSort(array);
	}
}
