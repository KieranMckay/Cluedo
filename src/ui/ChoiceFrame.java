package ui;

import java.awt.EventQueue;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;

import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoiceFrame {

	private JFrame frame;

	JLabel weaponLabel = new JLabel();

	JButton charSelectBtn = new JButton("Select Character (alt+1)");
	JButton weaponSelectBtn = new JButton("Select Weapon (alt+2)");
	JButton roomSelectBtn = new JButton("Select Room (alt+3)");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceFrame window = new ChoiceFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChoiceFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.desktop);
		frame.setBounds(100, 100, 802, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setBackground(SystemColor.desktop);
		frame.getContentPane().add(selectionPanel, BorderLayout.CENTER);
		selectionPanel.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel characterSelectionPanel = new JPanel();
		characterSelectionPanel.setBackground(SystemColor.desktop);
		selectionPanel.add(characterSelectionPanel);
		characterSelectionPanel.setLayout(new BorderLayout(0, 0));

		JLabel characterLabel = new JLabel();
		characterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		characterLabel.setIcon(new ImageIcon(ChoiceFrame.class.getResource("/MissScarlet.png")));
		characterSelectionPanel.add(characterLabel, BorderLayout.CENTER);

		charSelectBtn.setMnemonic(KeyEvent.VK_1);
		characterSelectionPanel.add(charSelectBtn, BorderLayout.SOUTH);

		JPanel weaponSelectionPanel = new JPanel();
		weaponSelectionPanel.setBackground(SystemColor.desktop);
		selectionPanel.add(weaponSelectionPanel);
		weaponSelectionPanel.setLayout(new BorderLayout(0, 0));

		weaponLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weaponLabel.setIcon(new ImageIcon(ChoiceFrame.class.getResource("/candlestick.png")));
		weaponSelectionPanel.add(weaponLabel, BorderLayout.CENTER);

		weaponSelectionPanel.add(weaponSelectBtn, BorderLayout.SOUTH);

		JPanel roomSelectionPanel = new JPanel();
		roomSelectionPanel.setBackground(SystemColor.desktop);
		selectionPanel.add(roomSelectionPanel);
		roomSelectionPanel.setLayout(new BorderLayout(0, 0));

		JLabel roomLabel = new JLabel();
		roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomLabel.setIcon(new ImageIcon(ChoiceFrame.class.getResource("/ColMustard.png")));
		roomSelectionPanel.add(roomLabel, BorderLayout.CENTER);

		roomSelectionPanel.add(roomSelectBtn, BorderLayout.SOUTH);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(SystemColor.desktop);
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Choose Suspects");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblNewLabel);

		JPanel confirmPanel = new JPanel();
		confirmPanel.setBackground(SystemColor.desktop);
		frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);

		JButton confirmBtn = new JButton(" Confirm (alt+Enter)");
		confirmBtn.setMnemonic(KeyEvent.VK_ENTER);
		confirmPanel.add(confirmBtn);
	}

	private void setActionListeners(){

		charSelectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO BUTTON STUFF
				JDialog charDialog = new JDialog();
				charDialog.setSize(800, 300);
				charDialog.setLayout(new GridLayout(6,1));
			}
		});
		charSelectBtn.setMnemonic(KeyEvent.VK_1);

		weaponSelectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO BUTTON STUFF
			}
		});
		weaponSelectBtn.setMnemonic(KeyEvent.VK_2);

		roomSelectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO BUTTON STUFF
			}
		});
		roomSelectBtn.setMnemonic(KeyEvent.VK_3);
	}
}
