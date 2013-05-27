/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		//Declare the arrays keeping track of scores and selected categories here. (because of public method)
		scoreboard = new int[nPlayers + 1][N_CATEGORIES + 1];
		selected_category = new int[nPlayers + 1][N_CATEGORIES + 1];
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		//Two loops to determine gamelength.
		//Gameturns (stops when categories are full) & playerturns.
		for (int i = 1; i <= N_CATEGORIES; i++) {
			for (int j = 1; j <= nPlayers; j++) {
				playerTurn(j);
			}
		}
		calculateBonus();
		for (int playerNumber = 1; playerNumber <= nPlayers; playerNumber++) {
			calculateTotalScores(playerNumber);
			display.updateScorecard(UPPER_SCORE, playerNumber, scoreboard[playerNumber][UPPER_SCORE]);
			display.updateScorecard(UPPER_BONUS, playerNumber, scoreboard[playerNumber][UPPER_BONUS]);
			display.updateScorecard(LOWER_SCORE, playerNumber, scoreboard[playerNumber][LOWER_SCORE]);
			display.updateScorecard(TOTAL, playerNumber, scoreboard[playerNumber][TOTAL]);
		}
		int winner = calculateWinner();
		display.printMessage("Congrats, " + playerNames[winner - 1] + "! You won!");
	}
	
	/*
	 * Pre-condition: playGame() passed the player number.
	 * Post-condition: Player completed his turn. Scores are calculated and displayed
	 */
	private void playerTurn(int playerNumber) {
		display.printMessage("It's " + playerNames[playerNumber-1] + "'s turn. Roll your dice!");
		display.waitForPlayerToClickRoll(playerNumber);
	 	firstRoll();
	 	display.displayDice(rolledDice);
	 	display.printMessage("It's time for your second roll, " + playerNames[playerNumber-1] + ". Select the dice you want to re-roll!");
	 	display.waitForPlayerToSelectDice();
	 	nextRoll();
	 	display.displayDice(rolledDice);
	 	display.printMessage("It time for the final roll this turn! Last chance!");
	 	display.waitForPlayerToSelectDice();
	 	nextRoll();
	 	display.displayDice(rolledDice);
	 	display.printMessage("These are your final dice, " + playerNames[playerNumber-1] + ". Select a category.");
	 	calculateAndDisplayScore(playerNumber);
	}
	
	/*
	 * Pre-condition: Player has rolled the dice and has to choose a category.
	 * Post-condition: Chosen category is selectable, score is calculated and displayed
	 */
	private void calculateAndDisplayScore(int playerNumber) {
		int category = display.waitForPlayerToSelectCategory();
		while ( selected_category[playerNumber][category] == 1 || YahtzeeMagicStub.checkCategory(rolledDice, category) == false) {
			display.printMessage("Invalid category! Choose a valid category!");
			category = display.waitForPlayerToSelectCategory();
		}
		addToSelectedCategory(playerNumber, category);
		score = calculateCategory(category);
		scoreboard[playerNumber][category] = score;
		display.updateScorecard(category, playerNumber, score);
		calculateTotalScores(playerNumber);
		int totalScore = scoreboard[playerNumber][TOTAL];
		display.updateScorecard(TOTAL, playerNumber, totalScore);
	}
	
	private boolean checkCategory(int rolledDice[][], int category) {
		// Still empty category. I'm making use of the precompiled method YahtzeeMagicStub for now! ;)
		return true;
	}
	
	/*
	 * Pre-condition: category is set to 0, and is now being used.
	 * Post-condition: category is set to 1, meaning it has been used by the player.
	 */
	private void addToSelectedCategory(int playerNumber, int category) {
		selected_category[playerNumber][category] = 1;
	}
		
	/*
	 * Pre-condition: Method needs a category, score is set to 0 by default.
	 * Post-condition: Score is calculated for the categorynumber and passed.
	 */	
	private int calculateCategory(int category) {
		score = 0;
		switch (category) {
			case ONES: score = scoreUpperCategory(ONES); break;
			case TWOS: score = scoreUpperCategory(TWOS); break;
			case THREES: score = scoreUpperCategory(THREES); break;
			case FOURS: score = scoreUpperCategory(FOURS); break;
			case FIVES: score = scoreUpperCategory(FIVES); break;
			case SIXES: score = scoreUpperCategory(SIXES); break;
			case THREE_OF_A_KIND: score = ofAKindOrChance(); break;
			case FOUR_OF_A_KIND: score = ofAKindOrChance(); break;
			case FULL_HOUSE: score = 25; break;
			case SMALL_STRAIGHT: score = 30; break;
			case LARGE_STRAIGHT: score = 40; break;
			case YAHTZEE: score = 50; break;
			case CHANCE: score = ofAKindOrChance(); break;
		}
		return score;
	}
	
	/*
	 * Pre-condition: score is calculated for an upper-category.
	 * Post-condition: score is calculated for the category number according for the rules for upper scores.
	 */
	private int scoreUpperCategory(int category_number) {
		int score_upper = 0;
		for (int j = 0; j < N_DICE; j++) {
			if (rolledDice[j] == category_number) score_upper = score_upper + category_number;
		}
		return score_upper;
	}
	
	private int ofAKindOrChance() {
		int score_ofakind = 0;
		for (int i = 0; i < N_DICE; i++) {
			score_ofakind += rolledDice[i];
		}
		return score_ofakind;
	}
		
	private void firstRoll() {
		for (int i = 0; i < N_DICE; i++) {
			rolledDice[i] = rgen.nextInt(1,6);
		}
	}
	
	private void nextRoll() {
		for (int j = 0; j < N_DICE; j++) {
			if (display.isDieSelected(j)) rolledDice[j] = rgen.nextInt(1,6);
		}
	}
	
	private void calculateTotalScores(int playerNumber) {
		int upper_score = 0;
		int lower_score = 0;
		int total_score = 0;
		for (int i = ONES; i <= SIXES; i++) {
				upper_score += scoreboard[playerNumber][i];
			}
		for (int j = THREE_OF_A_KIND; j <= CHANCE; j++) {
				lower_score += scoreboard[playerNumber][j];
			}
		total_score = upper_score + lower_score;
		scoreboard[playerNumber][UPPER_SCORE] = upper_score;
		scoreboard[playerNumber][LOWER_SCORE] = lower_score;
		scoreboard[playerNumber][TOTAL] = total_score;
	}
	
	private int calculateWinner() {
		int winningscore = 0;
		int winner = 0;
		for (int i = 1; i <= nPlayers; i++) {
			if (scoreboard[i][TOTAL] > winningscore) {
				winningscore = scoreboard[i][TOTAL];
				winner = i;
			}
		}
		return winner;
	}
	
	private void calculateBonus() {
		for (int playerNumber = 1; playerNumber <= nPlayers; playerNumber++) {
			if (scoreboard[playerNumber][UPPER_SCORE] > 63) {
				scoreboard[playerNumber][UPPER_BONUS] = 35;
			} else {
				scoreboard[playerNumber][UPPER_BONUS] = 0;
			}
		}
	}
	
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] rolledDice = new int[N_DICE];
	private int[][] scoreboard;
	private int[][] selected_category;
	private int score;
}
