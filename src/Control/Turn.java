package control;

import java.util.Random;

import model.*;
import view.*;

/**
 * A basic class for handling a players move
 * 
 * @author Johnny
 *
 */
public class Turn {
	static Random dice = new Random();

	Player player;
	Menu menu;
	Board board;
	int turns;

	public Turn(Player player, Board board, Menu menu) {
		this.player = player;
		this.menu = menu;
		this.board = board;
		turns = rollDice();
		takeTurn();
	}

	void takeTurn() {
		if (menu.promptAccusation()) {
			makeAccusation();
			return;
		}
		movePlayer();
		if (player.getPosition().isRoom()) {
			if (menu.promptSuggestion()) {
				makeSuggestion();
			}
		}
	}
	/**
	 * the given player will make a suggestion and check if it can be refuted
	 * @return Boolean true if suggestion proved correct
	 */
	private boolean makeSuggestion() {
		Envelope guess = getChoiceEnvelope();
		//TODO needs to go round players and check if suggestion can be disputed
		return true;
	}
	/**
	 * The given player makes an accusation
	 * 
	 * @return true accusation correct
	 */
	private boolean makeAccusation() {
		Envelope envelope = getChoiceEnvelope();
		return board.getSolution().equals(envelope);
	}
	/**
	 * Prompts the player to choose envelope contents
	 * @return Envelope with cards
	 */
	private Envelope getChoiceEnvelope() {
		//TODO get lists of choices
		int selection =  menu.userSelection(ListofSuspects);
		Card suspect = ListOfSuspects.get(selection);
		selection =  menu.userSelection(ListofRooms);
		Card room = ListOfRooms.get(selection);
		selection =  menu.userSelection(ListofWeapons);
		Card weapon = ListOfWeapons.get(selection);
		return new Envelope(suspect,room,weapon);
	}

	

	void movePlayer() {
		menu.println("It's your turn " + player.toString());
		menu.println("You have " + turns
				+ " turns, please move to your destination");
		while (turns > 0) {
			if (!singleMove(menu.getChar())) {
				menu.println("Invalid Move, try again");
				continue;
			} else if (player.getPosition().isRoom()) { // they have reached their destination
				return;
			}
		}
	}

	private boolean singleMove(char c) {
		menu.println("Please choose a direction: ");
		menu.println(player.getPosition().getDirections().toString());
		
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
		if (board.moveToken(player.getToken(),direction)) {
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
