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

	ArrayList<Player>players;//remember arraylist is a list of objects
	public final String FILE_NAME = "res/CasinoInfo.txt";
	
	public String loadData() {
		File info = new File(FILE_NAME);
		String[] splitLine = new String [3];
		String currentLine;
		
		//read in current line, parse with commas, create player based off of index 1,2,3 in splitLine
		if(info.exists()) {
			//load in while - filereader.hasnextline, currentline = filereader.nextline, parse 3 infos into array, make players with player object
			try {
				Scanner reader = new Scanner(info);
				while (reader.hasNextLine()) {
					currentLine = reader.nextLine();
					for(int i; i<3; i++) {
						
						splitLine[i] = Integer.par;
					}
					
					
				}
				reader.close();
			}catch (Exception e){
				System.out.println("An error occurred.");
			}
		
		}
		
		
		
		
		return data;
	}

}
