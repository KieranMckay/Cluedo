package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import control.Game;

public class StartPanel extends JPanel{

	private Game game;
	private StartFrame frame;
	/**
	 * Create the application.
	 */
	public StartPanel(Game game, StartFrame frame) {
		this.frame = frame;
		//ImagePanel panel = new ImagePanel(new Image("WelcomeImage.png"));
		setBackground(SystemColor.desktop);
		this.game = game;

		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setPreferredSize(new Dimension(900, 900));

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(SystemColor.desktop);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(SystemColor.desktop);
		centerPanel.add(inputPanel, BorderLayout.SOUTH);

		ButtonGroup rdbtnGroup = new ButtonGroup();
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(SystemColor.desktop);
		btnPanel.setForeground(SystemColor.desktop);
		btnPanel.setLayout(new GridLayout(1,5));
		createRadioBtns(rdbtnGroup, btnPanel);
		JButton btnStart = new JButton("Start");
		btnPanel.add(btnStart, 4);

		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String numPlayers = getSelectedButtonText(rdbtnGroup);
				if (numPlayers != null){
					game.initialise();
					int num = Integer.parseInt(numPlayers);
					List<String> remainingCharacters = game.assignPlayers(num);
					SelectionPanel select = new SelectionPanel(game, remainingCharacters, num, frame);
					frame.setWindow(select);
				}else{
					JOptionPane.showMessageDialog(getParent(), "INVALID ENTRY");
				}
			}
		});

		inputPanel.setLayout(new BorderLayout());

		JLabel welcomelbl = new JLabel("Welcome To Cluedo");
		welcomelbl.setFont(welcomelbl.getFont().deriveFont(welcomelbl.getFont().getSize() + 24f));
		centerPanel.add(welcomelbl, BorderLayout.CENTER);

		JLabel numPlayerslbl = new JLabel("How many Players?");
		numPlayerslbl.setBackground(SystemColor.desktop);
		inputPanel.add(numPlayerslbl, BorderLayout.CENTER);
		inputPanel.add(btnPanel, BorderLayout.SOUTH);

		add(inputPanel);
		add(btnPanel);
	}

	/**
	 * Adds radio buttons to the input button group and jpanel.
	 * Buttons used for selecting the number of players in the game
	 *
	 * @param rdbtnGroup a Button group to add the buttons to
	 * @param parent a JPanel to add the buttons to
	 */
	private void createRadioBtns(ButtonGroup rdbtnGroup, JPanel parent) {
		JRadioButton rdbtn3 = new JRadioButton("3");
		JRadioButton rdbtn4 = new JRadioButton("4");
		JRadioButton rdbtn5 = new JRadioButton("5");
		JRadioButton rdbtn6 = new JRadioButton("6");

		rdbtnGroup.add(rdbtn3);
		rdbtnGroup.add(rdbtn4);
		rdbtnGroup.add(rdbtn5);
		rdbtnGroup.add(rdbtn6);

		parent.add(rdbtn3, 0);
		parent.add(rdbtn4, 1);
		parent.add(rdbtn5, 2);
		parent.add(rdbtn6, 3);
	}


	/**
	 * A static method for getting the string of a selected radio button from a ButtonGroup
	 * @param buttonGroup
	 * @return
	 */
	public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
