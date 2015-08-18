package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JDialog;

public class CluedoFrame extends JFrame{
	
	public static void main(String[] args){
		new CluedoFrame();
	}
	
	private JMenuBar menu = new JMenuBar(); 
	private JMenuItem file = new JMenuItem("File");
	private JMenuItem edit = new JMenuItem("Edit");
	
	//TODO each of these needs to be a class that extends JPanel
	private JPanel board = new JPanel();
	private JPanel cards = new JPanel();
	private JPanel options = new JPanel();

	private JButton dice = new JButton("Roll Dice");
	private JButton move = new JButton("Move");
	private JButton suggest = new JButton("Make a suggestion");
	private JButton accuse = new JButton("Make an accusation");
	private JButton end = new JButton("End Turn");
	
	private JTextField text = new JTextField("text field", 20);
	

	public CluedoFrame(){
		super("Cluedo");		
		
		setLayout(new BorderLayout());
		
		//add components to menu
		menu.setBounds(0, 0, 400, 100);
		menu.add(file);
		menu.add(edit);
		
		//add components to panels
		//options.setLayout(new BorderLayout());
		options.add(dice, 0);
		options.add(move, 1);
		options.add(suggest, 2);
		options.add(accuse, 3);
		options.add(end, 4);		
		
		//then add menu and panels to frame
		setJMenuBar(menu);
		add(board, BorderLayout.NORTH);		
		add(options, BorderLayout.EAST);
		add(cards, BorderLayout.SOUTH);
		
		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		setBounds((scrnsize.width - getWidth()) / 2, (scrnsize.height - getHeight()) / 2, getWidth(), getHeight());
		pack();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
