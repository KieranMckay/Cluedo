package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import control.*;

public class CluedoFrame extends JFrame{
	private BoardPanel board;

	public CluedoFrame(Game game, String title, KeyListener...keys){
		super(title);

		this.board = new BoardPanel(this, game);
		setLayout(new BorderLayout());
		for(KeyListener k : keys) {
			board.addKeyListener(k);
		}
		add(board, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		setBounds((scrnsize.width - getWidth()) / 2,
				(scrnsize.height - getHeight()) / 2, getWidth(), getHeight());
		pack();
		setResizable(false);

		// Display window
		setVisible(true);
		board.requestFocus();
	}

	public void repaint() {
		board.repaint();
	}
}
