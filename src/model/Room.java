package model;

import java.util.*;

/**
 * A class representing a Room in the game Cluedo
 *
 * @author Johnny D
 *
 */
public class Room extends Tile{
	Map<String,Tile> neighbours = new HashMap<String,Tile>();
	List<Token> inRoom = new ArrayList<Token>();
	String name;

	public Room(String name){
		this.name = name;
	}

	@Override
	public void addToken(Token t){
		this.inRoom.add(t);
	}


	@Override
	public void moveTo(Token t) {
		if(t != null){
			t.getLocation().removeToken(t);
			inRoom.add(t);
			t.setLocation(this);
		}
	}

	@Override
	public boolean hasSpace() {
		return true;
	}


	@Override
	public void removeToken(Token t) {
		this.inRoom.remove(t);
	}

	public String[] getMembers(){
		String[] members = new String[inRoom.size()];
		for (int i = 0; i < members.length; i++) {
			members[i] = inRoom.get(i).toString();
		}
		return members;
	}

	@Override
	public boolean isRoom() {
		return true;
	}

	@Override
	public String toString(){
		return this.name;
	}

}