package ui;

import game.Room;
import game.Token;
import game.Weapon;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;
import javax.imageio.ImageIO;

import control.Game;

public class BoardPanel extends JPanel implements MouseListener{

	private static final String BOARD_NAME = "Cluedo Board.png";
	private Game game;
	public JLabel boardImage;

	public BoardPanel(Game game) {
		this.game = game;
		BufferedImage image = loadImage(BOARD_NAME);
		boardImage = new JLabel(new ImageIcon(image));
		boardImage.setMaximumSize(new Dimension(100, 100));
		add(boardImage);
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);

		for (Weapon w : game.weapons.values()){
			//w.draw(g,this);
		}
		for(Token t : game.tokens.values()){
			t.draw(g,this);
		}
		for(Room r : game.rooms.values()){
			r.draw(g, this);
		}
		// finally, draw any messages
	}

	/**
	 * Load an image from the file system, using a given filename.
	 *
	 * @param filename
	 * @return
	 */
	public static BufferedImage loadImage(String filename) {
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		java.net.URL imageURL = BoardPanel.class.getResource("/"+filename);

		try {
			BufferedImage img = ImageIO.read(imageURL);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}

	public static void main(String[] args){
		new BoardPanel(new Game());
	}

	@Override
	public void mouseClicked(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// TODO Auto-generated method stub
		int squareX = (e.getX()-10)/32;
		int squareY = (e.getY()-10)/32;
		System.out.println("clicked: x"+squareX + "  y:"+squareY);
		int curXPos = game.player.getToken().getX();
		int curYPos = game.player.getToken().getY();
		if(squareX == curXPos-1){ //move
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
