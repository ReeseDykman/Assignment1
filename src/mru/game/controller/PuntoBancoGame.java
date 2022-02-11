package mru.game.controller;

import java.util.ArrayList;


import mru.game.model.Player;
import mru.game.view.AppMenu;

/**
 * this class is used to get and store results and outcomes of a punto banco game
 * 
 * @author Reese
 *
 */

public class PuntoBancoGame {
	
	private CardDeck deck;//current deck in use
	
	private Player p;//player that is playing
	
	private ArrayList<Card> playersHand;//cards drawn by player
	private ArrayList<Card> bankersHand;//cards drawn by banker
	
	private int cardCounter;//for shuffle
	private int bankerPoints;//current points of banker resets with each game
	private int playerPoints;//current points of player resets with each game
	
	private int playerGuess;//what the player thinks the outcome will be
	private double playersBet;//how much the player bet
	
	/**
	 * Constructor for puntoBanco game
	 * 
	 * 
	 * @param p the player that is currently playing
	 * 
	 * creates a new deck, sets bet and card counter to zero
	 */
	public PuntoBancoGame(Player p){
		this.p = p;
		deck = new CardDeck();
		playersBet = 0;
		cardCounter = 0;
	}
	
	public void rigCounter(int counter) {
		cardCounter = counter;
	}
	/**
	 * retrieves the players points
	 * 
	 * for use with menus
	 * 
	 * @return the points the player finished with type int
	 */
	public int getPlayerPoints() {
		return playerPoints;
	}
	
	/**
	 * retrieves the bankers points
	 * 
	 * for use with menus
	 * 
	 * @return
	 */
	public int getBankerPoints() {
		return bankerPoints;
	}
	
	/**
	 * sets the players bet and subtracts from their balance
	 * 
	 * @param bet, returned from menus in type double
	 */
	public void setPlayerBet(double bet) {
		playersBet = bet;
		p.setBalance(p.getBalance() - bet);
	}
	
	/**
	 * retrieves the players bet
	 * 
	 * for use with menus
	 * 
	 * @return players bet type double
	 */
	public double getPlayerBet(){
		return playersBet;
	}
	
	/**
	 * retrieves players hand
	 * 
	 * for use with menus
	 * 
	 * @return an ArrayList of cards
	 */
	public ArrayList<Card> getPlayerHand(){
		return playersHand;
	}
	
	/**
	 * retrieves bankers hand
	 * 
	 * for use with menus
	 * 
	 * @return an ArrayList of cards
	 */
	public ArrayList<Card> getBankerHand(){
		return bankersHand;
	}
	
	/**
	 * sets what the player guesses will happen
	 * 
	 * @param guess in integer, returned from menus
	 */
	public void setPlayerGuess(int guess) {
		playerGuess = guess;
	}
	
	/**
	 * if the better wins they get their bet * 2 back
	 * 
	 * adjusts balance accordingly
	 */
	public void betterWins(){
		p.setBalance(p.getBalance() + (playersBet * 2));
		p.increaseWins();
	}
	/**
	 * if the better wins on tie they get paid 5 to 1
	 * 
	 * adjusts balance accordingly
	 */
	public void betterWinsOnTie() {
		p.setBalance(p.getBalance() + (playersBet * 5));
		p.increaseWins();
	}
	
	/**
	 * sets bankers points if they currently hold 2 cards
	 * 
	 * @param card1 from play method
	 * @param card2 from play method
	 */
	public void setBankerPoints(Card card1, Card card2) {
		
		//set integer values for ranks
		int card1Val = card1.getRank();
		int card2Val = card2.getRank();
		
		//adjust values of ranks that are faces
		if(card1Val == 11 || card1Val == 12 || card1Val == 13) {
			card1Val = 0;
		}else if(card2Val == 11 || card2Val == 12 || card2Val == 13) {
			card2Val = 0;
		}
		
		//add points mod 10
		bankerPoints = (card1Val + card2Val) %10;
		
	}
	
	/**
	 * sets bankers points if they have 3 cards
	 * 
	 * @param card1 from play method
	 * @param card2 from play method
	 * @param card3 from play method
	 */
	public void setBankerPoints(Card card1, Card card2, Card card3) {
		
		//assign integer values as ranks
		int card1Val = card1.getRank();
		int card2Val = card2.getRank();
		int card3Val = card3.getRank();
		
		//adjust for faces worth zero
		if(card1Val == 11 || card1Val == 12 || card1Val == 13) {
			card1Val = 0;
		}else if(card2Val == 11 || card2Val == 12 || card2Val == 13) {
			card2Val = 0;
		}else if(card3Val == 11 || card3Val == 12 || card3Val == 13) {
			card3Val = 0;
		}
		
		//add points mod 10
		bankerPoints = (card1Val + card2Val + card3Val) %10;
		
	}
	
	/**
	 * sets players points if they hold 2 cards
	 * 
	 * @param card1 from play method
	 * @param card2 from play method
	 */
	public void setPlayerPoints(Card card1, Card card2) {
		
		//assign integer values as ranks
		int card1Val = card1.getRank();
		int card2Val = card2.getRank();
		
		//adjust for faces worth zero
		if(card1Val == 11 || card1Val == 12 || card1Val == 13) {
			card1Val = 0;
		}else if(card2Val == 11 || card2Val == 12 || card2Val == 13) {
			card2Val = 0;
		}
		
		//add points mod 10
		playerPoints = (card1Val + card2Val) %10;
	}
	
	/**
	 * sets player points if they have 3 cards 
	 * 
	 * @param card1 from play method
	 * @param card2 from play method
	 * @param card3 from play method
	 */
	public void setPlayerPoints(Card card1, Card card2, Card card3) {
		
		// assign integer values as ranks
		int card1Val = card1.getRank();
		int card2Val = card2.getRank();
		int card3Val = card3.getRank();
		
		//adjust for faces worth zero
		if(card1Val == 11 || card1Val == 12 || card1Val == 13) {
			card1Val = 0;
		}else if(card2Val == 11 || card2Val == 12 || card2Val == 13) {
			card2Val = 0;
		}else if(card3Val == 11 || card3Val == 12 || card3Val == 13) {
			card3Val = 0;
		}
		
		//add points mod 10
		playerPoints = (card1Val + card2Val + card3Val) %10;
	}
	
	/**
	 * called when the banker needs a card
	 * 
	 * adds a card to their hand and calls methods to add points
	 * 
	 * advances card counter
	 */
	public void bankerDraws() { 
		//adds card to players hand from deck
		bankersHand.add(deck.getDeck().get(cardCounter));
		//advances to next card
		cardCounter ++;
		//calculates points based on hand size
		if(bankersHand.size() == 2) {
			setBankerPoints(bankersHand.get(0),bankersHand.get(1));
		}else if (bankersHand.size() == 3) {
			setBankerPoints(bankersHand.get(0),bankersHand.get(1),bankersHand.get(2));
		}
	}
	
	/**
	 * called when the banker needs a card
	 * 
	 * adds a card to their hand and calls methods to add points
	 * 
	 * advances card counter
	 */
	public void playerDraws() { 
		//adds card to players hand from deck
		playersHand.add(deck.getDeck().get(cardCounter));
		//advances to the next card
		cardCounter ++;
		//calculates points based on hand size
		if(playersHand.size() == 2) {
			setPlayerPoints(playersHand.get(0),playersHand.get(1));
		}else if (playersHand.size() == 3) {
			setPlayerPoints(playersHand.get(0),playersHand.get(1),playersHand.get(2));
		}
	}
	
	/**
	 * checks if the deck needs shuffling
	 * 
	 * if so it creates a new deck and resets card counter back to zero
	 */
	public void checkShuffle() {
		//shuffles if less than 6 cards remain 
		if(cardCounter > 45) {
			deck = new CardDeck();
			cardCounter = 0;
		}
	}
	
	/**
	 * creates new hands
	 * 
	 * player or banker draw cards based upon points calculated
	 * 
	 * @returns an integer from calculate outcome method, will be used to display win or loss in menus
	 */
	public int play() {
		
		//create new hands
		playersHand = new ArrayList<Card>();
		bankersHand = new ArrayList<Card>();
		
		//every play both points go to zero
		bankerPoints = 0;
		playerPoints = 0;
		
		//value of third card to an impossible amount
		int thirdCard = -1;
		
		//both players draw two cards
		for (int i = 0; i < 2; i++) {
			bankerDraws();
			playerDraws();
		}
		
		// if points total 8 or 9 the game ends and outcome is calculated or the player draws again granted they have score 0-5
		if((bankerPoints == 8) || (bankerPoints == 9) || (playerPoints == 8) || (playerPoints == 9)) {
			calculateOutcome();
		}else if((playerPoints) >= 0 && (playerPoints) <= 5) {
			playerDraws();
			thirdCard = playersHand.get(2).getRank();
		}
		
		//banker draws again based on players draw
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
		
		//calculate outcome based on points and return it to the gameManagaer for menus
		return calculateOutcome();
	}	
	
	/**
	 * calculates if the player was right or wrong in their guess
	 * 
	 * calls methods to adjust player balance
	 * 
	 * @return the result of the game to play method so it can return it to the menus for display
	 */
	public int calculateOutcome() {
		
		if((bankerPoints < playerPoints && playerGuess == 0) || (bankerPoints > playerPoints && playerGuess == 1)) {
			betterWins();
			return 0;
		}else if (playerPoints == bankerPoints && playerGuess == 2){
			betterWinsOnTie();
			return 1;
		}else {
			return 2;
		}
	}
	
}
