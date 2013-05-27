/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * The CheckerboardKarel class draws a checkerboard using beepers. Karel drops a beeper on each "even" position
 * and skips the "odd" positions. This solution has an even() and odd() method that call each other.
 */

import stanford.karel.*;
 
public class CheckerboardKarel extends SuperKarel {

	//Still has a bug. Boards with odd number of rows will not finish.
	//Update: bug fixed, however, Karel will be blocked sometimes. New Bug!!! YUKKK!
    public void run() {
    	putBeeper();
    	while (facingEast()){
    		checkerKarelLoop();
    	}
    }

	private void checkerKarelLoop() {
		fillRow();
		rightWallTurn();
		if (frontIsClear()) {
			fillRow();
			}
		leftWallTurn();
		
	}

	private void leftWallTurn() {
    	if (facingWest()) {
    		if (frontIsBlocked()) {
    		turnRight();
    		}
    	}
    	if (noBeepersPresent()) {
    		if (frontIsClear()) {
    			move();
    			putBeeper();
     			turnRight();
    			}
    	} else {
    				move();
    				turnRight();
    			}
    	}
    	
//first command edit, editted out else command, replaced it with another if to see if it stopped blocking Karel		

	private void rightWallTurn() {
        if (frontIsBlocked()) {
            if (noBeepersPresent()) {
                turnLeft();
                if (frontIsClear()) {
                    move();
                    turnLeft();
                    putBeeper();
                }
            }
            else {
                turnLeft();
                if (frontIsClear()) {
                    move();
                    turnLeft();
                    move();
                    putBeeper();
                }
            }
        }
/*		if (leftIsClear()) {
			if (facingEast()) {
				if (frontIsBlocked()) {
					turnLeft();
					}
			}
		}
		if (noBeepersPresent()) {
			if (frontIsClear()) {
					move();
					putBeeper();
					turnLeft();
    		}
		}
		if (beepersPresent()) {
			if (frontIsClear()) {
				move();
				turnLeft();
    		}
		} */
	}
    	
		

	private void fillRow() {
		if (beepersPresent()) {
			while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
				}
			}
		} else {
			if (frontIsClear()) {
					move();
					putBeeper();
					while (frontIsClear()) {
						move();
						if (frontIsClear()) {
							move();
							putBeeper();
							}
						}
			}
		}
	}
	
}