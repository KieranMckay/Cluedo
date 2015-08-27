package control;

import game.*;

import java.util.Random;

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


	private Player player;
	private Board board;
	private Envelope murderEnvelope;
	public int turns = -1;

	public Turn(Player player, Board board, Envelope murderEnvelope) {
		this.player = player;
		this.board = board;
		this.murderEnvelope = murderEnvelope;
	}

	/**
	 * Prompt for and move the player around the board until turns have run out
	 */
	public void movePlayer(String direction) {
		singleMove(direction);
	}

	/**
	 * Prompt the player for a move
	 * move them in the given direction and decrement turns
	 *
	 * @return Boolean successful move or not
	 */
	public boolean singleMove(String direction) {
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
