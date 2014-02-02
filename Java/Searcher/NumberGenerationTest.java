package Algorithms.CCNY.Searcher;
import java.util.Random;

public class NumberGenerationTest 
{

	public static void main(String args[])
	{
	Random rng = new Random();
	int MAX = 5;
	int [] results = new int[MAX+1];
	int NUMSEARCHES = 10000;
	int MAXSEARCH = (MAX * (MAX+1))/2 ;

	for (int i=0; i < NUMSEARCHES; i++)
	{
		int val = rng.nextInt(MAXSEARCH);
		int index = (int) Math.ceil 
				((Math.sqrt(2*val + 0.25) - 0.5));
		results[index]++;
	//	System.out.println("Max is " + index);
	}
	results[MAX] += results[0];
	System.out.println("\nResults Array:");
	for (int j=1; j<= MAX; j++)
	{
		System.out.print(results[j] + " ");
	}
	System.out.println("\nProbabilityies:");
	for (int j=1; j<= MAX; j++)
	{
		System.out.printf("%f  ", (double) results[j]/NUMSEARCHES);
	}

	System.out.println("\nProbabilityies:");
	for (int j=1; j<= MAX; j++)
	{
		System.out.printf("%f  ", (double) MAX * (MAX+1) * results[j]/(NUMSEARCHES*2)) ;
	}

	}
}
