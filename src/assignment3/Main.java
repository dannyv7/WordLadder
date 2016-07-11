/* WORD LADDER Main.java
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

import java.util.*;
import java.io.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
	final static boolean debug = false; // true to use subset dictionary and run
										// junit tests, false for normal
										// operation
	private final static boolean bfs = false; // set true to run bfs version,
												// set
												// false to run dfs version

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		if (debug) {
			System.out.println("Starting junit testing...");
			Result result = JUnitCore.runClasses(TestCases.class);
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
			System.out.println("Junit testing done!");
			System.out.println(result.getFailureCount() + " failed tests.");
		} else {
			Scanner kb = new Scanner(System.in);
			UserInterface ui = new UserInterface(kb);
			while (true) {
				String[] words = ui.nextCommand();
				if (!(words == null)) {
					ArrayList<String> wordladder = getWordLadder(words[0], words[1], bfs);
					ui.printLadder(wordladder, words[0], words[1]);
				}
			}
		}
	}

	public static ArrayList<String> getWordLadder(String start, String end, boolean bfs) {
		if (bfs) {
			return getWordLadderBFS(start, end);
		} else {
			return getWordLadderDFS(start, end);
		}
	}

	public static ArrayList<String> getWordLadderDFS(String start, String end) {

		/*
		 * Converts the dictionary into an ArrayList with only words that are
		 * the same length as start/end
		 */
		Set<String> dict = makeDictionary();
		start = start.toUpperCase();
		end = end.toUpperCase();
		if (start.equals(end)) {
			ArrayList<String> res = new ArrayList<String>();
			res.add(start);
			res.add(end);
			return res;
		}
		DFSTree DFSladder = new DFSTree(dict, end, start);
		Ladder startLadder = new Ladder(start);
		ArrayList<String> finalLadder = DFSladder.runDFS(startLadder).toArrList();

		if (finalLadder != null) {
			
			Set<String> backdict = makeDictionary();
			DFSTree backDFSLadder = new DFSTree(backdict, start, end);
			Ladder backStartLadder = new Ladder(end);
			ArrayList<String> backLadder = backDFSLadder.runDFS(backStartLadder).toArrList();
			if (backLadder.size() < finalLadder.size()) {
				Collections.reverse(backLadder);
				finalLadder=backLadder;
			}
			
			return finalLadder;
		} else {
			return new ArrayList<String>(0);
		}
	}

	@SuppressWarnings("unused")
	public static ArrayList<String> getWordLadderBFS(String start, String end) {

		/*
		 * Converts the dictionary into an ArrayList with only words that are
		 * the same length as start/end
		 */
		Set<String> dict = makeDictionary();
		/*
		 * for (String s : dict) { if (s.length() != start.length()) {
		 * dict.remove(s); } }
		 */
		/* BFS tree generation */
		start = start.toUpperCase();
		end = end.toUpperCase();
		if (start.equals(end)) {
			ArrayList<String> res = new ArrayList<String>();
			res.add(start);
			res.add(end);
			return res;
		}
		Queue<Ladder> queueBFS = new LinkedList<Ladder>();
		ArrayList<String> firstLadder = new ArrayList<String>();
		queueBFS.add(new Ladder(start)); // Treat start as the first "node"

		/* BFS terminates when there are no more possible paths to check from */
		while (!queueBFS.isEmpty()) {

			/* Check the head of the queue for the desired end */
			if (queueBFS.element().getLastWord().equals(end)) {
				return queueBFS.remove().toArrList();
			}

			/* If the head has been visited */
			else if (!dict.contains(queueBFS.element().getLastWord())) {
				queueBFS.remove();
			}

			/*
			 * If we have to continue searching and we have not visited the head
			 * yet
			 */
			else {
				/* Prevent searching of this word again */
				dict.remove(queueBFS.element().getLastWord());
				Ladder originLadder = queueBFS.remove();
				/* Search exhaustively through all the neighboring words */
				Neighbors toCheck = new Neighbors(originLadder.getLastWord(), dict, end);
				for (int i = 0; i < toCheck.getSize(); i += 1) {
					Ladder copyLadder = new Ladder(originLadder.toArrList());
					if (dict.contains(toCheck.getNeighboringWords().get(i))) { // Ignores
																				// words
																				// we
																				// already
																				// checked
						copyLadder.addLastWord(toCheck.getWord(i));
						queueBFS.add(copyLadder);
						// queueBFS.add(toCheck.getWord(i));
					}
				}
			}
		}

		/* Report not found */
		return new ArrayList<String>(0);
	}

	@SuppressWarnings("unused")
	public static Set<String> makeDictionary() {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			if (debug == true) {
				infile = new Scanner(new File("five_letter_words_subset.txt"));
			} else {
				infile = new Scanner(new File("five_letter_words.txt"));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}

}
