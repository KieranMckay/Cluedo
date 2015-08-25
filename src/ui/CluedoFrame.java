package ui;


import game.Board;
import game.Card;
import game.Player;
import game.Room;
import game.Tile;
import javafx.scene.control.RadioButton;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextField;

import sun.tools.jar.Main;
import control.Game;
import control.Turn;

public class CluedoFrame extends JFrame implements KeyListener, WindowListener{

	private Game game;

	private JMenuBar menu = new JMenuBar();

	private JMenu file = new JMenu("File");
	private JMenuItem newMenuItem = new JMenuItem("New Game");
	private JMenuItem quitMenuItem = new JMenuItem("Quit");


	//TODO each of these needs to be a class that extends JPanel
	private BoardPanel board;
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
		board = new BoardPanel(game);
		importCards();
		menuItems();

		setLayout(new BorderLayout());

		addActionListeners();

		//add components to menu
		menu.add(file, 0);

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

		// tell frame to fire a WindowsListener event
		// but not to close when "x" button clicked.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

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

	private void menuItems(){
		file.add(newMenuItem);
		file.add(quitMenuItem);
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game();
			}
		});

		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
				if (game.turn.turns > -1){
					JOptionPane.showMessageDialog(getParent(), "You have already rolled the dice this turn.");
				} else{
					game.turn.rollDice();
					JOptionPane.showMessageDialog(getParent(), String.format("You rolled a %d",game.turn.turns));
				}
			}
		});
		//When move is clicked
		move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.turn.turns > 0){
					Tile curPosition = game.player.getToken().getPosition();
					if(curPosition.isRoom()){
						JDialog dialog = new JDialog();
						dialog.setLayout(new GridLayout(curPosition.neighbourNames().size()+1,1));
						dialog.setSize(150,curPosition.neighbourNames().size()*100);
						dialog.setTitle("Choose an exit");
						ButtonGroup bg = new ButtonGroup();
						int i = 0;
						for(String exit : curPosition.neighbourNames()){
							JRadioButton radioButton = new JRadioButton(exit);
							if(i == 0) radioButton.setSelected(true); //set the first button as default
							bg.add(radioButton);
							dialog.add(radioButton);
							i++;
						}
						dialog.setLocationRelativeTo(board);
						dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						JButton acceptButton = new JButton("accept");
						acceptButton.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								String choice = StartFrame.getSelectedButtonText(bg);
								curPosition.getNeighbour(choice).moveTo(game.player.getToken());
								dialog.dispose();
								repaint();
							}
						});
						dialog.add(acceptButton);
						System.out.println("Choose an exit");
					}else{
						requestFocusInWindow();
					}
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

	/**
	 * This method is called when the user clicks on the "X" button in the
	 * right-hand corner.
	 *
	 * @param e
	 */
	public void windowClosing(WindowEvent e) {
		// Ask the user to confirm they wanted to do this
		int r = JOptionPane.showConfirmDialog(this, new JLabel(
		"Exit Cluedo?"), "Confirm Exit",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * This method is called after the X button has been depressed.
	 * @param e
	 */
    public void windowClosed(WindowEvent e) {}

    // The following methods are part of the WindowListener interface,
    // but are not needed here.
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}

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
		if(game.turn.turns < 1)return;
		if(game == null || game.turn == null)return;
		int keyCode = e.getKeyCode();
		 switch( keyCode ) {
	        case KeyEvent.VK_UP:
	        	//animateNorth();
	        	game.turn.movePlayer("North");
	        	break;
	        case KeyEvent.VK_DOWN:
	    		game.turn.movePlayer("South");
	    		break;
	        case KeyEvent.VK_LEFT:
	    		game.turn.movePlayer("West");
	            break;
	        case KeyEvent.VK_RIGHT :
	    		game.turn.movePlayer("East");

	            break;
	     }
    	repaint();
	}


}
