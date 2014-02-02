// Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: default
// File: InfixToPostfix.java
//
// Object:  InfixToPostfix 
//
//


import java.util.*;

public class InfixToPostfix {

	Stack <Character> conversionStack;
	StringBuffer postfix;
	StringBuffer infix;

	// Method:  CONSTRUCTOR
	// The constructor initializes the StringBuffers and the Stack
	
	public InfixToPostfix(String in)
	{
		infix = new StringBuffer(in);
		postfix = new StringBuffer();
		conversionStack = new Stack <Character> ();		
	}


	//
	// METHOD: isOperator
	// isOperator returns a boolean value based on 
	//  whether c is an operator or not
	//
	
	boolean isOperator (char c)
	{
		switch (c)
		{
		case '+':
		case '-':
		case '*':
		case '/':
		case '%':
			return true;
		default:
			return false;
		}
	}

	//
	// method: greaterOrEqualPrecedence
	// returns true if op1 has greater or equal precedence
	//   than op2
	//
	boolean greaterOrEqualPrecedence (char op1, char op2)
	{
		switch (op1)
		{
		case '+':
		case '-':
			switch(op2)
			{		
			case '+':
			case '-':
				return true;
			case '*':
			case '/':
			case '%':
				return false;
			}

		case '*':
		case '/':
		case '%':
			return true;
		default:
			assert(true);  // this function should only be called with an operator
			return false;	// as a parameter.  otherwise, we have a problem.
		}
	}

	//
	// METHOD: peek()
	//    returns the top value of the stack without popping it.
	//    (actually by popping and then pushing it)
	// Stack already provides a peek() method, so it's not clear
	//  why we need this method, but the specs asked for it
	//
	
	char peek()
	{
		assert (! conversionStack.isEmpty());

		char top = conversionStack.pop();
		conversionStack.push(top);
		return top;

	}
	//
	// METHOD: convertToPostfix()
	//   iterates through the infix StringBuffer and ConversionStack
	//   to create the postfix StringBuffer
	//   based on the conversion algorithm
	//
	
	void convertToPostfix()
	{
		conversionStack.push('(');
		infix.append(')');
		char currentChar, topChar, nextChar;
		boolean cont;
		int index = 0;
		while (! conversionStack.isEmpty() &&
				index < infix.length())
		{
			currentChar = infix.charAt(index++);
			switch (currentChar)
			{

			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				postfix.append(currentChar);				
				cont = true;
				while ((index < infix.length())
						&& cont == true)
				{
					String s = new String(infix.charAt(index)+ "");
					if (s.matches("[.\\d]"))
					{
						postfix.append(infix.charAt(index++));
						cont = true;
					}
					else 
					{
						cont = false;
					}
				}				
				break;

			case '(':
				conversionStack.push(currentChar);
				break;

			case '+':
			case '-':
			case '*':
			case '/':
			case '%':
				topChar = peek();
				while (isOperator(topChar)
						&& greaterOrEqualPrecedence(topChar, currentChar))
				{
					conversionStack.pop();
					postfix.append(topChar);
					topChar = peek();
				}
				conversionStack.push(currentChar);
				break;
			
			case ')':
				topChar = peek();
				while (topChar != '(')
				{
					if (isOperator(topChar))
					{
						conversionStack.pop();
						postfix.append(topChar);
						topChar = peek();
					}
				}
				conversionStack.pop();
				break;
				
			// a much too long list of cases?
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
				postfix.append(currentChar);
				
				cont = true;
				while ((index < infix.length())
						&& cont == true)
				{
					String s = new String(infix.charAt(index)+ "");
					if (s.matches("\\w"))
					{
						postfix.append(infix.charAt(index++));
						cont = true;
					}
					else 
					{
						cont = false;
					}
				}

				break;
			}
			postfix.append(" ");
		}

	}
	
	// 	Accessor Methods
	StringBuffer getPostfix()
	{
		return postfix;
	}
	StringBuffer getInfix()
	{
		return infix;
	}
	
	// METHOD: printInPost()
	//  Prints out the results of the infix and postfix
	
	void printInPost()
	{
		System.out.println("Infix is: " + getInfix() + 
					"\nPostfix is: " + postfix ); //getPostfix() + "\n\n\n");
	}
	public static void main (String[] args)
	{
		System.out.println("Here are some examples of infix to postfix conversion:");
		InfixToPostfix i2p;
		i2p = new InfixToPostfix("3 + 4");
		i2p.convertToPostfix();
		i2p.printInPost();

		i2p = new InfixToPostfix("7 / 9");
		i2p.convertToPostfix();
		i2p.printInPost();
		
		i2p = new InfixToPostfix("(6 + 2) * 5 - 8 / 4");
		i2p.convertToPostfix();
		i2p.printInPost();   //    6 2 + 5 * 8 4 / -
		
		i2p = new InfixToPostfix("(300+23)*(43-21)/(84+7)");
		i2p.convertToPostfix();
		i2p.printInPost();    //  300 23 + 43 21 -* 84 7 + /

		i2p = new InfixToPostfix("(4+8)*(6-5)/((3-2)*(2+2))");
		i2p.convertToPostfix();
		i2p.printInPost();    //  4 8 + 6 5 -* 3 2 –2 2 + *
		
		i2p = new InfixToPostfix("7.324242 / 9.9817231");
		i2p.convertToPostfix();
		i2p.printInPost();
		
		i2p = new InfixToPostfix("(0.1231+23)*(43.111-0.3333)/(84+7)");
		i2p.convertToPostfix();
		i2p.printInPost();    //  300 23 + 43 21 -* 84 7 + /

		i2p = new InfixToPostfix("george / sam");
		i2p.convertToPostfix();
		i2p.printInPost();
		
		i2p = new InfixToPostfix("(douglas+edward)*(akira-stephen)/(izdor+michael)");
		i2p.convertToPostfix();
		i2p.printInPost();    //  300 23 + 43 21 -* 84 7 + /

		i2p = new InfixToPostfix("george / 0.2222");
		i2p.convertToPostfix();
		i2p.printInPost();
		
		i2p = new InfixToPostfix("(douglas+2.111)*(3-stephen)/(0.52342+michael)");
		i2p.convertToPostfix();
		i2p.printInPost();    //  300 23 + 43 21 -* 84 7 + /
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter an infix math expression: ");
		i2p = new InfixToPostfix(input.nextLine());		
		i2p.convertToPostfix();

	}
}
