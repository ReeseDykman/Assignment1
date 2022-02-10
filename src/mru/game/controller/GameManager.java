package mru.game.controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import mru.game.model.Player;
import mru.game.view.AppMenu;



public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	
	private ArrayList<Player> players;
	private final String FILE_NAME = "res/CasinoInfo.txt";
	private File info;
	private AppMenu mainMenu;
	private PuntoBancoGame game;
	private boolean newPlayer = false;
	
	/**
	 * Constructor for GameManager
	 * 
	 * Checks for data file and creates one if not found
	 * 
	 * Loads data into arraylist
	 * 
	 * Calls method to creates menus
	 * 
	 */
	public GameManager() {
		//creating / loading info and player list
		info = new File(FILE_NAME);
		if (info.exists()) {
			loadData();
			
		}else {
			try {
				info.createNewFile();
			} catch (IOException e) {
				System.out.print("An error occurred.");
			}
			loadData();
		}
		//creating / displaying menus
		showMenus();
		
	}
	
	/**
	 * Called by constructor
	 * 
	 * Loads data from Casino.txt into an ArrayList
	 * 
	 */
	public void loadData() {
		// to keep track of current line that will be made into new player
		String currentLine = ""; 
		//initialize new ArrayList of players every time game launches
		players = new ArrayList<Player>();
		
		
		try {
			// new scanner, we pass it the file object created by the constructor
			Scanner reader = new Scanner(info); 
			while (reader.hasNextLine()) {
				currentLine = reader.nextLine();
				// temporary variable, holds one players information as name, balance, wins
				String[] splitLine = currentLine.trim().split(","); 
				//loads each variable from currentLine into a new player, adds to ArrayList
				players.add(new Player(splitLine[0],Double.parseDouble(splitLine[1]),Integer.parseInt(splitLine[2]))); 
			}																											 
			reader.close();
		}catch (Exception e){
			// display error if exception thrown
			System.out.println("An error occurred.");	
		}
	}
	
	/**
	 * called by constructor
	 * 
	 * creates new menu object
	 * 
	 * calls different menu methods to display different menus
	 * 
	 * different menu methods return ints that correspond with a method in the game manager	
	 * 
	 */
	public void showMenus() {
		
		// creates menu
		mainMenu = new AppMenu();
		
		//for menu2 return
		int j = -1;
		//for menu1 return
		int i = -1;
		
		//menu1 will show until at least one option is picked
		while(i != 0 && i != 1 && i != 2 ) {
			i = mainMenu.showMenu();
		}
		
		//based upon decision in menu1
		switch(i) {
			case 0://game method will run
				runGame();
				break;
			case 1://menu 2 will be shown and j will be set
				j = mainMenu.showMenu2();
				break;
			default://if game or menu2 isn't selected then the only thing left to do is save
				save();		
		}
		
		//based upon decision in menu2, will be skipped if menu2 is never shown
		switch (j) {
			case 1://show top players menu displayed, method of getTopPlayers called to return an ArrayList of players sorted by wins
				mainMenu.showTopPlayers(getTopPlayers());
				showMenus();//after showing top players, re call this method to start the menu process again
				break;
			case 2://search player menu called, player list passed
				mainMenu.searchPlayer(players);
				showMenus();// after showing player, re call this method to start the menu process again
				break;
			case 0:
				showMenus();// back to menu must have been selected, validation done in menu class
		}
		
	}
	
	/**
	 * getter method for a player from the players ArrayList
	 * 
	 * if no player is found a new one is created
	 * 
	 * @param name, returned from calling menu.enterName()
	 * 
	 * @return a Player, either new and added to the players ArrayList or fetched from the players ArrayList
	 * 
	 */
	public Player getPlayer(String name) {
		
		Player search = null;// for storing the return
		
		//searches through players based on name
		for(int i =0; i<players.size(); i++) {
			if(name.equalsIgnoreCase(players.get(i).getName())) {
				search = players.get(i);
				break;
			}
		}
		
		//creates new player and adds to list if the player wasnt found
		if (search == null) {
			System.out.print("New player successfully created...\n\n");
			search = new Player(name);
			newPlayer = true;
			players.add(search);
		}
		
		// returns player that was being searched
		return search;
	}
	
	/**
	 * creates a new ArrayList of Player
	 * 
	 * Sorts through players ArrayList and adds to the topPlayers ArrayList
	 * 
	 * 0 index is top score, scores decrease as index increases
	 * 
	 * ties are added to the list
	 * 
	 * @return a sorted ArrayList based on wins
	 * 
	 */
	public ArrayList<Player> getTopPlayers() {
		
		// for sorting
		int topScore = 0;
		
		ArrayList<Player> topPlayers = new ArrayList<Player>();
		
		//goes through players ArrayList and sorts into new ArrayList based on wins
		for(Player current : players) {
			if(current.getWins() >= topScore) {
				topPlayers.add(0, current);
				topScore = current.getWins();
			}
		}
		
		//returns a list of players based on wins
		return topPlayers;
	}
	
	/**
	 * creates a new puntoBancoGame
	 * 
	 * calls puntoBancoGame methods for results of the game and to set instance variables
	 * 
	 * calls methods from menu that was created in showMenu() method to display results
	 * 
	 */
	public void runGame() {
		
		//player that will play the game, based on get player method with string input from menu
		Player p = getPlayer(mainMenu.enterName());
		//creates new game, passes player that is playing
		game = new PuntoBancoGame(p);
		//shows welcome menu for player
		mainMenu.showWelcome(p.getName(), newPlayer, p.getBalance());
		//the same game will be played until the user quits chooses to exit
		do {
			
			//the user will be forced to exit the game when they are out of money
			if(p.getBalance() <= 0) {
				Scanner input = new Scanner(System.in);
				System.out.print("Sorry you are out of money and can no longer play. Press enter to return to menu...");
				input.nextLine();
				break;
			}
			//checks if the deck requires shuffling before play
			game.checkShuffle();
			//sets the player guess based off of input from menu
			game.setPlayerGuess(mainMenu.showGuess());
			//sets player bet based upon input from menu, passed player to ensure they can afford their bet
			game.setPlayerBet(mainMenu.showBet(p.getBalance()));
			//play the game and store the result
			int result = game.play();
			//show the board and results based upon outcomes of the game
			mainMenu.showBoard(result, game.getPlayerBet(), game.getPlayerHand(), game.getPlayerPoints(), game.getBankerHand(), game.getBankerPoints());
			
		}while(mainMenu.askPlayAgain());//continue playing same game until user exits or runs out of money
		
		showMenus();// returns to menus when game is over
	}
	
	/**
	 * writes to the file object that was created in the constructor
	 * 
	 * if written successfully it will display so
	 * 
	 * if not written successfully it will display error
	 * 
	 */
	public void save() {
		
		//create new FileWriter based upon File object created by constructor
		try {
			FileWriter fw = new FileWriter(info);
			//loop through player list and write to the text file
			for(Player p : players) {
				fw.write(p.toString() + "\n");
			}
			fw.close();
		//display error if saving fails
		} catch (IOException e) {
			System.out.print("Error: could not save\n");
		}
		//print success
		System.out.print("Save Successful");
	}

}
