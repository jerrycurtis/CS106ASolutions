/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {
	
	private JButton statusbutton;
	private JTextField statusfield;
	private JButton picturebutton;
	private JTextField picturefield;
	private JButton friendbutton;
	private JTextField friendfield;
	
	public static void main(String[] args) {
	    new FacePamphlet().start(args);
	}


	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		//Inits the WEST interactors
		statusfield = new JTextField(TEXT_FIELD_SIZE);
		statusbutton = new JButton("Change Status");
		picturefield = new JTextField(TEXT_FIELD_SIZE);
		picturebutton = new JButton("Change Picture");
		friendfield = new JTextField(TEXT_FIELD_SIZE);
		friendbutton = new JButton("Add Friend");
		
		add(statusfield, WEST);
		add(statusbutton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(picturefield, WEST);
		add(picturebutton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendfield, WEST);
		add(friendbutton, WEST);
		
		statusfield.addActionListener(this);
		friendfield.addActionListener(this);
		picturefield.addActionListener(this);
		
		//Inits the NORTH interactors
		JLabel namelabel = new JLabel("Name");
		JTextField namefield = new JTextField(TEXT_FIELD_SIZE);
		JButton add_button = new JButton("Add");
		JButton delete_button = new JButton("Delete");
		JButton lookup_button = new JButton("Lookup");
		
		add(namelabel, NORTH);
		add(namefield, NORTH);
		add(add_button, NORTH);
		add(delete_button, NORTH);
		add(lookup_button, NORTH);
		
		addActionListeners();
    } 
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == statusfield || e.getActionCommand().equals("Change Status")) {
			updateStatus();
		}
		else if (e.getSource() == picturefield || e.getActionCommand().equals("Change Picture")) {
			updatePicture();
		}
		else if (e.getSource() == friendfield || e.getActionCommand().equals("Add Friend")) {
			updateFriend();
		}
		else if (e.getActionCommand().equals("Add")) {
			//addName();
		}
		else if (e.getActionCommand().equals("Delete")) {
			//deleteName();
		}
		else if (e.getActionCommand().equals("Lookup")) {
			//friendLookup();
		}
			
	}
    
    private void updateStatus() {
    	String status = statusfield.getText();
    	println("Status: " + status);
    }
    
    private void updatePicture() {
    	String status = statusfield.getText();
    	println("Picture: " + status);
    }
    
    private void updateFriend() {
    	String status = statusfield.getText();
    	println("Friend: " + status);
    }

}
