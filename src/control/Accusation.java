package control;

import game.*;

public class Accusation extends Suggestion {

	private Player player;
	private Envelope guessEnvelope;
	private Envelope murderEnvelope;

	public Accusation(Player player, Envelope guessEnvelope, Envelope murderEnvelope) {
		super(player, guessEnvelope);
		this.player = player;
		this.guessEnvelope = guessEnvelope;
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
		return guessEnvelope.equals(murderEnvelope);
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
