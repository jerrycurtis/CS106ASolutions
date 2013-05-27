/*
 * Dit programma zorgt voor een eerlijke verkiezing. Karel verzekerd dat de ponskaartjes
 * op de juist manier worden gecontroleerd! HELL YEAH DEMOCRACY BITCH!
 * DemocracySavingKarel.java
 * Author: Kyle Adams
 * 
 * Have fun!
 */

import stanford.karel.SuperKarel;

public class DemocracySavingKarel extends SuperKarel {
	
	public void run() {
		while (frontIsClear()) {
			move();
			checkAndCleanBallot();
			move();
		}
	}
		
		private void checkAndCleanBallot() {
			if (noBeepersPresent()) {
				turnLeft();
				move();
				cleanCorner();
				turnAround();
				move();
				move();
				cleanCorner();
				turnAround();
				move();
				turnRight();
			}
		}
		
		private void cleanCorner() {
			while (beepersPresent()) {
				pickBeeper();
				}
		}
}
