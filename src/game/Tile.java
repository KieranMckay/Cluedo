package game;

import java.util.*;
/**
 * A class for representing tiles on the board
 * each tile contains a list of neighbours that each correspond to a direction enumeration
 * @author Johnny D
 *
 */
public abstract class Tile {

	Map<String,Tile> neighbours = new HashMap<String,Tile>();

	private int x;
	private int y;

	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * get a list of all the neighbours
	 *
	 * @return List of neighbour names
	 */
	public List<String> neighbourNames() {
		return new ArrayList<String>(neighbours.keySet());
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	/**	Decides whether a player can move to this square or not
	 *
	 * @return Boolean can move here or not
	 */
	public abstract boolean hasSpace();

	/**
	 * Adds the given tile in the listed direction
	 * @param direction String (N,S,E,W,doorNum or Teleport)
	 * @param t Neighbouring tile
	 */
	public void addNeighbour(String direction, Tile t) {
		neighbours.put(direction, t);
	}

	/**
	 * Returns the given Tile from neighbours
	 * @param name Name of neighbouring tile
	 * @return Neighbour tile or null if doesn't exist
	 */
	public Tile getNeighbour(String name) {
		return neighbours.get(name);
	}

	/**
	 * Moves the given token to this tile
	 *
	 * @param t Token to be moved here
	 */
	public abstract void moveTo(Token t);

	/**
	 * Add a token to this tile
	 * @param t Token to be added
	 */
	public abstract void addToken(Token t);

	/**
	 * Removes the given token from this tile
	 *
	 * @param t
	 */
	public abstract void removeToken(Token t);

	public abstract boolean isRoom();

	public List<Tile> getNeighbourTiles(){
		return new ArrayList<Tile>(neighbours.values());
	}
}