//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: MultiMediaItem.java
//
// Object:  MultiMediaItem
//
// MultiMediaItem is an abstract base class
//   which is instantiated through derived subclasses.
// It extends the Item class
// It provides an integer playingTime
// It provides methods for getting the value of
//    playingTime and for String representation


package libraryBase;

import java.util.Date;

abstract public class MultiMediaItem extends Item
{
	private int playingTime;

	public MultiMediaItem(String id, String title, Date addedOn, int playingTime)
	{
		super(id, title, addedOn);
		this.playingTime = playingTime;
	}

	public int getPlayingTime()
	{
		return playingTime;
	}
	@Override
	public String toString()
	{		
		return super.toString() + "; PLAYING_TIME: " + playingTime;
	}
}
