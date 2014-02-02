//  Mitchell Verter
// for Akira Kawaguchi
// Assignment 1
// file:  HeartRatesTest.java
import java.util.Date;

import javax.swing.JOptionPane;

public class HeartRatesTest
{
	
	String getInformation(String queryString)
	{	
		return JOptionPane.showInputDialog(queryString);
		
	}
	void displayInformation(String responseString)
	{
		JOptionPane.showMessageDialog(null, responseString);
	}

	// The following 5 or so get() functions can probably be eliminated 
	// and replaced with direct inline calls to getInformation(queryString)
	// but I'm leaving them like this for now.
	
	String getLastName()
	{
		String queryString = "Please enter your last name";
		return getInformation(queryString);
	}
	String getFirstName()
	{
		String queryString = "Please enter your first name";
		return getInformation(queryString);
	}
	String getDayOfBirth()
	{
		String queryString = "Please enter the day of your birth (1-31)";
		return getInformation(queryString);
	}
	String getMonthOfBirth()
	{
		String queryString = "Please enter the two-digit month of your birth (1-12)";
		return getInformation(queryString);
	}
	String getYearOfBirth()
	{
		String queryString = "Please enter the four-digit year of your birth";
		return getInformation(queryString);	
	}
	// The following 5 or so display() functions can probably be eliminated 
	// and replaced with direct inline calls to displayInformation(queryString)
	// but I'm leaving them like this for now

	void displayFullName(HeartRates hr)
	{
		displayInformation("Your full name is " + hr.getFullName());
	}
	void displayDateOfBirth(HeartRates hr)
	{	
		displayInformation("Your birthday is " + hr.getDateOfBirth());	
	}
	void displayAge (HeartRates hr)
	{
		displayInformation("You are " + hr.getAgeInYears() + " years old");
	}
	void displayMaxHeartRate(HeartRates hr)
	{
		displayInformation("Your maximum heart rate is " + 
				String.valueOf(hr.maxHeartRate()));
	}
	void displayHeartRateRange(HeartRates hr)
	{
		displayInformation("Your recommended heart rate range is " 
				+ hr.targetHeartRange());	
	}
	
	
	
	public static void main (String args[])
	{
		
		String firstName, lastName;
		int yearOfBirth, monthOfBirth, dayOfBirth ;
		firstName=lastName = null;
		yearOfBirth=monthOfBirth=dayOfBirth = -1;
		
		HeartRatesTest hrt = new HeartRatesTest();
		
		boolean idValid, nameValid, birthdayValid;
		idValid = nameValid = birthdayValid = false;
		
		while (idValid != true)
		{
			while (nameValid != true)
			{
				firstName = hrt.getFirstName();
				lastName = hrt.getLastName();
				if ( (firstName!=null) &&     
						(lastName != null) &&   // make sure user didn't just hit return
 						firstName.matches("[A-Za-z][a-z]*") &&
						lastName.matches("[A-Za-z][a-z]*"))  // make sure they entered a valid name 
				{
					nameValid = true;
				}
				else
				{
					hrt.displayInformation("Please enter a valid first name and last name");
				}
			}
			
			while (birthdayValid != true)
			{
				try    // unlike the name validation, I used a try-catch block here
						// mainly because parseInt will throw an exception if the user just hits return
						// and the return string is null
						// and I didn't want to deal with all the messy code
						// on a future revision, I would want to be more consistent on how I check inputs
				{
					yearOfBirth =  Integer.parseInt(hrt.getYearOfBirth());
					monthOfBirth = Integer.parseInt(hrt.getMonthOfBirth());
					dayOfBirth = Integer.parseInt(hrt.getDayOfBirth());	
					if ((yearOfBirth > 1900) &&
							( monthOfBirth > 0 && monthOfBirth < 13) &&
							(dayOfBirth > 0) && (dayOfBirth < 32))  // make sure the dates are within the valid range
																	// note that java *will* accept "February 31"
																	// and interpret it as March 2
					{
						Date dob = new Date(yearOfBirth-1900, monthOfBirth-1, dayOfBirth);  // deprecated constructor
						Date now = new Date();
						if (now.after(dob))               			// make sure they were born before now
							{
								birthdayValid = true;
							}
					}
				}
				
				catch(Exception e)
				{
				}
				if (birthdayValid == false)
				{
					hrt.displayInformation("You must enter a valid birth date.");					
				}
			} // end while birtdayValid != true

			if (nameValid == true && birthdayValid == true)  // this is a redundant conditional check
															// but we will keep it in the code just in case.
			{
				idValid = true;
			}
		} // end while idValid != true

		
		HeartRates hr = new HeartRates(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth);
	
		hrt.displayFullName(hr);
		hrt.displayDateOfBirth(hr);
		hrt.displayAge(hr);
		hrt.displayMaxHeartRate(hr);
		hrt.displayHeartRateRange(hr);	
	}
}