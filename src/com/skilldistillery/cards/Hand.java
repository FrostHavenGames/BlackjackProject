package com.skilldistillery.cards;

import java.util.ArrayList;

public abstract class Hand {
	ArrayList<Card> cards = new ArrayList<>();
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public boolean removeCard(Card card) {
		return cards.remove(card);
	}
	
	public int size() {
		return cards.size();
	}
	
	public void clear() {
		cards.clear();
	}
	
	public abstract int getHandValue();
	
	@Override
	public String toString() {
		String value = "";
		for (Card card : cards) {
			value += card.toString() + "\n";
		}
		return value;
	}
}
