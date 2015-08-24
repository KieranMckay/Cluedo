package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		new Game();
	}
}
