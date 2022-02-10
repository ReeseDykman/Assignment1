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
	//remember arraylist is a list of object
	private final String FILE_NAME = "res/CasinoInfo.txt";
	private File info;
	private AppMenu mainMenu;
	private PuntoBancoGame game;
	private boolean newPlayer = false;
	
	/**
	 * Constructor for GameManager
	 * Checks for data file and creates one if not found
	 * Loads data into arraylist
	 * Creates and calls menus
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
	
	public void loadData() {
		
		String currentLine = "";
		players = new ArrayList<Player>();

		try {
			Scanner reader = new Scanner(info);
			while (reader.hasNextLine()) {
				currentLine = reader.nextLine();
				String[] splitLine = currentLine.trim().split(",");
				players.add(new Player(splitLine[0],Double.parseDouble(splitLine[1]),Integer.parseInt(splitLine[2])));
			}
			reader.close();
		}catch (Exception e){
			System.out.println("An error occurred.");
		}
	}
		
	public void showMenus() {
		
		mainMenu = new AppMenu();
		
		int j = -1;
		int i = -1;
		
		while(i != 0 && i != 1 && i != 2 ) {
			i = mainMenu.showMenu();
		}
		
		switch(i) {
			case 0://game
				runGame();
				break;
			case 1:
				j = mainMenu.showMenu2();
				break;
			default:
				save();		
		}
		
		switch (j) {
			case 1:
				mainMenu.showTopPlayers(getTopPlayers());
				showMenus();
				break;
			case 2:
				mainMenu.searchPlayer(players);
				showMenus();
				break;
			case 0:
				showMenus();
		}
		
	}
	
	public Player getPlayer(String name) {
		
		Player search = null;
		
		
		for(int i =0; i<players.size(); i++) {
			if(name.equalsIgnoreCase(players.get(i).getName())) {
				search = players.get(i);
				break;
			}
		}
		
		if (search == null) {
			System.out.print("New player successfully created...\n\n");
			search = new Player(name);
			newPlayer = true;
			players.add(search);
		}
	
		return search;
	}
	
	public ArrayList<Player> getTopPlayers() {
		
		int topScore = 0;
		ArrayList<Player> topPlayers = new ArrayList<Player>();
		
		for(Player current : players) {
			if(current.getWins() >= topScore) {
				topPlayers.add(0, current);
				topScore = current.getWins();
			}
		}
		
		return topPlayers;
	}
	
	public void runGame() {
		Player p = getPlayer(mainMenu.enterName());
		game = new PuntoBancoGame(p);
		mainMenu.showWelcome(p, newPlayer);
		
		do {
			
			if(p.getBalance() <= 0) {
				Scanner input = new Scanner(System.in);
				System.out.print("Sorry you are out of money and can no longer play. Press enter to return to menu...");
				input.nextLine();
				break;
			}
			game.checkShuffle();
			game.setPlayerGuess(mainMenu.showGuess());
			game.setPlayerBet(mainMenu.showBet(p));
			int result = game.play();
			mainMenu.showBoard(result, game.getPlayerBet(), game.getPlayerHand(), game.getPlayerPoints(), game.getBankerHand(), game.getBankerPoints());
			
		}while(mainMenu.askPlayAgain());
		
		showMenus();
	}
	
	public void save() {
		
		try {
			FileWriter fw = new FileWriter(info);
			for(Player p : players) {
				fw.write(p.toString() + "\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.print("Error: could not save\n");;
		}
		
		System.out.print("Save Successful");
	}

}
