/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		println("This program finds the largest and smallest numbers and stops when you enter 0");
		
		int n1 = readInt("?");
		
		if ( n1 == 0 ) { 
			println("No values were entered!");
		} else {
			n1 = smallest;
			n1 = largest;
		
		while ( n1 != SENTINEL) {
			
			n2 = readInt("?");
			
			if ( n2 < smallest) n2 = smallest;
			if (n2 > largest) n2 = largest;
			
			if ( n2 == SENTINEL ) {
			println("smallest:" + smallest);
			println("largest:" + largest);
			n1 = SENTINEL;
			}
		}
		}
	}
	
	private int smallest;
	private int largest;
	private int n2;
}

