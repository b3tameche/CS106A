import acm.program.*;
import acm.util.RandomGenerator;

import java.io.*;

public class Hangman extends ConsoleProgram {
	private HangmanCanvas canvas;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanLexicon wordlist;
	
	private String current = ""; 
	private int guessesLeft = 8; 
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {
		canvas.reset();
		println("Welcome to Hangman!");

		try {
			wordlist = new HangmanLexicon();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		int randomIndex = rgen.nextInt(0, wordlist.getWordCount());
		String word = wordlist.getWord(randomIndex); 
		
		createHiddenWord(word);
		
		gameplay(word);
		
		loseCaseCheck(word);
		
		if(playsAnother()) {
			run();
		}
	}
	private void currentState() {
		println("The word now looks like this: " + current);
		println("You have " + guessesLeft + " guesses left");
		canvas.displayWord(current);
	}
	
	private void check(String word) {
		try {
			String input = readLine("Your guess: ");
			int ascii = input.charAt(0);
			if (((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) && input.length()==1 && !input.equals(" ")) { 
				char toUpperCased = Character.toUpperCase(input.charAt(0));
				if (word.contains(input.toUpperCase())) { 
					println("That guess is correct."); 
					for (int i = 0; i < word.length(); i++) {
						if(word.charAt(i) == toUpperCased) {
							current = current.substring(0,i) + toUpperCased + current.substring(i+1);
						}
					}
				}else {
					println("There are no " + input + "'s in the word."); 
					if(canvas.currentIncorrectNotes.indexOf(input.charAt(0)) == -1) {
						guessesLeft -= 1;						
					}
					canvas.noteIncorrectGuess(input.charAt(0));
					canvas.drawNext(); 
				}
			} else {
				println("Invalid Input Format!");
			}
		}catch(Exception e) {
			println("Type any character"); 
		}
	}
	
	private void createHiddenWord(String word) {
		int len = word.length();
		for (int i = 0; i < len; i++) {
			current += "-";
		}		
	}
	
	private void gameplay(String word) {
		while (guessesLeft != 0) {
			currentState();
			
			check(word);
			
			if(current.equals(word)) {
				canvas.displayWord(current);  
				println("You guessed the word: " + word);
				println("You win.");
				break;
			}
		}
	}
	
	private void loseCaseCheck(String word) { 
		if (guessesLeft == 0) {
			println("You're completely hung.");
			println("The word was: " + word);
			println("You Lose.");
		}
	}
	
	private boolean playsAnother() {
		String playAgain = readLine("Wanna Play Again? y or n " + "\n");
		if (playAgain.equals("y")) {
			canvas.removeAll(); 
			current = ""; 
			guessesLeft = 8;
			canvas.currentIncorrectNotes = "";
			return true;
		}else if (playAgain.equals("n")) {
			return false;
		}else { 
			println("Type correct letter"); 
		}
		return false;
	}
}
