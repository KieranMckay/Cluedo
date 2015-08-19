package ui;


import game.Card;
import game.Player;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JDialog;

import control.Game;

public class CluedoFrame extends JFrame{

	public static void main(String[] args){
		new CluedoFrame();
	}

	private JMenuBar menu = new JMenuBar();

	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");

	//TODO each of these needs to be a class that extends JPanel
	private BoardPanel board = new BoardPanel(this);
	private JPanel cards = new CardsPanel();
	private JPanel options = new JPanel();



	private JButton dice = new JButton("Roll Dice");
	private JButton move = new JButton("Move");
	private JButton suggest = new JButton("Make a suggestion");
	private JButton accuse = new JButton("Make an accusation");
	private JButton end = new JButton("End Turn");

	private JTextField text = new JTextField("text field", 20);

	Map<String,BufferedImage> cardImages = new HashMap<String,BufferedImage>();

	public  BufferedImage mrGreenImg;
	public  BufferedImage colMustardImg;
	public  BufferedImage missScarletImg;
	public  BufferedImage mrsWhiteImg;
	public  BufferedImage mrsPeacockImg;
	public  BufferedImage profPlumImg;


	public CluedoFrame(){
		super("Cluedo");

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
		setResizable(true);
		setVisible(true);
		repaint();
	}

	private void importCards() {
		for(String name : Game.CHARACTER_LIST){
			cardImages.put(name,null); //populate with null
		}
		//populate map with images
		/*mrGreenImg = BoardPanel.loadImage("MrGreen.png");
		profPlumImg = BoardPanel.loadImage("ProfessorPlum.png");
		colMustardImg = BoardPanel.loadImage("ColMustard.png");
		missScarletImg = BoardPanel.loadImage("MissScarlet.png");
		mrsPeacockImg = BoardPanel.loadImage("MrsPeacock.png");
		mrsWhiteImg = BoardPanel.loadImage("MrsWhite.png");*/
	}

	public void repaint() {
		board.repaint();
	}


	private class CardsPanel extends JPanel{

		Player player;


		public Player getPlayer() {
			return player;
		}


		public void setPlayer(Player player) {
			this.player = player;
		}


		public void paint(Graphics g){
			int numCards = player.getHand().size();
			int cardSize = this.WIDTH/(numCards+1);
			for(Card c : player.getHand()){
				BufferedImage img =
				g.drawImage(img, , y, width, height, observer)
			}
		}
	}
}
