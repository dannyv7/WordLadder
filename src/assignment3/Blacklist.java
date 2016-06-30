package assignment3;

import java.util.*;
public class Blacklist {
	private Set<String> alreadyVisited = new HashSet<String>();
	
	/**
	 * Adds a word to the Blacklist, which contains all words that we have attempted to tree from
	 * @param s
	 * 		The String to add
	 */
	public void addWord(String s){
		alreadyVisited.add(s);
	}
	
	
	/**
	 * Determine if a String has already been "visited"
	 * @param s
	 * 		String to check
	 * @return
	 * 		True if the String has already been visited, false otherwise
	 */
	public boolean containsWord(String s){
		return alreadyVisited.contains(s);
	}
}
