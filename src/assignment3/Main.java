/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
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
		
		// TODO methods to read in words, output ladder

	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		/* Converts the dictionary into an ArrayList with only words that are the same length as start/end */
		Set<String> dict = makeDictionary();
		String[] temp = null;
		ArrayList<String> modifiedDict = filterDictionary(start.length(), dict.toArray(temp));
		return null; // replace this line later with real return
	}
	
	public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		
		/* Converts the dictionary into an ArrayList with only words that are the same length as start/end */
		Set<String> dict = makeDictionary();
		String[] temp = null;
		ArrayList<String> modifiedDict = filterDictionary(start.length(), dict.toArray(temp));
		
		/* Actual BFS tree generation */
		Queue<String> queueBFS = new LinkedList<String>();
		Blacklist visited = new Blacklist();
		queueBFS.add(start);				//Treat start as the first "node"
		
		/* BFS terminates when there are no more possible paths to check from */
		while(!queueBFS.isEmpty()){
			
			/* Check the head of the queue for the desired end */
			if(queueBFS.element() == end){
				queueBFS.remove();
				break;
			}
			
			/* If the head has been visited */
			else if(visited.containsWord(queueBFS.element())){
				queueBFS.remove();
			}
			
			/* If we have to continue searching and we have not visited the head yet */
			else{
				/* Prevent searching of this word again */
				visited.addWord(queueBFS.element());
				
				/* Search exhaustively through all the neighboring words */
				Neighbors toCheck = new Neighbors(queueBFS.element(), modifiedDict);
				for(int i = 0; i < toCheck.getSize(); i+=1){	
					if(!visited.containsWord(toCheck.getNeighboringWords().get(i))){	//Ignores words we already checked
						queueBFS.add(toCheck.getWord(i));
					}
				}
			}
		}
		
		/* Report not found */
		
		return null; // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("short_dict.txt"));
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
