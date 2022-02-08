package mru.game.view;

import java.util.ArrayList;
import java.util.Scanner;

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
			return 1;									//return 1 to play
		}else if(option.equalsIgnoreCase("s")){		
			return 2;									//return 2 to search
		}else {
			return 0;									//return 0 to exit
		}			
	}
	
	public int showMenu2() {
		final String TOP = "(T) Top 2 Players"; //return 4
		final String NAME = "(N) Search By Name"; // return 5
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
		return 3;
	}
	
	public void showTopPlayers(ArrayList<Player> topPlayers){//should work off of top players list idk this hurts my head {
		final int HALFTABLEWIDTH = 19;
		final int DASHES = 18;
		final int NAME1LENGTH = topPlayers.get(0).getName().length() +1; //+1 to make pipe part of word
		final int NAME2LENGTH = topPlayers.get(1).getName().length() +1; 
		final int SPACING = (HALFTABLEWIDTH) - (NAME1LENGTH) + (String.valueOf(topPlayers.get(0).getWins()).length()+1); // +1 for center
		final int SPACING2 = (HALFTABLEWIDTH) - (NAME2LENGTH) + (String.valueOf(topPlayers.get(1).getWins()).length()+1);
		final int SPACINGRIGHTPIPE = HALFTABLEWIDTH - (String.valueOf(topPlayers.get(0).getWins()).length()); 
		final int SPACINGRIGHTPIPE2 = HALFTABLEWIDTH - (String.valueOf(topPlayers.get(1).getWins()).length());
		
		final String title = String.format("%27s", "-TOP PLAYERS-");
		final String row = String.format("+" + "=".repeat(DASHES) + "+" + "=".repeat(DASHES) + "+");
		final String secondRow = String.format("+" + "-".repeat(DASHES) + "+" + "-".repeat(DASHES) + "+");
		final String titleRow = String.format("%s" + "%15s" + "%s" + "%15s","|NAME", "|", "#WINS", "|\n");
		
		System.out.print(title + "\n" + row + "\n" + titleRow + row + "\n");
		System.out.printf("%s" + "%" + (SPACING) + "s" + "%" + SPACINGRIGHTPIPE + "s\n","|" + topPlayers.get(0).getName(), "|" + topPlayers.get(0).getWins(), "|");
		System.out.println(secondRow);
		System.out.printf("%s" + "%" + (SPACING2) + "s" + "%" + SPACINGRIGHTPIPE2 + "s\n","|" + topPlayers.get(1).getName(), "|" + topPlayers.get(1).getWins(), "|");
		System.out.println(secondRow);
		
		Scanner input = new Scanner(System.in);
		System.out.print("Press Enter to continue...\n");
		input.nextLine();
		
	}
	
	public void searchPlayer(ArrayList<Player> players) {
		String name = enterName();
		Player player = null;
		Scanner input = new Scanner(System.in);
		
		for(int i =0; i<players.size(); i++) {
			if(name.equalsIgnoreCase(players.get(i).getName())) {
				player = players.get(i);
				break;
			}
		}
		
		if(player == null) {
			System.out.print("Sorry, player not found.\nPress enter to return to menu...\n\n");
			input.nextLine();
			return;
		}
		
		final String row = String.format("+" + "=".repeat(18) + "+" + "=".repeat(15) + "+" + "=".repeat(18) + "+");
		final String row2 = String.format("+" + "-".repeat(18) + "+" + "-".repeat(15) + "+" + "-".repeat(18) + "+");
		final String title = String.format("%35s", "- PLAYER INFO -");
		final String titleRow = String.format("%s" + "%20s" + "%18s" + "%12s", "|NAME","|#WINS","|BALANCE", "|" );
		
		final int colLength = 19;
		final int midColLength = 15;
		final int nameLength = player.getName().length()+1;
		final int winsLength = String.valueOf(player.getWins()).length()+1;      //plus one accounts for pipes
		final int balLength = String.valueOf(player.getBalance()).length()+1;
		final int SPACING = colLength - nameLength + winsLength;
		final int SPACING2 = midColLength - winsLength + balLength+1;
		final int SPACING3 = colLength - balLength+2;
		
		System.out.print(title + "\n" + row + "\n" + titleRow + "\n" + row + "\n");
		System.out.printf("%s" + "%" + (SPACING) + "s" + "%" + SPACING2 + "s" + "%" + SPACING3 + "s","|" + player.getName(), "|" + player.getWins(), "|" + player.getBalance(), "|\n");
		System.out.print(row2 + "\n");
		
		
		System.out.print("Press Enter to continue...\n");
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
		
		final String WELCOME = "Welcome back " + p.getName();
		final String WELCOMENEW = "Welcome " + p.getName();
		final int SPACING1 = WELCOME.length() + 3;
		final int SPACING2 = 6;
		final String BALANCE = "your balance is: " + p.getBalance() + "$";
		final int SPACING3 = BALANCE.length() + 3;
		final int TABLEWIDTH = WELCOME.length() + BALANCE.length() + 21;
		final String STARS = "*";
		final int SPACING4 = WELCOMENEW.length() + 3;
		final int NEWTABLEWIDTH = TABLEWIDTH -5;
		
		if (newPlayer == false) {
			System.out.printf(STARS.repeat(TABLEWIDTH) + "\n***%" + SPACING1 + "s" + "%" + SPACING2 + "s" + "%" + SPACING3 + "s" + "%" + SPACING2 + "s\n%s" , WELCOME, "---", BALANCE,"***", STARS.repeat(TABLEWIDTH));
		}else {
			System.out.printf(STARS.repeat(NEWTABLEWIDTH) + "\n***%" + SPACING4 + "s" + "%" + SPACING2 + "s" + "%" + SPACING3 + "s" + "%" + SPACING2 + "s\n%s" , WELCOMENEW, "---", BALANCE,"***", STARS.repeat(NEWTABLEWIDTH));
		}
	}
	
	public int showGuess() {
		
		final String PLAYER = "(P) Player Wins"; //return 4
		final String BANKER = "(B) Banker Wins"; // return 5
		final String TIE = "(T) Tie Game"; //return 3
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
		double bet = -1;
		
		while(bet < p.getBalance()) {
			Scanner input = new Scanner(System.in);
			System.out.print("How much do you want to bet this round? ");
			bet = input.nextDouble();
		
			if (bet < p.getBalance()) {
				System.out.print("Insufficient funds, try again...\n");
			}
		}
		return bet;
	}
	
	public Boolean askPlayAgain() {
		String choice;
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("Do you want to play again? (Y/N): ");
			choice = input.next();
			
			if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
				System.out.print("Invalid choice, please try again\n\n");
			}
			
		}while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
		
		if(choice.equalsIgnoreCase("y")) {
			return true;
		}else {
			return false;
		}
		
	}
}
