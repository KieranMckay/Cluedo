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
	public Player(int playerNumber, Token myToken) {
		this.playerNumber = playerNumber;
		this.myToken = myToken;
		this.hand = new HashSet<Card>();
		this.suspects = new HashSet<Card>();

		//TODO populate suspects with all possible weapons, rooms and characters
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
	 * Adds a card to this players hand.
	 *
	 * @param card - Card.
	 */
	public void addCard(Card card) {
		hand.add(card);
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
	 * Returns the players player number as a string.
	 */
	@Override
	public String toString(){
		return "Player "+playerNumber;
	}
//Has now been implemented by the board class, TODO delete me (maybe)
	public boolean move(direction direction) {
		Tile tile = this.myToken.getLocation();
		Tile newTile = tile.getNeighbour(direction);
		if(newTile != null){//can move in the given direction
			tile.removeToken(myToken);
			newTile.addToken(myToken);
			this.myToken.setLocation(newTile);
			return true;
		}
		System.out.println("Not a valid Move");
		return false;
	}


}