/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import java.io.*;
import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {

	public void run() {
		int lines = 0;
		int words = 0;
		int chars = 0;
		BufferedReader wordcount = openFileReader("File: ");
		try {
			while (true) {
				String line = wordcount.readLine();
				if (line == null) break;
				lines++;
				words += countWords(line);
				chars += line.length();
			}
			wordcount.close();
		}
		catch (IOException ex) {
				println("An I/O exception occured!");
			}
		println("Lines: " + lines);
		println("Words: " + words);
		println("Chars: " + chars);
	}
	
	
	
	
	
	private BufferedReader openFileReader(String prompt) {
		BufferedReader wordcount = null;
		while (wordcount == null) {
			String name = readLine(prompt);
			try {
				wordcount = new BufferedReader(new FileReader(name));
			} catch (IOException ex) {
				println("Cannot open that file, try again!");
			}
		}
		return wordcount;
	}
	/*
	 * Counts words of input string. Parameter = line.
	 * Returns number of words in that line.
	 */
	private int countWords(String line) {
		boolean inWord = false;
		int words = 0;
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				inWord = true;
			} else {
				if (inWord) words++;
				inWord = false;
			}
		}
		if (inWord) words++;
		return words;
	}
}
