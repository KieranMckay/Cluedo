package game;

/**
 * A class representing a Weapon in the game Cluedo
 *
 * @author Kieran Mckay
 *
 */
public class Weapon {
	private String name;
	private Room room;

	public Weapon(String name) {
		this.name = name;
	}

	public String toString(){
		return name;
	}

	public void setLocation(Room room) {
		this.room = room;
	}

	public Room getRoom(){
		return room;
	}
}