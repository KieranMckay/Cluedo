package ui;

import game.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


import javax.swing.*;
import javax.imageio.ImageIO;

import control.Game;

public class BoardPanel extends JPanel{

	private static final String BOARD_NAME = "cluedo_board.png";

	public BoardPanel() {
		BufferedImage image = loadImage(BOARD_NAME);
		JLabel boardImage = new JLabel(new ImageIcon(image));
		boardImage.setMaximumSize(new Dimension(100, 100));
		add(boardImage);

	}

	public void paint(Graphics g) {
		super.paint(g);
		// First, draw the board
		// Second, draw the characters
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
			BufferedImage img = ImageIO.read(imageURL); //TODO get imageURL working?
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}
}
