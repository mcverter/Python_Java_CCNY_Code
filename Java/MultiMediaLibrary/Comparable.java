//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: Comparable.java
//
// Interface: Comparable
//
// Comparable provides an interface for
//   the method int compareTo(Object)
// It extends the interface java.lang.Comparable


package libraryBase;

public interface Comparable <T> extends java.lang.Comparable <T> 
{
	public int compareTo(Object compObj);
}
