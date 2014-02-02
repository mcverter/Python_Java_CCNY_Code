/*
  File: Planet.java
  Author: Mitchell Verter   
  Last Revision:  7/19/2012

This file holds the representation of a planet object.  
It contains the name of the planet, its number of moons, 
its distance from the sun, its size relative to the Earth,
and its ImageID.  

All of the string information is loaded from the strings.xml file
 */

package com.android.example.spinner;

public class Planet {
	String name;
	String moons;
	String distance;
	String size;
	int imageID;

	// constructor
	Planet(String name, String moons, String distance, 
			String size, int imageID)
			{
		this.name = name;
		this.moons = moons;
		this.distance = distance; 
		this.size = size;
		this.imageID = imageID;
			}

	// Accessor methods
	String getName()
	{
		return name;
	}

	String getMoons()
	{
		return moons;
	}

	String getDistance()
	{
		return distance;
	}

	String getSize()
	{
		return size;
	}
	int getImageID()
	{
		return imageID;
	}

	// Spinner requires us to override the toString() method
	@Override
	public String toString()
	{
		return name;
	}

}
