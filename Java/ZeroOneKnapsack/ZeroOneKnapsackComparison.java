package Algorithms.CCNY.ZeroOneKnapsack;
import java.util.Random;


public class ZeroOneKnapsackComparison {

	ZeroOneKnapsackBacktrack backtracker = new ZeroOneKnapsackBacktrack();
	ZeroOneKnapsackDynamic dynamic = new ZeroOneKnapsackDynamic();
	Random rng = new Random();
	DynKnapSackItem [] dynamicItems;
	BackKnapSackItem [] backtrackItems;
	
	void initializeItemArrays(int numItems)
	{
		int density;
		int weight;
		int value;
	
		dynamicItems = new DynKnapSackItem[numItems];
		backtrackItems = new BackKnapSackItem[numItems];
		
		for (int i=0; i<numItems;i++)
		{
			density = rng.nextInt(50) + 1;
			weight = rng.nextInt(50) + 1;
			value = density*weight;
			dynamicItems[i] = new DynKnapSackItem(value, weight);
			backtrackItems[i] = new BackKnapSackItem(value, weight);
		}
	}
	public static void main(String args[])
	{
		
	}
}
