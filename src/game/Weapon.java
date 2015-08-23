package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A class representing a Weapon in the game Cluedo
 *
 * @author Kieran Mckay
 *
 */
public class Weapon {
	public int size = 27;
	public int offset = 3;
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


	public int getX(){
		return room.getX();
	}

	public int getY(){
		return room.getY()-1;
	}

	public void draw(Graphics g){
		g.setColor(Color.red);
		g.drawImage(icon, (getX()*size)+offset, (getY()*size)+offset, size, size, null);
	}
}