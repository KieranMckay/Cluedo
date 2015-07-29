package Model;

/**
 * A class for a Card representing a Room "clue" in the game Cluedo
 * 
 * @author Kieran Mckay
 *
 */
public class RoomCard implements Card {
	String name;

	public RoomCard(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

}
