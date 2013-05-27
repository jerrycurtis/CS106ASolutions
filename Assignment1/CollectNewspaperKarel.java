/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run() {
		GoToNewspaper();
		PickupNewspaper();
		ReturntoStart();
	}
/**
 * Moves Karel to the newspaper
 */
	private void GoToNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
/**
 * Picks up the newspaper
 */
	private void PickupNewspaper() {
		pickBeeper();
	}
/**
 * Brings Karel back to his starting point!
 */
	private void ReturntoStart() {
		turnAround();
		move();
		move();
		move();
		turnRight();
		move();
		turnRight();
	}
}
