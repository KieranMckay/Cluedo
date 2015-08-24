package ui;

import game.Token;
import game.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;
import javax.imageio.ImageIO;

import control.Game;

public class BoardPanel extends JPanel{

	private static final String BOARD_NAME = "Cluedo Board.png";
	private Game game;
	public JLabel boardImage;

	public BoardPanel(Game game) {
		this.game = game;
		BufferedImage image = loadImage(BOARD_NAME);
		boardImage = new JLabel(new ImageIcon(image));
		boardImage.setMaximumSize(new Dimension(100, 100));
		add(boardImage);

	}

	public void paint(Graphics g) {
		super.paint(g);
		// First, draw the board
		// Second, draw the weapons then characters
		for (Weapon w : game.weapons.values()){
			w.draw(g,this);
		}
		for(Token t : game.tokens.values()){
			t.draw(g,this);
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
}
