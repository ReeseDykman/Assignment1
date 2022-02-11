package mru.game.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.controller.Card;
import mru.game.controller.PuntoBancoGame;
import mru.game.model.Player;

class testPuntoBancoGame {
	
	private PuntoBancoGame game;
	private Player player;
	
	@BeforeEach
	void createNew(){
		player = new Player("Test");
		game = new PuntoBancoGame(player);
	}
	
	

	@Test
	void testPlaceBet() {
		
		double bet = 100;
		game.setPlayerBet(100);
		//we should be able to retrieve what was bet this play
		assertTrue(game.getPlayerBet() == 100);
	}
	
	@Test
	void testBetSubtracts() {
		game.setPlayerBet(100);
		//after betting all of the new players money the balance should go to zero
		assertTrue(player.getBalance()==0);
	}
	
	@Test
	void makePlayersHand() {
		//play so hand is initialized
		game.play();
		//clear it so we can test
		game.getPlayerHand().clear();
		
		//draw three
		game.playerDraws();
		game.playerDraws();
		game.playerDraws();
		
		//check size
		assertTrue(game.getPlayerHand().size()==3);
	}
	
	@Test
	void makeBankersHand() {
		//play so hand is initialized
		game.play();
		//clear it so we can test
		game.getBankerHand().clear();
				
		//draw three
		game.bankerDraws();
		game.bankerDraws();
		game.bankerDraws();
				
		//check size
		assertTrue(game.getBankerHand().size()==3);
	}
	
	@Test
	void testBankerPoints() {
		//run the game
		game.play();
		//clear hand
		game.getBankerHand().clear();
		//add points based on cards
		game.setBankerPoints(new Card (13, "Spades"), new Card(7, "Hearts"));
		//calculation should be 7
		assertEquals(7 , game.getBankerPoints());
	}
	
	@Test
	void testPlayerPoints() {
		//run the game
		game.play();
		//clear hand		
		game.getPlayerHand().clear();
		//add points based on cards
		game.setPlayerPoints(new Card (13, "Spades"), new Card(7, "Hearts"));
		//calculation should be 7		
		assertEquals(7 , game.getPlayerPoints());
	}
	
	@Test
	void testWin() {
		//start game
		game.play();
		game.setPlayerBet(100);
		//rig points
		game.setPlayerPoints(new Card (13, "Spades"), new Card(7, "Hearts"));
		game.setBankerPoints(new Card (0, "Spades"), new Card(0, "Hearts"));
		
		//the outcome of the game should be win and players balance should adjust *2
		game.calculateOutcome();
		assertTrue(player.getBalance() == 200);
	}
	
	void testLoss() {
		//start game
		game.play();
		game.setPlayerBet(100);
		//rig points
		game.setPlayerPoints(new Card (0, "Spades"), new Card(0, "Hearts"));
		game.setBankerPoints(new Card (13, "Spades"), new Card(7, "Hearts"));
		
		//the outcome of the game should be win and players balance should adjust *2
		game.calculateOutcome();
		assertTrue(player.getBalance() == 0);
	}
	
	void testTie() {
		//start game
		game.play();
		game.setPlayerBet(100);
		//rig points
		game.setPlayerPoints(new Card (13, "Spades"), new Card(7, "Hearts"));
		game.setBankerPoints(new Card (13, "Spades"), new Card(7, "Hearts"));
				
		//the outcome of the game should be win and players balance should adjust *5
		game.calculateOutcome();
		assertTrue(player.getBalance() == 500);
	}

	
	
}
