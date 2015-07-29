package Model;

import java.util.*;

/**
 * A class representing a Player in the game Cluedo
 * 
 * @author Kieran Mckay
 *
 */
public class Player {
	private String name;
	private Location location;
	private Set<Card> hand;
	private Set<Card> suspects;
	private Room room;
	private Location start;

	/**
	 * Create a new player card.
	 *
	 * @param - ????.
	 */
	public Player(String name) {
		this.name = name;
		//this.location = start;
		//this.start = start;
		this.hand = new HashSet<Card>();
		this.suspects = new HashSet<Card>();
		//this.room = start.getRoom();

		// populate suspects with all possible weapons, rooms and characters
	}

	/**
	 * Returns the name of this players character.
	 *
	 * @return String - Player's character's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns players current location.
	 *
	 * @return Location - players current location.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Set's the players location.
	 *
	 * @param Location
	 *            - The location of the player.
	 */
	public void setLocation(Location location) {
		this.location = location;
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
	 * @param card
	 *            - Card.
	 */
	public void addCard(Card card) {
		hand.add(card);
	}
	
	/**
	 * Removes a given card from a players hand
	 * @param card
	 */
	public void removeCard(Card card){
		//not sure if this method is required yet
		hand.remove(card);
	}

	/**
	 * Returns the players current start point.
	 *
	 * @return start - Location.
	 */
	public Location getStart() {
		return start;
	}

	/**
	 * Set the start position of the player. Start is the location of which they
	 * entered the current room.
	 *
	 * @param start
	 *            - Location
	 */
	public void setEntrance(Location start) {
		this.start = start;
	}

	/**
	 * Get the players current room.
	 *
	 * @return room - Room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Set the players current room.
	 *
	 * @param room
	 *            - Room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
}