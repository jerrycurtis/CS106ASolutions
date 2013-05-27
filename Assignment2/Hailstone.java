/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	public void run() {
		int i = 0;
		int n = readInt("Enter a number:");
		while ( n != 1) {
			if ( n % 2 == 0 ) {
			//even, so divide n by 2 and print new n
			println( n + " is even so i take half: " + n/2);
			n /= 2;
			} else {
			//odd, so take 3n and add 1, then print new n
			println( n + " is odd so i make 3n + 1 :" + ((3 * n) + 1));
			n = ( n * 3 ) + 1;
			}
			i++;
		}
		println("The proces took " + i + " to reach 1!");
	}
}

