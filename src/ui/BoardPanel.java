package ui;

import game.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.*;
import javax.imageio.ImageIO;

import control.Game;

public class BoardPanel extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final String IMAGE_PATH = "images/";
	private Font font = new Font("Arial",Font.BOLD,24);

	private JFrame frame;
	

	public BoardPanel(JFrame frame) {
		this.frame = frame;
		BufferedImage image = loadImage("cluedo_board_1.png");
		JLabel boardImage = new JLabel(new ImageIcon(image));
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
		java.net.URL imageURL = BoardPanel.class.getResource(IMAGE_PATH
				+ filename);

		try {
			BufferedImage img = ImageIO.read(new File( IMAGE_PATH + filename ) ); //TODO get imageURL working?
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}
}
