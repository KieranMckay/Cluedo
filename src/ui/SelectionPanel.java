package ui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.javafx.font.Disposer;

import control.Game;

public class SelectionPanel extends JPanel{


	private JPanel characterLabelPanel;
	private JLabel charLabel0;
	private JLabel charLabel1;
	private JLabel charLabel3;
	private JLabel charLabel2;
	private JLabel charLabel4;
	private JLabel charLabel5;

	private JLabel titleLabel;
	private JPanel northPanel;

	JFrame frame;

	JTextField playerName;

	private List<String> remainingCharacters;
	private int remainingNumPlayers;
	private int playerNumber;
	private Game game;

	/**
	 * Create the application.
	 */
	public SelectionPanel(Game game, List<String>remainingCharacters, int remainingNumPlayers, JFrame frame) {
		this.frame = frame;
		this.playerNumber = game.numPlayers-remainingNumPlayers+1;
		this.game = game;
		this.remainingCharacters = remainingCharacters;
		this.remainingNumPlayers = remainingNumPlayers;
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setPreferredSize(new Dimension(900, 900));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));


		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2, 1));

		titleLabel = new JLabel(String.format("Select your character Player %d", playerNumber));
		titleLabel.setHorizontalAlignment(0); //sets to center
		titleLabel.setFont(game.FONT);
		northPanel.add(titleLabel, 0);

		JPanel namePanel = new JPanel();
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Please enter your name: ");
		nameLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		playerName = new JTextField("abc");
		namePanel.setLayout(new BorderLayout());
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(playerName, BorderLayout.CENTER);

		northPanel.add(namePanel, 1);

		panel.add(northPanel, BorderLayout.NORTH);

		characterLabelPanel = new JPanel();
		characterLabelPanel.setLayout(new GridLayout(2,3)); //
		panel.add(characterLabelPanel, BorderLayout.CENTER);

		charLabel0 = new JLabel();
		charLabel0.setIcon(new ImageIcon(BoardPanel.loadImage(game.CHARACTER_LIST[0]+".png")));
		charLabel0.setToolTipText(game.CHARACTER_LIST[0]);
		charLabel0.setDisabledIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));

		charLabel1 = new JLabel();
		charLabel1.setIcon(new ImageIcon(BoardPanel.loadImage(game.CHARACTER_LIST[1]+".png")));
		charLabel1.setToolTipText(game.CHARACTER_LIST[1]);
		charLabel1.setDisabledIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));

		charLabel2 = new JLabel();
		charLabel2.setIcon(new ImageIcon(BoardPanel.loadImage(game.CHARACTER_LIST[2]+".png")));
		charLabel2.setToolTipText(game.CHARACTER_LIST[2]);
		charLabel2.setDisabledIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));

		charLabel3 = new JLabel();
		charLabel3.setIcon(new ImageIcon(BoardPanel.loadImage(game.CHARACTER_LIST[3]+".png")));;
		charLabel3.setToolTipText(game.CHARACTER_LIST[3]);
		charLabel3.setDisabledIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));

		charLabel4 = new JLabel();
		charLabel4.setIcon(new ImageIcon(BoardPanel.loadImage(game.CHARACTER_LIST[4]+".png")));
		charLabel4.setToolTipText(game.CHARACTER_LIST[4]);
		charLabel4.setDisabledIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));

		charLabel5 = new JLabel();
		charLabel5.setIcon(new ImageIcon(BoardPanel.loadImage(game.CHARACTER_LIST[5]+".png")));
		charLabel5.setToolTipText(game.CHARACTER_LIST[5]);
		charLabel5.setDisabledIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));

		addActionListeners();

		characterLabelPanel.add(charLabel0);
		characterLabelPanel.add(charLabel1);
		characterLabelPanel.add(charLabel3);
		characterLabelPanel.add(charLabel2);
		characterLabelPanel.add(charLabel4);
		characterLabelPanel.add(charLabel5);

		this.add(northPanel);
		this.add(namePanel);
		this.add(characterLabelPanel);
	}

	private void addPlayer(JLabel label, String character, String player){
		if(character == null || !remainingCharacters.contains(character) || player.isEmpty()) {
			JOptionPane.showMessageDialog(getParent(), "INVALID CHOICE");
		} else {
			label.setEnabled(false);
			remainingCharacters.remove(character);
			game.createPlayer(playerNumber, player, character);
			if(remainingNumPlayers > 1){
				remainingNumPlayers--;
				this.playerNumber = game.numPlayers-remainingNumPlayers+1;
				titleLabel.removeAll();
				northPanel.remove(titleLabel);
				titleLabel = new JLabel(String.format("Select your character Player %d", playerNumber));
				titleLabel.setHorizontalAlignment(0); //sets to center
				titleLabel.setFont(game.FONT);
				northPanel.add(titleLabel, 0);
				revalidate();
				super.repaint();
			}else {
				game.dealCards();
				game.game = new CluedoFrame(game);
				frame.dispose();
			}
		}
	}

	private void addActionListeners(){
		charLabel0.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				addPlayer(charLabel0, charLabel0.getToolTipText(), playerName.getText());
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});
		charLabel1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				addPlayer(charLabel1, charLabel1.getToolTipText(), playerName.getText());
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});
		charLabel2.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				addPlayer(charLabel2, charLabel2.getToolTipText(), playerName.getText());
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});
		charLabel3.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				addPlayer(charLabel3, charLabel3.getToolTipText(), playerName.getText());
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});
		charLabel4.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				addPlayer(charLabel4, charLabel4.getToolTipText(), playerName.getText());
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});
		charLabel5.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				addPlayer(charLabel5, charLabel5.getToolTipText(), playerName.getText());
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});
	}
}
