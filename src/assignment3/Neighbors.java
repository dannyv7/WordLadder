package assignment3;

import java.util.*;

/**
 * Class to aid in interaction with all 'Neighboring' words of a word (1 letter
 * difference)
 * 
 * @author Danny Vo
 *
 */
public class Neighbors {
	private ArrayList<String> validWords = new ArrayList<String>();
	private int size = 0;

	/**
	 * Constructor for the Neighbors class which contains all "neighbor" nodes
	 * to a given node
	 * 
	 * @param lastWord
	 *            The String to find the neighbors to
	 * @param dictionary
	 *            Modified dictionary ArrayList<String> that contains only
	 *            Strings of the same length as s
	 */
	public Neighbors(String lastWord, Set<String> dictionary, String target) {
		LinkedList<Word> temp = new LinkedList<Word>();
		for (String s : dictionary) {
			int checker = isValid(lastWord, s, target);
			if (checker != -1) {
				if(temp.isEmpty() || (temp.getFirst().getLevel() < checker)){
					temp.addFirst(new Word(s, checker));
				}else{ temp.add(new Word(s, checker)); }
			}
		}
		
		for(int i = 0; i < temp.size(); i += 1){
			validWords.add(temp.remove(i).getString());
		}
		size = validWords.size();
		
	}

	public ArrayList<String> getNeighboringWords() {
		return validWords;
	}

	/**
	 * Tells if two strings only have a one letter difference
	 * 
	 * @param s1
	 *            The first String for comparison
	 * @param s2
	 *            The second String for comparison
	 * @return True if there is a one letter difference between s1 and s2, false
	 *         otherwise
	 */
	private int isValid(String s1, String s2, String s3) {
		int differences = 0;
		int matchLevel = 0;
		for (int i = 0; i < s1.length(); i += 1) {
			if (s1.charAt(i) != s2.charAt(i)) {
				differences += 1;
			}
			if(s1.charAt(i) == s3.charAt(i)){
				matchLevel += 1;
			}
		}

		if (differences != 1) {
			return -1;
		} else {
			return matchLevel;
		}
	}
	
	/**
	 * How many neighboring words are there
	 * 
	 * @return Number of 1-letter difference words
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Access a neighboring word at a given index
	 * 
	 * @param idx
	 *            Where in the neighbor array to retrieve
	 * @return String of the neighbord word at the given index
	 */
	public String getWord(int idx) {
		return validWords.get(idx);
	}

}
