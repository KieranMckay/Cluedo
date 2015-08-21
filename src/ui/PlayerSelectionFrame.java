package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import control.Game;


public class PlayerSelectionFrame extends JFrame{

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
	private JLabel titleLabel;

	private List<String> remainingCharacters;
	private int remainingNumPlayers;
	private int playerNumber;
	private Game game;

	/**
	 * Create the application.
	 */
	public PlayerSelectionFrame(Game game, List<String>remainingCharacters, int remainingNumPlayers) {
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
		setTitle("Player Selection");
		setBounds(100, 100, 908, 899);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		ButtonGroup btnGroup = new ButtonGroup();

		rdbtnPanel = new JPanel();
		panel.add(rdbtnPanel, BorderLayout.SOUTH);

		rdbtnScarlet = new JRadioButton("Miss Scarlet");
		rdbtnMustard = new JRadioButton("Colonel Mustard");
		rdbtnPlum = new JRadioButton("Professor Plum");
		rdbtnPeacock = new JRadioButton("Mrs Peacock");
		rdbtnGreen = new JRadioButton("Mr Green");
		rdbtnWhite = new JRadioButton("Mrs White");

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
		scarletLabel.setIcon(new ImageIcon(PlayerSelectionFrame.class.getResource("/MissScarlet.png")));
		characterLabelPanel.add(scarletLabel);

		mustardLabel = new JLabel("");
		mustardLabel.setIcon(new ImageIcon(PlayerSelectionFrame.class.getResource("/ColMustard.png")));
		characterLabelPanel.add(mustardLabel);

		plumLabel = new JLabel("");
		plumLabel.setIcon(new ImageIcon(PlayerSelectionFrame.class.getResource("/ProfessorPlum.png")));
		characterLabelPanel.add(plumLabel);

		peacockLabel = new JLabel("");
		peacockLabel.setIcon(new ImageIcon(PlayerSelectionFrame.class.getResource("/MrsPeacock.png")));
		characterLabelPanel.add(peacockLabel);

		greenLabel = new JLabel("");
		greenLabel.setIcon(new ImageIcon(PlayerSelectionFrame.class.getResource("/MrGreen.png")));
		characterLabelPanel.add(greenLabel);

		whiteLabel = new JLabel("");
		whiteLabel.setIcon(new ImageIcon(PlayerSelectionFrame.class.getResource("/MrsWhite.png")));
		characterLabelPanel.add(whiteLabel);

		titleLabel = new JLabel(String.format("Select your character player %d", playerNumber));
		titleLabel.setHorizontalAlignment(0); //sets to center
		titleLabel.setFont(game.FONT);
		panel.add(titleLabel, BorderLayout.NORTH);

		JButton select = new JButton("Select");
		rdbtnPanel.add(select);

		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String character = getSelectedButtonText(btnGroup);
				if(character == null || !remainingCharacters.contains(character)) {
					JOptionPane.showMessageDialog(panel, "INVALID CHOICE");
				} else {
					remainingCharacters.remove(character);


					//TODO Display dialog box of player number and choice here


					game.createPlayer(playerNumber, character);
					if(remainingNumPlayers > 1){
						new PlayerSelectionFrame(game, remainingCharacters, remainingNumPlayers-1);
					}else {
						game.dealCards();
						new CluedoFrame(game);
					}
					dispose();
				}
			}
		});
	}

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
