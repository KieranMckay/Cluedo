package gui_design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		frmCluedo.setBounds(100, 100, 838, 1124);
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
		boardPanel.setBackground(Color.BLACK);
		frmCluedo.getContentPane().add(boardPanel, BorderLayout.NORTH);

		JLabel lblBoardImage = new JLabel("");
		boardPanel.add(lblBoardImage);
		lblBoardImage.setIcon(new ImageIcon(design.class.getResource("/cluedo_board.png")));
		lblBoardImage.setVerticalAlignment(SwingConstants.TOP);
		lblBoardImage.setHorizontalAlignment(SwingConstants.LEFT);
		lblBoardImage.setBackground(Color.DARK_GRAY);

		JTabbedPane actionTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmCluedo.getContentPane().add(actionTabbedPane, BorderLayout.CENTER);

		JPanel actionPanel = new JPanel();
		actionTabbedPane.addTab("Actions", null, actionPanel, null);
		GridBagLayout gbl_actionPanel = new GridBagLayout();
		gbl_actionPanel.columnWidths = new int[] {1, 113, 30};
		gbl_actionPanel.rowHeights = new int[] {130};
		gbl_actionPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_actionPanel.rowWeights = new double[]{0.0};
		actionPanel.setLayout(gbl_actionPanel);

		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new GridLayout(1, 0, 0, 0));
		GridBagConstraints gbc_cardPanel = new GridBagConstraints();
		gbc_cardPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_cardPanel.insets = new Insets(0, 0, 0, 5);
		gbc_cardPanel.gridx = 0;
		gbc_cardPanel.gridy = 0;
		actionPanel.add(cardPanel, gbc_cardPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttonPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JButton moveBtn = new JButton("Make a Move");
		buttonPanel.add(moveBtn);
		moveBtn.setForeground(Color.BLACK);

		JButton accuseBtn = new JButton("Make Accusation");
		buttonPanel.add(accuseBtn);

		JButton endBtn = new JButton("End Turn");
		buttonPanel.add(endBtn);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.anchor = GridBagConstraints.WEST;
		gbc_buttonPanel.fill = GridBagConstraints.VERTICAL;
		gbc_buttonPanel.gridx = 1;
		gbc_buttonPanel.gridy = 0;
		actionPanel.add(buttonPanel, gbc_buttonPanel);
		moveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Move Button Pushed");
			}
		});

		JPanel infoPanel = new JPanel();
		actionTabbedPane.addTab("Information", null, infoPanel, null);
		infoPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNewLabel = new JLabel("Picture of current player");
		infoPanel.add(lblNewLabel);

		JButton handBtn = new JButton("View Cards");
		handBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		infoPanel.add(handBtn);

		JButton suspectsBtn = new JButton("Remaining Suspects");
		suspectsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		infoPanel.add(suspectsBtn);
	}

}
