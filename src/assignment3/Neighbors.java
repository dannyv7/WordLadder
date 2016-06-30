package assignment3;

import java.util.*;
public class Neighbors {
	private ArrayList<String> validWords = new ArrayList<String>();
	private int size =0;
	/**
	 * Constructor  for the Neighbors class which contains all "neighbor" nodes to a given node
	 * @param s
	 * 	The String to find the neighbors to
	 * @param dictionary
	 * 	Modified dictionary ArrayList<String> that contains only Strings of the same length as s
	 */
	public Neighbors(String s, ArrayList<String> dictionary){
		for(int i = 0; i < dictionary.size(); i+= 1){
			if (isValid(s, dictionary.get(i))){
				validWords.add(dictionary.get(i));
			}
		}
		size = validWords.size();
	}
	
	
	public ArrayList<String> getNeighboringWords(){
		return validWords;
	}
	/**
	 * Tells if two strings only have a one letter difference
	 * @param s1
	 * 	The first String for comparison
	 * @param s2
	 * 	The second String for comparison
	 * @return
	 * 	True if there is a one letter difference between s1 and s2, false otherwise
	 */
	private boolean isValid(String s1, String s2){
		int differences = 0;
		for(int i  = 0; i < s1.length(); i += 1){
			if (s1.charAt(i) != s2.charAt(i)){
				differences += 1;
			}
		}
		
		if(differences != 1){
			return false;
		}else{ return true; }
	}
	
	public int getSize(){
		return size;
	}
	
	public String getWord(int idx){
		return validWords.get(idx);
	}
	
	
	
}
