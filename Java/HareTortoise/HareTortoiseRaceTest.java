// Name: Mitchell Verter

// File: HareTortoiseRaceTest.java
// Class:  HareTortoiseRaceTest
// The HareTortoiseRaceTest creates a HareTortoiseRace and
//    tells it to run the race.
//
// In order to create the HareTortoiseRace, 
//  the HareTortoiseRaceTest asks the user to input a number
//  from 5 to 500.  If the user does not enter a number within
//  that range or if she doesn't enter anything at all, 
//  the length of the race will be set to the DEFAULT_VALUE, 70


import javax.swing.JOptionPane;

public class HareTortoiseRaceTest {

	public static void main (String [] args)
	{
		final int DEFAULT_LENGTH = 70;
		int length=-1;
		
		try 
		{
			length = Integer.parseInt(
					JOptionPane.showInputDialog("Please enter a number between 5 and 500 for the length of the race."));
		}
		catch (Exception e)
		{			
		}
			
		if ((length < 5) || (length>500))
		{
			length = DEFAULT_LENGTH;
		}
		
		HareTortoiseRace htr = new HareTortoiseRace(length);
		htr.runTheRace();
	}
}
