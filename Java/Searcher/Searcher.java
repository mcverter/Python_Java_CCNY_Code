/* Name: Mitchell Verter
 * For:  Algorithms
 * File:  Searcher.java
 * 
 * Objects:
 *   (1) Searcher
 *   (2) SequentialSearcher
 *   (3) OrderedSearcher
 *   (4) TenSearcher
 *   (5) DynamicSearcher
 *   (6) InterchangePreviousSearcher
 *   (7) InterchangeTopSearcher
 *            
 *  This file contains the classes and the code
 *  necessary for comparing various search algorithms.
 */


package Algorithms.CCNY.Searcher;
import java.util.Arrays;
import java.util.Random;

/*
 * CLASS: Searcher
 * Abstract superclass for all search algorithms * 
 */
abstract public class Searcher 
{
	// constants for implementing searches
	final int LARGEST_SEARCH_VALUE = SearchTestRunner.MAX_SEARCH_VALUE;
	final int DEFAULT_SIZE = SearchTestRunner.DEFAULT_SIZE;
	
	// return value if key is not found
	final int NOT_FOUND = DEFAULT_SIZE +1;

	// internal representation of keys
	int [] myArray;
	
	// total comparisons needed for search
	int totalComparisons;
	
	/* 
	 * METHOD: CONSTRUCTOR Searcher(int[])
	 * POSTCONDITIONS: myArray is initialized 
	 */
	Searcher (int[] array)
	{
		myArray = array;		
	}
	/* 
	 * METHOD: abstract int searchForValue(int value);
	 * POSTCONDITIONS: This is the primary function of
	 *      the Searcher class and all of its subclasses.
	 *      Each subclass defines how to implement search.
	 */
	abstract public int searchForValue(int value);

	/* 
	 * METHOD: 	void printArray()
	 * POSTCONDITIONS: prints out contents of the array.
	 *    Useful for debugging
	 */
	public void printArray()
	{
		for (int i : myArray)
		{
			System.out.print(i + " ");
		}
		System.out.println();
	}
}

class SequentialSearcher extends Searcher
{
	/* 
	 * METHOD: Constructor
	 * POSTCONDITIONS: Calls superclass constructor
	 */
	SequentialSearcher (int[] array)
	{
		super(array);
	}
	
	/* 
	 * METHOD:  int searchForValue(int value)
	 * POSTCONDITIONS: Iterates through each element
	 *     of the array looking for the value.  If 
	 *     the value is not found, a constant 
	 *     NOT_FOUND is returned
	 */
	@Override
	public int searchForValue(int value)
	{
		for (int i=0 ; i<myArray.length; i++)
		{
			if (value == myArray[i])
			{
				return i;
			}
		}
		return NOT_FOUND;
	}	
}

class OrderedSearcher extends Searcher
{
	/* 
	 * METHOD: Constructor
	 * POSTCONDITIONS: Calls superclass
	 *     constructor and sorts the 
	 *     internal array
	 */
	OrderedSearcher (int[] array)
	{
		super(array);
		Arrays.sort(myArray);
	}
	
	/* 
	 * METHOD: int searchForValue(int value)
	 * POSTCONDITIONS: Iterates through the array
	 *     looking for the value.  When the value is found
	 *     or when the value is passed in the array, the
	 *     number of searches is returned
	 */
	@Override
	public int searchForValue(int value)
	{
		for (int i=0 ; i < myArray.length; i++)
		{
			if (value <= myArray[i])
			{
				return i + 1; // we return the same value whether or not it is found
			}
		}
		return NOT_FOUND;
	}
}
class TenSearcher extends OrderedSearcher 
{
	/* 
	 * METHOD: Constructor
	 * POSTCONDITIONS: Calls superclass constructor
	 */
	TenSearcher (int[] array)
	{
		super(array);
	}
	
	/* 
	 * METHOD: int searchForValue(int value)
	 * POSTCONDITIONS: Iterates through every tenth
	 *    key in the array looking for value until it 
	 *    finds or passes that value.  If it passes that
	 *    value, it iterates through the ten-element 
	 *    interval looking for a match
	 */
	@Override
	public int searchForValue(int value)
	{
		int i;
		int ten_i;
		
		for (i = ten_i = 0; 
					ten_i <myArray.length && (value > myArray[ten_i]); 
					i++, ten_i += 10)
		{}
		if (ten_i >= myArray.length) // we've indexed off the top of the array
		{
			return i + 1;
		}
		
		else if (myArray[ten_i] == value) // we found our value on the first round
		{
			return i + 1;
		}
		else  // value is smaller than currently indexed key
		{
			if (ten_i == 0)  // value is smaller than smallest key in array
			{
				return 1;
			}
			
			// look through the ten-element interval
			for (int j = ten_i-9; j<ten_i; j++)
			{
				if (value <= myArray[j])
				{
					return (i +1) + 
							((j-(ten_i-10)));
				}
			}
			// not found
			return (i+1)+9;
		}
	}
}


abstract class DynamicSearcher extends SequentialSearcher 
{
	// a permanent, unchanging record of the original keys
	int [] permArray;
	
	// Needed for simulating searches and calculating
	//  probability distribution
	final static Random rng = new Random();
	final static int DEFAULT_SIZE = SearchTestRunner.DEFAULT_SIZE;
	final static int WEIGHT_TOTAL = (DEFAULT_SIZE) *(DEFAULT_SIZE +1)/2;
	
	/* 
	 * METHOD: Constructor
	 * POSTCONDITIONS: initializes dynamic, changing array
	 *     as well as the permanent, unchanging array
	 */
	DynamicSearcher (int[] array)
	{
		super(array);
		permArray = array.clone();
	}
		
	/* 
	 * METHOD: int searchForValue(int value)
	 * POSTCONDITIONS: We first initiate a 
	 *     simulatedSearchForValue.  If we receive 
	 *     a valid array index from this call,
	 *     we actually iterate through the array and
	 *     look for the value.
	 *        When we find it, we perform a swap.
	 */
	@Override
	public int searchForValue(int value)
	{
		int index = simulateSearchForValue(value);
		if (index != NOT_FOUND && index != 0)
		{
			switchValues (index);
		}
		return index;
	
	}

	
	/* 
	 * METHOD: 	int simulateSearchForValue (int value)
	 * PRECONDITIONS:  This function receives a value 
	 *     up to MAX_VALUE -- currently set at 10500
	 * POSTCONDITIONS: If the argument received is larger
	 *    than the size of the array -- currently 1000 --
	 *    the function returns the constant NOT_FOUND.
	 *      Otherwise, it generates a search key and returns it.
	 */
	int simulateSearchForValue (int value)
	{
		if (value >= DEFAULT_SIZE)
		{
			return NOT_FOUND;
		}
		else 
		{
			int searchValue = generateSearchKey();
			return super.searchForValue(searchValue);
		}
	}


	/* 
	 * METHOD: 	int generateSearchKey()
	 * POSTCONDITIONS: The total number of 
	 *   probabilities in the array is 
	 *   n*(n+1)/2, so we first randomly generate 
	 *   a number within that range.
	 *      We then use an inverse function to convert
	 *   that random number to an array index.
	 *      We use that index to get a value from the
	 *   permanent, unchanging array and use that value
	 *   as our search value.
	 */
	int generateSearchKey()
	{
		int weightedValue = rng.nextInt(WEIGHT_TOTAL); 
		int weightedIndex = (int) Math.ceil 
				((Math.sqrt(2*weightedValue + 0.25) - 0.5));
		weightedIndex--;
		
		// this was found to necessary for several reasons, 
		// one of which is that sqrt of 1 and sqrt of 0
		//  are redundant.
		if (weightedIndex < 0)
		{
			weightedIndex = DEFAULT_SIZE - 1;
		}
		return permArray[weightedIndex];
	}
	
	
	/* 
	 * METHOD: 	abstract void switchValues(int index);
	 * POSTCONDITIONS: Switches two values in the array.
	 *    Different dynamic approaches implement this differently.
	 */
	abstract void switchValues(int index);
}

class InterchangePreviousSearcher extends DynamicSearcher
{
	/* 
	 * METHOD: Constructor
	 * POSTCONDITIONS: Calls superclass constructor
	 */
	InterchangePreviousSearcher (int[] array)
	{
		super(array);
	}
	
	/* 
	 * METHOD: 	void switchValues(int index)
	 * POSTCONDITIONS: switches value with the one 
	 *     at the previous index
	 */
	void switchValues(int index)
	{
		int value = myArray[index];
		int switchIndex = index - 1;
		int switchValue = myArray[switchIndex];
		myArray[switchIndex] = value;
		myArray[index] = switchValue;
	}
}

class InterchangeTopSearcher extends DynamicSearcher 
{
	/* 
	 * METHOD: Constructor
	 * POSTCONDITIONS: Calls superclass constructor
	 */
	InterchangeTopSearcher (int[] array)
	{
		super(array);
	}
	
	/* 
	 * METHOD: 	void switchValues(int index)
	 * POSTCONDITIONS: switches value with the
	 *    one at the top.
	 */
	void switchValues(int index)
	{
		int value = myArray[index];
		int switchIndex = 0;
		int switchValue = myArray[switchIndex];
		myArray[switchIndex] = value;
		myArray[index] = switchValue;
	}
}

