package control;

import java.util.Random;

import model.*;
import view.*;

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
	private Menu menu;
	private Board board;
	private Envelope murderEnvelope;
	private int turns;

	public Turn(Player player, Board board, Menu menu, Envelope murderEnvelope) {
		this.player = player;
		this.menu = menu;
		this.board = board;
		this.murderEnvelope = murderEnvelope;
		turns = rollDice();
	}

	/**
	 * Initiates the logic of the player taking their turn.
	 *
	 * @return true if the player made an accusation or false if they did not
	 */
	public Suggestion takeTurn() {
		menu.println(board.toString());
		menu.playerInfo(player);
		int choice = menu.promptTurn();
		if (choice == 1) {
			return makeAccusation();
		} else if (choice == 2) {
			movePlayer();
			if (player.getToken().getLocation().isRoom()) {
				if (menu.promptSuggestion()) {
					return makeSuggestion();
				}
			}
		}
		return null;
	}

	/**
	 * the given player will make a suggestion and check if it can be refuted
	 * @return Boolean true if suggestion proved correct
	 */
	private Suggestion makeSuggestion() {
		Envelope guess = getChoiceEnvelope();
		Suggestion suggest = new Suggestion(player, guess);
		//TODO needs to go round players and check if suggestion can be disputed
		return suggest;
	}

	/**
	 * The given player makes an accusation
	 *
	 * @return true accusation correct
	 */
	private Accusation makeAccusation() {
		Envelope guess = getChoiceEnvelope();
		Accusation accuse = new Accusation(player, guess, murderEnvelope);
		return accuse;
	}

	/**
	 * Prompts the player to choose a Character, Room and Weapon involved in commiting the murder
	 *
	 * @return Envelope with suspected cards involved in murder
	 */
	private Envelope getChoiceEnvelope() {
		//Choose a character from the list of all characters then get its card
		String selection =  menu.selectMurderItem(Game.characters.keySet());
		Card suspect = Game.cards.get(selection);

		//Choose a Room from the list of all Rooms then get its card
		selection =  menu.selectMurderItem(Game.rooms.keySet());
		Card room = Game.cards.get(selection);

		//Choose a Weapon from the list of all weapons then get its card
		selection =  menu.selectMurderItem(Game.weapons.keySet());
		Card weapon = Game.cards.get(selection);

		return new Envelope(weapon,room,suspect);
	}

	private void movePlayer() {
		menu.println("It is " + player.toString() +"'s turn. ("
				+player.getToken().toString()+")");
		menu.println("You rolled a " + turns
				+ " please move to your destination");
		menu.println(board.toString());

		while (turns > 0) {
			if (!singleMove()) {
				menu.println("Invalid Move, try again");
				continue;
			} else if (player.getToken().getLocation().isRoom()) { // they have reached a room
				return;
			} else {
				menu.println(board.toString());
			}
		}
	}

	private boolean singleMove() {
		menu.println("Please choose a direction: ");
		menu.println(player.getToken().getLocation().getDirections().toString());
		char c = menu.getChar();

		Tile.direction direction;
		switch (c) {
		case 'w':
			direction = Tile.direction.NORTH;
			break;
		case 's':
			direction = Tile.direction.SOUTH;
			break;
		case 'a':
			direction = Tile.direction.WEST;
			break;
		case 'd':
			direction = Tile.direction.EAST;
			break;
		case 'q':
			direction = Tile.direction.TELE;
			break;
		default:
			return false;
		}
		if (player.move(direction)) {
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
	private static int rollDice() {
		return dice.nextInt(11) + 2;
	}
}
