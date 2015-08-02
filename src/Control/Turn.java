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

	private void makeSuggestion() {
		//TODO Auto-generated method stub
	}

	private void makeAccusation() {
		// TODO Auto-generated method stub
		
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
