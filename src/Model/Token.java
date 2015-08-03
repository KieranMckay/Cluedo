package model;

/**
 * A class representing the token of a Character for moving around the Board in the game Cluedo.
 * 
 * @author Kieran Mckay
 *
 */
public class Token {
	private String name;
	private Tile location;
	
	public Token(String name, Tile start) {
		this.name = name;
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
	 * Get the players current room. Can return Null.
	 *
	 * @return Room the player is in or NULL if they're in the corridor
	 */
	public Room getRoom() {
		if (location.isRoom()){
			return (Room) location;
		}
		return null;
	}
	
	/**
	 * A char representation of this Token
	 * 
	 * @return First char of the token name
	 */
	public char toChar(){
		return this.name.charAt(0);
	}
}