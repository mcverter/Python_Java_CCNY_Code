// Name: Mitchell Verter

// File: Hare.java
// Class:  Hare
// The Hare class inherits from Animal
//  It overrides the move() function

public class Hare extends Animal
{

	@Override
	int move()
	{
		int moveType = moveGenerator.nextInt(10);
		switch (moveType)
		{
		case 0:
		case 1 : 
			return 0;
		case 2:
		case 3: 
			return 9;
		case 4:
			return -12;
		case 5:
		case 6:
		case 7:
			return 1; 
		case 8:
		case 9:
			return -2; 
		}
		return 6000; // this should never happen
	}
}
