/* Wordladder Word.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Danny Vo
 * dpv292
 * <Student1 5-digit Unique No.>
 * James Tsao
 * jt28593
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2015
 */
package assignment3;

public class Word {
	private int matchLevel = 0;
	private String word;
	
	public Word(String s, int l){
		matchLevel = l;
		word = s;
	}
	
	public int getLevel(){
		return matchLevel;
	}
	
	public String getString(){
		return word;
	}
}
