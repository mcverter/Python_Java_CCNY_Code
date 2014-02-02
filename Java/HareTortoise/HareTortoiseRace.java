// Name: Mitchell Verter
///
// File: HareTortoiseRace.java
// Class:  HareTortoiseRace
// The HareTortoiseRace class is an instance of the race between the hare and the tortoise.  
//
// The class is responsible for keeping track of the locations of the hare
//  and the tortoise, for figuring out when the race is over, and for telling 
//  the graphics object to depict the race.

import javax.swing.JOptionPane;

public class HareTortoiseRace {

	int raceLength;	
	
	Hare raceHare;         // hare data
	int harePosition;         

	Tortoise raceTortoise;	// tortoise data
	int tortoisePosition;
	
	int bitePosition;    // if there is a bite save it for the graphics object
	
	RaceDrawer raceTrack;   // graphics object
	
	boolean finishLinePassed;  // game over
	
	// METHOD:  constructor(int length)
	// We create a new race of length given in the input
	// We set the initial positions of the hare and the tortoise
	HareTortoiseRace(int length)
	{
		raceLength = length;			
		finishLinePassed = false;
		
		raceHare = new Hare();
		raceTortoise = new Tortoise();
		harePosition = tortoisePosition = 1;

		bitePosition = -1;
		
		raceTrack = new RaceDrawer(raceLength);
		drawBoard();
	}
	
	// METHOD: runTheRace()
	// THis is the main executable thread of the class
	// This starts the race,  plays each turn, and then finishes it
	
	void runTheRace()
	{
		startGame();
		playTurns();
		finishGame();
	}

	// METHOD:  startGame()
	// Announces the start of the game
	void startGame()
	{
		displayMessage ("BANG !!!!! \n AND THEY'RE OFF !!!!!");
	}

	// METHOD:  finishGame()
	// Announces the winner of the game: 
	//  either a tie, the hare, or the tortoise
	void finishGame()
	{
		if (tortoisePosition == raceLength)
		{
			if (harePosition == raceLength)
			{
				displayMessage("It's a tie!");
			}
			else 
			{
				displayMessage("TORTOISE WINS!!! \nYAY!!!");
			}
		}
		else 
		{
			displayMessage ("Hare wins. \nYuch.");
		}
	}
	
	//
	// METHOD:  playTurns()
	// This method loops while the finish line has not been passed, 
	//   pausing a second between each loop
	//
	// For each iteration of the loop, the new positions of the 
	//   tortoise and the hare are calculated.
	//
	// If the tortoise and the hare occupy the same position
	//   (not the final position)
	//   then bitePosition is set to that location
	//
	// Once positions have been calculated, the graphics object
	//   depicts the race track.
	//
	
	void playTurns()
	{
		while(! finishLinePassed)
		{
			bitePosition = -1;
			
			harePosition = getNewPosition(harePosition, raceHare);
			tortoisePosition = getNewPosition(tortoisePosition, raceTortoise); 
			
			if ((harePosition == tortoisePosition) &&
					(harePosition != raceLength))
			{
				bitePosition = harePosition;
			}
			if ((harePosition == raceLength) || 
					(tortoisePosition == raceLength))
			{
				finishLinePassed = true;
			}
			
			try
			{
				Thread.currentThread().sleep(1000);
			}				
			
			catch(InterruptedException ie)
			{
			}
			drawBoard();
		}
	}
	
	//  METHOD:  getNewPostion (int position, Animal racer)
	//  This method calculates the Animal's next position given
	//    its current position and its move() function.
	//
	// If the position is calculated to be before 
	//   the beginning of the track, position is set to 1
	//
	// If the position is calculated to be after
	//   the end of the trace, position is set to raceLength.
	
	int getNewPosition (int position, Animal racer)
	{
		position += racer.move();
		if (position < 1)
		{
			position = 1;
		}
		if (position > raceLength)
		{
			position = raceLength;
		}
		return position;
	}
	
	
	// METHOD drawBoard()
	// THis Method is called by playTurns()
	// It is responsible for drawing the raceTrack
	//  for each round of the race.
	//	
	void drawBoard()
	{
		//	displayMessage ("Tortoise position is " + tortoisePosition + ".  Hare Position is " + harePosition);		
		raceTrack.drawRace(tortoisePosition, harePosition, bitePosition);
	}
	

	// METHOD:  displayMessage (String message)
	// Outputs message to user.

	void displayMessage (String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
}
