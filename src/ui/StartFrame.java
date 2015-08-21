package ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import control.Game;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.SystemColor;

public class StartFrame extends JFrame{

	private Game game;
	/**
	 * Create the application.
	 */
	public StartFrame(Game game) {
		this.game = game;
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Cluedo");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JDialog dlog = new JDialog();
		dlog.setTitle("Select Number of Players");
		dlog.setSize(400, 200);
		dlog.getContentPane().setLayout(new GridLayout(2,1));
		JButton dlogButton = new JButton("Start Game");
		JTextArea dlogText= new JTextArea(String.format("Enter number of players between %d and %d", game.MIN_PLAYERS, game.MAX_PLAYERS));

		dlogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					game.numPlayers = Integer.parseInt(dlogText.getText());
					if (game.numPlayers >= game.MIN_PLAYERS && game.MAX_PLAYERS >= game.numPlayers){
						dlog.dispose();
						game.initialise();
						game.assignPlayers();
						dispose();
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
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel welcome = new JPanel();
		welcome.setBackground(SystemColor.desktop);
		panel.add(welcome);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					dlog.setVisible(true);
			}
		});
		welcome.setLayout(new BorderLayout(0, 0));

		JLabel lblWelc = new JLabel("Welcome To Cluedo");
		lblWelc.setFont(lblWelc.getFont().deriveFont(lblWelc.getFont().getSize() + 24f));
		welcome.add(lblWelc, BorderLayout.CENTER);
		welcome.add(btnStart, BorderLayout.SOUTH);
	}
}
