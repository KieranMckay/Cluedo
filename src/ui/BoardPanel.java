package ui;

import game.Room;
import game.Tile;
import game.Token;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;
import javax.imageio.ImageIO;

import control.Game;

/**
 * A panel to display the playing board and all character and weapon tokens on as the main game display
 *
 * @author Kieran Mckay
 */
public class BoardPanel extends JPanel implements MouseListener{

	private static final String BOARD_NAME = "Cluedo Board.png";
	private Game game;
	public JLabel boardImage;

	/**
	 * Creates the panel with the game currently in play.
	 *
	 * @param game - the Game object currently in use
	 */
	public BoardPanel(Game game) {
		this.game = game;
		BufferedImage image = loadImage(BOARD_NAME);
		boardImage = new JLabel(new ImageIcon(image));
		boardImage.setMaximumSize(new Dimension(100, 100));
		add(boardImage);
		addMouseListener(this);
	}

	/**
	 * Paint this component and any tokens and weapons on the board or in rooms.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(Token t : Game.tokens.values()){
			t.draw(g,this);
		}
		for(Room r : Game.rooms.values()){
			r.draw(g, this);
		}
	}

	/**
	 * Load an image from the file system, using a given filename.
	 *
	 * @param filename
	 * @return
	 */
	public static BufferedImage loadImage(String filename) {
		try {
			BufferedImage img = ImageIO.read(new File("src/assets/"+filename));
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * listens to when a mouse click is released on the board game
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(game.turn.turns <= 0)return;
		int squareX = (int)((e.getX()-10)/33.0);
		int squareY = (int)((e.getY()-10)/33.0);

		int curXPos = game.player.getToken().getX();
		int curYPos = game.player.getToken().getY();
		for(String name:game.player.getToken().getPosition().neighbourNames()){
			Tile t = game.player.getToken().getPosition().getNeighbour(name);
			System.out.println("neighbour "+ name);
			if(t.getX() == squareX && t.getY() == squareY){
				game.turn.movePlayer(name);
				repaint();
				return;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
