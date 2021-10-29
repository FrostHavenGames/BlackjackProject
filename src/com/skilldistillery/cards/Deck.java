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
	
	public int checkDeckSize() {
		return cards.size();
	}
	
	public Card dealCard() {
		if (cards.size() <= 0) 
			return null;
		
		Card card = cards.remove(cards.size() - 1);
		cards.remove(card);
		return card;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
		Collections.shuffle(cards);
		Collections.shuffle(cards);
	}
}
