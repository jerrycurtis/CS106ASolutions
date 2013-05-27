/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	/* Named constant that defines the width of the GLabel */
	private static final int LABEL_WIDTH = 100;
	
	/* Named constant that defines the height of the GLabel */
	private static final int LABEL_HEIGHT = 40;
	
	/* Named constant that defines the height of the GLabel */
	private static final int PROGRAM_WIDTH = LABEL_WIDTH * 5;
	
	/* Named constant that defines the height of the GLabel */
	private static final int PROGRAM_HEIGHT = LABEL_HEIGHT * 6;
	
	/* Named constant that defines the height of the GLabel */
	private static final int X_OFFSET = LABEL_WIDTH / 2;
	
	public void run() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		drawProgramBox();
		drawGraphicsProgramBox();
		drawConsoleProgramBox();
		drawDialogProgramBox();
		drawLines();
	}
	
	private void drawProgramBox() {
		int program_x = (PROGRAM_WIDTH - LABEL_WIDTH) / 2;
		int y = LABEL_HEIGHT;
		GRect labelbox = new GRect(program_x, y, LABEL_WIDTH, LABEL_HEIGHT);
		add(labelbox);
		GLabel labelprogram = new GLabel("Program");
		labelprogram.setLocation( program_x + (labelbox.getWidth() - labelprogram.getWidth()) / 2, LABEL_HEIGHT + (LABEL_HEIGHT /2));
		add(labelprogram);
	}
	
	private void drawGraphicsProgramBox() {
		int x = X_OFFSET;
		int y = LABEL_HEIGHT * 3 ;
		GRect labelbox = new GRect(x, y, LABEL_WIDTH, LABEL_HEIGHT);
		add(labelbox);
		GLabel labelprogram = new GLabel("Graphics Program");
		labelprogram.setLocation( x + (labelbox.getWidth() - labelprogram.getWidth()) / 2, y + (LABEL_HEIGHT /2));
		add(labelprogram);
	}
	
	private void drawConsoleProgramBox() {
		int console_x = (PROGRAM_WIDTH - LABEL_WIDTH) / 2;
		int y = LABEL_HEIGHT * 3 ;
		GRect labelbox = new GRect(console_x, y, LABEL_WIDTH, LABEL_HEIGHT);
		add(labelbox);
		GLabel labelprogram = new GLabel("Console Program");
		labelprogram.setLocation( console_x + (labelbox.getWidth() - labelprogram.getWidth()) / 2, y + (LABEL_HEIGHT /2));
		add(labelprogram);
	}
	
	private void drawDialogProgramBox() {
		int dialog_x = PROGRAM_WIDTH - (LABEL_WIDTH + X_OFFSET);
		int y = LABEL_HEIGHT * 3 ;
		GRect labelbox = new GRect(dialog_x, y, LABEL_WIDTH, LABEL_HEIGHT);
		add(labelbox);
		GLabel labelprogram = new GLabel("Dialog Program");
		labelprogram.setLocation( dialog_x + (labelbox.getWidth() - labelprogram.getWidth()) / 2, y + (LABEL_HEIGHT /2));
		add(labelprogram);
	}
	
	private void drawLines() {
		int cx = PROGRAM_WIDTH /2;
		int start_y = LABEL_HEIGHT * 2;
		GLine centerline = new GLine( cx, start_y, cx, LABEL_HEIGHT *3);
		add(centerline);
		GLine leftline = new GLine( cx, start_y, X_OFFSET + (LABEL_WIDTH /2), LABEL_HEIGHT *3);
		add(leftline);
		GLine rightline = new GLine( cx, start_y, PROGRAM_WIDTH - ((LABEL_WIDTH / 2) + X_OFFSET), LABEL_HEIGHT *3);
		add(rightline);
	}
}

