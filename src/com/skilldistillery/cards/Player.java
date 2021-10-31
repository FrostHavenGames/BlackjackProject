package com.skilldistillery.cards;

public class Player {
	
	protected String name;
	
	public String getName() {
		return name;
	}
	
	protected Hand hand;
	
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	protected int cash = 1000;
	
	public int getCash() {
		return cash;
	}

	public void setCash(int amount) {
		cash = amount;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public void addCard(Card card) {
		hand.addCard(card);
	}
	
	public int getHandTotal() {
		return hand.getHandValue();
	}
	
	public String displayHand() {
		return hand.toString();
	}
}
