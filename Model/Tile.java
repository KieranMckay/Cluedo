package Model;

import java.util.*;
/**
 * A class for representing tiles on the board
 * each tile contains a list of neighbours that each correspond to a direction enumeration
 * @author JTFM
 *
 */
public abstract class Tile {
	
	public static enum direction{ //possible directions to move
		NORTH,EAST,SOUTH,WEST,TELE
	};
	
	Map<direction,Tile> neighbours; //neighbouring tiles
	
	public Tile(){
		neighbours = new HashMap<Tile.direction, Tile>();
	}
	/**
	 *  returns the tile in the given direction
	 *  null if not possible
	 * @param dir
	 * @return
	 */
	public Tile getNeighbour(direction dir){
		return neighbours.get(dir);
	}
	/**
	 * List of the possible directions that can be moved to from a square
	 * note - this does not account for a neighbouring square being ocupied
	 * @return
	 */
	public List<direction> getDirections(){
		return new ArrayList(neighbours.keySet());
	}
	/**
	 * List of the neighbouring tiles
	 * @return
	 */
	public List<Tile> getNeighbours(){
		return new ArrayList(this.neighbours.values());
	}
	/**
	 *  add a neighbouring tile to this tile
	 * @param Direction must be a valid direction
	 * @param tile Neighbour to add
	 */
	public void addNeighbour(direction dir, Tile tile){
		neighbours.put(dir,tile);
	}
	
	public char toChar(){
		return ' ';
	}
}