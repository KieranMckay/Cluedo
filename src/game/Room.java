package game;

import java.awt.Graphics;
import java.util.*;

import ui.BoardPanel;

/**
 * A class representing a Room in the game Cluedo
 *
 * @author Johnny D
 *
 */
public class Room extends Tile{
	Map<String,Tile> neighbours = new HashMap<String,Tile>();
	List<Token> inRoom = new ArrayList<Token>();
	private String name;
	private List<Weapon> weapons;


	public Room(String name,int x, int y){
		super(x,y);
		this.name = name;
		this.weapons = new ArrayList<Weapon>();
	}

	public void draw(Graphics g,BoardPanel panel){
		int size = 34;
		int offset = 10;
		int i;
		for(i = 0; i < inRoom.size(); i++){
			inRoom.get(i).draw(g, panel, getX()+(i%4), getY()+(i/4));
			System.out.println("room " + getX() +" " + getY() + " drawing: " + inRoom.get(i).toString());
		}
		for(int j = 0; j < weapons.size(); j++){
			weapons.get(j).draw(g, panel, getX()+((i+j)%4), getY()+((i+j)/4));
			System.out.println("weapon " + getX() +" " + getY() + " drawing: " + weapons.get(j).toString());
		}
	}

	@Override
	public void addToken(Token t){
		this.inRoom.add(t);
	}

	@Override
	public void moveTo(Token t) {
		if(t != null){
			t.getPosition().removeToken(t);
			//inRoom.add(t);
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

	public void addWeapon(Weapon weapon) {
		weapons.add(weapon);
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void removeWeapon(Weapon weapon) {
		weapons.remove(weapon);
	}


}