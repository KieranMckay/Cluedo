package gui_design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class playerSelection {

	private JFrame frame;
	private JPanel rdbtnPanel;
	private JRadioButton rdbtnWhite;
	private JRadioButton rdbtnGreen;
	private JRadioButton rdbtnPeacock;
	private JRadioButton rdbtnPlum;
	private JRadioButton rdbtnMustard;
	private JRadioButton rdbtnScarlet;
	private JPanel characterLabelPanel;
	private JLabel scarletLabel;
	private JLabel mustardLabel;
	private JLabel plumLabel;
	private JLabel peacockLabel;
	private JLabel greenLabel;
	private JLabel whiteLabel;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playerSelection window = new playerSelection();
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
	public playerSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 899);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		ButtonGroup btnGroup = new ButtonGroup();

		rdbtnPanel = new JPanel();
		panel.add(rdbtnPanel, BorderLayout.SOUTH);

		rdbtnScarlet = new JRadioButton("Scarlet");
		rdbtnMustard = new JRadioButton("Mustard");
		rdbtnPlum = new JRadioButton("Plum");
		rdbtnPeacock = new JRadioButton("Peacock");
		rdbtnGreen = new JRadioButton("Green");
		rdbtnWhite = new JRadioButton("White");

		btnGroup.add(rdbtnScarlet);
		btnGroup.add(rdbtnMustard);
		btnGroup.add(rdbtnPlum);
		btnGroup.add(rdbtnPeacock);
		btnGroup.add(rdbtnGreen);
		btnGroup.add(rdbtnWhite);
		
		rdbtnPanel.add(rdbtnScarlet);
		rdbtnPanel.add(rdbtnMustard);
		rdbtnPanel.add(rdbtnPlum);
		rdbtnPanel.add(rdbtnPeacock);
		rdbtnPanel.add(rdbtnGreen);
		rdbtnPanel.add(rdbtnWhite);

		characterLabelPanel = new JPanel();
		panel.add(characterLabelPanel, BorderLayout.CENTER);

		scarletLabel = new JLabel("");
		scarletLabel.setLabelFor(rdbtnScarlet);
		scarletLabel.setIcon(new ImageIcon(playerSelection.class.getResource("/MissScarlet.png")));
		characterLabelPanel.add(scarletLabel);

		mustardLabel = new JLabel("");
		mustardLabel.setIcon(new ImageIcon(playerSelection.class.getResource("/ColMustard.png")));
		characterLabelPanel.add(mustardLabel);

		plumLabel = new JLabel("");
		plumLabel.setIcon(new ImageIcon(playerSelection.class.getResource("/ProfessorPlum.png")));
		characterLabelPanel.add(plumLabel);

		peacockLabel = new JLabel("");
		peacockLabel.setIcon(new ImageIcon(playerSelection.class.getResource("/MrsPeacock.png")));
		characterLabelPanel.add(peacockLabel);

		greenLabel = new JLabel("");
		greenLabel.setIcon(new ImageIcon(playerSelection.class.getResource("/MrGreen.png")));
		characterLabelPanel.add(greenLabel);

		whiteLabel = new JLabel("");
		whiteLabel.setIcon(new ImageIcon(playerSelection.class.getResource("/MrsWhite.png")));
		characterLabelPanel.add(whiteLabel);

		lblNewLabel = new JLabel("SELECT PLAYER MOTHER FUCKER");
		panel.add(lblNewLabel, BorderLayout.NORTH);
	}

}
