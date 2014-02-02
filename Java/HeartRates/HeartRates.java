//  Mitchell Verter
// for Akira Kawaguchi
// Assignment 1
// file:  HeartRates.java

import java.util.*;
import java.text.SimpleDateFormat;


public class HeartRates {
/*
 * [CCNY Logo uploading...] 	CSc221
Software Design Laboratory
Bachelor's Program In Computer Science at City College of New York, Summer 2012 Update History:
Programming Assignment #1
Install your most preferred Java IDE, and implement textbook (9/E) problem 3.16 in page 101. The problem statement is reproduced next:

3.16 (Target-Heart-Rate Calculator) While exercising, you can use a heart-rate monitor 
to see that your heart rate stays within a safe range suggested by your trainers and 
doctors. According to the American Heart Association (AHA), the formula for calculating
 your maximum heart rate in beats per minute is 220 minus your age in years. Your 
 target heart rate is a range that's 50-85% of your maximum heart rate. Note: These 
 formulas are estimates provided by the AHA. Maximum and target heart rates may vary
  based on the health, fitness and gender of the individual. Always consult a 
  physician or qualified health care professional before beginning or modifying an exercise program.

Create a class called HeartRates. The class attributes should include the 
person's first name, last name and date of birth (consisting of separate 
attributes for the month, day and year of birth). Your class should have a 
constructor that receives this data as parameters. For each attribute provide 
set and get methods. The class also should include a method that calculates
 and returns the person's age (in years), a method that calculates and 
 returns the person's maximum heart rate and a method that calculates 
 and returns the person's target heart rate. Write a Java application
  that prompts for the person's information, instantiates an object of 
  class HeartRates and prints the information from that object—including 
  the person's first name, last name and date of birth—then calculates 
  and prints the person's age in (years), maximum heart rate and 
  target-heart-rate range.


Requirements
Realize your implementation that satisfies the following minimal requirements:

    2 pt: properly exhibits right logic, i.e., readable and compilable coding
    2 pt: properly reads personal information from terminal
    2 pt: properly writes maximum and target heart rates to terminal
    2 pt: properly writes error message to terminal in response to incorrect input
    extra credit: properly interfaces with dialog boxes (see 3.19) 

Look at the 3rd implementation of this which is simplest for date validation.
Submission
You are to hand in your development by Monday 18:00 P.M. June 11th, i.e., just before the lecture. Once you have done all your work, email me your zipped project file or Java source file(s). Your work will be counted about 8% of your final grade. 
You can submit your work by Wednesday 18:00 P.M. June 13th (with 25% score deduction).

© Akira Kawaguchi, June 2012.	CSc221 Software Design Laboratory

 * 
 */
	private String firstName, lastName;
	private int yearOfBirth, monthOfBirth, dayOfBirth ;
	private Date dateOfBirth;
	private int ageInYears;
	
	public HeartRates(String first, String last, int year, int month, int day)
	{
		firstName = first;
		lastName = last;
		yearOfBirth = year;
		monthOfBirth = month;
		dayOfBirth = day;
		dateOfBirth = new Date(year-1900, month-1, day);  // deprecated constructor
		ageInYears = calculateAge(dateOfBirth);
	}
	
	public void setFirstName (String name)
	{
		firstName = name;
	}
	
	public String getFirstName ()
	{
		return firstName;
	}
	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	public void setLastName (String name)
	{
		lastName = name;
	}
	public String getLastName ()
	{
		return lastName;
	}
	public void setYearOB (int year)
	{
		yearOfBirth = year;
	}
	public int getYearOB ()
	{
		return yearOfBirth;
	}
	public int getAgeInYears()
	{
		return ageInYears;
	}
	public void setMonthOB (int month)
	{
		monthOfBirth = month;
	}
	public int getMonthOB ()
	{
		return monthOfBirth;
	}
	public void setDayOB (int day)
	{
		dayOfBirth = day;
	}
	public int getDayOB ()
	{
		return dayOfBirth;
	}

	public String getDateOfBirth()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy"); 
		return sdf.format(dateOfBirth);		
	}
	public int calculateAge(Date dob)
	{
		Date now = new Date();
		long ageInMS = now.getTime() - dob.getTime();
		int years = (int) (ageInMS / (1000L * 60L * 60L *24L * 365L));
		return years;
	}

	public int maxHeartRate()
	{
		return 220 - ageInYears;
	}


	public String targetHeartRange()
	{
		int maxRate = maxHeartRate();
		int lowerBound = (int) Math.ceil(.5 * maxRate);
		int upperBound = (int) Math.floor(.85 * maxRate);
		String range = lowerBound + " to " + upperBound; 
		return range;
	}
	 
}
