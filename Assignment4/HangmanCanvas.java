/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		//remove(all);
		GLine scaffold = new GLine( 600 / 8, 100 + SCAFFOLD_HEIGHT, 600 / 8, 100);
		GLine beam = new GLine (600 / 8, 100, (600/8) + BEAM_LENGTH, 100);
		GLine rope = new GLine ((600/8) + BEAM_LENGTH, 100, (600/8) + BEAM_LENGTH, 100 + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		double x = 600 /8;
		double y = 200 + SCAFFOLD_HEIGHT;
		GLabel wordlabel = new GLabel(word, x, y);
		if (getElementAt(x,y) != null) remove(getElementAt(x,y));
		add(wordlabel);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		numberOfGuesses++;
		addNextPart(numberOfGuesses);
		addIncorrectLetter(letter);
	}
	
	private void addIncorrectLetter(char letter){
		double x = 600 /8;
		double y = 250 + SCAFFOLD_HEIGHT;
		GLabel incorrectletters = new GLabel(letters + letter, x, y);
		letters = letters + letter;
		if (getElementAt(x, y) != null) remove(getElementAt(x, y));
		add(incorrectletters);
	}
	
	/*
	 * The following methods make sure the hangman is drawed.
	 * The first method checks the number of wrong guesses.
	 * Then the appropriate method is called. (Named conveniently)
	 */
	private void addNextPart(int numberOfGuesses) {
		if ( numberOfGuesses == 1 ) drawHead();
		if ( numberOfGuesses == 2 ) drawBody();
		if ( numberOfGuesses == 3 ) drawLeftArm();
		if ( numberOfGuesses == 4 ) drawLeftHand();
		if ( numberOfGuesses == 5 ) drawRightArm();
		if ( numberOfGuesses == 6 ) drawRightHand();
		if ( numberOfGuesses == 7 ) drawLeftLeg();
		if ( numberOfGuesses == 8 ) drawRightLeg();
	}
	
	private void drawHead() {
		GOval head = new GOval( CANVAS_CENTRE - HEAD_RADIUS, 100 + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head);
	}
	
	private void drawBody() {
		int x = CANVAS_CENTRE;
		int y = 100 + ROPE_LENGTH + (HEAD_RADIUS * 2);
		GLine body = new GLine( x, y, x, y + BODY_LENGTH);
		add(body);
		}
	
	private void drawLeftArm() {
		int x = CANVAS_CENTRE;
		int y = 100 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
		GLine arm = new GLine( x, y, x - UPPER_ARM_LENGTH, y);
		add(arm);
	}
	
	private void drawLeftHand() {
		int x = CANVAS_CENTRE - UPPER_ARM_LENGTH;
		int y = 100 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
		GLine hand = new GLine( x, y, x, y + LOWER_ARM_LENGTH);
		add(hand);
	}
	
	private void drawRightArm() {
		int x = CANVAS_CENTRE;
		int y = 100 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
		GLine arm = new GLine( x, y, x + UPPER_ARM_LENGTH, y);
		add(arm);
	}
	
	private void drawRightHand() {
		int x = CANVAS_CENTRE + UPPER_ARM_LENGTH;
		int y = 100 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
		GLine hand = new GLine( x, y, x, y + LOWER_ARM_LENGTH);
		add(hand);
	}
	
	private void drawLeftLeg() {
		int x = CANVAS_CENTRE;
		int y = 100 + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
		GLine hip = new GLine(x, y, x - ( HIP_WIDTH / 2), y);
		add(hip);
		GLine leg = new GLine(x -(HIP_WIDTH/2), y, x -(HIP_WIDTH/2), y + LEG_LENGTH);
		add(leg);
		GLine foot = new GLine(x - (HIP_WIDTH/2), y + LEG_LENGTH, x - (HIP_WIDTH/2) - FOOT_LENGTH, y + LEG_LENGTH);
		add(foot);	
	}
	
	private void drawRightLeg() {
		int x = CANVAS_CENTRE;
		int y = 100 + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
		GLine hip = new GLine(x, y, x + ( HIP_WIDTH / 2), y);
		add(hip);
		GLine leg = new GLine(x +(HIP_WIDTH/2), y, x +(HIP_WIDTH/2), y + LEG_LENGTH);
		add(leg);
		GLine foot = new GLine(x + (HIP_WIDTH/2), y + LEG_LENGTH, x + (HIP_WIDTH/2) + FOOT_LENGTH, y + LEG_LENGTH);
		add(foot);	
		
	}
		

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private int numberOfGuesses = 0;
	private static final int CANVAS_CENTRE = (600/8) + BEAM_LENGTH;
	private String letters = "Previous wrong guesses:";
}
