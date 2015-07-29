package Model;

/**
 * A class which represents a real location on the board of the Cluedo game
 * ]
 * @author Kieran Mckay
 *
 */
public class Location {
	private int x;
	private int y;

	private Room room;

	//do I need these?????????
	private Location north;
	private Location east;
	private Location south;
	private Location west;

	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Room getRoom(){
		return room;
	}
}
