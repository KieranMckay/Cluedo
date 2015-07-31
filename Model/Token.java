package Model;

/**
 * A class representing the token of a Character for moving around the Board in the game Cluedo.
 * 
 * @author Kieran Mckay
 *
 */
public class Token {
	private String name;
	private Tile start;
	private Tile location;
	//private Room room;
	
	public Token(String name, Tile start) {
		this.name = name;
		this.start = start;
		this.location = start;
		//this.room = location.getRoom();  //TODO Location or Tile needs a method getRoom() to return what room is at that location
	}

	/**
	 * Returns the name of this character.
	 *
	 * @return String - Character's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns characters current location.
	 *
	 * @return Location - players current location.
	 */
	public Tile getLocation() {
		return location;
	}

	/**
	 * Set's the Tokens location.
	 *
	 * @param Location - The location of the Token.
	 */
	public void setLocation(Tile location) {
		this.location = location;
	}

	/**
	 * Returns the Tokens current start point.
	 *
	 * @return start - Location.
	 */
	public Tile getStart() {
		return start;
	}

	/**
	 * Set the start position of the player. Start is the location of which they
	 * entered the current room.
	 *
	 * @param start
	 *            - Location
	 */
	public void setEntrance(Tile start) {
		this.start = start;
	}
	
	/**
	 * Get the players current room.
	 *
	 * @return room - Room
	 */
	//public Room getRoom() {
	//	return room;
	//}

	/**
	 * Set the players current room.
	 *
	 * @param room
	 *            - Room
	 */
	//public void setRoom(Room room) {
	//	this.room = room;
	//}
	
}
