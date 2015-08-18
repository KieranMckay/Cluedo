package gui_design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;

public class design {

	private JFrame frmCluedo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					design window = new design();
					window.frmCluedo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public design() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCluedo = new JFrame();
		frmCluedo.setBackground(Color.RED);
		frmCluedo.setTitle("Cluedo");
		frmCluedo.setBounds(100, 100, 844, 795);
		frmCluedo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmCluedo.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnFile);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmClose);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		frmCluedo.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel boardPanel = new JPanel();
		frmCluedo.getContentPane().add(boardPanel, BorderLayout.NORTH);
		
		JLabel lblBoardImage = new JLabel("");
		boardPanel.add(lblBoardImage);
		lblBoardImage.setIcon(new ImageIcon("E:\\Eclipse\\workspace\\Cluedo\\images\\test.png"));
		lblBoardImage.setVerticalAlignment(SwingConstants.TOP);
		lblBoardImage.setHorizontalAlignment(SwingConstants.LEFT);
		lblBoardImage.setBackground(Color.DARK_GRAY);
		
		JTabbedPane actionTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmCluedo.getContentPane().add(actionTabbedPane, BorderLayout.CENTER);
		
		JPanel actionPanel = new JPanel();
		actionTabbedPane.addTab("Actions", null, actionPanel, null);
		
		JButton moveBtn = new JButton("Make a Move");
		moveBtn.setForeground(Color.BLACK);
		moveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		actionPanel.setLayout(new GridLayout(1, 5, 0, 0));
		actionPanel.add(moveBtn);
		
		JButton accuseBtn = new JButton("Make Accusation");
		actionPanel.add(accuseBtn);
		
		JButton endBtn = new JButton("End Turn");
		actionPanel.add(endBtn);
		
		JPanel infoPanel = new JPanel();
		actionTabbedPane.addTab("Information", null, infoPanel, null);
		infoPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Picture of current player");
		infoPanel.add(lblNewLabel);
		
		JButton handBtn = new JButton("View Cards");
		infoPanel.add(handBtn);
		
		JButton suspectsBtn = new JButton("Remaining Suspects");
		infoPanel.add(suspectsBtn);
	}

}
