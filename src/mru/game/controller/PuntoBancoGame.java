package mru.game.controller;

import java.util.ArrayList;

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
	
	private ArrayList<Card> playersHand;
	private ArrayList<Card> bankersHand;
	
	private int cardCounter;
	private int bankerPoints;
	private int playerPoints;
	private int playerGuess;
	
	private double playersBet;
	
	
	public PuntoBancoGame(Player p){
		this.p = p;
		deck = new CardDeck();
		playersBet = 0;
		cardCounter = 0;
	}
	
	public void setPlayerBet(double bet) {
		playersBet = bet;
		p.setBalance(p.getBalance() - bet);
	}
	
	public double getPlayerBet(){
		return playersBet;
	}
	
	public ArrayList<Card> getPlayerHand(){
		return playersHand;
	}
	
	public ArrayList<Card> getBankerHand(){
		return bankersHand;
	}
	
	public void setPlayerGuess(int guess) {
		playerGuess = guess;
	}
	
	public void betterWins(){
		p.setBalance(p.getBalance() + (playersBet * 2));
		playersBet = 0;
	}
	
	public void betterLoses() {
		p.setBalance(p.getBalance() + (playersBet * 2));
		playersBet = 0;
	}
	
	public void betterWinsOnTie() {
		p.setBalance(p.getBalance() + (playersBet * 5));
		playersBet = 0;
	}
	
	public void bankerDraws() { 
		bankersHand.add(deck.getDeck().get(cardCounter));
		cardCounter ++;
		if(bankersHand.size() == 3) {
			bankerPoints = (bankersHand.get(0).getRank() + bankersHand.get(1).getRank() + bankersHand.get(2).getRank()  % 10);
		}else if (bankersHand.size() == 2) {
			bankerPoints = (bankersHand.get(0).getRank() + bankersHand.get(1).getRank()  % 10);
		}
	}
	
	public void playerDraws() { 
		playersHand.add(deck.getDeck().get(cardCounter));
		cardCounter ++;
		if(playersHand.size() == 3) {
			playerPoints = (playersHand.get(0).getRank() + playersHand.get(1).getRank() + playersHand.get(2).getRank()  % 10);
		}else if (playersHand.size() == 2) {
			playerPoints = (playersHand.get(0).getRank() + playersHand.get(1).getRank()  % 10);
		}
	}
	
	public int play() {
		playersHand = new ArrayList<Card>();
		bankersHand = new ArrayList<Card>();
		bankerPoints = 0;
		playerPoints = 0;
	
		int thirdCard = -1;
		
		for (int i = 0; i < 2; i++) {
			bankerDraws();
			playerDraws();
		}
		
		if((bankerPoints == 8) || (bankerPoints == 9) || (playerPoints == 8) || (playerPoints == 9)) {
			calculateOutcome();
		}else if((playerPoints) >= 0 && (playerPoints) <= 5) {
			playerDraws();
			thirdCard = playersHand.get(2).getRank();
		}
		
		if(thirdCard == -1) {
			if((bankerPoints) >= 0 && (bankerPoints) <= 5) {
				bankerDraws();
			}
		}else if(thirdCard == 2 || thirdCard == 3) {
			if((bankerPoints) >= 0 && (bankerPoints) <= 4) {
				bankerDraws();
			}
		}else if(thirdCard == 4 || thirdCard == 5) {
			if((bankerPoints) >= 0 && (bankerPoints) <= 5) {
				bankerDraws();
			}
		}else if(thirdCard == 6 || thirdCard == 7) {
			if((bankerPoints) >= 0 && (bankerPoints) <= 6) {
				bankerDraws();
			}
		}else if(thirdCard == 8) {
			if((bankerPoints) >= 0 && (bankerPoints) <= 2) {
				bankerDraws();
			}
		}else if(thirdCard == 10 || thirdCard == 1 || thirdCard == 9 || thirdCard == 11 || thirdCard == 12 || thirdCard == 13) {
			if((bankerPoints) >= 0 && (bankerPoints) <= 3) {
				bankerDraws();
			}
		}
		
		return calculateOutcome();
	}	
	
	public int calculateOutcome() {
		
		
		if((bankerPoints < playerPoints && playerGuess == 0) || (bankerPoints > playerPoints && playerGuess == 1)) {
			betterWins();
			return 0;
		}else if (playerPoints == bankerPoints && playerGuess == 2){
			betterWinsOnTie();
			return 0;
		}else {
			betterLoses();
			return 1;
		}
	}
	
}
