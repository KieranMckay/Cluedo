package control;

import model.*;

public class Suggestion {
	private Player player;
	private Envelope guess;

	public Suggestion(Player player, Envelope guess) {
		this.player = player;
		this.guess = guess;
	}

	public Envelope getGuess(){
		return guess;
	}

	public String getSuggestedCharacter(){
		return guess.characterToString();
	}

	public String getSuggestedWeapon(){
		return guess.weaponToString();
	}

	public String getSuggestedRoom(){
		return guess.roomToString();
	}

	/**
	 * Returns whether this is an accusation or not
	 * @return false - because this is parent class Suggestion
	 */
	public boolean isAccusation(){
		return false;
	}
}
