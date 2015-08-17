package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.*;
import javax.imageio.ImageIO;

public class CluedoPanel extends JPanel{

	private static final String IMAGE_PATH = "images/";

	private static final Image[] WALL_IMGS = {	};

	private static final String[] preferredFonts = {"Courier New","Arial","Times New Roman"};
	private Font font;
	private final int uid;
	private final Cluedo gameBoard;

	public CluedoPanel(int uid, Cluedo gameBoard) {
		this.gameBoard = gameBoard;
		this.uid = uid;
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		HashSet<String> availableNames = new HashSet();

		for(String name : env.getAvailableFontFamilyNames()) {
			availableNames.add(name);
		}

		for(String pf : preferredFonts) {
			if(availableNames.contains(pf)) {
				font = new Font(pf,Font.BOLD,24);
				break;
			}
		}
		setSize(new Dimension(gameBoard.width()*30,(gameBoard.height()*30) + 30));
	}

	public void paint(Graphics g) {
		int width = gameBoard.width();
		int height = gameBoard.height();

		// First, draw the board

		// Second, draw the characters

		// finally, draw any messages
	}

	private void drawScore(String score, Graphics g) {
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics();
		int ascent = metrics.getAscent();
		int width = gameBoard.width();
		int height = gameBoard.height();
		int y = (height*30);
		g.setColor(Color.BLACK);
		g.fillRect(0,y, width*30, 30);
		char[] chars = score.toCharArray();
		g.setColor(Color.YELLOW);
		g.drawChars(chars,0,chars.length,5,y+ascent);
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
	 * Rotate an image a given number of degrees.
	 * @param src
	 * @param angle
	 * @return
	 */
	public static Image rotate(Image src, double angle) {
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.rotate(Math.toRadians(angle), width/2, height/2);
		g.drawImage(src,0,0,width,height,null);
		g.dispose();
		return img;
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
		java.net.URL imageURL = CluedoPanel.class.getResource(IMAGE_PATH
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
