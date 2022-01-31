import java.util.Arrays;
import java.util.HashMap;
import acm.io.*;
import acm.program.*;
import acm.util.*;
import acmx.export.java.util.Collections;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		usedCategories = new int[nPlayers+1][YahtzeeConstants.N_CATEGORIES + 1];  
		playerScoreboard = new int[nPlayers+1][YahtzeeConstants.N_CATEGORIES + 1];
		while( nPlayers < 1 || nPlayers > YahtzeeConstants.MAX_PLAYERS) {
			nPlayers = dialog.readInt(String.format("Maximum number of players is %s", YahtzeeConstants.MAX_PLAYERS));
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for(int i = 0; i < YahtzeeConstants.N_SCORING_CATEGORIES; i++) { 
			for(int j = 1; j <= nPlayers; j++) { 
				display.printMessage(String.format("%s's turn! Click \"Roll Dice\" button to roll the dice.", playerNames[j-1]));
				firstRoll(j);
				display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
				nextRoll();
				display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
				nextRoll();
				display.printMessage("Select a category for this roll.");
				updateCategory(j);
			}
		}
		for(int i = 1; i <= nPlayers; i++) {
			displayFinalScores(i);
		}
		showWinner();
	}
	
	private void firstRoll(int playerNumber) {
		display.waitForPlayerToClickRoll(playerNumber);
		dices = new int[YahtzeeConstants.N_DICE];
		for(int i = 0; i < YahtzeeConstants.N_DICE; i++) {
			dices[i] = rgen.nextInt(1, 6);
		}
		display.displayDice(dices);
	}
	
	private void nextRoll() {
		display.waitForPlayerToSelectDice();
		for(int i = 0; i < YahtzeeConstants.N_DICE; i++) {
			if(display.isDieSelected(i)) dices[i] = rgen.nextInt(1, 6);
		}
		display.displayDice(dices);
	}
	
	private void updateCategory(int playerNumber) {
		int category = display.waitForPlayerToSelectCategory();
		int score = 0; 
		while(usedCategories[playerNumber][category] == 1) { 
			display.printMessage("Choose the category which isn't already used!");
			category = display.waitForPlayerToSelectCategory();
		}
		if(checkedCategory(category)) { 
			score = assignScore(category); 
		}else score = 0; 
		playerScoreboard[playerNumber][category] = score; 
		usedCategories[playerNumber][category] = 1; 
		display.updateScorecard(category, playerNumber, score); 
	}
	
	private boolean checkedCategory(int selectedCategory) { 
		counter = new int[7]; 
		for(int i = 0; i < YahtzeeConstants.N_DICE; i++) {
			counter[dices[i]]++;
		}
		int[] counterSorted = Arrays.copyOf(counter, 7); 
		Arrays.sort(counterSorted);
		
		if(selectedCategory >= YahtzeeConstants.ONES && selectedCategory <= YahtzeeConstants.SIXES || selectedCategory == YahtzeeConstants.CHANCE) return true;
		if(selectedCategory == YahtzeeConstants.THREE_OF_A_KIND && counterSorted[counterSorted.length - 1]>=3) return true;
		if(selectedCategory == YahtzeeConstants.FOUR_OF_A_KIND && counterSorted[counterSorted.length - 1]>=4) return true;
		if(selectedCategory == YahtzeeConstants.FULL_HOUSE && counterSorted[counterSorted.length - 1]==3 && counterSorted[counterSorted.length - 2]==2) return true;
		if(selectedCategory == YahtzeeConstants.SMALL_STRAIGHT && counter[3]>0 && counter[4]>0 && ((counter[1]>0 && counter[2]>0) || (counter[2]>0 && counter[5]>0) || (counter[5]>0 && counter[6]>0))) return true;
		if(selectedCategory == YahtzeeConstants.LARGE_STRAIGHT && counter[2]==1 && counter[3]==1 && counter[4]==1 && counter[5]==1) return true;
		if(selectedCategory == YahtzeeConstants.YAHTZEE && counterSorted[counterSorted.length - 1]==5) return true;
		return false; 
	}
	
	private int assignScore(int selectedCategory) {
		int sum = 0; 
		for(int i = 1; i < 7; i++) sum += counter[i]*i; 
		switch(selectedCategory) { 
			case 1  : return counter[1]*1; 
			case 2  : return counter[2]*2;
			case 3  : return counter[3]*3;
			case 4  : return counter[4]*4;
			case 5  : return counter[5]*5;
			case 6  : return counter[6]*6;
			case 9  : return sum; // three of a kind case
			case 10 : return sum; // four of a kind case
			case 11 : return 25; // full house
			case 12 : return 30; // small straight
			case 13 : return 40; // large straight
			case 14 : return 50; // yahtzee
			case 15 : return sum; // chance
//			default : break;
		}
		return 0; // placeholder
	}
	
	private void displayFinalScores(int playerNumber) {  
		for(int i = 1; i <= 15; i++) { 
			if(i <= 6) playerScoreboard[playerNumber][YahtzeeConstants.UPPER_SCORE] += playerScoreboard[playerNumber][i];
			if(i >= 9) playerScoreboard[playerNumber][YahtzeeConstants.LOWER_SCORE] += playerScoreboard[playerNumber][i];
			if(playerScoreboard[playerNumber][YahtzeeConstants.UPPER_SCORE] >= 63) playerScoreboard[playerNumber][YahtzeeConstants.UPPER_BONUS] = 35;
		}
		int upperScore = playerScoreboard[playerNumber][YahtzeeConstants.UPPER_SCORE]; 
		int upperBonus= playerScoreboard[playerNumber][YahtzeeConstants.UPPER_BONUS];
		int lowerScore = playerScoreboard[playerNumber][YahtzeeConstants.LOWER_SCORE];
		playerScoreboard[playerNumber][YahtzeeConstants.TOTAL] = upperScore + upperBonus + lowerScore;
		int total = playerScoreboard[playerNumber][YahtzeeConstants.TOTAL];
				
		display.updateScorecard(YahtzeeConstants.UPPER_SCORE, playerNumber, upperScore);
		display.updateScorecard(YahtzeeConstants.UPPER_BONUS, playerNumber, upperBonus);
		display.updateScorecard(YahtzeeConstants.LOWER_SCORE, playerNumber, lowerScore);
		display.updateScorecard(YahtzeeConstants.TOTAL, playerNumber, total);
	}
	
	private void showWinner() {
		int maxScore = 0;
		int playerNumber = 1;
		for(int i = 1; i <= nPlayers; i++) {
			if(playerScoreboard[i][YahtzeeConstants.TOTAL] > maxScore) {
				playerNumber = i;
				maxScore = playerScoreboard[i][YahtzeeConstants.TOTAL]; 
			}
		}
		display.printMessage(String.format("Congratulations, %1$s, you're the winner with a total score of %2$s!", playerNames[playerNumber-1], maxScore));
	}
	
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	private int[][] playerScoreboard;
	private int[][] usedCategories;
	private int[] dices;
	private int[] counter;
}
