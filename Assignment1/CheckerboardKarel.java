/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * Author: Coding Kyle
 * This solution should work for EVERY world Karel is put in.
 * Edited out some none working else part, left it to learn from. ;)
 */

import stanford.karel.*;
 
public class CheckerboardKarel extends SuperKarel {

	
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