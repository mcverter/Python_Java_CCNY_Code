//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: Textbook.java
//
// Object:  Textbook
//
// Textbook extends the abstract class Item
// It provides an String, author
// It provides methods for getting the value of
//    author and for String representation

package libraryBase;

import java.util.*;

public class Textbook extends Item
{
	private String author;
	
	public Textbook (String id, String title, Date addedOn, String author)
	{
		super (id, title, addedOn);
		this.author = author;
	}
	public String getAuthor()
	{
		return author;
	}
	@Override
	public String toString()
	{		
		return super.toString() + "; AUTHOR: " + author;
	}
}

