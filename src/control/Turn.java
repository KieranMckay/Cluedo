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
	public int turns;

	public Turn(Player player, Board board, Envelope murderEnvelope) {
		this.player = player;
		//this.menu = menu;
		this.board = board;
		this.murderEnvelope = murderEnvelope;
	}

	/**
	 * the given player will make a suggestion and check if it can be refuted
	 * @return Boolean true if suggestion proved correct
	 */
	public Suggestion makeSuggestion() {
		Envelope guess = getChoiceEnvelope(true);
		Suggestion suggest = new Suggestion(player, guess);
		return suggest;
	}

	/**
	 * The given player makes an accusation
	 *
	 * @return true accusation correct
	 */
	public Accusation makeAccusation() {
		Envelope guess = getChoiceEnvelope(false);
		System.out.println(guess.characterToString());
		System.out.println(guess.weaponToString());
		System.out.println(guess.roomToString());
		Accusation accuse = new Accusation(player, guess, murderEnvelope);
		return accuse;
	}

	/**
	 * Prompts the player to choose a Character, Room and Weapon involved in commiting the murder
	 *
	 * @return Envelope with suspected cards involved in murder
	 */
	public Envelope getChoiceEnvelope(boolean isSuggestion) {
		//Choose a character from the list of all characters then get its card
		String selection =  menu.selectMurderItem(Game.characters.keySet());
		Card suspect = Game.allCards.get(selection);

		if(isSuggestion){
			//suggestion so room has to default to room player is currently in
			selection = player.getToken().getRoom().toString();
		} else {
			//accusation so we choose a room
			//Choose a Room from the list of all Rooms then get its card
			selection =  menu.selectMurderItem(Game.rooms.keySet());
		}
		Card room = Game.allCards.get(selection);

		//Choose a Weapon from the list of all weapons then get its card
		selection =  menu.selectMurderItem(Game.weapons.keySet());
		Card weapon = Game.allCards.get(selection);


		return new Envelope(suspect, weapon,room);
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
