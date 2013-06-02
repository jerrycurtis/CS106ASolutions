/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		//Inits the first applicationMessage (blank)
		add(applicationMessage);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		remove(applicationMessage);
		applicationMessage = new GLabel(msg);
		applicationMessage.setFont(MESSAGE_FONT);
		applicationMessage.setLocation((getWidth() - applicationMessage.getWidth()) / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
        add(applicationMessage);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		displayName(profile);
		displayStatus(profile);
		displayImage(profile);
		displayFriends(profile);
	}
	
	private void displayName(FacePamphletProfile profile) {
		String name = profile.getName();
		namelabel = new GLabel(name);
		namelabel.setLocation(LEFT_MARGIN, TOP_MARGIN + namelabel.getAscent());
		namelabel.setColor(Color.blue);
		namelabel.setFont(PROFILE_NAME_FONT);
		add(namelabel);
	}
	
	private void displayImage(FacePamphletProfile profile) {
		GPoint imageStartPoint = new GPoint(LEFT_MARGIN, TOP_MARGIN + (IMAGE_MARGIN * 2));
		if (profile.getImage() == null) {
		GRect no_image = new GRect(imageStartPoint.getX(), imageStartPoint.getY(), IMAGE_WIDTH, IMAGE_HEIGHT);
		GLabel no_image_label = new GLabel("No Image");
		no_image_label.setLocation(imageStartPoint.getX() + (no_image.getWidth()/2) - (no_image_label.getWidth() / 2), imageStartPoint.getY() - (no_image.getHeight() /2 ));
		add(no_image_label);
		add(no_image);
		} else {
			GImage profilepicture = profile.getImage();
			profilepicture.setLocation(imageStartPoint.getX(), imageStartPoint.getY());
			profilepicture.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(profilepicture);
		}
	}
	
	private void displayStatus(FacePamphletProfile profile) {
		String status = profile.getStatus();
		if (status == "") status = "No current status";
		GLabel statuslabel = new GLabel(status);
		statuslabel.setLocation(LEFT_MARGIN, TOP_MARGIN + namelabel.getHeight() + IMAGE_MARGIN + IMAGE_HEIGHT + IMAGE_MARGIN);
		statuslabel.setFont(PROFILE_STATUS_FONT);
		add(statuslabel);
		}
	
	private void displayFriends(FacePamphletProfile profile) {
		double x = getWidth() / 2;
		double y = TOP_MARGIN + (IMAGE_MARGIN *2);
		GLabel friendheader = new GLabel("Friends: ");
		friendheader.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendheader.setLocation(x, y);
		add(friendheader);
		double y_friend = TOP_MARGIN + (IMAGE_MARGIN *2) + friendheader.getHeight();
		for ( int i = 0; i < profile.getFriends().size(); i ++) {
			String friend = profile.getFriends().get(i);
			friendlabel = new GLabel(friend, x, y_friend);
			friendlabel.setFont(PROFILE_FRIEND_FONT);
			add(friendlabel);
			y_friend += friendlabel.getHeight();
		}
	}

	private GLabel applicationMessage = new GLabel("");
	private GLabel namelabel;
	private GLabel friendlabel;
}
