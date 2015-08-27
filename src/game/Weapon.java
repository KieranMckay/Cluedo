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
	public int size = 33;
	public int offset = 10;
	private String name;
	private BufferedImage icon;
	private Room room;

	public Weapon(String name, BufferedImage icon) {
		this.icon = icon;
		this.name = name;
	}

	@Override
	public String toString(){
		return name;
	}

	public void setLocation(Room room) {
		if(this.room != null) this.room.removeWeapon(this);
		this.room = room;
		room.addWeapon(this);
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

	public void draw(Graphics g,BoardPanel panel,int x, int y){
		/*double squareSize = panel.getWidth()/25.0;
		g.setColor(Color.red);
		g.drawImage(icon, (int)((x*squareSize)+offset), (int)((y*squareSize)+offset), size, size, null);*/

		double squareSize = panel.getWidth()/25.0;
		g.setColor(Color.red);
		g.drawImage(icon, (int)(x*squareSize*1.01)+offset, (int)(y*squareSize)+offset, size, size, null);
	}
}