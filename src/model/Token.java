package model;

/**
 * A class representing the token of a Character for moving around the Board in the game Cluedo.
 *
 * @author Kieran Mckay
 *
 */
public class Token {
	private String name;
	private Tile position;

	public Token(String name, Tile position) {
		this.name = name;
		this.position = position;
		this.position.addToken(this);
	}

	/**
	 * Returns the name of this character.
	 *
	 * @return String - Character's name
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Returns characters current position.
	 *
	 * @return Tile - players current position.
	 */
	public Tile getPosition() {
		return position;
	}

	/**
	 * Set's the Tokens location.
	 *
	 * @param Location - The location of the Token.
	 */
	public void setLocation(Tile location) {
		this.position = location;
		location.addToken(this);
	}

	/**
	 * Get the players current room. Can return Null.
	 *
	 * @return Room the player is in or NULL if they're in the corridor
	 */
	public Room getRoom() {
		if (position.isRoom()){
			return (Room) position;
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