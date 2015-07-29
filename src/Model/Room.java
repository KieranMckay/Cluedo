package Model;

/**
 * A class representing a Room in the game Cluedo
 * 
 * @author Kieran Mckay
 *
 */
public class Room {
	String name;

	public Room(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
