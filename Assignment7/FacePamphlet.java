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

public class FacePamphlet extends ConsoleProgram 
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
    } 
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == statusfield || e.getActionCommand().equals("Change Status")) {
			updateStatus();
			showCurrentProfile();
		}
		else if (e.getSource() == picturefield || e.getActionCommand().equals("Change Picture")) {
			updatePicture();
			showCurrentProfile();
		}
		else if (e.getSource() == friendfield || e.getActionCommand().equals("Add Friend")) {
			addFriend();
			showCurrentProfile();
		}
		else if (e.getActionCommand().equals("Add")) {
			addName();
			showCurrentProfile();
		}
		else if (e.getActionCommand().equals("Delete")) {
			deleteName();
			showCurrentProfile();
		}
		else if (e.getActionCommand().equals("Lookup")) {
			lookUpName();
			showCurrentProfile();
		}
			
	}
    
    private void showCurrentProfile() {
    	if (currentprofile != null) {
    		String currentProfileName = currentprofile.getName();
    		println("--> Current profile: " +  profiles.getProfile(currentProfileName).toString());
    	} else {
    		println("--> No current profile");
    	}
    }
    
    private void updateStatus() {
    	String status = statusfield.getText();
    	if (currentprofile != null) {
    		currentprofile.setStatus(status);
    		println("Status update to "+ currentprofile.getStatus());
    	} else {
    		println("Please select a profile to change status");
    	}
    }
    
    private void updatePicture() {
    	String filename = picturefield.getText();
    	if (currentprofile != null) {
    		GImage picture = null;
    		try {
    			picture = new GImage(filename);
    			println("Image updated!");
    		} catch (ErrorException ex) {
    			println("Cannot open image");
    		}
    	} else {
    		println("Please select a profile to change image");
    	}
    }
    
    private void updateFriend() {
    	String friend = friendfield.getText();
    	//If there is no profile:
    	if (currentprofile == null) {
    		println("Add friend: No profile selected. Please select a profile to add friend");
    		return;
    	}
    	//If input does not exist
    	if (profiles.containsProfile(friend) == false) {
    		println("Add friend: profile " + friend + " does not exist!");
    		return;
    		}
    	//Own name...
    	
    	if (currentprofile.getName().equals(friend)) {
    		println("You are already friends with yourself! FOR LIFE!");
    		return;
    	}
        FacePamphletProfile friendProfile = profiles.getProfile(friend);
        //checks to see if the user is already friends with the friend name entered
      
        //if the user and the friend entered are not friends, makes them friends
        if(currentprofile.addFriend(friend) == true) {
        friendProfile.addFriend(currentprofile.getName());
        
        println(friend + " added as a friend.");
        } else {
    		println("Still happy");
    	}
    	
    }
    
    private void addFriend() {
    	String newFriend = friendfield.getText();
    	if (!currentprofile.getFriends().contains(newFriend)) {
    	currentprofile.getFriends().add(newFriend);
    	//finds the newly added friend's profile, gets his/her list of friends, and adds the current user's (adder's) name to that list
    	profiles.getProfile(newFriend).getFriends().add(currentprofile.getName());
    	//FacePamphletCanvas.displayProfile(currentprofile);
    	println(newFriend + " has been added as a friend.");
    	} else {
    	println(currentprofile.getName() + " is already friends with " + newFriend);
    	}
    	}
    
    private void addName() {
    	String addname = namefield.getText();
    	if (profiles.containsProfile(addname)) {
    		println("Profile for " + addname + " already excists: " + profiles.getProfile(addname).toString());
    	} else {
    		FacePamphletProfile profile = new FacePamphletProfile(addname);
    		profiles.addProfile(profile);
    		currentprofile = profiles.getProfile(addname);
    		println("Add: new profile: " + profiles.getProfile(addname).toString());
    	}
    }
    
    private void deleteName() {
    	String deletename = namefield.getText();
    	if (profiles.containsProfile(deletename)) {
    		profiles.deleteProfile(deletename);
    		println("Delete: profile of " + deletename + " deleted.");
    		currentprofile = null;
    	} else {
    		println("Delete: profile with name " + deletename + " does not exist.");
    	}
    }
    
    private void lookUpName() {
    	String lookupname = namefield.getText();
    	if (profiles.containsProfile(lookupname)) {
    		println("Lookup: " + profiles.getProfile(lookupname).toString());
    		currentprofile = profiles.getProfile(lookupname);
    	} else {
    		println("Lookup: profile with name " +lookupname+ " does not exist.");
    	}
    }

}
