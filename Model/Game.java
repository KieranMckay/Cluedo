package Model;

import java.util.*;

/**
 * Wrapper class for cluedo game. 
 * Sets the game up.
 * 
 * @author Kieran Mckay
 *
 */
public class Game {
	private final static String[] CHARACTER_NAME = { "Miss Scarlet", "Colonel Mustard","Mrs White", "Reverend Green", "Mrs Peacock", "Professor Plum" };
	private final static int CHARACTER_X = { , , , , , };
	private final static int CHARACTER_Y = { , , , , , };
	
	private final static String[] WEAPON_NAME = { "Candelstick", "Wrench", "Rope", "Lead Pipe", "Knife", "Revolver", "Axe", "Poison" };
	private final static String[] ROOM_NAME = { "Kitchen", "Ball Room","Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dinning Room" };
		
	private final static int MIN_PLAYERS = 3;
	private final static int MAX_PLAYERS = 6;
	private final static int NUM_WEAPONS = 2;
	private final static int NUM_CHARACTERS = 6;
	private final static int NUM_ROOMS = 6;
	
	private static int numPlayers;
	private static boolean gameOver = false;
	
	public static Board board;
	public static Card[] envelope = new Card[3];
		
	public static Set<Token> characters = new HashSet<Token>();
	public static Set<Card> cards = new HashSet<Card>();
	public static Set<Weapon> weapons = new HashSet<Weapon>();
	public static Set<Room> rooms = new HashSet<Room>();

	public static void main(String[] args){
		Menu menu = new Menu();
		numPlayers = menu.promptNumberPlayers(MIN_PLAYERS, MAX_PLAYERS);
		
		initialise();
		menu.drawGame();
	}
	
	/**
	 * Controls the game logic in a loop until the game is over
	 */
	private static void gameLoop(){
		while(!gameOver){
			
			if(false){
				gameOver = true;
			}
		}
	}

	private static void initialise() {	
		board = new Board("board.csv");
		//initialise characters and character cards
		for (int i = 0; i < NUM_CHARACTERS; i++){
			Location loc = board.getTile(x, y);
			Token t = new Token(CHARACTER_NAME[i], loc);		
			Card c = new CharacterCard(CHARACTER_NAME[i]);
			characters.add(t);
			cards.add(c);
		}
		
		//initialise weapons and weapon cards
		for (int i = 0; i < NUM_WEAPONS; i++){
			Weapon w = new Weapon(WEAPON_NAME[i]);
			Card c = new WeaponCard(WEAPON_NAME[i]);
			weapons.add(w);
			cards.add(c);
		}
		
		//initialise rooms and room cards
		for (int i = 0; i < NUM_ROOMS; i++){
			Room r = new Room(ROOM_NAME[i]);
			Card c = new RoomCard(ROOM_NAME[i]);
			rooms.add(r);
			cards.add(c);
		}		
		
		
		fillEnvelope();
		
		
	}
	
	private static void fillEnvelope(){
		Random random = new Random();
		
		//get random weapon and move its card from cards into envelope
		
		//envelope[0] = 		
		
		//get random room and move its card from cards into envelope
		
		//envelope[1] = 	
		
		//get random character and move its card from cards into envelope
		
		//envelope[2] = 
	}
}
