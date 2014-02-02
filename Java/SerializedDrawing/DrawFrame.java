// Name: Mitchell Verter
// For: Akira Kawaguchi
// CS 221 : Software Design Lab
//
// Package: default
// File: DrawFrame.java
//
// Object: DrawFrame  extends JFrame
//
// Draw Frame is the top level container for 
//  all objects in the current project
// It contains a DrawPanel for making pictures 
//  and all of the various GUI controls for 
//  determining its behavior.
// It also contains buttons which control the
//   input and output serialization of drawings


import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

class DrawFrame extends JFrame
{

	DrawPanel myDrawing;
	
	JComboBox selectColor;  // determine shape features
	JComboBox selectShape;
	JCheckBox fillChooser;

	JButton undo;           // undo drawings
	JButton clearAll;
	

	JButton load;           // object serialization
	JButton save;   
	JFileChooser fileChooser;

	JLabel mouseLocationLabel;

	DrawFrame()
	{

		// create DrawPanel
		mouseLocationLabel = new JLabel("");
		myDrawing = new DrawPanel(mouseLocationLabel);

		// UNDO button erases last shape
		undo = new JButton("UNDO");
		undo.setToolTipText("Erases last shape");
		undo.addActionListener( 
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{	
						myDrawing.clearLastShape();
					}
				}	
				);


		// CLEAR ALL button erases all shapes
		clearAll = new JButton("CLEAR ALL");
		clearAll.setToolTipText("Erases all shapes");
		clearAll.addActionListener( 
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{	
						myDrawing.clearDrawing();
					}
				}	
				);


		//  selectColor comboBox for choosing color
		selectColor = new JComboBox(DrawConstants.colorNames);
		selectColor.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged (ItemEvent e)
					{
						if ( e.getStateChange() == ItemEvent.SELECTED )
						{
							myDrawing.setCurrentColor(selectColor.getSelectedIndex());						
						}
					}
				}
				);



		//  selectShape combo box for selecting the shape to draw.
		selectShape = new JComboBox(DrawConstants.shapeNames);
		selectShape.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged (ItemEvent e)
					{
						if ( e.getStateChange() == ItemEvent.SELECTED )
						{
							myDrawing.setShapeType(selectShape.getSelectedIndex());						
						}
					}
				}
				);

		//   fillChooser checkbox to specify whether shape should be filled or not. 
		fillChooser = new JCheckBox ("FILL");
		fillChooser.setToolTipText("Click me to create a filled shape");
		fillChooser.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged (ItemEvent e)
					{
						if (fillChooser.isSelected())
						{
							myDrawing.setFilledShape(true);
						}
						else
						{
							myDrawing.setFilledShape(false);							
						}
					}
				}
				);

		// LOAD button to load a previous drawing
		load = new JButton("LOAD");
		load.setToolTipText("Load a previous drawing.");
		load.addActionListener( 
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{	
						fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
						int result = fileChooser.showOpenDialog(DrawFrame.this); 
						if (result != JFileChooser.CANCEL_OPTION)
						{
							File file = fileChooser.getSelectedFile(); // get File
							if ( ( file != null ) && !( file.getName().equals( "" ) ) )
							{
								myDrawing.loadDrawing(file);
							} 
						}

					}
				}	
				);

		// SAVE button to save current drawing 
		save = new JButton("SAVE");
		save.setToolTipText("Save current drawing.");
		save.addActionListener( 
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{	
						fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
						int result = fileChooser.showOpenDialog(DrawFrame.this); 
						if (result != JFileChooser.CANCEL_OPTION)
						{
							File file = fileChooser.getSelectedFile(); 
							if ( ( file != null ) && !( file.getName().equals( "" ) ) )
							{
								myDrawing.saveDrawing(file);
							} 
						}

					}
				}	
				);


		// Layout components
		
		//  Control GUI
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new FlowLayout());
		controlsPanel.add(undo);
		controlsPanel.add(clearAll);
		controlsPanel.add(selectColor);
		controlsPanel.add(selectShape);
		controlsPanel.add(fillChooser);

		// mouse location & serialization GUI
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(mouseLocationLabel, BorderLayout.WEST);
		JPanel serializationPanel = new JPanel(new GridLayout(1,2));
		serializationPanel.add(save);
		serializationPanel.add(load);
		bottomPanel.add(serializationPanel, BorderLayout.EAST);

		
		// Lay out panels
		this.setLayout(new BorderLayout());
		myDrawing.setPreferredSize(new Dimension(500,500));
		this.add(controlsPanel, BorderLayout.NORTH);
		this.add(myDrawing, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.pack();		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// METHOD:  main()
	//   create a DrawFrame object
	public static void main(String args[])

	{	
		DrawFrame df = new DrawFrame();
	}

}
