package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {

	Scanner scanner = new Scanner(System.in);
	int numPlayers;
	ArrayList<BlackjackPlayer> players;
	BlackjackDealer dealer;
	Deck deck;

	public void run() {
		init();

		do {
			shuffle();
			bet();
			dealCards();
			checkForBlackjacks();
			doPlayerTurn();
			doDealerTurn();
			calculateWinnings();
			showMoney();
			reset();

		} while (playAgain());
		
		System.out.println("Thanks for playing Blackjack!");
	}

	public void init() {
		System.out.println("Let's play some Blackjack!");

		do {
			System.out.print("How many people are playing (1-5)? ");
			numPlayers = scanner.nextInt();
			scanner.nextLine();

		} while (numPlayers > 5 || numPlayers < 0);

		deck = new Deck();
		players = new ArrayList<>(numPlayers);
		dealer = new BlackjackDealer("Dealer");

		String name = "";
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("What is player " + (i + 1) + "'s name? ");
			name = scanner.next();
			players.add(new BlackjackPlayer(name));
		}

	}

	private void shuffle() {
		deck.shuffle(numPlayers);
	}

	private void bet() {
		int betValue;

		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i).getCash() > 0) {
				Player p = players.get(i);
				do {
					System.out.print("How much do you want to bet " + p.getName() + (" (1-" + p.getCash()) + ")? ");
					betValue = scanner.nextInt();
					players.get(i).setBet(betValue);
				} while (!(betValue > 0 && betValue <= p.getCash()));

				System.out.println("");
			}
		}
	}

	private void dealCards() {

		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < numPlayers; y++) {
				if (players.get(y).getCash() > 0) {
					players.get(y).addCard(deck.dealCard());
				}
			}

			dealer.addCard(deck.dealCard());
		}
	}

	private void checkForBlackjacks() {
		if (dealer.hasBlackjack()) {
			System.out.println("Dealer has Blackjack!");
			for (int i = 0; i < numPlayers; i++) {
				if (players.get(i).getHandTotal() == 21) {
					System.out.println(players.get(i).getName() + " pushes.");
					players.get(i).push();
				} else {
					System.out.println(players.get(i).getName() + " loses.");
					players.get(i).loss();
				}
			}
		} else {
			System.out.println("Dealer doesn't have blackjack.");

			for (int i = 0; i < numPlayers; i++) {
				if (players.get(i).getHandTotal() == 21) {
					System.out.println(players.get(i).getName() + " has blackjack!");
					players.get(i).blackjack();
				}
			}
		}
	}

	private void doPlayerTurn() {
		String choice;
		char c;
		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i).getBet() > 0) {
				Player p = players.get(i);
				System.out.println();
				System.out.println(p.getName() + " has:\n" + p.displayHand());

				do {
					do {
						System.out.print(p.getName() + " do you want to Hit or Stay? ");
						choice = scanner.next();
						c = choice.toLowerCase().charAt(0);
					} while (!(c == 'h' || c == 's'));
					if (c == 'h') {
						p.addCard(deck.dealCard());
						System.out.println(p.getName() + " has:\n" + p.displayHand());
					}
				} while (c != 's' && p.getHandTotal() <= 21);
			}
		}
	}

	private void doDealerTurn() {
		boolean playing = false;
		for (int i = 0; i < numPlayers && playing == false; i++) {
			if (players.get(i).getBet() > 0 && players.get(i).getHandTotal() <= 21) {
				playing = true;
			}
		}
		if (playing) {
			dealer.doTurn(deck);
		}
	}

	private void calculateWinnings() {
		System.out.println();

		for (int i = 0; i < numPlayers; i++) {
			BlackjackPlayer p = players.get(i);
			if (p.getBet() > 0) {
				if (p.getHandTotal() > 21) {
					System.out.println(p.getName() + " has busted");
					p.loss();
				} else if (p.getHandTotal() == dealer.getHandTotal()) {
					System.out.println(p.getName() + " has pushed");
					p.push();
				} else if (p.getHandTotal() < dealer.getHandTotal() && dealer.getHandTotal() <= 21) {
					System.out.println(p.getName() + " has lost");
					p.loss();
				} else if (p.getHandTotal() == 21) {
					System.out.println(p.getName() + " has won with blackjack!");
					p.blackjack();
				} else {
					System.out.println(p.getName() + " has won");
					p.win();
				}
			}
		}
	}

	private void showMoney() {
		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i).getCash() > 0) {
				System.out.println(players.get(i).getName() + " has $" + players.get(i).getCash());
			}
			if (players.get(i).getCash() == 0) {
				System.out.println(
						players.get(i).getName() + " has $" + players.get(i).getCash() + " and is out of the game.");
				players.get(i).setCash(-1);
			}
		}
	}

	private void reset() {
		for (int i = 0; i < numPlayers; i++) {
			players.get(i).clearHand();
		}
		dealer.clearHand();
	}

	private boolean playAgain() {
		String command;
		char c;
		
		if (gameOver()) {
			return false;
		} else {
			do {
				System.out.println("");
				System.out.print("Do you want to play again Yes or No? ");
				command = scanner.next();
				c = command.toLowerCase().charAt(0);
			} while (!(c == 'y' || c == 'n'));
			if (c == 'n') {
				return false;
			}
		}
		return true;
	}
	
	private boolean gameOver() {
		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i).getCash() > 0) {
				return false;
			}
		}
		
		System.out.println("All players have no money. Game Over!");
		return true;
	}

	public static void main(String[] args) {
		BlackjackGame game = new BlackjackGame();

		game.run();

	}
}
