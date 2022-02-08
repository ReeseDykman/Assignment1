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
	
	
	private int bankerPoints;
	private int playerPoints;
	private double playersBet;
	private int playerOutcome;
	private int actualOutcome;
	
	public PuntoBancoGame(Player p){
		this.p = p;
		deck = new CardDeck();
		bankerPoints = 0;
		playerPoints = 0;
		playersBet = 0;
	}
	
	public void setPlayerBet(double bet) {
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
	
	public void setPlayerOutcome(int outcome) {
		playerOutcome = outcome;
	}
	
	public int getPlayerOutcome() {
		return playerOutcome;
	}
	
	public void play() {
		playersHand = new ArrayList<Card>();
		bankersHand = new ArrayList<Card>();
		int j = 0;
		Card thirdCard = null;
		
		for (int i = 0; i < 2; i++) {
			bankersHand.add(deck.getDeck().get(j));
			j++;
			playersHand.add(deck.getDeck().get(j));
			j++;
		}
		
		bankerPoints = (bankersHand.get(0).getRank()) + (bankersHand.get(1).getRank());
		playerPoints = (playersHand.get(0).getRank()) + (playersHand.get(1).getRank());
		
		
		
		if((bankerPoints == 8) || (bankerPoints == 9) || (playerPoints == 9) || (playerPoints == 9)) {
			//calculateOutcome(;)
		}else if((playerPoints) >= 0 && (bankerPoints) <= 5) {
			playersHand.add(deck.getDeck().get(j));
			thirdCard = playersHand.get(2);
			j++;
		}
		
		if(thirdCard == null) {
			if((bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) >= 0 && (bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) <= 5) {
				bankersHand.add(deck.getDeck().get(j));
				j++;
			}
		}else if(thirdCard.getRank() == 2 || thirdCard.getRank() == 3) {
			if((bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) >= 0 && (bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) <= 4) {
				bankersHand.add(deck.getDeck().get(j));
				j++;
			}
		}else if(thirdCard.getRank() == 4 || thirdCard.getRank() == 5) {
			if((bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) >= 0 && (bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) <= 5) {
				bankersHand.add(deck.getDeck().get(j));
				j++;
			}
		}else if(thirdCard.getRank() == 6 || thirdCard.getRank() == 7) {
			if((bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) >= 0 && (bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) <= 6) {
				bankersHand.add(deck.getDeck().get(j));
				j++;
			}
		}else if(thirdCard.getRank() == 8) {
			if((bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) >= 0 && (bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) <= 2) {
				bankersHand.add(deck.getDeck().get(j));
				j++;
			}
		}else if(thirdCard.getRank() % 10 == 0 || thirdCard.getRank() == 1 || thirdCard.getRank() == 9) {
			if((bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) >= 0 && (bankersHand.get(0).getRank() + bankersHand.get(1).getRank()) <= 3) {
				bankersHand.add(deck.getDeck().get(j));
				j++;
			}
		}
	}

}
