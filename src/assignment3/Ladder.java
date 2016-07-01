package assignment3;

import java.util.*;
/**
 * Essentially just an ArrayList <String> for all the components of the building word ladder
 * Contains methods to make accesses significantly easier 
 * @author Danny Vo
 *
 */
public class Ladder {
	private ArrayList<String> rungs = new ArrayList<String>(0);
	
	/**
	 * Constructs a Ladder object from a pre-existing ArrayList<String>
	 * @param s
	 * 	An ArrayList<String> to copy to the Ladder
	 */
	public Ladder(ArrayList<String> s){
		for(int i = 0; i < s.size(); i+=1){
			rungs.add(s.get(i));
		}
	}
	
	/**
	 * Constructor to generate a ladder with exactly one rung
	 * @param s
	 * 	The String to attach as a 'rung'
	 */
	public Ladder(String s){
		rungs.add(s);
	}
	
	/**
	 * Creates a copy of this ladder 
	 * @return
	 * 	A Ladder object that is a copy of itself
	 */
	public Ladder copyLadder(){
		return new Ladder(rungs);
	}
	
	/**
	 * Retrieves the last rung of the Ladder
	 * @return
	 * 	The last String 'rung'
	 */
	public String getLastWord(){
		return rungs.get(rungs.size()-1);
	}
	
	/**
	 * Adds a rung to the bottom of the current ladder
	 * @param s
	 * 	String to add
	 */
	public void addLastWord(String s){
		rungs.add(s);
	}
	
	/**
	 * Retrieves the rungs of the Ladder 
	 * @return
	 * 	Rungs in ArrayList<String> form 
	 */
	public ArrayList<String> toArrList(){
		return rungs;
	}
}
