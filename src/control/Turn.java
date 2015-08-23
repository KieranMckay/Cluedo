package control;

import game.*;

import java.util.Random;

import ui.*;

/**
 * A basic class for handling a players turn.
 * Includes the ability to make an accusation at the start of their turn, or move.
 * If they move into a Room, they then have the ability to suggest a suspect, weapon and that room as the murder solution.
 *
 * @author Johnny, Kieran Mckay
 *
 */
public class Turn {
	static Random dice = new Random();

	Menu menu = new Menu();

	private Player player;
	//private Menu menu;
	private Board board;
	private Envelope murderEnvelope;
	public int turns = -1;

	public Turn(Player player, Board board, Envelope murderEnvelope) {
		this.player = player;
		//this.menu = menu;
		this.board = board;
		this.murderEnvelope = murderEnvelope;
	}

	/**
	 * Prompt for and move the player around the board until turns have run out
	 */
	public void movePlayer() {
		menu.println("It is " + player.toString() +"'s turn. ("
				+player.getToken().toString()+")");
		menu.println("You rolled a " + turns
				+ " please move to your destination");
		menu.println(board.toString());

		while (turns > 0) {
			if (!singleMove()) {
				menu.println("Invalid Move, try again");
				continue;
			} else if (player.getToken().getPosition().isRoom()) { // they have reached a room
				return;
			} else {
				menu.println(board.toString());
			}
		}
	}
	/**
	 * Prompt the player for a move
	 * move them in the given direction and decrement turns
	 *
	 * @return Boolean successful move or not
	 */
	public boolean singleMove() {
		menu.println("Please choose a direction: ");
		//menu.println(player.getToken().getLocation().getDirections().toString());
		int choice = menu.chooseListIndex(player.getToken().getPosition().neighbourNames());
		String direction = player.getToken().getPosition().neighbourNames().get(choice);
		if (player.move(direction)){
			turns--;
			return true;
		}
		return false;
	}

	/**
	 * get a number between 2 and 12 inclusive
	 *
	 * @return
	 */
	public void rollDice() {
		turns = dice.nextInt(11) + 2;
	}
}
