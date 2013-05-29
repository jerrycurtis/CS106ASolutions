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
public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		setSize(APPLICATION_WIDTH - 100, APPLICATION_HEIGHT - 400);
	    initializeInteractors();
	    addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == namefield || e.getSource() == graph) {
			println("Graph: \"" + namefield.getText() + "\"");
		} else if ( e.getSource() == clear) {
			println("Clear");
		}
	}

	private void initializeInteractors() {
		JLabel name = new JLabel("Name");
		namefield = new JTextField(20);
		graph = new JButton("Graph");
		clear = new JButton("Graph");
		
		add(name, SOUTH);
		add(namefield, SOUTH);
		add(graph, SOUTH);
		add(clear, SOUTH);
	}

	//Declare as instance variables so actionPerformed knows what they are
	private JTextField namefield;
	private JButton graph;
	private JButton clear;

}
