/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;
import java.io.*;
import acm.util.*;

public class Histogram extends ConsoleProgram {
	
	public void run() {
		loadArray();
		readScoresToArray();
		printHistogram();
		}
	
	private void loadArray() {
		histogram = new int[11];
		for (int i = 0; i <= 10; i++) {
			histogram[i] = 0;
		}
	}
	
	private void readScoresToArray() {
		try {
			BufferedReader list = new BufferedReader (new FileReader("MidtermScores.txt"));
			while (true) {
			String line = list.readLine();
			if (line == null) break;
			int mark = Integer.parseInt(line);
			if (mark < 0 || mark > 100) {
				throw new ErrorException("That mark is out of range!");
			} else {
				int index = mark / 10;
				histogram[index]++;
			}
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private void printHistogram() {
		for (int range = 0; range < 11; range++) {
			String label;
			switch (range) {
			case 0: label = "00-09: "; break;
			case 10: label = "  100: "; break;
			default: label = (range * 10) + "-" + (range * 10 + 9) + ": ";
			break;
			}
			String stars = convertToStars(histogram[range]);
			println(label + stars);
		}
	}
	
	private String convertToStars(int n) {
		String stars = "";
		for (int i = 0; i < n; i ++) {
			stars = stars + "*";
		}
		return stars;
	}
	
	
	private int[] histogram;
	}
