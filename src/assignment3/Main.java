/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * James Tsao
 * jt28593
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2015
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		ArrayList<String> tempB = getWordLadderBFS("HEART", "TWAIN");
		if(tempB != null){ System.out.println(tempB); }
		ArrayList<String> tempD = getWordLadderDFS("HEART", "TWAIN");
		if(tempD != null){ System.out.println(tempD); }
		// TODO methods to read in words, output ladder

	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		/* Converts the dictionary into an ArrayList with only words that are the same length as start/end */
		Set<String> dict = makeDictionary();
		String[] temp = new String[0];
		ArrayList<String> modifiedDict = filterDictionary(start.length(), dict.toArray(temp));
		DFSTree DFSladder = new DFSTree(modifiedDict, end, start);
		Ladder startLadder = new Ladder(start);
		return DFSladder.runDFS(startLadder).toArrList();
	}
	
	public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		
		/* Converts the dictionary into an ArrayList with only words that are the same length as start/end */
		Set<String> dict = makeDictionary();
		String[] temp = new String[0];
		ArrayList<String> modifiedDict = filterDictionary(start.length(), dict.toArray(temp));
		
		/* Actual BFS tree generation */
		Queue<Ladder> queueBFS = new LinkedList<Ladder>();
		Blacklist visited = new Blacklist();
		ArrayList<String> firstLadder = new ArrayList<String>();
		queueBFS.add(new Ladder(start));				//Treat start as the first "node"
		
		/* BFS terminates when there are no more possible paths to check from */
		while(!queueBFS.isEmpty()){
			
			/* Check the head of the queue for the desired end */
			if(queueBFS.element().getLastWord().equals(end)){
				return queueBFS.remove().toArrList();
			}
			
			/* If the head has been visited */
			else if(visited.containsWord(queueBFS.element().getLastWord())){
				queueBFS.remove();
			}
			
			/* If we have to continue searching and we have not visited the head yet */
			else{
				/* Prevent searching of this word again */
				visited.addWord(queueBFS.element().getLastWord());
				Ladder originLadder = queueBFS.remove();
				/* Search exhaustively through all the neighboring words */
				Neighbors toCheck = new Neighbors(originLadder.getLastWord(),  modifiedDict);
				for(int i = 0; i < toCheck.getSize(); i+=1){
					Ladder copyLadder = new Ladder(originLadder.toArrList());
					if(!visited.containsWord(toCheck.getNeighboringWords().get(i))){	//Ignores words we already checked
						copyLadder.addLastWord(toCheck.getWord(i));
						queueBFS.add(copyLadder);
						//queueBFS.add(toCheck.getWord(i));
					}
				}
			}
		}
		
		/* Report not found */
		return null; 
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
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
	
	/**
	 * Removes all words from the dictionary that are not the same length as the Start word
	 * @param len
	 * 	Length of the start word
	 * @param dict
	 * 	The dictionary in String[] form
	 * @return
	 * 	An ArrayList with only dictionary words of the same size
	 */
	public static ArrayList<String> filterDictionary (int len, String[] dict){
		ArrayList<String> temp= new ArrayList<String>(0);
		for(int i = 0; i < dict.length; i += 1){
			if(dict[i].length() == len){
				temp.add(dict[i]);
			}
		}
		return temp;
	}
}
