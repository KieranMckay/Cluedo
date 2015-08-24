package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ui.BoardPanel;

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
		return room.getY();
	}

	public void draw(Graphics g,BoardPanel panel){
		double squareSize = panel.getWidth()/25.0;
		g.setColor(Color.red);
		g.drawImage(icon, (int)((getX()*squareSize)+offset), (int)((getY()*squareSize)+offset), size, size, null);
	}
}