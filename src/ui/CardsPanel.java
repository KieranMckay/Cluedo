package ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import game.Card;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Game;

public class CardsPanel extends JPanel {

	private JPanel hand = new JPanel();
	private JLabel playerLabel = new JLabel();

	private Game game;

	public CardsPanel(Game game){
		this.game = game;
		update();
		setLayout(new BorderLayout());
		setVisible(true);
	}

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

	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("!!!!!!!!!!!!!!painting card panel"+game.player.toString());
	}
}