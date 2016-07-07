package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class UserInterface{
	
	private final static String[] commands={"quit"};
	
	/**
	 * Parses user input.
	 * @param input
	 * @return String[] of words if valid, null if command or invalid 2 words
	 */
	public String[] parse(String input){
		if(input.charAt(0) == '/'){
			for (String c: commands){
				if(c.equals(input.substring(1))){
					execute(input.substring(1));
				}
			}
		}
		else{
			String[] words = input.split("\\s++");
			if(validwords(words[0],words[1])){
				return words;
			}
			else{
				System.out.println("no word ladder can be found between "+words[0]+" and "+words[1]+".");
			}
		}
		return null;
	}
	
	/**
	 * Prints the ladder in the format required by the assignment.
	 * @param ladder: word ladder returned by dfs or bfs
	 */
	public void printLadder(ArrayList<String> ladder){
		System.out.println("a "+(ladder.size()-2)+" rung word ladder exists between "
				+ladder.get(0) +" and "+ladder.get(ladder.size()-1)+".");
		for (String s : ladder){
			System.out.println(s);
		}
	}
	
	/**
	 * Checks if both words are valid
	 * @param start
	 * @param end
	 * @return true if both words are valid, false if not
	 */
	private boolean validwords(String start, String end){
		Set<String> dict = Main.makeDictionary();
		return (dict.contains(start) && dict.contains(end));
	}
	
	/**
	 * Executes special commands in the program. 
	 * @param cmd: Command to execute.
	 */
	private void execute(String cmd){
		if(cmd.equals("quit")){
			System.exit(0);
		}
	}
	

}