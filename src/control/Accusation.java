package control;

import model.*;

public class Accusation extends Suggestion {

	private Player player;
	private Envelope guess;
	private Envelope murderEnvelope;

	public Accusation(Player player, Envelope guess, Envelope murderEnvelope) {
		super(player, guess);
		this.player = player;
		this.guess = guess;
		this.murderEnvelope = murderEnvelope;
	}

	/**
	 * Returns the player who made the accusation
	 * @return Player - player who made the accusation
	 */
	public Player getPlayer(){
		return player;
	}

	/**
	 * Returns whether the accusation is correct or not.
	 * @return boolean - whether or not the accusation is correct
	 */
	public boolean isCorrect(){
		return guess.equals(murderEnvelope);
	}

	/**
	 * Returns whether this is an accusation or not
	 * @return true - because this is Accusation class
	 */
	@Override
	public boolean isAccusation(){
		return true;
	}
}
