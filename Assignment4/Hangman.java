/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanLexicon hangmanWords = new HangmanLexicon();
	private HangmanCanvas canvas;
	
	/* Guesses the player has */
	private static final int WRONG_GUESSES = 8;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
    public void run() {
    	setupGame();
		playGame();
	}
    
    private void setupGame() {
    	setSize(600,800);
    	canvas.reset();
		println("Welcome to my Hangman game! Enjoy!");
		println("The word now looks like this: " + hiddenWord());
		canvas.displayWord(word);
    }
    
    private void playGame() {
    	    while ( guessesCounter > 0) {
    		println("You have " + guessesCounter + " guesses left!");
    		String input = readLine("Your guess:");
    		input = input.toUpperCase();
    		letter = input.charAt(0);
    		checkWord();
    		if (word.equals(chosenWord)) break;
    		println("The word now looks like this: " + word);
    		}
    	if (guessesCounter == 0) gameOver();
    	else youWin();
    	}
    
    private void gameOver() {
    	println("GAME OVER! :( TRY AGAIN!!");
    	println("This was the chosen word: " + chosenWord);
    }
    
    private void youWin() {
    	println("You guessed the right word: " + chosenWord);
    	println("YOU WIN!!!!!");
    }
    
    private void checkWord() {
    	if (chosenWord.indexOf(letter) != -1) {
    			for (int i = 0; i < chosenWord.length(); i++) {
    				if (chosenWord.charAt(i) == letter && word.charAt(i) != letter) {
    					println("That guess is correct!!");
    					if ( i > 0 ) {
    						word = word.substring(0, i) + letter + word.substring( i + 1);
    					}
    					if ( i == 0 ) {
    						word = letter + word.substring(1);
    					}
    				}
    			}
    		} 
    		else {
    			println("There are no " + letter + "'s in the word!");
    			canvas.noteIncorrectGuess(letter);
    			guessesCounter--;
    			 }
    	canvas.displayWord(word);
    	}
    
    /*Turns the choosen word into the first state of the hidden word.
     * -----
     */
    
    private String hiddenWord() {
    	word = "";
    	for ( int i = 0; i < chosenWord.length(); i++) {
    		word = "-" + word;
    	}
    	return word;
    	
    	
    }
    	
    /* Makes the program choose a word from the HangmanLexicon.
     * Still need to make sure it will generate the number of words automatically.
     */
    
    private String chooseWord() {
    	int wordCount = hangmanWords.getWordCount();
    	int index = rgen.nextInt(0, wordCount);
    	String word = hangmanWords.getWord(index);
    	return word;
    }
    
    /* Problem Set 4.2 Solution. */
    
    public String removeAllOccurances(String str, char ch) {
    	String result = "";
    	/* Loop rebuilds the word, but if it encounters ch, it doesnt add it to the result */
    	for (int i = 0; i < str.length(); i++) {
    		if (str.charAt(i) != ch) result += str.substring(i);
    	}
    		return result;
    	}
    
    private String chosenWord = chooseWord(); //instance variable of the chosenword!
    private char letter; // instance variable of the letter chosen on that turn.
    private String word; //instance variable keeping track of the hidden word.
    private int guessesCounter = WRONG_GUESSES; //instance variable that keeps tracks of the number of guesses.
    	
}
