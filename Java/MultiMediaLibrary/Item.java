//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: Item.java
//
// Objects:
//  (1) Item
//  (2) IDComparator
//  (3) TitleComparator
//  (4) AddedOnComparator
//  (5) DirectorComparator
//  (6) PlayingTimeComparator
//  (7) AuthorComparator
//  (8) ArtistComparator
//
// This file defines the abstract base class of Item
//  Item contains an ID String, a Title String, and AddedOn Date
//
// The file also contains comparator objects for Item
//  and all of its subclasses


package libraryBase;

import java.util.*;

abstract public class Item implements Comparable
{
	private String id;
	private String title;
	private Date addedOn;

	public Item(String id, String title, Date addedOn)
	{
		this.id = id;
		this.title = title;
		this.addedOn = addedOn;
	}
	public String getID()
	{
		return id;
	}
	public String getTitle()
	{
		return title;
	}
	public Date getAddedOn()
	{
		return addedOn;
	}
	@Override
	public int compareTo(Object compObj)
	{
		Item compItem = (Item) compObj;
		return (this.id.compareTo(compItem.id));
	}
	@Override
	public String toString()
	{
		return "ID: " + id + "; TITLE: " + title + 
				"; DATE: " + addedOn.toString();
	}

}

//
// The following three Comparator objects can be
//  used to compare two objects of the Item class
//  or subclasses that derive from Item
//

//
// IDComparator is defined so that it can be used 
//  within a ComparatorChain
//
class IDComparator implements Comparator <Item>
{
  public int compare(Item itemOne, Item itemTwo) 
  {
	  return itemOne.compareTo(itemTwo);
  }
}


class TitleComparator implements Comparator <Item>
{
  public int compare(Item itemOne, Item itemTwo) 
  {
	  return itemOne.getTitle().compareTo(itemTwo.getTitle());
  }
}

class AddedOnComparator implements Comparator <Item>
{
  public int compare(Item itemOne, Item itemTwo) 
  {
	  return itemOne.getAddedOn().compareTo(itemTwo.getAddedOn());
  }
}

//
// The following four Comparator Objects are used
//   to compare between objects of subclasses that
//   extend from Item.
// If the compared object is not a member of the 
//   subclass, then it is evaluated as being "less than"
//   an object of that subclass
// If neither object is a member of the subclass,
//   then the two objects are evaluated as being equal
// If both objects are members of the subclass, 
//   then their respective values are compared in 
//   using String or int comparisons
//


class AuthorComparator implements Comparator <Item>
{
  public int compare(Item itemOne, Item itemTwo) 
  {
	  if ((itemOne instanceof Textbook) &&
			  (itemTwo instanceof Textbook))
	  {
		  String authorOne = ((Textbook)itemOne).getAuthor();
		  String authorTwo = ((Textbook)itemTwo).getAuthor();
		  return authorOne.compareTo(authorTwo);
	  }
	  else if (itemOne instanceof Textbook) 
	  {
		  return 1;
	  }
	  else if (itemTwo instanceof Textbook)
	  {
		  return -1;
	  }
	  else
	  {
		  return 0;
	  }
  }
}

class ArtistComparator implements Comparator <Item>
{
  public int compare(Item itemOne, Item itemTwo) 
  {
	  if ((itemOne instanceof CD) &&
			  (itemTwo instanceof CD))
	  {
		  String artistOne = ((CD)itemOne).getArtist();
		  String artistTwo = ((CD)itemTwo).getArtist();
		  return artistOne.compareTo(artistTwo);
	  }
	  else if (itemOne instanceof CD) 
	  {
		  return 1;
	  }
	  else if (itemTwo instanceof CD)
	  {
		  return -1;
	  }
	  else
	  {
		  return 0;
	  }
  }
}

class DirectorComparator implements Comparator <Item>
{
  public int compare(Item itemOne, Item itemTwo) 
  {
	  if ((itemOne instanceof Video) &&
			  (itemTwo instanceof Video))
	  {
		  String directorOne = ((Video)itemOne).getDirector();
		  String directorTwo = ((Video)itemTwo).getDirector();
		  return directorOne.compareTo(directorTwo);
	  }
	  else if (itemOne instanceof Video) 
	  {
		  return 1;
	  }
	  else if (itemTwo instanceof Video)
	  {
		  return -1;
	  }
	  else
	  {
		  return 0;
	  }
  }
}

class PlayingTimeComparator implements Comparator <Item>
{
  public int compare(Item itemOne, Item itemTwo) 
  {
	  if ((itemOne instanceof MultiMediaItem) &&
			  (itemTwo instanceof MultiMediaItem))
	  {
		  int playingTimeOne = 
				  ((MultiMediaItem)itemOne).getPlayingTime();
		  int playingTimeTwo = 
				  ((MultiMediaItem)itemTwo).getPlayingTime();
		  if (playingTimeOne > playingTimeTwo)
		  {
			  return 1;
		  }
		  else if (playingTimeOne < playingTimeTwo)
		  {
			  return -1;
		  }	
		  else
		  {	
			  return 0;
		  }	
	  }	
	  else if (itemOne instanceof MultiMediaItem) 
	  {
		  return 1;
	  }
	  else if (itemTwo instanceof MultiMediaItem)
	  {
		  return -1;
	  }
	  else
	  {
		  return 0;
	  }
  }
}

