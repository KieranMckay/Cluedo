package model;

/**
 * A class representing a Room in the game Cluedo
 * 
 * @author Kieran Mckay
 *
 */
public class Room extends Tile{	
	private String name;

	public Room(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of this Room.
	 */
	public String toString(){
		return name;
	}
	
	/**
	 * Returns the first character of this rooms name
	 */
	public char toChar(){
		return this.name.charAt(0);
	}
}