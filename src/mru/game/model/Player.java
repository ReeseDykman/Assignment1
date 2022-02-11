package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	
	private String name;
	private double balance;
	private int wins;
	
	/**
	 * constructor
	 * 
	 * @param name of player
	 * @param balance they will start with
	 * @param wins they will start with
	 */
	public Player(String name, double balance, int wins) {
		this.name = name;
		this.balance = balance;
		this.wins = wins;
	}
	
	/**
	 * constructor 
	 * 
	 * initializes wins to 0 and balance to 100
	 * 
	 * @param name of the player
	 */
	public Player (String name) {
		this.setName(name);
		balance = 100;
		wins = 0;
		
	}
	
	/**
	 * getter for name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for name of player
	 * @param name of player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter for player balance
	 * 
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * sets the balance of the player
	 * 
	 * @param balance desired
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * getter for wins
	 * 
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * setter for wims
	 * @param wins desired
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	/**
	 *increase the win counter by one 
	 */
	public void increaseWins() {
		wins++;
	}
	
	/**
	 * prints the name balance and wins of a player
	 * format friendly for saving data to a file
	 */
	public String toString() {
		return name+","+balance+","+wins;
	}
	
}
