package control;

import model.Envelope;
import model.Player;

public class Suggestion {
	Player player;
	Envelope guess;

	public Suggestion(Player player, Envelope guess) {
		this.player = player;
		this.guess = guess;
	}

	/**
	 * Returns whether this is an accusation or not
	 * @return false - because this is parent class Suggestion
	 */
	public boolean isAccusation(){
		return false;
	}
}
