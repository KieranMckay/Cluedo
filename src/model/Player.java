package model;

import java.util.*;

import model.Tile.direction;

/**
 * A class representing a Player in the game Cluedo
 *
 * @author Kieran Mckay
 *
 */
public class Player {
	private int playerNumber;
	private Token myToken;
	private Set<Card> hand;
	private Set<Card> suspects;

	/**
	 * Create a new player card.
	 *
	 * @param - Token the character belonging to this player.
	 */
	public Player(int playerNumber, Token myToken, Set<Card> suspects) {
		this.playerNumber = playerNumber;
		this.myToken = myToken;
		this.hand = new HashSet<Card>();
		this.suspects = suspects;
	}

	/**
	 * Returns a set representing this players hand of cards.
	 *
	 * @return hand - Set of type Card.
	 */
	public Set<Card> getHand() {
		return hand;
	}

	/**
	 * Returns a set representing this players hand of cards.
	 *
	 * @return hand - Set of type Card.
	 */
	public Set<Card> getSuspects() {
		return suspects;
	}

	/**
	 * Adds a card to this players hand.
	 *
	 * @param card - Card.
	 */
	public void addCard(Card card) {
		hand.add(card);
		suspects.remove(card);
	}

	/**
	 * Get this players Token
	 *
	 * @return the myCharacter
	 */
	public Token getToken() {
		return myToken;
	}

	/**
	 * Returns this players player number
	 * @return int - players number
	 */
	public int getPlayerNumber(){
		return playerNumber;
	}

	/**
	 * Returns the players player number as a string.
	 */
	@Override
	public String toString(){
		return "Player "+playerNumber;
	}

	public boolean move(direction direction) { //TODO fix this so that the tile handles moving
		Tile tile = this.myToken.getLocation();
		Tile newTile = tile.getNeighbour(direction);
		if(newTile != null && newTile.hasSpace()){//can move in the given direction
			tile.removeToken(myToken);
			newTile.addToken(myToken);
			this.myToken.setLocation(newTile);
			return true;
		}
		System.out.println("Not a valid Move");
		return false;
	}

	public void removeSuspect(Card refuted) {
		suspects.remove(refuted);

	}


}