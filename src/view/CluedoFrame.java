package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

import view.CluedoCanvas;

public class CluedoFrame extends JFrame{

	private final CluedoCanvas canvas;

	public CluedoFrame(String title, Cluedo game, int uid, KeyListener... keys) {
		super(title);

		canvas = new CluedoCanvas(uid,game);
		setLayout(new BorderLayout());

		for(KeyListener k : keys) {
			canvas.addKeyListener(k);
		}

		add(canvas, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		setBounds((scrnsize.width - getWidth()) / 2,
					(scrnsize.height - getHeight()) / 2, getWidth(), getHeight());
		pack();
		setResizable(false);	//TODO change to true for challange stuff

		// Display window
		setVisible(true);
		canvas.requestFocus();
	}

	public void repaint() {
		canvas.repaint();
	}
}