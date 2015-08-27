package ui;


import game.Card;
import game.Room;
import game.Tile;
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
import java.awt.Dimension;
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
import control.Game;

public class CluedoFrame extends JFrame implements KeyListener, WindowListener{

	private Game game;

	private JMenuBar menu = new JMenuBar();

	private JMenu file = new JMenu("File");
	private JMenuItem newMenuItem = new JMenuItem("New Game");
	private JMenuItem quitMenuItem = new JMenuItem("Quit");

	private CardsPanel cards;
	private BoardPanel board;

	private JPanel options = new JPanel();

	private JDialog playerSuspectsDialog = new JDialog();

	private JButton dice = new JButton("Roll Dice");
	private JButton move = new JButton("Move");
	private JButton suspects = new JButton("Unresolved Clues");
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
		options.setLayout(new GridLayout(6,1));
		options.add(dice, 0);
		options.add(move, 1);
		options.add(suspects, 2);
		options.add(suggest, 3);
		options.add(accuse, 4);
		options.add(end, 5);

		//add components to cards panel
		cards = new CardsPanel(game);

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
		cards.update();
	}
	
	/**
	 * populate map with images
	 */
	private void importCards() {

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

	@Override
	public void repaint() {
		super.repaint();
		board.repaint();
		cards.repaint();
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

	/**
	 * Displays the current players remaining suspects in a JDialog
	 */
	public void playersSuspects(){
		playerSuspectsDialog = new JDialog();
		playerSuspectsDialog.setTitle(String.format("%s's Remaining Suspects - Player %d",game.player.getName(), game.player.getPlayerNumber()));
		playerSuspectsDialog.setSize(300, 600);
		playerSuspectsDialog.setLayout(new GridLayout(3,6));

		int i = 0;
		for (Card c : game.player.getSuspects().values()){
			JLabel label = new JLabel();
			ImageResize size = new ImageResize(label);
			label.setIcon(new ImageIcon(BoardPanel.loadImage(c.toString()+".png")));
			size.resize(.35);
			playerSuspectsDialog.add(label, i);
			i++;
		}
		playerSuspectsDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		playerSuspectsDialog.pack();
		playerSuspectsDialog.setVisible(true);

	}

	public void addActionListeners(){
		dice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.turns > -1){
					JOptionPane.showMessageDialog(getParent(), "You have already rolled the dice this turn.");
				} else{
					game.rollDice();
					JOptionPane.showMessageDialog(getParent(), String.format("You rolled a %d",game.turns));
				}
			}
		});
		//When move is clicked
		move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.turns > 0){
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
								String choice = StartPanel.getSelectedButtonText(bg);
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


		suspects.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playersSuspects();
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
		playerSuspectsDialog.dispose();
		cards.update();
		repaint();
	}

	/**
	 * This method is called when the user clicks on the "X" button in the
	 * right-hand corner.
	 *
	 * @param e
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		// Ask the user to confirm they wanted to do this
		int r = JOptionPane.showConfirmDialog(this, new JLabel(
		"Exit Cluedo?"), "Confirm Exit",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

    // The following methods are part of the WindowListener interface,
    // but are not needed here.
	public void windowClosed(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(game.turns < 1||game == null || game == null)return;
		int keyCode = e.getKeyCode();
		 switch( keyCode ) {
	        case KeyEvent.VK_UP:
	        	//animateNorth();
	        	game.movePlayer("North");
	        	break;
	        case KeyEvent.VK_DOWN:
	    		game.movePlayer("South");
	    		break;
	        case KeyEvent.VK_LEFT:
	    		game.movePlayer("West");
	            break;
	        case KeyEvent.VK_RIGHT :
	    		game.movePlayer("East");

	            break;
	     }
    	repaint();
	}
}
