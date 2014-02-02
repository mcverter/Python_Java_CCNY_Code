//  Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: default
// File: TextDisplayGUI.java
//
// Objects: 
//   (1) TextDisplayGUI
//   (2) AdjustableTextArea (inner class)
//   (3) EventHandlers (anonymous inner classes)
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextDisplayGUI extends JFrame
{
	// font for buttons and other GUI controls
	Font controlsFont;  

	// text area
	private AdjustableTextArea typingArea;

	// change font size controls
	private JButton increaseFont;
	private JButton decreaseFont;

	// toggle bold font
	private JCheckBox boldChooser;

	// font style chooser
	String [] styleFonts = {"Serif", "Sans-serif", "Monospaced"};
	private JComboBox styleChooser;

	// change display colors
	private JButton changeBackgroundColor;
	private JButton changeTextColor;
	private JColorChooser colorChooser;


	// METHOD:  Constructor
	// Initializes and displays the GUI
	TextDisplayGUI()
	{
		super("Text Display GUI");

		// create typing area
		typingArea = new AdjustableTextArea ("Type text here", 40, 40);

		// set default font for buttons and other components
		controlsFont = new Font(Font.SERIF, Font.PLAIN, 20);

		// initialize increaseFont button
		increaseFont = new JButton("Larger");
		increaseFont.setFont(controlsFont);
		increaseFont.setToolTipText("Click me to make the text bigger!");
		increaseFont.addActionListener( 
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{	
						typingArea.increaseFont();
					}
				}	
				);


		// initialize decreaseFont button		
		decreaseFont = new JButton("Smaller");
		decreaseFont.setFont(controlsFont);
		decreaseFont.setToolTipText("Click me to make the text smaller!");
		decreaseFont.addActionListener( 
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{	
						typingArea.decreaseFont();
					}
				}	
				);


		// initialize boldChooser checkBox		
		boldChooser = new JCheckBox ("Bold");
		boldChooser.setFont(controlsFont);
		boldChooser.setToolTipText("Click me to change the darkness of the text!");
		boldChooser.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged (ItemEvent e)
					{
						if (boldChooser.isSelected())
						{
							typingArea.setBoldness(true);
						}
						else
						{
							typingArea.setBoldness(false);							
						}
					}
				}
				);

		// initialize styleChooser comboBox		
		styleChooser = new JComboBox(styleFonts);
		styleChooser.setFont(controlsFont);
		styleChooser.setToolTipText("Click me to change the style of the text!");
		styleChooser.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged (ItemEvent e)
					{
						if ( e.getStateChange() == ItemEvent.SELECTED )
						{
							typingArea.setFontStyle(
									styleFonts[styleChooser.getSelectedIndex()]);						
						}
					}
				}
				);

		// Initialize color controls
		colorChooser = new JColorChooser();

		// Initialize background color chooser
		changeBackgroundColor = new JButton("Change Background Color");
		changeBackgroundColor.setFont(controlsFont);
		changeBackgroundColor.addActionListener(
				new ActionListener()	
				{
					public void actionPerformed( ActionEvent event )
					{
						Color c = JColorChooser.showDialog(
								colorChooser, "Choose a background color", 
								typingArea.getBackground() );
						if ( c != null )
						{
							typingArea.setBackground(c);
						}
					}
				}
				);

		// initialize text color chooser
		changeTextColor = new JButton("Change Text Color");
		changeTextColor.setFont(controlsFont);
		changeTextColor.addActionListener(
				new ActionListener()	
				{
					public void actionPerformed( ActionEvent event )
					{
						Color c = JColorChooser.showDialog(
								colorChooser, "Choose a text color", 
								typingArea.getForeground() );
						if ( c != null )
						{
							typingArea.setForeground(c);
						}
					}
				}
				);

		// lay out the components
		JPanel sizePanel = new JPanel();
		sizePanel.setLayout(new FlowLayout());
		sizePanel.add (increaseFont);
		sizePanel.add (decreaseFont);

		JPanel fontTypePanel = new JPanel();
		fontTypePanel.setLayout(new FlowLayout());
		fontTypePanel.add (styleChooser, BorderLayout.EAST);
		fontTypePanel.add(boldChooser);


		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new FlowLayout());
		colorPanel.add (changeBackgroundColor);
		colorPanel.add (changeTextColor);

		this.setLayout(new GridLayout(4, 1, 1, 1));
		this.add (sizePanel);
		this.add(fontTypePanel);
		this.add(colorPanel);
		this.add (new JScrollPane(typingArea));

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
		pack();
		setVisible(true);	
	}

	//
	//  Inner class:  AdjustableTextArea extends JTextArea
	//
	//  This class implements a text area whose font can be
	//    altered in terms of weight, style, and size and color.
	//    The color of the background can also be altered.
    //  It implements this flexibility through functions which 
	//    allow one to alter these parameters
	
	class AdjustableTextArea extends JTextArea
	{
		String fontStyle = "Serif";
		int fontWeight = Font.PLAIN;
		int fontSize = 18;
		
		//
		// METHOD :  Default constructor
		//
		// This constructor is never called in the 
		//   present program but may be useful in
		//   a future implementation
		AdjustableTextArea()
		{
			super();
			super.setLineWrap(true);
			setLineWrap(true);
			setFont(new Font(fontStyle, fontWeight, fontSize));
		}
		
		//
		// METHOD :  Constructor (String, int, int)
		//  
		// Creates an AdjustableTextArea containing
		//   defaultString, which has height X width dimensions
		AdjustableTextArea(String defaultString, int height, int width)
		{
			super(defaultString, height, width);
			super.setLineWrap(true);
			setFont(new Font(fontStyle, fontWeight, fontSize));
		}

		void setBoldness(boolean bold)
		{
			if (bold == true)
			{
				fontWeight = Font.BOLD;
			}
			else
			{
				fontWeight = Font.PLAIN;
			}
			setFont(new Font(fontStyle, fontWeight, fontSize));
		}
		void increaseFont()
		{
			fontSize++;
			setFont(new Font(fontStyle, fontWeight, fontSize));
		}
		void decreaseFont()
		{
			fontSize--;
			setFont(new Font(fontStyle, fontWeight, fontSize));
		}
		void setFontStyle(String style)
		{
			fontStyle = style;
			setFont(new Font(fontStyle, fontWeight, fontSize));
		}
		// inherited methods
		// getForeground, getBackground, setForeground, setBackground
		//
		// The following four functions implement methods of the 
		//   java.awt.Component superclass
		//
		// They are explicitly declared in this class so that 
		//   a future programmer can locate method calls from
 		//   the TextDisplayGUI class
		
		@Override
		public Color getForeground()
		{
			return super.getForeground();
		}
		@Override
		public Color getBackground()
		{
			return super.getBackground();
		}
		@Override
		public void setForeground(Color c)
		{
			super.setForeground(c);
		}
		@Override
		public void setBackground(Color c)
		{
			super.setBackground(c);
		}
	}

	//
	// METHOD: main(String[])
	//
	// This function creates a TextDisplayGUI
	public static void main (String args[])
	{
		TextDisplayGUI myTDG = new TextDisplayGUI();

	}
}
