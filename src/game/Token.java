package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ui.BoardPanel;

/**
 * A class representing the token of a Character for moving around the Board in the game Cluedo.
 *
 * @author Kieran Mckay
 *
 */
public class Token{
	public int size = 33;
	public int offset = 10;
	private BufferedImage icon;
	private String name;
	private Tile position;
	private double offsetX = 0;
	private double offsetY = 0;

	public Token(String name, Tile position, BufferedImage icon) {
		this.icon = icon;
		this.name = name;
		this.position = position;
		this.position.addToken(this);
	}
	/*increment the visual position of this token between 0 -1.0 per square*/
	public void setYoffset(double amt){
		this.offsetY = amt;
	}
	public double getXOffset(){
		return offsetX;
	}
	public double getYOffset(){
		return offsetY;
	}

	/*increment the visual position of this token between 0 -1.0 per square*/
	public void setXoffset(double amt){
		this.offsetX = amt;
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

	public void draw(Graphics g,BoardPanel panel){
		double squareSize = panel.getWidth()/25.0;
		g.drawImage(icon, (int)((getX()*squareSize*1.01)+((offsetX%1)*squareSize))+offset, (int)((getY()*squareSize)+((offsetY%1)*squareSize))+offset, size, size, null);
	}

	/*an overloading method for drawing a token in a room, specifiying the x and y*/
	public void draw(Graphics g,BoardPanel panel,int x, int y){
		double squareSize = panel.getWidth()/25.0;
		g.drawImage(icon, (int)((x*squareSize*1.01)+((offsetX%1)*squareSize))+offset, (int)((y*squareSize)+((offsetY%1)*squareSize))+offset, size, size, null);
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

	public boolean move(String direction) {
		double drawIncrement = 0.01;
		Tile tile = getPosition();
		Tile newTile = tile.getNeighbour(direction);
		if(newTile != null && newTile.hasSpace()){//can move in the given direction
			newTile.moveTo(this);
			return true;
		}
		return false;
	}
}