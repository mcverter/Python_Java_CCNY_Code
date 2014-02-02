// Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: CD.java
//
// Object:  CD
//
// CD extends the abstract class MultiMediaItem
// It provides an String, artist
// It provides methods for getting the value of
//    artist and for String representation


package libraryBase;

import java.util.Date;

public class CD extends MultiMediaItem
{
	private String artist;

	public CD(String id, String title, Date addedOn, int playingTime, String artist)
	{
		super (id, title, addedOn, playingTime);
		this.artist = artist;
	}	
	public String getArtist()
	{
		return artist;
	}
	@Override
	public String toString()
	{
		return super.toString() + "; ARTIST: " + artist;
	}
}
