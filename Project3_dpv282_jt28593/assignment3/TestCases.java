/* Wordladder TestCases.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Danny Vo
 * dpv292
 * <Student1 5-digit Unique No.>
 * James Tsao
 * jt28593
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Summer 2016
 */
package assignment3;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
/**
 * Note: these test cases are based on the five_letter_words_subset.txt dictionary 
 * @author Tsao
 *
 */
public class TestCases{	
	
	@Before
	public void checkdebug(){
		org.junit.Assume.assumeTrue(Main.debug);
	}
	/**
	 * tests for invalid special commands fed into the ui
	 */
	@Test
	public void invalidCommandTest(){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Scanner kb = new Scanner("/foobar");
		UserInterface ui = new UserInterface(kb);
		@SuppressWarnings("unused")
		String[] words = ui.nextCommand();
		String checkout = output.toString().trim();
		assertEquals("invalid command /foobar", checkout);
		
	}
	
	/**
	 * checks for fail when inputting invalid words
	 */
	@Test
	public void wordvalidity(){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Scanner kb = new Scanner("about ab0ut");
		UserInterface ui = new UserInterface(kb);
		@SuppressWarnings("unused")
		String[] words = ui.nextCommand();
		String checkout = output.toString().trim();
		assertEquals("no word ladder can be found between about and ab0ut.", checkout);
	}
	
	/**
	 * checks for correct self-match word ladder and output message
	 */
	@Test
	public void selfmatch(){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Scanner kb = new Scanner("aorta aorta");
		UserInterface ui = new UserInterface(kb);
		String[] words = ui.nextCommand();
		ArrayList<String> bfsactualout = Main.getWordLadder(words[0], words[1], true);
		assertTrue(bfsactualout.isEmpty());
		ui.printLadder(bfsactualout, words[0], words[1]);
		String[] checkout = output.toString().trim().split("\n");
		String[] correctout = {"a 0-rung word ladder exists between aorta and aorta.","AORTA","AORTA"};
		for(int i = 0;i<checkout.length;i++){
			assertEquals(correctout[i],checkout[i].trim());
		}
	}
	
	@Test
	public void validladderBFS(){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Scanner kb = new Scanner("aorta berts");
		UserInterface ui = new UserInterface(kb);
		String[] words = ui.nextCommand();
		ArrayList<String> bfscorrectout = new ArrayList<String>();
		bfscorrectout.add("AORTA");
		bfscorrectout.add("BORTA");
		bfscorrectout.add("BERTA");
		bfscorrectout.add("BERTS");
		ArrayList<String> bfsactualout = Main.getWordLadder(words[0], words[1], true);
		assertEquals(bfscorrectout, bfsactualout);
		ui.printLadder(bfsactualout, words[0], words[1]);
		String[] checkout = output.toString().trim().split("\n");
		String[] correctout = {"a 2-rung word ladder exists between aorta and berts.","AORTA","BORTA","BERTA","BERTS"};
		for(int i = 0;i<checkout.length;i++){
			assertEquals(correctout[i],checkout[i].trim());
		}
	}
	@Test
	public void validladderDFS(){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Scanner kb = new Scanner("aorta berts");
		UserInterface ui = new UserInterface(kb);
		String[] words = ui.nextCommand();
		ArrayList<String> bfscorrectout = new ArrayList<String>();
		bfscorrectout.add("AORTA");
		bfscorrectout.add("BORTA");
		bfscorrectout.add("BERTA");
		bfscorrectout.add("BERTS");
		ArrayList<String> bfsactualout = Main.getWordLadder(words[0], words[1], false);
		assertEquals(bfscorrectout, bfsactualout);
		ui.printLadder(bfsactualout, words[0], words[1]);
		String[] checkout = output.toString().trim().split("\n");
		String[] correctout = {"a 2-rung word ladder exists between aorta and berts.","AORTA","BORTA","BERTA","BERTS"};
		for(int i = 0;i<checkout.length;i++){
			assertEquals(correctout[i],checkout[i].trim());
		}
	}
	
	@Test
	public void noladderBFS(){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Scanner kb = new Scanner("aorta ables");
		UserInterface ui = new UserInterface(kb);
		String[] words = ui.nextCommand();
		ArrayList<String> bfsactualout = Main.getWordLadder(words[0], words[1], true);
		assertTrue(bfsactualout.isEmpty());
		ui.printLadder(bfsactualout, words[0], words[1]);
		String[] checkout = output.toString().trim().split("\n");
		String[] correctout = {"no word ladder can be found between aorta and ables."};
		for(int i = 0;i<checkout.length;i++){
			assertEquals(correctout[i],checkout[i].trim());
		}
	}
	@Test
	public void noladderDFS(){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Scanner kb = new Scanner("aorta ables");
		UserInterface ui = new UserInterface(kb);
		String[] words = ui.nextCommand();
		ArrayList<String> bfsactualout = Main.getWordLadder(words[0], words[1], false);
		assertTrue(bfsactualout.isEmpty());
		ui.printLadder(bfsactualout, words[0], words[1]);
		String[] checkout = output.toString().trim().split("\n");
		String[] correctout = {"no word ladder can be found between aorta and ables."};
		for(int i = 0;i<checkout.length;i++){
			assertEquals(correctout[i],checkout[i].trim());
		}
	}
}