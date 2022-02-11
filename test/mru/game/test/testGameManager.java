package mru.game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.game.controller.GameManager;
import mru.game.model.Player;

class testGameManager {

	@Test
	void testTopPlayers() {
		//this will display menu, exit immediately
		GameManager game = new GameManager();
		//make 2 new players with ridiculous wins
		Player test = game.getPlayer("Test", true);
		Player test2 = game.getPlayer("Test2", true);
		test.setWins(10000);
		test2.setWins(9999);
		
		//make sure they are top two
		assertTrue(game.getTopPlayers().get(0) == test);
		assertTrue(game.getTopPlayers().get(1) == test2);
	}
	
	@Test
	void testSaveExit() {
		
		//tests by creating a new player, menu will have to be quit multiple times because its in the constructor
		GameManager gameManager = new GameManager();
		gameManager.getPlayer("Test", true);
		gameManager.save();
		
		GameManager game = new GameManager();
		assertTrue(game.getPlayer("test", false).getName().equals("Test"));
		//delete player in Casino Info file to repeat test
		
	}

}
