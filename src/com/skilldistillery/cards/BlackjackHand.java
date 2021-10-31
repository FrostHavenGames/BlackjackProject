package com.skilldistillery.cards;

public class BlackjackHand extends Hand {

	@Override
	public int getHandValue() {
		int value = 0;
		for (Card card : cards) {
			value += card.getValue();
		}

		return value;
	}
}
