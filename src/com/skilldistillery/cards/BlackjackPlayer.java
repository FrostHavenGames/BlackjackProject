package com.skilldistillery.cards;

public class BlackjackPlayer extends Player {
	
	private int bet;
	public void setBet(int bet) {
		this.bet = bet;
	}
	public int getBet() {
		return bet;
	}
	
	public BlackjackPlayer(String name) {
		super(name);
		hand = new BlackjackHand();
	}
	
	public void win() {
		cash += bet;
		bet = 0;
	}

	public void loss() {
		cash -= bet;
		bet = 0;
	}
	
	public void blackjack() {
		cash += bet * 1.5;
		bet = 0;
	}
	
	public void push() {
		bet = 0;
	}
}
