package mru.game.application;

import mru.game.controller.GameManager;

public class AppDriver {

	public static void main(String[] args) {
		
		// This is the starting point of the project!
		// hint - It usually calls method from GameManager class to start the app, so only one or two lines will be written here
		//call menus, load data, find top player, save
		GameManager gm = new GameManager();
		GameManager.loadData();
	}

}
