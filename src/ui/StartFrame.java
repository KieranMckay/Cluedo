package ui;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import control.Game;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import java.awt.SystemColor;
import java.util.Enumeration;

public class StartFrame extends JFrame{

	private Game game;
	/**
	 * Create the application.
	 */
	public StartFrame(Game game) {
		getContentPane().setBackground(SystemColor.desktop);
		this.game = game;

		initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Cluedo");

		setIconImage(BoardPanel.loadImage("Miss ScarletToken.png"));

		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();

		setSize(scrnsize.width/2, scrnsize.height/2);
		setBounds((scrnsize.width - getWidth()) / 2, (scrnsize.height - getHeight()) / 2, getWidth(), getHeight());

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(SystemColor.desktop);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
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
					game.assignPlayers(num);
					dispose();
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
	}

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
