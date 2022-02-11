package mru.game.controller;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a card deck
 * @author ksalmani
 * @version 1.0
 */
public class CardDeck {
	
	/**
	 * deck holds all of the cards that currently are in the current deck
	 */
	private ArrayList <Card> deck;
	
	/**
	 * This constructor initiate the arraylist and calls the repective methods to create a new deck
	 */
	public CardDeck() {
		deck = new ArrayList<Card>();
		createDeck();
		shuffleDeck();
	}
	
	/**
	 * creates a deck but doesn't shuffle it
	 * 
	 * for testing
	 * 
	 * @param rigTheDeck insert value to rig the deck
	 */
	public CardDeck(boolean rigTheDeck) {
		deck = new ArrayList<Card>();
		createDeck();
	}

	/**
	 * This method creates the deck
	 */
	private void createDeck() {
		// suits holds the name of the suits
		String[] suits = {"Spades", "Diamond", "Clubs", "Hearts"};
		
		/*
		 * The for loop creates a whole new deck based on their suit and rank
		 */
		for (int i = 0 ; i < 4; i++) {
			for (int j = 1 ; j <=13 ; j++) {
				deck.add(new Card (j,suits[i])); //creates deck from card objects
			}
		}
		
	}

	/**
	 * this method shuffle the deck after creating a new deck
	 */
	private void shuffleDeck() {
		Collections.shuffle(deck); 
	}

	/**
	 * The deck getter method
	 * @return the deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}
}
