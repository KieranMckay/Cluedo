package ui;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;

import control.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChoiceFrame extends JFrame{

	private boolean charSelected = false, weaponSelected = false, roomSelected = false;

	private Game game;
	private String choiceRoom;
	private CluedoFrame frame;

	private JLabel characterLabel = new JLabel();
	private JLabel weaponLabel = new JLabel();
	private JLabel roomLabel = new JLabel();

	JButton confirmBtn = new JButton(" Confirm (alt+Enter)");

	/**
	 * Create the application.
	 */
	public ChoiceFrame(CluedoFrame frame, Game game, String room) {
		this.frame = frame;
		this.choiceRoom = room;
		this.game = game;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Murder Selection");
		getContentPane().setBackground(SystemColor.desktop);
		setBounds(100, 100, 802, 531);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setBackground(SystemColor.desktop);
		getContentPane().add(selectionPanel, BorderLayout.CENTER);
		selectionPanel.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel characterSelectionPanel = new JPanel();
		characterSelectionPanel.setBackground(SystemColor.desktop);
		selectionPanel.add(characterSelectionPanel);
		characterSelectionPanel.setLayout(new BorderLayout(0, 0));

		characterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		characterLabel.setIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));
		characterSelectionPanel.add(characterLabel, BorderLayout.CENTER);


		JPanel weaponSelectionPanel = new JPanel();
		weaponSelectionPanel.setBackground(SystemColor.desktop);
		selectionPanel.add(weaponSelectionPanel);
		weaponSelectionPanel.setLayout(new BorderLayout(0, 0));

		weaponLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weaponLabel.setIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));
		weaponSelectionPanel.add(weaponLabel, BorderLayout.CENTER);


		JPanel roomSelectionPanel = new JPanel();
		roomSelectionPanel.setBackground(SystemColor.desktop);
		selectionPanel.add(roomSelectionPanel);
		roomSelectionPanel.setLayout(new BorderLayout(0, 0));

		roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomLabel.setIcon(new ImageIcon(BoardPanel.loadImage("Questionmark.png")));
		roomSelectionPanel.add(roomLabel, BorderLayout.CENTER);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(SystemColor.desktop);
		getContentPane().add(titlePanel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Choose Suspects");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblNewLabel);

		JPanel confirmPanel = new JPanel();
		confirmPanel.setBackground(SystemColor.desktop);
		getContentPane().add(confirmPanel, BorderLayout.SOUTH);

		confirmPanel.add(confirmBtn);

		setActionListeners();
		setVisible(true);
	}

	private void setActionListeners(){

		characterLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				promptMurderer();
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});

		weaponLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				promptWeapon();
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});

		roomLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (choiceRoom == "Accuse"){ 	//we are making an accusation
					promptAccusationRoom();
				} else {						//we are making a suggestion
					promptSuggestionRoom();
				}
			}

			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});

		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (charSelected && weaponSelected && roomSelected ){

					String character = characterLabel.getToolTipText();
					String weapon = weaponLabel.getToolTipText();
					String room = roomLabel.getToolTipText();

					if (choiceRoom == "Accuse"){ 	//we are making an accusation
						game.accuse(character, weapon, room);
					} else {						//we are making a suggestion
						game.suggest(character, weapon, room);
					}
					frame.endTurn();
					frame.repaint();
					dispose();
				} else {
					JOptionPane.showMessageDialog(getParent(), "Please select one of each murder suspects");
				}
			}
		});
		confirmBtn.setMnemonic(KeyEvent.VK_ENTER);
	}

	public void promptMurderer(){
		JDialog charDialog = new JDialog();
		charDialog.setBackground(SystemColor.desktop);
		charDialog.setTitle("Select Murder Suspect");
		charDialog.setSize(800, 800);
		charDialog.setLayout(new GridLayout(2,3));
		charDialog.setVisible(true);

		for(int i = 0; i < Game.CHARACTER_LIST.length; i++){
			JLabel label = new JLabel();
			label.setBackground(SystemColor.desktop);
			label.setIcon(new ImageIcon(BoardPanel.loadImage(Game.CHARACTER_LIST[i]+".png")));
			label.setToolTipText(Game.CHARACTER_LIST[i]);

			label.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					characterLabel.setIcon(label.getIcon());
					characterLabel.setToolTipText(label.getToolTipText());
					charSelected = true;
					charDialog.dispose();
				}

				@Override public void mousePressed(MouseEvent e) {}
				@Override public void mouseExited(MouseEvent e) {}
				@Override public void mouseEntered(MouseEvent e) {}
				@Override public void mouseClicked(MouseEvent e) {}
			});
			charDialog.add(label, i);
		}
	}

	private void promptWeapon() {
		JDialog weaponDialog = new JDialog();
		weaponDialog.setBackground(SystemColor.desktop);
		weaponDialog.setTitle("Select Murder Weapon");
		weaponDialog.setSize(800, 800);
		weaponDialog.setLayout(new GridLayout(2,3));
		weaponDialog.setVisible(true);

		for(int i = 0; i < Game.WEAPONS_LIST.length; i++){
			JLabel label = new JLabel();
			label.setBackground(SystemColor.desktop);
			label.setIcon(new ImageIcon(BoardPanel.loadImage(Game.WEAPONS_LIST[i]+".png")));
			label.setToolTipText(Game.WEAPONS_LIST[i]);

			label.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					weaponLabel.setIcon(label.getIcon());
					weaponLabel.setToolTipText(label.getToolTipText());
					weaponSelected = true;
					weaponDialog.dispose();
				}

				@Override public void mousePressed(MouseEvent e) {}
				@Override public void mouseExited(MouseEvent e) {}
				@Override public void mouseEntered(MouseEvent e) {}
				@Override public void mouseClicked(MouseEvent e) {}
			});
			weaponDialog.add(label, i);
		}
	}

	private void promptAccusationRoom() {
		JDialog roomDialog = new JDialog();
		roomDialog.setBackground(SystemColor.desktop);
		roomDialog.setTitle("Select Murder Room");
		roomDialog.setSize(1200, 800);
		roomDialog.setLayout(new GridLayout(2,5));
		roomDialog.setVisible(true);

		for(int i = 0; i < Game.ROOM_LIST.length; i++){
			JLabel label = new JLabel();
			label.setBackground(SystemColor.desktop);
			label.setIcon(new ImageIcon(BoardPanel.loadImage(Game.ROOM_LIST[i]+".png")));
			label.setToolTipText(Game.ROOM_LIST[i]);

			label.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					roomLabel.setIcon(label.getIcon());
					roomLabel.setToolTipText(label.getToolTipText());
					roomSelected = true;
					roomDialog.dispose();
				}

				@Override public void mousePressed(MouseEvent e) {}
				@Override public void mouseExited(MouseEvent e) {}
				@Override public void mouseEntered(MouseEvent e) {}
				@Override public void mouseClicked(MouseEvent e) {}
			});
			roomDialog.add(label, i);
		}
	}

	private void promptSuggestionRoom() {
		JDialog roomDialog = new JDialog();
		roomDialog.setBackground(SystemColor.desktop);
		roomDialog.setTitle("Select Murder Room");
		roomDialog.setSize(300, 400);
		roomDialog.setLayout(new GridLayout(1,1));
		roomDialog.setVisible(true);

		for(int i = 0; i < Game.ROOM_LIST.length; i++){
			if (!choiceRoom.equals(Game.ROOM_LIST[i])){continue;}
			JLabel label = new JLabel();
			label.setBackground(SystemColor.desktop);
			label.setIcon(new ImageIcon(BoardPanel.loadImage(Game.ROOM_LIST[i]+".png")));
			label.setToolTipText(Game.ROOM_LIST[i]);

			label.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					roomLabel.setIcon(label.getIcon());
					roomLabel.setToolTipText(label.getToolTipText());
					roomSelected = true;
					roomDialog.dispose();
				}

				@Override public void mousePressed(MouseEvent e) {}
				@Override public void mouseExited(MouseEvent e) {}
				@Override public void mouseEntered(MouseEvent e) {}
				@Override public void mouseClicked(MouseEvent e) {}
			});
			roomDialog.add(label);
		}
	}
}
