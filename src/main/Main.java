package main;

import javax.swing.*;

import control.Game;
import ui.BoardPanel;

public class Main {
	/**
	 * Start point for the game, calls initial methods then the game loop method
	 *
	 * @param args
	 */
	public static void main(String[] args){
		JFrame frame = new JFrame("Cluedo");
		Game game = new Game();

		JPanel board = new BoardPanel(frame, game);
		frame.add(board);
	}
}
