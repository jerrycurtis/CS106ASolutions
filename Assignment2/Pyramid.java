/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		int initial_x = -30;
		int initial_y = 200;
		int bricksInRow = BRICKS_IN_BASE;
		for (int i = 0; i < BRICKS_IN_BASE; i--) {
			for (int j = bricksInRow; j > 0; j--) {
				GRect brick = new GRect( initial_x += BRICK_WIDTH, initial_y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
			initial_y -= BRICK_HEIGHT;
			bricksInRow--;
			initial_x = 0 - ((i + 1) * (BRICK_WIDTH/2));
		}
	}

}

