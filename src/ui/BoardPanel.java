package ui;

import game.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


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
	BufferedImage testImage;
	public BoardPanel(JFrame frame) {
		this.frame = frame;
		BufferedImage image = loadImage("cluedo_board.png");
		testImage = loadImage("cluedo_board.png");
		JLabel boardImage = new JLabel(new ImageIcon(image));
		boardImage.setMaximumSize(new Dimension(100, 100));
		add(boardImage);

	}



	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.red);
		g.fillOval(50, 50, 50, 50);
		// First, draw the board
		g.drawImage(testImage, 100, 100, 60, 60, null);
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
