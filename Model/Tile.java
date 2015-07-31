package Model;

import java.util.*;

public abstract class Tile {
	
	public static enum direction{
		NORTH,EAST,SOUTH,WEST,TELE
	};
	
	Map<direction,Tile> neighbours;
	
	public Tile(){
		neighbours = new HashMap<Tile.direction, Tile>();
	}
	
	public Tile getNeighbour(direction dir){
		return neighbours.get(dir);
	}
	public List<direction> getDirections(){
		return new ArrayList<direction>(neighbours.keySet());
	}
	
	public List<Tile> getNeighbours(){
		return new ArrayList<Tile>(this.neighbours.values());
	}
	
	public void addNeighbour(direction dir, Tile tile){
		neighbours.put(dir,tile);
	}
	
	public char toChar(){
		return ' ';
	}
}