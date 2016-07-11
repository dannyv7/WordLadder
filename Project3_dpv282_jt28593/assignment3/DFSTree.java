/* Wordladder DFSTree.java
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

/**
 * Class to generate the DFS
 * 
 * @author Danny Vo
 *
 */
public class DFSTree {
	private Set<String> dictionary = null;
	private String target;
	private boolean findable = true;

	public DFSTree(Set<String> dict, String e, String s) {
		dictionary = dict;
		target = e;
		// maxLen = Main.getWordLadderBFS(s, e).size() * 2;

		findable = true;
		/*
		 * if(Main.getWordLadderBFS(s, e).size() == 0){ findable = false; }
		 */
	}

	/**
	 * Recursive based Depth First Search method
	 * 
	 * @param l
	 *            Ladder object
	 * @return Either the completed WordLadder or null if cannot be created
	 */
	public Ladder runDFS(Ladder l) {

		/* Prevent searching on the newest added word to the ladder again */
		// visited.addWord(l.getLastWord());
		dictionary.remove(l.getLastWord());

		/* Do not attempt unfindable ladders */
		if (!findable) {
			l.removeLastWord();
			return l;
		}
		/* DFS part, search down each neighbor path */
		Neighbors toCheck = new Neighbors(l.getLastWord(), dictionary, target);
		if (toCheck.getSize() == 0) {
			l.removeLastWord();
			return l;
		} // Hit deadend

		/* Check for instant finish */
		for (int i = 0; i < toCheck.getSize(); i += 1) {
			if (toCheck.getWord(i).equals(target)) {
				l.addLastWord(toCheck.getWord(i));
				return l;
			}
		}

		/*
		 * Remove neighbor words from the dictionary; we know we will go over
		 * them in the iteration anyway, so there is no point in wasting
		 * recursive calls to them
		 */
		for (int i = 0; i < toCheck.getSize(); i++) {
			dictionary.remove(toCheck.getWord(i));
		}

		/* Recursive DFS */
		for (int i = 0; i < toCheck.getSize(); i += 1) {

			/* Launch recursive search if neighbor is unexplored */
			dictionary.add(toCheck.getWord(i));

			/*
			 * Generates instances of building ladders until the correct one is
			 * found or run out of words
			 */
			Ladder temp = new Ladder(l.toArrList());
			temp.addLastWord(toCheck.getWord(i));

			/* Instant return if adding a word creates the ladder */
			if (temp.getLastWord().equals(target)) {
				return temp;
			}
			/* Don't make the DFS too long...temporary solution */
			// else if(temp.toArrList().size() >= maxLen){

			// }
			/* Recursive search, return ladder if correct */
			else {
				temp = runDFS(temp);
				if (temp != null && temp.size() != 0 && temp.getLastWord().equals(target)) {
					return temp;
				}
			}
		}
		return new Ladder();
	}

}
