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
