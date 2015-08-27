package ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import game.Card;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Game;

/**
 * A class that extends JPanel.
 * Displays current player's Picture and all the cards in their hand in a panel in the main CluedoFrame
 *
 * @author Kieran Mckay
 *
 */
public class CardsPanel extends JPanel {

	private JPanel hand = new JPanel();
	private JLabel playerLabel = new JLabel();

	private Game game;

	/**
	 * Constructs the panel.
	 *
	 * @param game - the game which is currently being played
	 */
	public CardsPanel(Game game){
		this.game = game;
		update();
		setLayout(new BorderLayout());
		setVisible(true);
	}

	/**
	 * updates the panel to display the information of the current player
	 */
	public void update() {
		hand.removeAll();
		playerLabel.removeAll();
		removeAll();
		revalidate();

		playerLabel.setIcon(new ImageIcon(BoardPanel.loadImage(game.player.getToken().toString()+".png")));
		ImageResize size = new ImageResize(playerLabel);
		size.resize(0.75);
		add(playerLabel, BorderLayout.WEST);
		playerLabel.setVisible(true);

		hand.setLayout(new GridLayout(1,game.player.getHand().size()));
		add(hand, BorderLayout.CENTER);

		addHand();

		hand.setVisible(true);
	}

	/**
	 * Adds pictures of all the cards in the players hand to this panel
	 */
	private void addHand(){
		int i = 0;
		for(Card c : game.player.getHand()){
			JLabel label = new JLabel();
			label.setIcon(new ImageIcon(BoardPanel.loadImage(c.toString()+".png")));
			ImageResize size = new ImageResize(label);
			size.resize(0.35);
			hand.add(label, i);
			i++;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}