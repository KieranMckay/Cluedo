package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import control.Game;
import java.awt.SystemColor;

/**
 * An initial frame to hold the initialisation panels of the cluedo game
 *
 * @author Kieran Mckay
 */
public class StartFrame extends JFrame{

	private Game game;
	private JPanel window;

	/**
	 * Create the application.
	 */
	public StartFrame(Game game) {
		getContentPane().setBackground(SystemColor.desktop);
		this.game = game;

		initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		setSize(1000, 1000);
		setBounds((scrnsize.width - getWidth()) / 2,
				(scrnsize.height - getHeight()) / 2, getWidth(), getHeight());
		window = new StartPanel(game, this);
		getContentPane().add(window);
	}

	/**
	 * Loads in the current main window of the game into this frame.
	 *
	 * @param window - Jpanel
	 */
	public void setWindow(JPanel window){
		setContentPane(window);
		this.window = window;
		revalidate();
		repaint();
	}
}
