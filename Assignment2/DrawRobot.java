/*
* File: DrawRobot.java
* --------------------
* This program draws a simple robot diagram in the window. Its
* programming style leaves much to be desired, mostly because
* the coordinate values are specified explicitly and not defined
* so that they automatically adjust according to specified
* parameters of the image as a whole. You will learn how to
* improve the style in Chapter 7.
*/

import acm.graphics.*;
import acm.program.*;

public class DrawRobot extends GraphicsProgram {
	public void run() {
		add(new GOval(120, 20, 40, 40));
		add(new GOval(110, 60, 60, 60));
		add(new GOval(100, 120, 80, 80));
		add(new GRect(135, 65, 10, 10));
		add(new GRect(135, 85, 10, 10));
		add(new GRect(135, 105, 10, 10));
	}
}