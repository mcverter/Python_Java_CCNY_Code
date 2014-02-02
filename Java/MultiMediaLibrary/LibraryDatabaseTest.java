// Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: libraryBase
// File: LibraryDatabaseTest.java
//
// ALERT:  This file contains UNICODE characters 
//   and must not be saved as ASCII
//
// Object:  LibraryDatabaseTest
//
// LibraryDatabaseTest provides a main() function
//   that acts as the driver to test the funcitonality
//   of the library.
// LibraryDatabaseTest begins by initializing several
//   Textbook, CD, and Video objects and by adding them
//   to the Database object
// Once the Database object has been populated with these
//   values, LibraryDatabseTest systematically tests the
//   sorting functionality of the library.
// It does so by performing the default sort, which sorts
//    items by ID by calling compareTo()
//  Then it sorts by all of the fields within the library objects:
//     ID, title, addedOn, author, playingTime, director, artist
//  Then it chains together several comparators and performs 
//     compound searches

package libraryBase;

import java.util.*;


public class LibraryDatabaseTest {
	
	public static void main(String args[]) {
		CompChain chain = new CompChain();
		Database library = new Database();
		Calendar cal = Calendar.getInstance();

		// adding database entries
		cal.set(1890, Calendar.AUGUST, 10);
		Date date = (Date) cal.getTime();
		library.addItem(new Textbook("TB15", "TextX", date, "John Doe"));
		
		cal.set(1954, Calendar.JANUARY, 18);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V09", "VideoB", date, 70000, "J. Smith"));
		
		cal.set(2000, Calendar.FEBRUARY, 29);
		date = (Date) cal.getTime() ;
		library.addItem(new Textbook("TB01", "TextY", date, "John Doe"));
		
		cal.set(2000, Calendar.FEBRUARY, 29);
		date = (Date) cal.getTime() ;
		library.addItem(new CD("CD07", "CD1", date, 1000, "B.D."));
		
		cal.set(1990, Calendar.APRIL, 30);
		date = (Date) cal.getTime() ;
		library.addItem(new CD("CD10", "CD1", date, 800, "X.Y."));
		
		cal.set(2000, Calendar.FEBRUARY, 29);
		date = (Date) cal.getTime();
		library.addItem(new CD("CD05", "CD1", date, 1000, "B.C."));

		cal.set(1890, Calendar.JULY, 2);
		date = (Date) cal.getTime();
		library.addItem(new Video("V12", "VideoA", date, 7000, "Joe Smith"));

		cal.set(1952, Calendar.OCTOBER, 9);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V51_E", "Ikiru", date, 143, "Akira Kurasowa"));

		cal.set(1952, Calendar.OCTOBER, 9);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V51_J", "生きる", date, 143, "黒澤 明"));

		cal.set(1961, Calendar.APRIL, 25);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V52_E", "Yojimbo", date, 110, "Akira Kurasowa"));

		cal.set(1961, Calendar.OCTOBER, 25);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V52_J", "用心棒", date, 110, "黒澤 明"));

		cal.set(1962, Calendar.JANUARY, 1);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V53_E", "Sanjuro", date, 96, "Akira Kurasowa"));

		cal.set(1962, Calendar.JANUARY, 1);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V53_J", "椿三十郎 T", date, 96, "黒澤 明"));

		cal.set(1962, Calendar.APRIL, 18);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V54_E", "Zatōichi", date, 96, "Kenji Misumi"));

		cal.set(1962, Calendar.APRIL, 18);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V54_J", "座頭市物語", date, 96, "三隅 研次"));

		cal.set(2003, Calendar.SEPTEMBER, 3);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V55_E", "Zatōichi", date, 116, "Takeshi Kitano(Beat Takeshi)"));

		cal.set(1962, Calendar.SEPTEMBER, 3);
		date = (Date) cal.getTime() ;
		library.addItem(new Video("V55_J", "座頭市", date, 116, "北野 武(ビートたけし)"));

		cal.set(1985, Calendar.JANUARY, 1);
		date = (Date) cal.getTime();
		library.addItem(new Textbook("TB55_E", "Nip the Buds, Shoot the Kids", date, "Kenzaburō Ōe"));

		cal.set(1958, Calendar.JANUARY, 1);
		date = (Date) cal.getTime();
		library.addItem(new Textbook("TB55_J", "芽むしり仔撃ち", date, "大江 健三郎"));

		cal.set(1253, Calendar.JANUARY, 1);
		date = (Date) cal.getTime();
		library.addItem(new Textbook("TB56_E", "Shōbōgenzō", date, "Dōgen Zenji"));

		cal.set(1253, Calendar.JANUARY, 1);
		date = (Date) cal.getTime();
		library.addItem(new Textbook("TB56_J", "正法眼蔵", date, "道元禅師"));


		cal.set(1963, Calendar.JANUARY, 1);
		date = (Date) cal.getTime();
		library.addItem(new Textbook("TB57_E", "The Sailor Who Fell from Grace with the Sea", date, "Yukio Mishima"));

		cal.set(1963, Calendar.JANUARY, 1);
		date = (Date) cal.getTime();
		library.addItem(new Textbook("TB57_J", "午後の曳航", date, "三島 由紀夫"));
	
		cal.set(1982, Calendar.FEBRUARY, 21);
		date = (Date) cal.getTime();
		library.addItem(new CD("CD55_E", "Everybody Happy", date, 32, "Shonen Knife"));

		cal.set(1982, Calendar.FEBRUARY, 21);
		date = (Date) cal.getTime();
		library.addItem(new CD("CD55_J", "Minna Tanoshiku", date, 32, "少年ナイフ"));

		cal.set(1994, Calendar.MAY, 29);
		date = (Date) cal.getTime();
		library.addItem(new CD("CD56_E", "Cactuses Come in Flocks", date, 28, "MELT-BANANA"));

		cal.set(1994, Calendar.MAY, 29);
		date = (Date) cal.getTime();
		library.addItem(new CD("CD56_J", "Cactuses Come in Flocks", date, 28, "のオフィシャルサイト"));

		cal.set(1988, Calendar.MAY, 29);
		date = (Date) cal.getTime();
		library.addItem(new CD("CD57_E", "Osorezan no Stooges Kyo", date, 35, "Boredoms"));

		cal.set(1988, Calendar.MAY, 29);
		date = (Date) cal.getTime();
		library.addItem(new CD("CD57_J", "Osorezan no Stooges Kyo", date, 35, "ボアダムス"));

		
		// print unsorted database
		System.out.println("----- DATABASE BEFORE SORTING: -----\n");
		library.list();
		
		// sort and print sorted database (by id)
		System.out.println("\n\n----- DATABASE AFTER SORTING BY ID (default): -----\n");
		List list = library.getItems();
		Collections.sort(list);		
		library.list();
		
		
		// sort by other fields
		System.out.println("\n\n----- DATABASE AFTER SORTING BY OTHER FIELDS: -----");
		
		// sorting by fields native to Item superclass
		System.out.println("\n\n------------ (title) -----------\n");
		chain.addComparator(new TitleComparator());
		Collections.sort(library.getItems(), chain);
		library.list();


		System.out.println("\n\n------------ (addedOn) -----------\n");
		chain = new CompChain();
		chain.addComparator(new AddedOnComparator());
		Collections.sort(library.getItems(), chain);
		library.list();


		System.out.println("\n\n------------ (ID) -----------\n");
		chain = new CompChain();
		chain.addComparator(new IDComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

		// chaining together several comparators
		System.out.println("\n\n------------ (title, addedOn) -----------\n");
		chain = new CompChain();
		chain.addComparator(new TitleComparator());
		chain.addComparator(new AddedOnComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

		
		// sorting by fields native to subclasses		
		System.out.println("\n\n--Sorting by subclass fields--\n");
		
		// Textbook field
		System.out.println("\n\n------------ (author) -----------\n");
		chain = new CompChain();
		chain.addComparator(new AuthorComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

		// Video field
		System.out.println("\n\n------------ (director) -----------\n");
		chain = new CompChain();
		chain.addComparator(new DirectorComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

		// CD field
		System.out.println("\n\n------------ (artist) -----------\n");
		chain = new CompChain();
		chain.addComparator(new ArtistComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

		// MultiMediaItem field
		System.out.println("\n\n------------ (playing time) -----------\n");
		chain = new CompChain();
		chain.addComparator(new PlayingTimeComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

		
		// chaining together several comparators
		System.out.println("\n\n------------ (title, director, playing time, artist) -----------\n");
		chain = new CompChain();
		chain.addComparator(new TitleComparator());
		chain.addComparator(new DirectorComparator());
		chain.addComparator(new PlayingTimeComparator());
		chain.addComparator(new ArtistComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

		System.out.println("\n\n------------ (Author, ID, AddedOn) -----------\n");
		chain = new CompChain();
		chain.addComparator(new AuthorComparator());
		chain.addComparator(new IDComparator());
		chain.addComparator(new AddedOnComparator());
		Collections.sort(library.getItems(), chain);
		library.list();

	}
}
