// Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: default
// File: DrawConstants.java
//
// Object: DrawConstants
// 
// This class is used to define constants
//  that are shared by several other classes,
//  in particular, color and shape information.


import java.awt.Color;

public class DrawConstants {

	static final String[] colorNames = { "Black", "Blue", "Cyan",
		 "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
		 "Orange", "Pink", "Red", "White", "Yellow" };
	
	static final Color[] colors = { Color.BLACK, Color.BLUE,
		 Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
		 Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
		 Color.RED, Color.WHITE, Color.YELLOW };
		
	static final String[] shapeNames  = { "Line", "Oval", "Rectangle"};
}
