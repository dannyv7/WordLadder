/* Wordladder UserInterface.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Danny Vo
 * dpv292
 * <Student1 5-digit Unique No.>
 * James Tsao
 * jt28593
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Summer 2016
 */
package assignment3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class UserInterface {

	private final static String[] commands = { "QUIT" };
	private Scanner scanin;

	public UserInterface(Scanner input) {
		scanin = input;
	}

	/**
	 * Parses user input.
	 * 
	 * @param input
	 * @return String[] of words if valid, null if command or invalid 2 words
	 */
	public String[] nextCommand() {
		String input = scanin.nextLine();
		if (input.charAt(0) == '/') {
			for (String c : commands) {
				if (c.equals(input.substring(1).toUpperCase())) {
					execute(input.substring(1).toUpperCase());
					return null;
				}
			}
			System.out.println("invalid command " + input);
		} else {
			String[] words = input.split("\\s++");
			if (words.length != 2) {
				throw new IllegalArgumentException();
			}
			if (validwords(words[0].toUpperCase(), words[1].toUpperCase())) {
				return words;
			} else {
				System.out.println("no word ladder can be found between " + words[0] + " and " + words[1] + ".");
			}
		}
		return null;
	}

	/**
	 * Prints the ladder in the format required by the assignment.
	 * 
	 * @param ladder:
	 *            word ladder returned by dfs or bfs
	 */
	public void printLadder(ArrayList<String> ladder, String start, String end) {
		if (ladder.isEmpty()) {
			System.out.println("no word ladder can be found between " + start + " and " + end + ".");
		} else {
			System.out.println("a " + (ladder.size() - 2) + " rung word ladder exists between " + start + " and " + end + ".");
			for (String s : ladder) {
				System.out.println(s);
			}

		}

	}

	/**
	 * Checks if both words are valid
	 * 
	 * @param start
	 * @param end
	 * @return true if both words are valid, false if not
	 */
	private boolean validwords(String start, String end) {
		Set<String> dict = Main.makeDictionary();
		return (dict.contains(start) && dict.contains(end));
	}

	/**
	 * Executes special commands in the program.
	 * 
	 * @param cmd:
	 *            Command to execute.
	 */
	private void execute(String cmd) {
		if (cmd.equals("QUIT")) {
			System.exit(0);
		}
	}

}