package Model;

import java.util.*;

public abstract class Tile {
	
	public static enum direction{
		NORTH,EAST,SOUTH,WEST,TELE
	};
	
	Map<direction,Tile> neighbours;
	
	public List<direction> getDirections(){
		return new ArrayList(neighbours.keySet());
	}
	
	public List<Tile> getNeighbours(){
		return new ArrayList(this.neighbours.values());
	}
	
	public void addNeighbour(direction dir, Tile tile){
		neighbours.put(dir,tile);
	}
	
	public char toChar(){
		return ' ';
	}
}