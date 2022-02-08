package mru.game.application;

import mru.game.controller.GameManager;
import mru.game.controller.PuntoBancoGame;
import mru.game.model.Player;
import mru.game.view.AppMenu;

public class AppDriver {
	
	private static GameManager gameData; //should i be doing this

	public static void main(String[] args) {
		
		// This is the starting point of the project!
		// hint - It usually calls method from GameManager class to start the app, so only one or two lines will be written here
		//call menus, load data, find top player, save
		
//		gameData = new GameManager();
		Player p = new Player("a", 5, 0);
		AppMenu m = new AppMenu();
		m.askPlayAgain();
		

	}
}
