//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: Video.java
//
// Object:  Video
//
// Video extends the abstract class Item
// It provides an String, director
// It provides methods for getting the value of
//    director and for String representation


package libraryBase;

import java.util.Date;

public class Video extends MultiMediaItem 
{
	private String director;

	public Video (String id, String title, Date addedOn, int playingTime, String director)
	{
		super(id, title, addedOn, playingTime);
		this.director = director;
	}
	public String getDirector()
	{
		return director;
	}
	@Override
	public String toString()
	{
		return super.toString() +  "; DIRECTOR: " + director;
	}
}
