package model;

/**
 * A class representing a Weapon in the game Cluedo
 * 
 * @author Kieran Mckay
 *
 */
public class Weapon {
	String name;

	public Weapon(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}