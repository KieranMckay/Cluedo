package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A class representing a Weapon in the game Cluedo
 *
 * @author Kieran Mckay
 *
 */
public class Weapon {
	private String name;
	private BufferedImage icon;
	private Room room;

	public Weapon(String name, BufferedImage icon) {
		this.icon = icon;
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

	public void draw(Graphics g){
		//TODO draw weapon
	}
}