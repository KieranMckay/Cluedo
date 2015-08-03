package model;

import java.util.*;
/**
 * A class for representing tiles on the board
 * each tile contains a list of neighbours that each correspond to a direction enumeration
 * @author JTFM
 *
 */
public class Tile {
	
	public static enum direction{ //possible directions to move
		NORTH,EAST,SOUTH,WEST,TELE
	};
	
	private Map<direction,Tile> neighbours; //neighbouring tiles
	
	private Set<Token> tokens; //a set of tokens that are contained by this square (in the tile class this can only be one, dictated by hasSpace())
	
	
	
	public Tile(){
		neighbours = new HashMap<Tile.direction, Tile>();
	}
	/**
	 * Returns true if this tile has space for a token
	 * Tile class can only hold one
	 * @return
	 */
	public boolean hasSpace(){
		if(tokens == null || tokens.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Add a given token to this tile
	 * @param t Token to be added
	 */
	public void addToken(Token t){
		if(this.hasSpace()){
			if(tokens == null){
				tokens = new HashSet<Token>();
			}
			tokens.add(t);
		}
	}
	
	/**
	 * Remove a token from this tile
	 * @param t
	 */
	public void removeToken(Token t){
		if(tokens!= null) tokens.remove(t);
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
		return new ArrayList<direction>(neighbours.keySet());
	}
	/**
	 * List of the neighbouring tiles
	 * @return
	 */
	public List<Tile> getNeighbours(){
		return new ArrayList<Tile>(this.neighbours.values());
	}
	/**
	 *  add a neighbouring tile to this tile
	 * @param Direction must be a valid direction
	 * @param tile Neighbour to add
	 */
	public void addNeighbour(direction dir, Tile tile){
		neighbours.put(dir,tile);
	}
	
	/**
	 * Returns false unless overridden by child class Room
	 * 
	 * @return false
	 */
	public boolean isRoom(){		
		return false;
	}
	/**
	 * A character representation of this tile
	 * @return
	 */
	public char toChar(){
		if(!hasSpace()){
			return tokens.iterator().next().toChar(); //get the character at this position
		}
		return ' '; //the square is empty
	}
}