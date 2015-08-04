package control;

import model.*;

public class Suggestion {
	private Player player;
	private Envelope guess;

	public Suggestion(Player player, Envelope guess) {
		this.player = player;
		this.guess = guess;
	}

	public String getSuggestedCharacter(){
		return guess.characterToString();
		//String suggested = guess.characterToString();
		//return Game.characters.get(suggested);
	}

	public String getSuggestedWeapon(){
		return guess.weaponToString();
		//String suggested = guess.weaponToString();
		//return Game.weapons.get(suggested);
	}

	public String getSuggestedRoom(){
		return guess.roomToString();
		//String suggested = guess.roomToString();
		//return Game.rooms.get(suggested);
	}

	/**
	 * Returns whether this is an accusation or not
	 * @return false - because this is parent class Suggestion
	 */
	public boolean isAccusation(){
		return false;
	}
}
