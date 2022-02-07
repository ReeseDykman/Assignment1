package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	
	private String name;
	private double balance;
	private int wins;
	
	public Player(String name, double balance, int wins) {
		this.name = name;
		this.balance = balance;
		this.wins = wins;
	}
	
	public Player (String name) {
		this.setName(name);
		balance = 100;
		wins = 0;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}
	
}
