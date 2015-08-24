package ui;


import game.Board;
import game.Card;
import game.Player;
import game.Room;

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextField;

import control.Game;
import control.Turn;

public class CluedoFrame extends JFrame implements KeyListener{

	private Game game;

	private JMenuBar menu = new JMenuBar();

	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");

	//TODO each of these needs to be a class that extends JPanel
	private BoardPanel board = new BoardPanel(game);
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
		//add key listener to main window
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
	}

	private void importCards() {
		//populate map with images


		cardImages.put(Game.CHARACTER_LIST[0],BoardPanel.loadImage(Game.CHARACTER_LIST[0]+".png"));
		cardImages.put(Game.CHARACTER_LIST[1],BoardPanel.loadImage(Game.CHARACTER_LIST[1]+".png"));
		cardImages.put(Game.CHARACTER_LIST[2],BoardPanel.loadImage(Game.CHARACTER_LIST[2]+".png"));
		cardImages.put(Game.CHARACTER_LIST[3],BoardPanel.loadImage(Game.CHARACTER_LIST[3]+".png"));
		cardImages.put(Game.CHARACTER_LIST[4],BoardPanel.loadImage(Game.CHARACTER_LIST[4]+".png"));
		cardImages.put(Game.CHARACTER_LIST[5],BoardPanel.loadImage(Game.CHARACTER_LIST[5]+".png"));

		cardImages.put(Game.WEAPONS_LIST[0],BoardPanel.loadImage(Game.WEAPONS_LIST[0]+".png"));
		cardImages.put(Game.WEAPONS_LIST[1],BoardPanel.loadImage(Game.WEAPONS_LIST[1]+".png"));
		cardImages.put(Game.WEAPONS_LIST[2],BoardPanel.loadImage(Game.WEAPONS_LIST[2]+".png"));
		cardImages.put(Game.WEAPONS_LIST[3],BoardPanel.loadImage(Game.WEAPONS_LIST[3]+".png"));
		cardImages.put(Game.WEAPONS_LIST[4],BoardPanel.loadImage(Game.WEAPONS_LIST[4]+".png"));
		cardImages.put(Game.WEAPONS_LIST[5],BoardPanel.loadImage(Game.WEAPONS_LIST[5]+".png"));

		cardImages.put(Game.ROOM_LIST[0],BoardPanel.loadImage(Game.ROOM_LIST[0]+".png"));
		cardImages.put(Game.ROOM_LIST[1],BoardPanel.loadImage(Game.ROOM_LIST[1]+".png"));
		cardImages.put(Game.ROOM_LIST[2],BoardPanel.loadImage(Game.ROOM_LIST[2]+".png"));
		cardImages.put(Game.ROOM_LIST[3],BoardPanel.loadImage(Game.ROOM_LIST[3]+".png"));
		cardImages.put(Game.ROOM_LIST[4],BoardPanel.loadImage(Game.ROOM_LIST[4]+".png"));
		cardImages.put(Game.ROOM_LIST[5],BoardPanel.loadImage(Game.ROOM_LIST[5]+".png"));
		cardImages.put(Game.ROOM_LIST[6],BoardPanel.loadImage(Game.ROOM_LIST[6]+".png"));
		cardImages.put(Game.ROOM_LIST[7],BoardPanel.loadImage(Game.ROOM_LIST[7]+".png"));
		cardImages.put(Game.ROOM_LIST[8],BoardPanel.loadImage(Game.ROOM_LIST[8]+".png"));
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
					JOptionPane.showMessageDialog(getParent(), "You have already rolled the dice this turn.");
				} else{
					game.turn.rollDice();
					JOptionPane.showMessageDialog(getParent(), String.format("You rolled a %d",game.turn.turns));
				}
			}
		});

		move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.turn.turns > 0){
					// TODO MOVE HERE
					requestFocusInWindow();
					//game.turn.movePlayer("South");
				} else{
					JOptionPane.showMessageDialog(getParent(), "Please roll the dice before moving.");
				}
			}
		});



		suggest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Room room = game.player.getToken().getRoom();
				if(room != null){
					choice(room.toString());
				}else{
					JOptionPane.showMessageDialog(getParent(), "You must be in a room to make a suggestion.");
				}
			}
		});

		accuse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO prompt are you sure here
				choice("Accuse");
			}
		});

		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				promptEndTurn();
			}
		});

	}

	public void choice(String room){
		new ChoiceFrame(this, game, room);
	}

	public void promptEndTurn(){
		JDialog end = new JDialog();
		end.setTitle("End Turn");
		end.setLayout(new GridLayout(2,1));
		end.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		end.setSize(300, 100);

		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,2));
		btnPanel.add(yes, 0);
		btnPanel.add(no, 1);

		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				end.dispose();
				endTurn();
			}
		});

		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				end.dispose();
			}
		});

		JLabel prompt = new JLabel("Are you sure you want to end your turn?");
		end.add(prompt, 0);
		end.add(btnPanel, 1);

		end.setLocationRelativeTo(this);
		end.setVisible(true);
	}

	public void endTurn(){
		game.endPlayerTurn();
		playerDialog.dispose();
		currentPlayer();
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		//TODO add wasd
		System.out.println("key Pressed " + e.toString());
		if(game == null || game.turn == null)return;
		System.out.println("game not null");
		int keyCode = e.getKeyCode();
		 switch( keyCode ) {
	        case KeyEvent.VK_UP:
	        	System.out.println("UPUPUP");
	        	//animateNorth();
	    		game.turn.movePlayer("North");
	            break;
	        case KeyEvent.VK_DOWN:
	        	System.out.println("DownDown");
	    		game.turn.movePlayer("South");
	            break;
	        case KeyEvent.VK_LEFT:
	        	System.out.println("LeftLeft");
	    		game.turn.movePlayer("West");
	            break;
	        case KeyEvent.VK_RIGHT :
	        	System.out.println("RightRight");
	    		game.turn.movePlayer("East");
	            break;
	     }
//    	if(e.equals(KeyEvent.VK_UP)){
//    		System.out.println("UPUPUP");
//    		game.turn.movePlayer("North");
//    	}else if(e.equals(KeyEvent.VK_DOWN)){
//    		game.turn.movePlayer("South");
//    	}
//    	else if(e.equals(KeyEvent.VK_LEFT)){
//    		game.turn.movePlayer("East");
//    	}else if(e.equals(KeyEvent.VK_RIGHT)){
//    		game.turn.movePlayer("West");
//    	}
    	repaint();
	}

	private void animateNorth() {
		double offset = 0.0;
//		while(offset < 1.0){
//			offset-=0.01;
//			System.out.println("offset: "+ offset);
//			game.player.getToken().setYoffset(offset);
//			//board.paintImmediately(0, 0, 1000, 1000);
//		}
	}



//	addKeyListener(new KeyListener(){
//		public void keyTyped(KeyEvent e){};
//
//	    public void keyPressed(KeyEvent e){};
//
//	    public void keyReleased(KeyEvent e){
//	    	System.out.println("key Pressed " + e.toString());
//	    	if(e.equals(KeyEvent.VK_UP)){
//	    		System.out.println("UPUPUP");
//	    		game.turn.movePlayer("North");
//	    	}else if(e.equals(KeyEvent.VK_DOWN)){
//
//	    	}
//	    }
//
//	});
}
