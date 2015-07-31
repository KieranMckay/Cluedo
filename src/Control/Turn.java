package Model;

import java.util.Random;

import model.*;
import view.*;

/**
 * A basic class for handling a players move
 * @author Johnny
 *
 */
public class Turn {
	static Random dice = new Random();
	
	Player player;
	Menu menu;
	int turns;
	
	public Turn(Player player, Menu menu) {
		this.player = player;
		this.menu = menu;
		turns = rollDice();
	}
	
	void takeTurn(){
		movePlayer();
	}
	
	void movePlayer(){
		Tile playerLocation = player.getPosition();
		System.out.println("It's your turn "+ player.toString());
		System.out.println("You have " + turns + " turns, please move to your destination");
		while(turns > 0) {
			//char selection = menu.getChar();
			singleMove(menu.getChar());
			if(player.getPosition().isRoom());
			//TODO
		}
	}
	
	void singleMove(char c){
		switch(c){
		case 'w':
			if(!player.moveNorth()){
				return;
			}
			break;
		case 's':
			if(!player.moveSouth()){
				return;
			}
			break;
		case 'a':
			if(!player.moveWest()){
				return;
			}
			break;
		case 'd':
			if(!player.moveEast()){
				return;
			}
			break;
		default:
			return;
		}
		turns --;
		
	}
	
	
	/**
	 * get a number between 2 and 12 inclusive
	 * @return
	 */
	private static int rollDice(){
		return (int)((dice.nextFloat() * 10)+2);
	}
}
