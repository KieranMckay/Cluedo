package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A class representing the token of a Character for moving around the Board in the game Cluedo.
 *
 * @author Kieran Mckay
 *
 */
public class Token {
	public int size = 27;
	public int offset = 3;
	private BufferedImage icon;
	private String name;
	private Tile position;

	public Token(String name, Tile position, BufferedImage icon) {
		this.icon = icon;
		this.name = name;
		this.position = position;
		this.position.addToken(this);
	}

	/**
	 * Returns the name of this character.
	 *
	 * @return String - Character's name
	 */
	@Override
	public String toString() {
		return name;
	}
	
	public int getX(){
		return position.getX();
	}
	
	public int getY(){
		return position.getY();
	}
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.drawImage(icon, (7*size)+offset, (7*size)+offset, size, size, null);
	}


	/**
	 * Returns characters current position.
	 *
	 * @return Tile - players current position.
	 */
	public Tile getPosition() {
		return position;
	}

	/**
	 * Set's the Tokens location.
	 *
	 * @param Location - The location of the Token.
	 */
	public void setLocation(Tile location) {
		this.position = location;
		location.addToken(this);
	}

	/**
	 * Get the players current room. Can return Null.
	 *
	 * @return Room the player is in or NULL if they're in the corridor
	 */
	public Room getRoom() {
		if (position.isRoom()){
			return (Room) position;
		}
		return null;
	}

	/**
	 * A char representation of this Token
	 *
	 * @return First char of the token name
	 */
	public char toChar(){
		return this.name.charAt(0);
	}
}