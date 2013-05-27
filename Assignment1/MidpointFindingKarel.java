import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
public void run() {
	putBeeper();
	putEndBeeper();

	while (noBeepersPresent()) {
		moveBeeper();
	}
	pickBeeper();
}

	private void putEndBeeper() {
		while (frontIsClear()) {
			move();
		}
		putBeeper();
		turnAround();
		move();
	}

	private void moveBeeper() {
		while (noBeepersPresent()) {
			move();
		}
		pickBeeper();
		turnAround();
		move();
		putBeeper();
		move();
	}
}