package mru.game.controller;

import mru.game.model.Player;
import mru.game.view.AppMenu;

public class PuntoBancoGame {
	
	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */
	
	private CardDeck deck;
	private Player p;
	private AppMenu mainMenu;
	private int bankerPoints;
	private int playerPoints;
	private double playersBalance;
	private double playersBet;
	private int playerOutcome;
	private int actualOutcome;
	
	public PuntoBancoGame(Player p, AppMenu mainMenu){
		this.p = p;
		this.mainMenu = mainMenu;
		playersBalance = p.getBalance();
		deck = new CardDeck();
		bankerPoints = 0;
		playerPoints = 0;
		playersBet = 0;
		mainMenu.showWelcomMessage(p); // display the welcome message and their balance right as they press play
	}
	
	public void playerBets(double bet) {
		playersBet = bet;
		p.setBalance(p.getBalance() - bet);
	}
	
	public void playerWins(){
		p.setBalance(p.getBalance() + (playersBet * 2));
		playersBet = 0;
	}
	
	public void playerLoses() {
		playersBet = 0;
	}
	
	public void bankerWins() {
		p.setBalance(p.getBalance() - playersBet);
		playersBet = 0;
	}
	
	public void setPlayerOutcome() {
		//show outcome menu
		//new Scanner
		//case (input.next())
		//set based on cases
	}
	
	public int getPlayerOutcome() {
		return playerOutcome;
	}

}
