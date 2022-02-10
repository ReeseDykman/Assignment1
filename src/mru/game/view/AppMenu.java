package mru.game.view;

import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Card;
import mru.game.controller.GameManager;
import mru.game.model.Player;

public class AppMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	
	// should have method show main menu and return int with option
	//method with show sub menus
	
	public  int showMenu() {
		final String PLAY = "(P) Play";
		final String SEARCH = "(S) Search";
		final String EXIT = "(E) Exit";
		final String SELECT = "Select one of these options:";
		final String CHOICE = "Enter a choice: ";
		
		String option = "";
		
		System.out.println(SELECT);
		System.out.printf("%16s\n" + "%18s\n" +  "%16s\n", PLAY, SEARCH, EXIT);
			
		Scanner input = new Scanner(System.in);
		System.out.print(CHOICE);
		option = input.next();
			
		if(!option.equalsIgnoreCase("p") && !option.equalsIgnoreCase("e") && !option.equalsIgnoreCase("s")) {
			System.out.print("Sorry, invalid input. Try again.\n\n");  // display error
			return 3;									//return 3 to go back to menu
		}else if(option.equalsIgnoreCase("p")) {
			return 0;									//return 0 to play
		}else if(option.equalsIgnoreCase("s")){		
			return 1;									//return 1 to search
		}else {
			return 2;									//return 2 to exit
		}			
	}
	
	public int showMenu2() {
		final String TOP = "(T) Top 2 Players"; //return 1
		final String NAME = "(N) Search By Name"; // return 2
		final String BACK = "(B) Back to Main Menu"; //return 3
		final String SELECT = "Select one of these options:";
		final String CHOICE = "Enter a choice: ";
		String option = "";
		
		do {
			
			
			System.out.println(SELECT);
			System.out.printf("%16s\n" + "%18s\n" +  "%16s\n", TOP, NAME, BACK);
			
			Scanner input = new Scanner(System.in);
			System.out.print(CHOICE);
			option = input.next();
			
			if(!option.equalsIgnoreCase("T") && !option.equalsIgnoreCase("N") && !option.equalsIgnoreCase("B")) {
				System.out.print("Sorry, invalid input. Try again.\n\n");
			}
			
		}while(!option.equalsIgnoreCase("T") && !option.equalsIgnoreCase("N") && !option.equalsIgnoreCase("B"));
			
		if(option.equalsIgnoreCase("T"))
			return 1;
		else if(option.equalsIgnoreCase("N")) {
			return 2;
		}
		return 0;
	}
	
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
	
	public void searchPlayer(ArrayList<Player> players) {
		String name = enterName();
		Player player = null;
		Scanner input = new Scanner(System.in);
		//search player
		for(int i =0; i<players.size(); i++) {
			if(name.equalsIgnoreCase(players.get(i).getName())) {
				player = players.get(i);
				break;
			}
		}
		//display if not found
		if(player == null) {
			System.out.print("Sorry, player not found.\nPress enter to return to menu...\n\n");
			input.nextLine();
			return;
		}
		
		//make basic rows for print
		final String THICKROW = String.format("+" + "=".repeat(18) + "+" + "=".repeat(15) + "+" + "=".repeat(18) + "+");
		final String THINROW = String.format("+" + "-".repeat(18) + "+" + "-".repeat(15) + "+" + "-".repeat(18) + "+");
		final String TITLE = String.format("%35s", "- PLAYER INFO -");
		final String titleRow = String.format("%s" + "%20s" + "%18s" + "%12s", "|NAME","|#WINS","|BALANCE", "|" );
		//calculate spacing
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
		System.out.print("Press Enter to continue...\n\n");
		input.nextLine();
	}
	
	public String enterName() {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a name: ");
		String name = input.next();
		input.nextLine();
		
		return name;
	}
	
	public void showWelcome(Player p, Boolean newPlayer) {
		//welcome spacing
		final String WELCOME = "Welcome back " + p.getName();
		final String WELCOMENEW = "Welcome " + p.getName();
		final int WELCOMESPACING = WELCOME.length() + 3;
		//hyphen spacing
		final int HYPHENSPACING = 6;
		//balance spacing
		final String BALANCE = "your balance is: " + p.getBalance() + "$";
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
	
	public int showGuess() {
		
		final String PLAYER = "(P) Player Wins"; //return 0
		final String BANKER = "(B) Banker Wins"; // return 1
		final String TIE = "(T) Tie Game"; //return 2
		final String SELECT = "Who do you want to bet on?";
		final String CHOICE = "Enter a choice: ";
		
		String option = "";
		
		do {
			
			
			System.out.println(SELECT);
			System.out.printf("%25s\n" + "%25s\n" +  "%22s\n", PLAYER, BANKER, TIE);
			
			Scanner input = new Scanner(System.in);
			System.out.print(CHOICE);
			option = input.next();
			System.out.print("\n");
			
			if(!option.equalsIgnoreCase("P") && !option.equalsIgnoreCase("B") && !option.equalsIgnoreCase("T")) {
				System.out.print("Sorry, invalid input. Try again.\n\n");
			}
			
		}while(!option.equalsIgnoreCase("P") && !option.equalsIgnoreCase("B") && !option.equalsIgnoreCase("T"));
			
		if(option.equalsIgnoreCase("P"))
			return 0;
		else if(option.equalsIgnoreCase("B")) {
			return 1;
		}else {
			return 2;
		}	
	}
	
	public double showBet(Player p) {
		double bet = 0;
		
		do{
			Scanner input = new Scanner(System.in);
			System.out.print("How much do you want to bet this round? ");
			
			while(!input.hasNextDouble()) {
				
				System.out.print("Nice try! No Numerical bets.\nHow much do you want to bet this round? ");
				input.next();
				
			}
			bet = input.nextDouble();
		
			if (bet > p.getBalance() || bet <= 0) {
				System.out.print("Invalid Entry (Negative value or insufficient funds), try again...\n");
			}
		}while(bet > p.getBalance() || bet <= 0);
		return bet;
	}
	
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
	
	public void showBoard(int result, double bet, ArrayList <Card> playerHand, int playerPoints, ArrayList<Card> bankerHand, int bankerPoints) {
		//declare width and make simle strings
		final int TABLEWIDTH = 51;
		final String DASHES = String.format("=".repeat(24));
		final String THICKROW = ("+" + DASHES + "+" + DASHES + "+");
		final int PLAYERSPACING = (TABLEWIDTH/2);
		final int BANKERSPACING = TABLEWIDTH-PLAYERSPACING - 7;
		//print top row
		System.out.printf("\n" + THICKROW + "\n" + "|PLAYER%" + PLAYERSPACING + "s%" + (BANKERSPACING) + "s\n" + THICKROW + "\n", "|BANKER" , "|");
		//variables for second / third row
		final String HYPHENS = String.format("-".repeat(24));
		final String THINROW = "+" + HYPHENS + "+" + HYPHENS + "+";
		final String playerCard1 = "| "+playerHand.get(0).toString();
		final String playerCard2 = "| "+playerHand.get(1).toString();
		final String bankerCard1 = "| "+bankerHand.get(0).toString();
		final String bankerCard2 = "| "+bankerHand.get(1).toString();
		//spacing for second / third row
		final int SPACINGCARD1 = (TABLEWIDTH/2) - playerCard1.length() + bankerCard1.length();
		final int SPACINGCARD2 = (TABLEWIDTH/2) - bankerCard1.length() + 1;
		final String ROW1 = String.format(playerCard1 + "%" + SPACINGCARD1 + "s" + "%" + SPACINGCARD2 + "s", bankerCard1, "|");
		final int SPACINGCARD3 = (TABLEWIDTH/2) - playerCard2.length() + bankerCard2.length();
		final int SPACINGCARD4 = (TABLEWIDTH/2) - bankerCard2.length() + 1;
		final String ROW2 = String.format(playerCard2 + "%" + SPACINGCARD3 + "s" + "%" + SPACINGCARD4 + "s", bankerCard2, "|");
		//print second / third row
		System.out.printf(ROW1 + "\n" + THINROW + "\n" + ROW2 + "\n");
		
		//declare in case of another row
		final String playerCard3;
		final String bankerCard3;
		//length of toString depends on outcomes of game
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
