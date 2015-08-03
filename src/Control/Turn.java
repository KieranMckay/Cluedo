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
	private int turns;

	public Turn(Player player, Board board, Menu menu) {
		this.player = player;
		this.menu = menu;
		this.board = board;
		turns = rollDice();
	}

	/**
	 * Initiates the logic of the player taking their turn.
	 * 
	 * @return true if the player made an accusation or false if they did not
	 */
	public boolean takeTurn() {
		menu.playerInfo(player);
		if (menu.promptAccusation()) {
			makeAccusation();
			return true;
		}
		movePlayer();
		if (player.getToken().getLocation().isRoom()) {
			if (menu.promptSuggestion()) {
				makeSuggestion();
			}
		}
		return false;
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
		
		return new Envelope(suspect,room,weapon);
	}	

	private void movePlayer() {
		menu.println("It's your turn " + player.toString());
		menu.println("You have " + turns
				+ " turns, please move to your destination");
		while (turns > 0) {
			if (!singleMove(menu.getChar())) {
				menu.println("Invalid Move, try again");
				continue;
			} else if (player.getToken().getLocation().isRoom()) { // they have reached their destination
				return;
			}
		}
	}

	private boolean singleMove(char c) {
		menu.println("Please choose a direction: ");
		menu.println(player.getToken().getLocation().getDirections().toString());
		
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
