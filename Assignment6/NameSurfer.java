/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

//Changed Program to ConsoleProgram
public class NameSurfer extends Program implements NameSurferConstants {
	
	public static void main(String[] args) {
	    new NameSurfer().start(args);
	}

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	    initializeInteractors();
	    addActionListeners();
	    display = new NameSurferGraph();
	    add(display);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == namefield || e.getSource() == graph) {
			/*
			 * Milestone 1 code:
			 * println("Graph: " + namefield.getEntry());
			 */
			NameSurferEntry decadeRanking = namesdb.findEntry(namefield.getText());
			if (decadeRanking != null) {
				display.addEntry(decadeRanking);
				display.update();			}
		} else if ( e.getSource() == clear) {
			display.clear();
			display.update();
		}
	}

	private void initializeInteractors() {
		JLabel name = new JLabel("Name");
		namefield = new JTextField(20);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		
		add(name, SOUTH);
		add(namefield, SOUTH);
		add(graph, SOUTH);
		add(clear, SOUTH);
	}

	//Declare as instance variables so actionPerformed knows what they are
	private JTextField namefield;
	private JButton graph;
	private JButton clear;
	private NameSurferGraph display;
	private NameSurferDataBase namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
}
