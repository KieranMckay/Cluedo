package ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JToggleButton;

import control.Game;

import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.xml.bind.ParseConversionEvent;

import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.Font;

public class StartWindow {

	private JFrame frame;
	private Game game;
	/**
	 * Create the application.
	 */
	public StartWindow(Game game) {
		this.game = game;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JDialog dlog = new JDialog();
		dlog.setTitle("Select Number of Players");
		dlog.setSize(400, 200);
		dlog.getContentPane().setLayout(new GridLayout(2,1));
		JButton dlogButton = new JButton("Start Game");
		JTextArea dlogText= new JTextArea(String.format("Enter number of players between %d and %d", game.MIN_PLAYERS, game.MAX_PLAYERS));

		dlogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.numPlayers = Integer.parseInt(dlogText.getText());
					if (game.numPlayers >= game.MIN_PLAYERS && game.MAX_PLAYERS >= game.numPlayers){
						//change this to player selection window
						new CluedoFrame(game);
					}else{
						JOptionPane.showMessageDialog(dlog, "INVALID ENTRY");
					}
				} catch(Exception error){
					JOptionPane.showMessageDialog(dlog, "INVALID ENTRY");
				}
			}
		});

		dlog.getContentPane().add(dlogText, 0);
		dlog.getContentPane().add(dlogButton, 1);


		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel welcome = new JPanel();
		welcome.setBackground(SystemColor.desktop);
		panel.add(welcome);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dlog.show();
			}
		});
		welcome.setLayout(new BorderLayout(0, 0));

		JLabel lblWelc = new JLabel("Welcome To Cluedo");
		lblWelc.setFont(lblWelc.getFont().deriveFont(lblWelc.getFont().getSize() + 24f));
		welcome.add(lblWelc, BorderLayout.CENTER);
		welcome.add(btnStart, BorderLayout.SOUTH);
	}
}
