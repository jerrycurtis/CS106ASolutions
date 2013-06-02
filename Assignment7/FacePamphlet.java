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
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {
	
	private JButton statusbutton;
	private JTextField statusfield;
	private JButton picturebutton;
	private JTextField picturefield;
	private JButton friendbutton;
	private JTextField friendfield;
	private JTextField namefield;
	private FacePamphletProfile currentprofile = null;
	private FacePamphletDatabase profiles = new FacePamphletDatabase();
	private FacePamphletCanvas canvas;
	
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
		namefield = new JTextField(TEXT_FIELD_SIZE);
		JButton add_button = new JButton("Add");
		JButton delete_button = new JButton("Delete");
		JButton lookup_button = new JButton("Lookup");
		
		add(namelabel, NORTH);
		add(namefield, NORTH);
		add(add_button, NORTH);
		add(delete_button, NORTH);
		add(lookup_button, NORTH);
		
		addActionListeners();
		canvas = new FacePamphletCanvas();
		add(canvas);
    } 
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == statusfield || e.getActionCommand().equals("Change Status")) {
			updateStatus();
			//showCurrentProfile();
		}
		else if (e.getSource() == picturefield || e.getActionCommand().equals("Change Picture")) {
			updatePicture();
			//showCurrentProfile();
		}
		else if (e.getSource() == friendfield || e.getActionCommand().equals("Add Friend")) {
			addFriend();
			//showCurrentProfile();
		}
		else if (e.getActionCommand().equals("Add")) {
			addName();
			//showCurrentProfile();
		}
		else if (e.getActionCommand().equals("Delete")) {
			deleteName();
			//showCurrentProfile();
		}
		else if (e.getActionCommand().equals("Lookup")) {
			lookUpName();
			//showCurrentProfile();
		}
			
	}
    
    private void updateStatus() {
    	String status = statusfield.getText();
    	if (currentprofile != null) {
    		currentprofile.setStatus(currentprofile.getName() + " is " + status);
    		canvas.displayProfile(currentprofile);
    		canvas.showMessage("Status updated!");
    	} else {
    		canvas.showMessage("Please select a profile to change status");
    	}
    }
    
    private void updatePicture() {
    	String filename = picturefield.getText();
    	if (currentprofile != null) {
    		GImage picture = null;
    		try {
    			picture = new GImage("images/" + filename);
    			currentprofile.setImage(picture);
    			canvas.displayProfile(currentprofile);
    			canvas.showMessage("Image updated!");
    		} catch (ErrorException ex) {
    			canvas.showMessage("Cannot open image");
    		}
    	} else {
    		canvas.showMessage("Please select a profile to change image");
    	}
    }
    
    private void addFriend() {
    	String newFriend = friendfield.getText();
    	if (!currentprofile.getFriends().contains(newFriend)) {
    	currentprofile.getFriends().add(newFriend);
    	//finds the newly added friend's profile, gets his/her list of friends, and adds the current user's (adder's) name to that list
    	profiles.getProfile(newFriend).getFriends().add(currentprofile.getName());
    	canvas.displayProfile(currentprofile);
    	canvas.showMessage(newFriend + " has been added as a friend.");
    	} else {
    	canvas.showMessage(currentprofile.getName() + " is already friends with " + newFriend);
    	}
    	}
    
    private void addName() {
    	String addname = namefield.getText();
    	if (profiles.containsProfile(addname)) {
    		canvas.showMessage("A profile with the name " + addname + " already exists");
    	} else {
    		FacePamphletProfile profile = new FacePamphletProfile(addname);
    		profiles.addProfile(profile);
    		currentprofile = profiles.getProfile(addname);
    		canvas.displayProfile(currentprofile);
    		canvas.showMessage("New profile created");
    	}
    }
    
    private void deleteName() {
    	String deletename = namefield.getText();
    	if (profiles.containsProfile(deletename)) {
    		profiles.deleteProfile(deletename);
    		currentprofile = null;
    		canvas.removeAll();
    		canvas.showMessage("Deleted " + deletename);
    	} else {
    		canvas.showMessage("Cannot delete: profile with name " + deletename + " does not exist.");
    	}
    }
    
    private void lookUpName() {
    	String lookupname = namefield.getText();
    	if (profiles.containsProfile(lookupname)) {
    		currentprofile = profiles.getProfile(lookupname);
    		canvas.displayProfile(currentprofile);
    		canvas.showMessage("Displaying " + lookupname);		
    	} else {
    		canvas.showMessage("Profile with name " +lookupname+ " does not exist.");
    	}
    }

}
