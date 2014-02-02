// Name: Mitchell Verter

// File: Animal.java
// Class:  Animal
// The Animal class is an abstract class.  
// Every subclass must redefine the move() function
// The Animal class provides a Random number generator.  

import java.util.Random;

abstract class Animal {
	
	static Random moveGenerator = new Random(System.currentTimeMillis());

	int move()
	{
		return 0;
	}
}
