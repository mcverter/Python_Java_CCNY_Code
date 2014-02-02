// Name: Mitchell Verter


// File: RaceDrawer.java
// Class:  RaceDrawer
//
// This package is responsible for depicting the 
//  HareTortoise Race graphically.
//
// It lays out a Grid within a Frame and paints
//   images of the Hare and the Tortoise and
//   any possible collision
//
// I struggled to figure out how to correctly
//   use the paint() command, and am not sure  
//   whether I am doing so.  
//
// As it stands, each iteration of paint() clears
//   out all the contents from the container object
//   and adds new contents. 
// I am unclear whether this is appropriate behavior.
//


import java.awt.*;
import javax.swing.*;


public class RaceDrawer extends JFrame 
{

	private int length;         // length of the race
	private int gridDimension;  // square dimension of grid
	private GridLayout raceTrack;  // grid layout
	
	private Container container;    // graphics container
	
	
	private ImageIcon duckBunny;   // stored images
	private ImageIcon infiniteTortoise;
	private ImageIcon ouch;
	private ImageIcon field; 
	
	
	int tortoisePosition, harePosition, ouchPosition;  
	
	// METHOD:  constructor(int size)
	// We create a new RaceDrawer of the input length
	// we then create a square grid layout
	//
	// We then initialize our images.

	public RaceDrawer(int size)
	{
		super ("The Great Tortoise Hare Race!!!");
		length = size;
		gridDimension = (int) Math.ceil(Math.sqrt(size));
		
		raceTrack = new GridLayout (gridDimension, gridDimension, 3, 3);
	
		duckBunny = createImageIcon("bunnyduck.png", "BUNNY");
		infiniteTortoise = createImageIcon("tortoise_infinite.png", "TORTOISE");
		field = createImageIcon("escher_racetrack.png", "GREEN");
		ouch = createImageIcon("ouch.png", "OUCH!!!");
	}

	// METHOD: createImageIcon (String path, String description)
	//
	// Initializes the images used in the race.
	//
	protected ImageIcon createImageIcon(String path,
			String description) 
	{
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) 
		{
			return new ImageIcon(imgURL, description);
		} 
		else 
		{
			System.err.println("Couldn't find file: " + path);			
			return null;
		}
	}
	
	// METHOD drawRace(int tortoiseLocation, int hareLocation , int ouchLocation )
	//
	// This funciton is called by HareTortoiseRace.playTurns()
	// 
	// It sets the positions of the tortoise, hare, and ouch
	// Then it calls paint()
	//
	
	public void drawRace (int tortoiseLocation, int hareLocation , int 	ouchLocation )
	{
		tortoisePosition = tortoiseLocation ;
		harePosition = hareLocation;
		ouchPosition = 	ouchLocation ;
		paint();
	}

	// METHOD paint()
	//
	// The paint method begins by clearing the contents
	//  that were left over from a previous iteration of
	//  paint()
	//
	// This function sets the GridLayout and then
	//  adds pictures to the grid.  
	//
	// It adds pictures according to the stored values
	//   for the positions of the ouch, hare, and tortoise
	// If there is an ouch, only the ouch will be painted
	// Otherwise, both the hare and the tortoise will be painted
	// 
	// Every square which is not painted with a hare, tortoise,
	//  or ouch is painted with a generic image of a field.
	//
	// I'm still not sure if this is the best way to implement paint()
	//
	
	public void  paint()
	{
		container = getContentPane();
		container.removeAll();		
		container.setLayout(raceTrack); 

		if (ouchPosition > 0)
		{
			for (int i=1; i<= length; i++)
			{
				if (i==ouchPosition)
				{
					container.add(new JLabel(ouch));
				  }	
				else 
				{
					container.add(new JLabel(field));
				}	
			  }
		  }
		  else
		  {
			for (int i = 1; i <= length ; i++)
			{
				if (i == tortoisePosition)
				{
					container.add(new JLabel(infiniteTortoise));
				}
				else if (i == harePosition)
				{
					container.add(new JLabel(duckBunny));
				}
				else
				{
					container.add(new JLabel(field));
				}
			}	
		  }
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  pack();
		  setVisible(true);
	}
}

