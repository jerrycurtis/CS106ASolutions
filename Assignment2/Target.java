/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	//Constanten: de radius van de drie circles in pixels. (1 inch is 72!)
	private static final double MIDDLE_RADIUS = 0.65 * 72;
	private static final double OUTER_RADIUS = 72;
	private static final double INNER_RADIUS = 0.3 * 72;
	
	public void run() {
		addTarget(getWidth() / 2, getHeight() / 2);
	}
	
	//deze methode verklaart addTarget. Maak drie circles, gebaseerd op radius en kleur.
	private void addTarget(double x, double y) {
		addCircle(x, y, OUTER_RADIUS, Color.RED);
		addCircle(x, y, MIDDLE_RADIUS, Color.WHITE);
		addCircle(x, y, INNER_RADIUS, Color.RED);
}
	
	private void addCircle( double x, double y, double radius, Color color) {
		x -= radius;
		y -= radius;
		GOval circle = new GOval(x, y, radius * 2, radius *2);
		circle.setColor(color);
		circle.setFilled(true);
		add(circle);
	}
}

	