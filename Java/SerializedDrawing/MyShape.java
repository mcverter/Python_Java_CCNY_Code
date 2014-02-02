//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: default
// File: MyShape.java
//
// Objects: 
//   (1) MyShape         implements Serializable
//   (2) MyBoundedShape  extends MyShape
//   (3) MyLine          extends MyShape
//   (4) MyOval          extends MyBoundedShape
//   (5) MyRectangle     extends MyBoundedShape


import java.awt.*;
import java.io.Serializable;


// abstract class MyShape implements Serializable
//
// MyShape contains the coordinates that determine its dimension
//   and the color which determines its appearance
// Various get and set methods can access/set these variables.
// The draw() method is abstract
//
abstract class MyShape implements Serializable
{
	private int x1, y1;
	private int x2, y2; 
	private Color myColor;

	public abstract void draw(Graphics g);

	// THe default constructor sets all cooordinates to 0
	//  and the color to black.
	public MyShape()
	{		
		x1 = y1 = x2 = y2 = 0;
		myColor = Color.BLACK;
	}

	public MyShape(int x1, int y1, int x2, int y2, Color myColor)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.myColor = myColor;
	}	

	public void setX1 (int val)
	{
		x1 = val;
	}
	public void setY1 (int val)
	{
		y1 = val;
	}
	public void setX2 (int val)
	{
		x2 = val;
	}
	public void setY2 (int val)
	{
		y2 = val;
	}
	public void setColor (Color c)
	{
		myColor = c;
	}
	public int getX1()
	{
		return x1;
	}
	public int getY1()
	{
		return y1;
	}
	public int getX2()
	{
		return x2;
	}
	public int getY2()
	{
		return y2;
	}
	public Color getColor()
	{
		return myColor;
	}
	
}

//abstract class BoundedShape extends MyShape
//
// BoundedShape extends MyShape by storing
//  a boolean that determines whether or not it
//  is filled and set and get methods for this variable
// In order to set the dimensions of this drawn object
//  this object provides methods for determining the 
//  the topmost coordinate, the leftmost coordinate,
//  the height, and the length.
//

abstract class MyBoundedShape extends MyShape
{
	private boolean filled;
	
	MyBoundedShape()
	{		
		super();
		filled = false;
	}
	MyBoundedShape(int x1, int y1, int x2, int y2, Color myColor, boolean filled)
	{
		super(x1, y1, x2, y2, myColor);
		this.filled = filled;
	}

	public boolean getFilled()
	{
		return filled;
	}

	public void setFilled(boolean filled)
	{
		this.filled = filled;
	}
	
	// the following four methods are needed because Java
	//  won't tolerate a negative height or width
	public int getHeight()
	{
		return Math.abs(getY1() - getY2());
	}
	public int getWidth()
	{
		return Math.abs(getX1() - getX2());		
	}
	public int getSmallerX()
	{
		return Math.min(getX1(), getX2());
	}
	public int getSmallerY()
	{
		return Math.min(getY1(), getY2());
	}

	public abstract void draw(Graphics g);
	
}

//class MyLine extends MyShape
//
//MyLine draws a line of the appropriate color 
// between its two coordinate pairs
//  

class MyLine extends MyShape
{
	MyLine()
	{		
		super();
	}
	MyLine(int x1, int y1, int x2, int y2, Color myColor)
	{
		super(x1, y1, x2, y2, myColor);
	}
	public void draw(Graphics g)
	{
		Color originalColor = g.getColor();
		g.setColor(getColor());
		g.drawLine(getX1(), getY1(), getX2(), getY2());
		g.setColor(originalColor);
	}
}

//class MyOval extends MyBoundedShape
//
//MyOval draws an oval of the appropriate color,
//  either filled or not filled
//  within its coordinates

class MyOval extends MyBoundedShape
{
	MyOval()
	{		
		super();
	}
	MyOval (int x1, int y1, int x2, int y2, Color myColor, boolean filled)
	{
		super(x1, y1, x2, y2, myColor, filled);
	}
	public void draw(Graphics g)
	{
		if (getFilled())
		{
			Color originalColor = g.getColor();
			g.setColor(getColor());
			g.fillOval(getSmallerX(), getSmallerY(), getWidth(), getHeight());
			g.setColor(originalColor);

		}
		else 
		{
			Color originalColor = g.getColor();
			g.setColor(getColor());
			g.drawOval(getSmallerX(), getSmallerY(), getWidth(), getHeight());			
			g.setColor(originalColor);

		}
	}
	
}

//class MyRectangle extends MyBoundedShape
//
//MyRectangle draws a rectangle of the appropriate color,
// either filled or not filled,
// within its coordinates

class MyRectangle extends MyBoundedShape
{
	MyRectangle()
	{		
		super();
	}
	MyRectangle(int x1, int y1, int x2, int y2, Color myColor, boolean filled)
	{
		super(x1, y1, x2, y2, myColor, filled);
	}
	public void draw(Graphics g)
	{
		if (getFilled())
		{
			Color originalColor = g.getColor();
			g.setColor(getColor());
			g.fillRect(getSmallerX(), getSmallerY(), getWidth(), getHeight());
			g.setColor(originalColor);
		}
		else 
		{
			Color originalColor = g.getColor();
			g.setColor(getColor());
			g.drawRect(getSmallerX(), getSmallerY(), getWidth(), getHeight());			
			g.setColor(originalColor);
		}		
	}
}

