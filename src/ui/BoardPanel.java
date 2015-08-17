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

	private Game game;
	private Board gameBoard;
	private JFrame frame;

	public BoardPanel(JFrame frame, Game game) {
		this.game = game;
		this.gameBoard = game.getBoard();
		this.frame = frame;
	}

	public void paint(Graphics g) {
		int width = gameBoard.width();
		int height = gameBoard.height();

		// First, draw the board

		// Second, draw the characters

		// finally, draw any messages
	}

	private void drawMessage(String msg, Graphics g) {
		g.setFont(font);
		int width = gameBoard.width();
		int height = gameBoard.height();
		FontMetrics metrics = g.getFontMetrics();
		int ascent = metrics.getAscent();
		char[] chars = msg.toCharArray();
		int msgWidth = metrics.charsWidth(msg.toCharArray(),0,msg.length());
		int msgHeight = metrics.getHeight();
		int boxWidth = msgWidth + 30;
		int boxHeight = msgHeight + 30;
		int x = ((width*30) - boxWidth) / 2;
		int y = ((height*30) - boxHeight) / 2;
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x,y, boxWidth, boxHeight);
		g.setColor(Color.yellow);
		g.drawRect(x,y, boxWidth, boxHeight);
		g.drawRect(x+1,y+1, boxWidth-2, boxHeight-2);
		g.drawChars(chars,0,chars.length,x+15,y+15+ascent);
		offscreen = null; // reset offscreen, since we want to get rid of the
							// message
	}


	private Image offscreen = null;

	public void update(Graphics g) {
		if(offscreen == null) {
			initialiseOffscreen();
		}
		Image localOffscreen = offscreen;
		Graphics offgc = offscreen.getGraphics();
		// do normal redraw
		paint(offgc);
		// transfer offscreen to window
		g.drawImage(localOffscreen, 0, 0, this);
	}

	private void initialiseOffscreen() {
		Dimension d = size();
		offscreen = createImage(d.width, d.height);
		// clear the exposed area
		Graphics offgc = offscreen.getGraphics();
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, d.width, d.height);
		offgc.setColor(getForeground());

		int width = gameBoard.width();
		int height = gameBoard.height();

		// First, draw the board

	}

	/**
	 * Load an image from the file system, using a given filename.
	 *
	 * @param filename
	 * @return
	 */
	public static Image loadImage(String filename) {
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		java.net.URL imageURL = BoardPanel.class.getResource(IMAGE_PATH
				+ filename);

		try {
			Image img = ImageIO.read(imageURL);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}
}
