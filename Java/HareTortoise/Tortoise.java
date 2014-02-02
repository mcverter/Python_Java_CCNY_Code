// Name: Mitchell Verter

// File: Tortoise.java
// Class:  Tortoise
// The Tortoise class inherits from Animal
//  It overrides the move() function

public class Tortoise extends Animal
{
	@Override
	int move()
	{
		int moveType = moveGenerator.nextInt(10);
		switch (moveType)
		{
		case 0 :
		case 1 : 
		case 2 :
		case 3 :
		case 4 :
			return 3;
		case 5 :
		case 6 :
			return -6; 
		case 7 :
		case 8 :
		case 9 :
			return 1;
		}
		return 6000; // this should never happen
	}
}
