package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<>(52);

	public Deck() {
		for (int x = 0; x < Suit.values().length; x++) {
			for (int y = 0; y < Rank.values().length; y++) {
				cards.add(new Card((Suit.values()[x]), (Rank.values()[y])));
			}
		}
	}
	
	public int cardsInDeck() {
		return cards.size();
	}
	
	public Card dealCard() {
		if (cardsInDeck() <= 0) 
			return null;
		
		return cards.remove(cards.size() - 1);
	}
	
	public void shuffle(int numOfTimes) {
		for (int x = 0; x < numOfTimes; x++) {
			Collections.shuffle(cards);
		}
	}
}
