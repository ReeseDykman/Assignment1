package mru.game.view;

import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Card;
import mru.game.controller.GameManager;
import mru.game.model.Player;


/**
 * This Class can be called to display menus
 * 
 * Methods take parameters from puntoBancoGame
 * 
 * @author Reese
 *
 */
public class AppMenu {
	
	/**
	 * displays the main menu of the application
	 * 
	 * @return 0 if selection is play, 1 if selection is search, 2 if selection exit, 3 if invalid input
	 */
	public  int showMenu() {
		//strings that will be displayed
		final String PLAY = "(P) Play";
		final String SEARCH = "(S) Search";
		final String EXIT = "(E) Exit";
		final String SELECT = "Select one of these options:";
		final String CHOICE = "Enter a choice: ";
		
		//initialize for user input
		String option = "";
		
		// print the selections
		System.out.println(SELECT);
		System.out.printf("%16s\n" + "%18s\n" +  "%16s\n", PLAY, SEARCH, EXIT);
		
		//get user input and assign it to option
		Scanner input = new Scanner(System.in);
		System.out.print(CHOICE);
		option = input.next();
		
		//return based upon input
		if(!option.equalsIgnoreCase("p") && !option.equalsIgnoreCase("e") && !option.equalsIgnoreCase("s")) {
			System.out.print("Sorry, invalid input. Try again.\n\n");  // display error
			return 3;									
		}else if(option.equalsIgnoreCase("p")) {
			return 0;									
		}else if(option.equalsIgnoreCase("s")){		
			return 1;									
		}else {
			return 2;									
		}			
	}
	
	/**
	 * shows menu 2
	 * 
	 * called if search option is chosen in menu 1
	 * 
	 * validates input
	 * 
	 * @return 0 if selection is back to main menu, 1 if selection is show top players, if selection is search by name
	 */
	public int showMenu2() {
		//create strings of options
		final String TOP = "(T) Top 2 Players";
		final String NAME = "(N) Search By Name";
		final String BACK = "(B) Back to Main Menu";
		final String SELECT = "Select one of these options:";
		final String CHOICE = "Enter a choice: ";
		//players choice will eventually be filled
		String option = "";
		
		//display menu until valid input
		do {
			
			//print selections
			System.out.println(SELECT);
			System.out.printf("%16s\n" + "%18s\n" +  "%16s\n", TOP, NAME, BACK);
			
			//ask for user input and assign to option
			Scanner input = new Scanner(System.in);
			System.out.print(CHOICE);
			option = input.next();
			
			//validate input, display error if invalid
			if(!option.equalsIgnoreCase("T") && !option.equalsIgnoreCase("N") && !option.equalsIgnoreCase("B")) {
				System.out.print("Sorry, invalid input. Try again.\n\n");
			}
			
		}while(!option.equalsIgnoreCase("T") && !option.equalsIgnoreCase("N") && !option.equalsIgnoreCase("B"));// exit if valid
		
		//return based on input
		if(option.equalsIgnoreCase("T"))
			return 1;
		else if(option.equalsIgnoreCase("N")) {
			return 2;
		}
		return 0;
	}
	
	/**
	 * Shows table of top players
	 * 
	 * @param topPlayers list of players ranked by wins from gameManager
	 */
	public void showTopPlayers(ArrayList<Player> topPlayers){
		//calculate lengths
		final int HALFTABLEWIDTH = 19;
		final int DASHES = 18;
		final int NAME1LENGTH = topPlayers.get(0).getName().length() +1; //+1 to make pipe part of word
		final int NAME2LENGTH = topPlayers.get(1).getName().length() +1; 
		
		//put spacing into variables
		final int SPACINGFIRSTNAME = (HALFTABLEWIDTH) - (NAME1LENGTH) + (String.valueOf(topPlayers.get(0).getWins()).length()+1); // +1 for center
		final int SPACINGSECONDNAME = (HALFTABLEWIDTH) - (NAME2LENGTH) + (String.valueOf(topPlayers.get(1).getWins()).length()+1);
		final int SPACINGRIGHTPIPE = HALFTABLEWIDTH - (String.valueOf(topPlayers.get(0).getWins()).length()); 
		final int SPACINGRIGHTPIPE2 = HALFTABLEWIDTH - (String.valueOf(topPlayers.get(1).getWins()).length());
		
		//make easy strings
		final String title = String.format("%27s", "-TOP PLAYERS-");
		final String row = String.format("+" + "=".repeat(DASHES) + "+" + "=".repeat(DASHES) + "+");
		final String secondRow = String.format("+" + "-".repeat(DASHES) + "+" + "-".repeat(DASHES) + "+");
		final String titleRow = String.format("%s" + "%15s" + "%s" + "%15s","|NAME", "|", "#WINS", "|\n");
		
		//print results
		System.out.print(title + "\n" + row + "\n" + titleRow + row + "\n");
		System.out.printf("%s" + "%" + (SPACINGFIRSTNAME) + "s" + "%" + SPACINGRIGHTPIPE + "s\n","|" + topPlayers.get(0).getName(), "|" + topPlayers.get(0).getWins(), "|");
		System.out.println(secondRow);
		System.out.printf("%s" + "%" + (SPACINGSECONDNAME) + "s" + "%" + SPACINGRIGHTPIPE2 + "s\n","|" + topPlayers.get(1).getName(), "|" + topPlayers.get(1).getWins(), "|");
		System.out.println(secondRow);
		
		//wait for entry
		Scanner input = new Scanner(System.in);
		System.out.print("Press Enter to continue...\n\n");
		input.nextLine();
		
	}
	
	/**
	 * Searches for player
	 * 
	 * tells the user if the player is not found 
	 * 
	 * @param players list of players from GameManager
	 */
	public void searchPlayer(Player p) {

		//initialize player
		Player player = p;
		
		//make basic rows for print
		final String THICKROW = String.format("+" + "=".repeat(18) + "+" + "=".repeat(15) + "+" + "=".repeat(18) + "+");
		final String THINROW = String.format("+" + "-".repeat(18) + "+" + "-".repeat(15) + "+" + "-".repeat(18) + "+");
		final String TITLE = String.format("%35s", "- PLAYER INFO -");
		final String titleRow = String.format("%s" + "%20s" + "%18s" + "%12s", "|NAME","|#WINS","|BALANCE", "|" );
		
		//calculate lengths
		final int COLLENGTH = 19;
		final int MIDCOLLENGTH = 15;
		final int NAMELENGTH = player.getName().length()+1;
		final int WINSLENGTH = String.valueOf(player.getWins()).length()+1;      //plus one accounts for pipes
		final int BALLENGTH = String.valueOf(player.getBalance()).length()+1;
		
		//put spacing into variables
		final int NAMESPACING = COLLENGTH - NAMELENGTH + WINSLENGTH;
		final int WINSSPACING = MIDCOLLENGTH - WINSLENGTH + BALLENGTH+1;
		final int BALSPACING = COLLENGTH - BALLENGTH+2;
		
		//print graph
		System.out.print(TITLE + "\n" + THICKROW + "\n" + titleRow + "\n" + THICKROW + "\n");
		System.out.printf("%s" + "%" + (NAMESPACING) + "s" + "%" + WINSSPACING + "s" + "%" + BALSPACING + "s","|" + player.getName(), "|" + player.getWins(), "|" + player.getBalance(), "|\n");
		System.out.print(THINROW + "\n");
		
		//wait for entry
		Scanner input = new Scanner(System.in);
		System.out.print("Press Enter to continue...\n\n");
		input.nextLine();
	}
	
	/**
	 * prompts the user to enter a name
	 * 
	 * @return a String of the name the user wants to input
	 */
	public String enterName() {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a name: ");
		String name = input.next();
		input.nextLine();
		
		return name;
	}
	
	/**
	 * shows welcome when player enters a new game
	 * 
	 * @param name is the name of the player to be displayed
	 * @param newPlayer if the player is playing for the first time different messages will be displayed
	 * @param balance of the player
	 */
	public void showWelcome(String name, Boolean newPlayer, double balance) {
		
		//welcome spacing
		final String WELCOME = "Welcome back " + name;
		final String WELCOMENEW = "Welcome " + name;
		final int WELCOMESPACING = WELCOME.length() + 3;
		
		//hyphen spacing
		final int HYPHENSPACING = 6;
		
		//balance spacing
		final String BALANCE = "your balance is: " + balance + "$";
		final int BALANCESPACING = BALANCE.length() + 3;
		
		//table spacing
		final int TABLEWIDTH = WELCOME.length() + BALANCE.length() + 21;
		final String STARS = "*";
		
		//new player spacing
		final int NEWWELCOMESPACING = WELCOMENEW.length() + 3;
		final int NEWTABLEWIDTH = TABLEWIDTH -5;
		
		//print depending on if new or not
		if (newPlayer == false) {
			System.out.printf("\n" + STARS.repeat(TABLEWIDTH) + "\n***%" + WELCOMESPACING + "s" + "%" + HYPHENSPACING + "s" + "%" + BALANCESPACING + "s" + "%" + HYPHENSPACING + "s\n%s" , WELCOME, "---", BALANCE,"***", STARS.repeat(TABLEWIDTH) + "\n");
		}else {
			System.out.printf("\n" + STARS.repeat(NEWTABLEWIDTH) + "\n***%" + NEWWELCOMESPACING + "s" + "%" + HYPHENSPACING + "s" + "%" + BALANCESPACING + "s" + "%" + HYPHENSPACING + "s\n%s" , WELCOMENEW, "---", BALANCE,"***", STARS.repeat(NEWTABLEWIDTH) + "\n");
		}
	}
	
	/**
	 * displayes menu for player to guess on the outcome of the game
	 * 
	 * prompts for input from the user
	 * 
	 * validates input
	 * 
	 * @return 0 if they think the player will win, 1 if they think that the banker will win, 2 if they think it will be a tie
	 */
	public int showGuess() {
		
		//create strings for easy print
		final String PLAYER = "(P) Player Wins"; //return 0
		final String BANKER = "(B) Banker Wins"; // return 1
		final String TIE = "(T) Tie Game"; //return 2
		final String SELECT = "Who do you want to bet on?";
		final String CHOICE = "Enter a choice: ";
		
		//empty string that will be filled with users choice
		String option = "";
		
		//continue to show the menu until input is valid
		do {
			
			//print the selections
			System.out.println(SELECT);
			System.out.printf("%25s\n" + "%25s\n" +  "%22s\n", PLAYER, BANKER, TIE);
			
			//prompt for input
			Scanner input = new Scanner(System.in);
			System.out.print(CHOICE);
			option = input.next();
			System.out.print("\n");
			
			//validate input
			if(!option.equalsIgnoreCase("P") && !option.equalsIgnoreCase("B") && !option.equalsIgnoreCase("T")) {
				System.out.print("Sorry, invalid input. Try again.\n\n");
			}
			
		}while(!option.equalsIgnoreCase("P") && !option.equalsIgnoreCase("B") && !option.equalsIgnoreCase("T"));//exit if input is valid
		
		// return based on option picked by the user
		if(option.equalsIgnoreCase("P"))
			return 0;
		else if(option.equalsIgnoreCase("B")) {
			return 1;
		}else {
			return 2;
		}	
	}
	
	/**
	 * Asks the player how much they would like to bet
	 * 
	 * validates input
	 * 
	 * @param p to ensure the player has the money to make the bet
	 * 
	 * @return a double of the amount to bet
	 */
	public double showBet(double balance) {
		//what they player bet will be
		double bet = 0;
		
		//continue until input is valid
		do{
			Scanner input = new Scanner(System.in);
			System.out.print("How much do you want to bet this round? ");
			
			//make sure input is double
			while(!input.hasNextDouble()) {
				
				System.out.print("Nice try! No Numerical bets.\nHow much do you want to bet this round? ");
				input.next();
				
			}
			
			//set bet variable if input is double
			bet = input.nextDouble();
			
			//display error if negative number or insufficient funds
			if (bet > balance || bet <= 0) {
				System.out.print("Invalid Entry (Negative value or insufficient funds), try again...\n");
			}
		}while(bet > balance || bet <= 0);//exit if valid
		
		//return the amount to bet
		return bet;
	}
	
	/**
	 * asks the player if they would like to play again
	 * 
	 * validates input
	 * 
	 * @return true if they wish to continue playing, false if they wish to exit
	 */
	public Boolean askPlayAgain() {
		String choice;
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("\nDo you want to play again? (Y/N): ");
			choice = input.next();
			
			if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
				System.out.print("\nInvalid choice, please try again\n\n");
			}
			
		}while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
		
		if(choice.equalsIgnoreCase("y")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * displays the outcome of the game and bet
	 * 
	 * all parameters from PuntoBancoGame called by GameManager after game is played
	 * 
	 * then prompts calls askPlayAgain
	 * 
	 * @param result of the game type integer
	 * @param bet the player made type double
	 * @param playerHand hand of the player after game type Card ArrayList
	 * @param playerPoints points of the player after game type integer
	 * @param bankerHand hand of the banker after game type Card ArrayList
	 * @param bankerPoints point of the banker after the game type integer
	 */
	public void showBoard(int result, double bet, ArrayList <Card> playerHand, int playerPoints, ArrayList<Card> bankerHand, int bankerPoints) {
		
		//declare width of table
		final int TABLEWIDTH = 51;
		
		// make strings for top row
		final String DASHES = String.format("=".repeat(24));
		final String THICKROW = ("+" + DASHES + "+" + DASHES + "+");
		
		//spacing for top row
		final int PLAYERSPACING = (TABLEWIDTH/2);
		final int BANKERSPACING = TABLEWIDTH-PLAYERSPACING - 7;
		
		//print top row
		System.out.printf("\n" + THICKROW + "\n" + "|PLAYER%" + PLAYERSPACING + "s%" + (BANKERSPACING) + "s\n" + THICKROW + "\n", "|BANKER" , "|");
		
		// make simple strings for rows 1 and 2
		final String HYPHENS = String.format("-".repeat(24));
		final String THINROW = "+" + HYPHENS + "+" + HYPHENS + "+";
		final String playerCard1 = "| "+playerHand.get(0).toString();
		final String playerCard2 = "| "+playerHand.get(1).toString();
		final String bankerCard1 = "| "+bankerHand.get(0).toString();
		final String bankerCard2 = "| "+bankerHand.get(1).toString();
		
		//calculate spacing for row 1
		final int SPACINGCARD1 = (TABLEWIDTH/2) - playerCard1.length() + bankerCard1.length();
		final int SPACINGCARD2 = (TABLEWIDTH/2) - bankerCard1.length() + 1;
		
		//combine strings for row 1
		final String ROW1 = String.format(playerCard1 + "%" + SPACINGCARD1 + "s" + "%" + SPACINGCARD2 + "s", bankerCard1, "|");
		
		//calculate spacing for row 2
		final int SPACINGCARD3 = (TABLEWIDTH/2) - playerCard2.length() + bankerCard2.length();
		final int SPACINGCARD4 = (TABLEWIDTH/2) - bankerCard2.length() + 1;
		
		// combine strings of row 2
		final String ROW2 = String.format(playerCard2 + "%" + SPACINGCARD3 + "s" + "%" + SPACINGCARD4 + "s", bankerCard2, "|");
		
		//print rows 1 and 2
		System.out.printf(ROW1 + "\n" + THINROW + "\n" + ROW2 + "\n");
		
		//declare in case of another row
		final String playerCard3;
		final String bankerCard3;
		
		//make strings for final row based upon game outcomes
		if(playerHand.size()==3 && bankerHand.size() == 3) {
			playerCard3 = "| "+playerHand.get(2).toString();
			bankerCard3 = "| "+bankerHand.get(2).toString();
		}else if(playerHand.size()==3 && bankerHand.size()==2) {
			playerCard3 = "| "+playerHand.get(2).toString();
			bankerCard3 = "| ";
		}else if(playerHand.size()==2 && bankerHand.size()==3){
			playerCard3 = "| ";
			bankerCard3 = "| "+bankerHand.get(2).toString();
		}else {
			playerCard3 = "|";
			bankerCard3 = "|";
		}
		
		//spacing and printing final row
		final int SPACINGCARD5 = (TABLEWIDTH/2) - playerCard3.length() + bankerCard3.length();
		final int SPACINGCARD6 = (TABLEWIDTH/2) - bankerCard3.length() + 1;
		final String ROW3 = String.format(playerCard3 + "%" + SPACINGCARD5 + "s" + "%" + SPACINGCARD6 + "s", bankerCard3, "|");
		System.out.print(THINROW + "\n" + ROW3 + "\n" + THINROW + "\n");
		
		//make Strings for points row 
		final String PLAYERPOINTS = "|PLAYER POINTS: " + playerPoints;
		final String BANKERPOINTS = "|BANKER POINTS: " + bankerPoints;
		
		//calculate spacing for points row
		final int SPACINGPLAYERPOINTS = (TABLEWIDTH/2) - PLAYERPOINTS.length() + BANKERPOINTS.length();
		final int SPACINGBANKERPOINTS = (TABLEWIDTH/2) - BANKERPOINTS.length() + 1;
		final String ROW4 = String.format("%s" + "%" + SPACINGPLAYERPOINTS + "s" + "%" + SPACINGBANKERPOINTS + "s\n", PLAYERPOINTS, BANKERPOINTS, "|");
		
		//print points row
		System.out.print(ROW4 + THICKROW);
		
		//declare how wide box will be and make dollar lines
		final int BOXWIDTH = 31;
		
		final String GAME;
		
		final String DOLLARS = String.format("\n%" + ((TABLEWIDTH/2) + (BOXWIDTH/2)+1) + "s", "$".repeat(BOXWIDTH) + "\n");
		
		//game string depends on outcomes of the game
		if (result == 0) {
			GAME = "PLAYER WON " + (bet*2) + "$";
		}else if(result == 1) {
			GAME = "PLAYER WON " + (bet*5) + "$";
		}else if(result == 2) {
			GAME = "PLAYER LOST " + bet + "$";
		}else {
			GAME = "ERROR";
		}
		
		//calculate spacing
		final int GAMERESULTSPACING = GAME.length();
		final int SPACINGFIRSTDOLLAR = (TABLEWIDTH/2) - (BOXWIDTH/2);
		final int SPACINGGAME = ((BOXWIDTH/2) + (GAMERESULTSPACING/2) -1);
		final int SPACINGSECONDDOLLAR = (BOXWIDTH/2) - (GAMERESULTSPACING/2)  +1;
		
		//print winnings or losings
		System.out.print(DOLLARS);
		System.out.printf("%" + SPACINGFIRSTDOLLAR  + "s" + "%" + SPACINGGAME + "s%" + SPACINGSECONDDOLLAR + "s" , "$", GAME, "$");
		System.out.print(DOLLARS);
		
		
	}
}
