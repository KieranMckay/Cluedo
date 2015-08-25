package game;

import java.awt.Color;
import java.awt.Graphics;

import ui.BoardPanel;
import ui.CluedoFrame;

public class Door extends Tile{
	public int size = 33;
	public int offset = 10;
	public Room room;
	public Location location;
	private String name;

	public Door(String name,int x, int y){
		super(x,y);
		this.name = name;
	}
	@Override
	public boolean hasSpace() {
		return room.hasSpace();
	}

	@Override
	public void moveTo(Token t) {
		if(t.getPosition().isRoom()){
			location.moveTo(t);
		}
		else{ //must be a location
			room.moveTo(t);
		}
	}

	public void draw(Graphics g,BoardPanel panel){
		double squareSize = panel.getWidth()/25.0;
		g.setColor(Color.red);
		g.drawString(name, (int)(getX()*squareSize*1.01)+offset, (int)(getY()*squareSize*1.01)+offset);
		//g.drawImage(icon, (int)((getX()*squareSize*1.01)+((offsetX%1)*squareSize))+offset, (int)((getY()*squareSize)+((offsetY%1)*squareSize))+offset, size, size, null);
	}

	@Override
	public void addNeighbour(String direction, Tile t) {
		this.location = (Location)t;
	}

	@Override
	public void addToken(Token t) {
	}

	@Override
	public void removeToken(Token t) {
	}

	@Override
	public boolean isRoom() {
		return false;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
