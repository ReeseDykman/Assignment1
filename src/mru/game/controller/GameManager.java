package mru.game.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;



public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	
	static ArrayList<Player>players;//remember arraylist is a list of objects
	private final static String FILE_NAME = "res/CasinoInfo.txt";
	
	public static String loadData() {
		File info = new File(FILE_NAME);
		String currentLine;
		
		//read in current line, parse with commas, create player based off of index 1,2,3 in splitLine
		if(info.exists()) {
			//load in while - filereader.hasnextline, currentline = filereader.nextline, parse 3 infos into array, make players with player object
			try {
				Scanner reader = new Scanner(info);
				while (reader.hasNextLine()) {
					currentLine = reader.nextLine();
					String[] splitLine = currentLine.split(",");
					System.out.print(splitLine.toString());
					players.add(new Player(splitLine[0],Double.parseDouble(splitLine[1]),Integer.parseInt(splitLine[2])));
				}
				reader.close();
			}catch (Exception e){
				System.out.println("An error occurred.");
			}
		
		}
		
		
		
		System.out.print(players.get(0).toString());
		return players.toString();
	}

}
