// Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: default
// File: DrawPanel.java
//
// Object: DrawPanel extends JPanel
//
// DrawPanel contains an array of drawn shapes
//  as well as variables that enable the user to 
//  draw additional shapes
//
// A great deal of its code is dedicated to 
//  responding to events propagated by GUI elements
//  within the containing DrawFrame object
//
// It also implements responses to a variety of 
//   mouse events.


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DrawPanel extends JPanel
{

	// Array of shapes
	MyShape [] allShapes; 
	int shapeCount;
	
	// Making a new shape
	MyShape currentShape;
	int shapeType;
	int currentFirstX;
	int currentFirstY;
	int currentSecondX;
	int currentSecondY;
	Color currentColor;
	boolean filledShape;

	// Mouse events
	MouseHandler handler;
	JLabel mouseLocation; 


	// METHOD:  paintComponent(Graphics g)
	// 
	// paintComponent first calls super.paintComponet
	//  to clear the screen.
	// Then, it draws all the shapes in the allShapes
	//  array, as well as the current shape if it exists
	//
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for (int i = 0; i < shapeCount; i++)
		{
			allShapes[i].draw(g);
		}
		if (currentShape != null)
		{
			currentShape.draw(g);
		}	
	}

	// METHOD:  setShapeType (int shape)
	// 
	// setShapeType sets the current shapeType
	// 
	// It receives its input from a GUI button 
	//    defined in DrawFrame
	
	public void setShapeType(int shape)
	{
		shapeType = shape;
	}
	
	// METHOD:  setCurrentColor (int color)
	// 
	// setCurrentColor sets the currentColor variable
	// 
	// It receives its input from a GUI button 
	//    defined in DrawFrame
	
	public void setCurrentColor(int color)
	{
		currentColor = DrawConstants.colors[color];
	}

	// METHOD:  setFilledShape (boolean fill)
	// 
	// setFilledShape sets the filledShape variable
	// 
	// It receives its input from a GUI button 
	//    defined in DrawFrame
	
	public void setFilledShape(boolean fill)
	{
		filledShape = fill;
	}

	// METHOD:  clearLastShape ()
	// 
	// clearLastShape reduces the shapeCount index
	//   to the allShapes[] array and repaints()
	//
	// It is activated in response to a GUI button 
	//    defined in DrawFrame

	public void clearLastShape()
	{
		shapeCount--;
		if (shapeCount < 0)
		{
			shapeCount = 0; // count never less than 0
		}
		repaint();
	}
	
	// METHOD:  clearDrawing ()
	// 
	// clearDrawing sets the shapeCount index to 0
	//    and repaints()
	//
	// It is activated in response to a GUI button 
	//    defined in DrawFrame

	
	public void clearDrawing()
	{
		shapeCount = 0;
		repaint();
	}


	// METHOD:  loadDrawing (File file)
	// 
	// loadDrawing takes an input file and reads it 
	//    in as a set of MyShape objects.
	// It creates a new allShapes[] array from the 
	//    MyShapes objects it reads, and resets the
	//    shapeCount index
	//
	// It is activated in response to a GUI button 
	//    defined in DrawFrame
	
	public void loadDrawing(File file)
	{
		try
		{
			ObjectInputStream input;
			input = new ObjectInputStream(new FileInputStream(file));
			MyShape shape;
			allShapes = new MyShape[100];
			int index = 0;
			
			while ((shape = (MyShape) input.readObject()) != null)
			{
				allShapes[index++] = shape;
			}
			shapeCount= index;
		} 
		catch ( IOException ioException )
		{
			System.err.println( "Error opening file." );
		} 	
		catch (ClassNotFoundException cnfe)
		{
			System.err.println( "Unable to create object." );
		}
	}

	// METHOD:  saveDrawing (File file)
	// 
	// loadDrawing takes an output file and writes to it 
	//    the set of MyShapes contained in the allShapes[] array
	//
	// It is activated in response to a GUI button 
	//    defined in DrawFrame
	

	public void saveDrawing(File file)
	{
		try 
		{
			ObjectOutputStream output;
			output = new ObjectOutputStream(new FileOutputStream(file));
			for (MyShape shape : allShapes)
			{
				output.writeObject(shape);
			}
		} 
		catch ( IOException ioException )
		{
			System.err.println( "Error opening file." );
		} 
	}

	
	//
	//  Inner class:  MouseHandler 
	//      extends MouseAdapter implements MouseMotionListener
	//
	//  The MouseHandler allows the DrawPanel to respond to mouse events
	// 
	//  Moving the mouse causes the mouselocation label to be updated
	//  Pressing the mouse causes the drawing of a new shape to be initiated
	//  Releasing the mouse causes the drawing of a new shape to be completed
	//      and added to the allShapes array
	//  Dragging the mouse redraws the temporary shape stored in currentShape
	//     and updates the mouselocation label
	
	class MouseHandler extends MouseAdapter implements MouseMotionListener
	{
		//  Pressing the mouse causes initiates the drawing of a new shape,
		//   temporarily stored in currentShape
		
		public void mousePressed( MouseEvent event )
		{
			int xLocation = event.getX();
			int yLocation = event.getY();


			String shapeString = DrawConstants.shapeNames[shapeType];
			if (shapeString.equals("Line"))
			{
				currentShape = new MyLine(xLocation, yLocation, xLocation, yLocation, currentColor);
			}
			else if (shapeString.equals("Rectangle"))
			{
				currentShape = new MyRectangle(xLocation, yLocation, 
						xLocation, yLocation, currentColor, filledShape);
			}
			else if (shapeString.equals("Oval"))
			{
				currentShape = new MyOval(xLocation, yLocation, 
						xLocation, yLocation, currentColor, filledShape);
			}
			else
			{
				assert(false);
			}
		}

		//  Releasing the mouse completes the drawing of the shape
		//      stored in currentShape and adds it to the allShapes array
		public void mouseReleased( MouseEvent event )
		{
			int xLocation = event.getX();
			int yLocation = event.getY();

			currentShape.setX2(xLocation);
			currentShape.setY2(yLocation);
			allShapes[shapeCount++] = currentShape;
			currentShape = null;

			repaint();
		}

		//  Moving the mouse causes the mouselocation label to be updated

		public void mouseMoved( MouseEvent event )
		{
			int xLocation = event.getX();
			int yLocation = event.getY();
			mouseLocation.setText("(" + xLocation + ", " + yLocation + ")");
		}

		//  Dragging the mouse redraws the temporary shape stored in currentShape
		//     and updates the mouselocation label

		public void mouseDragged( MouseEvent event )
		{
			int xLocation = event.getX();
			int yLocation = event.getY();

			currentShape.setX2(xLocation);
			currentShape.setY2(yLocation);

			repaint();

			mouseLocation.setText("(" + xLocation + ", " + yLocation + ")");

		}
	}

	DrawPanel(JLabel label)
	{
		// initialize Panel appearance
		super();
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// initialize allShapes array
		allShapes = new MyShape[100];
		shapeCount = 0;

		// initialize currentShape 		
		currentShape = null;
		shapeType = 0;
		currentColor = DrawConstants.colors[0];

		// initialize mouseLocation label
		mouseLocation = label;

		// initialize mouse handler
		handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);

		setVisible(true);

	}

}
