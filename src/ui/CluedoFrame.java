package ui;


import game.Card;
import game.Player;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private JDialog playerDialog;

	private JButton dice = new JButton("Roll Dice");
	private JButton move = new JButton("Move");
	private JButton suggest = new JButton("Make a suggestion");
	private JButton accuse = new JButton("Make an accusation");
	private JButton end = new JButton("End Turn");

	Map<String,BufferedImage> cardImages = new HashMap<String,BufferedImage>();

	public CluedoFrame(Game game){
		super("Cluedo");
		this.game = game;

		importCards();

		setLayout(new BorderLayout());

		addActionListeners();

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

		currentPlayer();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
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
		super.repaint();
		board.repaint();
	}

	public void currentPlayer(){
		playerDialog = new JDialog();
		playerDialog.setTitle("Current Player");
		playerDialog.setSize(300, 600);
		playerDialog.getContentPane().setLayout(new BorderLayout());
		JLabel playerIcon = new JLabel();

		BufferedImage icon = cardImages.get(game.player.getToken().toString());
		playerIcon.setIcon(new ImageIcon(icon));

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerDialog.dispose();
			}
		});

		playerDialog.add(playerIcon, BorderLayout.CENTER);
		playerDialog.add(closeButton, BorderLayout.SOUTH);

		playerDialog.setVisible(true);
	}

	public void addActionListeners(){
		dice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.turn.turns > 0){
					JOptionPane.showMessageDialog(playerDialog, "You have already rolled the dice this turn.");
				} else{
					game.turn.rollDice();
					JOptionPane.showMessageDialog(playerDialog, String.format("You rolled a %d",game.turn.turns));
				}
			}
		});

		move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.turn.turns > 0){
					// TODO MOVE HERE
				} else{
					JOptionPane.showMessageDialog(playerDialog, "Please roll the dice before moving.");
				}
			}
		});

		suggest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				game.turn.makeSuggestion();
				game.endPlayerTurn();
				playerDialog.dispose();
				currentPlayer();
			}
		});

		accuse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				game.turn.makeAccusation();
			}
		});

		end.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO PROMPT ARE YOU SURE
				game.endPlayerTurn();
				playerDialog.dispose();
				currentPlayer();
			}
		});

	}

	private class CardsPanel extends JPanel{

		private Player player;

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = game.player;
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
