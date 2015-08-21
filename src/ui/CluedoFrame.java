package ui;


import game.Card;
import game.Player;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextField;

import control.Game;

public class CluedoFrame extends JFrame{

	private Game game;

	private JMenuBar menu = new JMenuBar();

	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");

	//TODO each of these needs to be a class that extends JPanel
	private BoardPanel board = new BoardPanel();
	private JPanel cards = new CardsPanel();
	private JPanel options = new JPanel();

	private JButton dice = new JButton("Roll Dice");
	private JButton move = new JButton("Move");
	private JButton suggest = new JButton("Make a suggestion");
	private JButton accuse = new JButton("Make an accusation");
	private JButton end = new JButton("End Turn");

	private JTextField text = new JTextField("text field", 20);

	Map<String,BufferedImage> cardImages = new HashMap<String,BufferedImage>();

	public CluedoFrame(Game game){
		super("Cluedo");
		this.game = game;

		importCards();

		setLayout(new BorderLayout());

		//add components to menu
		menu.add(file, 0);
		menu.add(edit, 1);

		//add components to options panel
		options.setLayout(new GridLayout(5,1));
		options.add(dice, 0);
		options.add(move, 1);
		options.add(suggest, 2);
		options.add(accuse, 3);
		options.add(end, 4);

		//add components to cards panel

		//then add menu and panels to frame
		setJMenuBar(menu);
		add(board, BorderLayout.NORTH);
		add(options, BorderLayout.WEST);
		add(cards, BorderLayout.CENTER);

		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		setBounds((scrnsize.width - getWidth()) / 2, (scrnsize.height - getHeight()) / 2, getWidth(), getHeight());
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		//repaint();
	}

	private void importCards() {
		//populate map with images
		cardImages.put(Game.CHARACTER_LIST[0], BoardPanel.loadImage("MissScarlet.png"));
		cardImages.put(Game.CHARACTER_LIST[1],BoardPanel.loadImage("ColMustard.png"));
		cardImages.put(Game.CHARACTER_LIST[2],BoardPanel.loadImage("MrsPeacock.png"));
		cardImages.put(Game.CHARACTER_LIST[3],BoardPanel.loadImage("ProfessorPlum.png"));
		cardImages.put(Game.CHARACTER_LIST[4],BoardPanel.loadImage("MrGreen.png"));
		cardImages.put(Game.CHARACTER_LIST[5],BoardPanel.loadImage("MrsWhite.png"));

		cardImages.put(Game.WEAPONS_LIST[0],BoardPanel.loadImage("candlestick.png"));
		cardImages.put(Game.WEAPONS_LIST[1],BoardPanel.loadImage("leadpipe.png"));
		cardImages.put(Game.WEAPONS_LIST[2],BoardPanel.loadImage("dagger.png"));
		cardImages.put(Game.WEAPONS_LIST[3],BoardPanel.loadImage("rope.png"));
		cardImages.put(Game.WEAPONS_LIST[4],BoardPanel.loadImage("revolver.png"));
		cardImages.put(Game.WEAPONS_LIST[5],BoardPanel.loadImage("spanner.png"));
	}

	public void repaint() {
		board.repaint();
	}

	private class CardsPanel extends JPanel{

		private Player player;

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		public void paint(Graphics g){
			/*
			int numCards = player.getHand().size();
			int cardWidth = this.WIDTH/(numCards+1);
			int i = 0;
			for(Card c : player.getHand()){ //draw the cards across the cardPanel
				BufferedImage image = cardImages.get(c.toString());
				g.drawImage(image, cardWidth*i, 0,cardWidth,(int)(((float)image.getWidth()/cardWidth)*image.getHeight()),null);
				i++;
			}
			*/
		}
	}

}
