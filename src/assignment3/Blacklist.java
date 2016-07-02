package assignment3;

import java.util.*;
/**
 * HashSet<String> to contain words already attempted and avoid
 * @author Danny Vo
 *
 */
public class Blacklist {
	private Set<String> alreadyVisited = new HashSet<String>();
	
	/**
	 * Adds a word to the Blacklist, which contains all words that we have attempted to tree from
	 * @param s
	 * 	The String to add
	 */
	public void addWord(String s){
		alreadyVisited.add(s);
	}
	
	/**
	 * Temporarily adds elements to the blacklist to indicate incursion has not been finished
	 * @param s
	 * 	ArrayList<String> of elements to add
	 */
	public void visitInProgress(ArrayList<String> s){
		for(int i = 0; i < s.size(); i+= 1){
			alreadyVisited.add(s.get(i));
		}
	}
	
	/**
	 * Remove elements added by visitInProgress
	 * @param s
	 * 	ArrayList<String> of elements to remove
	 */
	public void removeInProgress(ArrayList<String>s){
		for(int i = 0; i < s.size(); i+= 1){
			alreadyVisited.remove(s.get(i));
		}
	}
	
	
	/**
	 * Determine if a String has already been "visited"
	 * @param s
	 * 	String to check
	 * @return
	 * 	True if the String has already been visited, false otherwise
	 */
	public boolean containsWord(String s){
		return alreadyVisited.contains(s);
	}
}
