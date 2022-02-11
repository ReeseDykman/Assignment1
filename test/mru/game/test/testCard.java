package mru.game.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.controller.Card;
import mru.game.controller.CardDeck;

class testCard {
	
	private Card card1;
	private CardDeck deck;
	private Card card2;
	
	@BeforeEach
	void test() {
		deck = new CardDeck(true);
		card1 = new Card(13, "Spades");
	}
	
	@Test
	void testSuit(){
		String suit = card1.getSuit();
		assertEquals(suit, "Spades");
		
	}
	
	@Test
	void testSetRank() {
		//set a new card rank
		card1.setRank(11);
		//+1 because a deck doesnt start from 0
		int rank = card1.getRank();
		//make sure the rank of the card matches up with the rank of a card in an un-shuffled deck
		assertEquals(rank, 11);
	}
	
	@Test
	void testStrings() {
		//new unshuffled deck, 0-12 spades, 13-25 diamonds, 26-38 clubs, 39-51 hearts
		assertEquals(deck.getDeck().get(0).getSuit(), "Spades");
		assertEquals(deck.getDeck().get(13).getSuit(), "Diamond");
		assertEquals(deck.getDeck().get(26).getSuit(), "Clubs");
		assertEquals(deck.getDeck().get(51).getSuit(), "Hearts");
		
		card2 = deck.getDeck().get(49);
		String shouldBe = "Jack of Hearts"; // 49th card is ranked 50
		assertEquals(shouldBe, card2.toString());
	}
	
	@Test void testShuffle() {
		
		CardDeck d2 = new CardDeck();
		//the shuffled deck shouldnt match the unshuffled unless very lucky
		
		assertNotEquals(d2.getDeck().get(1).toString(), deck.getDeck().get(1).toString());
	}
	

}
