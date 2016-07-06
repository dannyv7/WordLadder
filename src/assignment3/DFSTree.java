package assignment3;

import java.util.*;

/**
 * Class to generate the DFS
 * @author Danny Vo
 *
 */
public class DFSTree {
	private Set<String> dictionary = null;
	private Blacklist visited = new Blacklist();
	private String target;
	private int maxLen;
	
	public DFSTree (Set<String> dict, String e, String s){
		dictionary  = dict;
		target = e;
		maxLen = Main.getWordLadderBFS(s, e).size() * 2;
	}
	
	private void selectClosest(Neighbors n){
		
	}
	
	/**
	 * Recursive based Depth First Search method
	 * @param l
	 * 	Ladder object 
	 * @return
	 * 	Either the completed WordLadder or null if cannot be created
	 */
	public Ladder runDFS(Ladder l){
		
		/* Prevent searching on the newest added word to the ladder again */
		//visited.addWord(l.getLastWord());
		dictionary.remove(l.getLastWord());
		/* DFS part, search down each neighbor path */
		Neighbors toCheck = new Neighbors(l.getLastWord(), dictionary);
		if(toCheck.getSize() == 0){ return l; }	//Hit deadend	
		for(int i = 0; i < toCheck.getSize(); i +=1){
			
			/* Launch recursive search if neighbor is unexplored */
			if(dictionary.contains(toCheck.getWord(i))){
				
				/* Generates instances of building ladders until the correct one is found or run out of words */
				Ladder temp = new Ladder(l.toArrList());
				temp.addLastWord(toCheck.getWord(i));
				
				/* Instant return if adding a word creates the ladder */
				if(temp.getLastWord().equals(target)){
					return temp;
				}
				/* Don't make the DFS too long...temporary solution */
				else if(temp.toArrList().size() >= maxLen){
					
				}
				/* Recursive search, return ladder if correct */
				else{
					temp = runDFS(temp);
					if(temp != null && temp.getLastWord().equals(target)){
						return temp;
					}
				}
			}
		}
		
		return null;
	}
		
}
