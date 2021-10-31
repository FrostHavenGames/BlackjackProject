package com.skilldistillery.cards;

public class BlackjackDealer extends Player {

	public BlackjackDealer(String name) {
		super(name);
		hand = new BlackjackHand();
	}

	public boolean hasBlackjack() {
		if (hand.getHandValue() == 21) {
			return true;
		}
		return false;
	}

	public void doTurn(Deck deck) {
		System.out.println();
		while (hand.getHandValue() <= 16) {
			System.out.println("Dealer has " + hand.getHandValue() + " and hits");
			hand.addCard(deck.dealCard());
			System.out.println("Dealer:\n" + displayHand());
		}
		if (hand.getHandValue() > 21) {
			System.out.println("Dealer busts with " + hand.getHandValue());
		} else {
			System.out.println("Dealer stands with " + hand.getHandValue());
		}
	}
}
