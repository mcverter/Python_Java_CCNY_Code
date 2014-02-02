//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: Database.java
//
// Object:  Database
//  The Database object contains a
//     List (ArrayList) of objects within
//     the Database.
//  It provides methods for adding to 
//    the list, for converting the list
//    to a String, and for getting the list
//

package libraryBase;

import java.util.*;

public class Database 
{
	private List <Item> myItems; 
	
	public Database()
	{
		myItems = new ArrayList <Item>();
	}
	public List getItems()
	{
		return myItems;
	}
	public void addItem(Item addedItem)
	{
		myItems.add(addedItem);
	}
	public void list()
	{		
		for (int i = 0 ; i< myItems.size() ; i++)
		{
			System.out.println(myItems.get(i).toString());
		}	
	}
}
