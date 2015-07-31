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
	//private final static int CHARACTER_X = { , , , , , };
	//private final static int CHARACTER_Y = { , , , , , };
	
	private final static String[] WEAPON_NAME = { "Candelstick", "Wrench", "Rope", "Lead Pipe", "Knife", "Revolver", "Axe", "Poison" };
	private final static String[] ROOM_NAME = { "Kitchen", "Ball Room","Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dinning Room" };
		
	private final static int MIN_PLAYERS = 3;
	private final static int MAX_PLAYERS = 6;
	private final static int NUM_WEAPONS = 6;
	private final static int NUM_CHARACTERS = 6;
	private final static int NUM_ROOMS = 9;
	
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
		assignPlayers();
		dealCards();
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
		Random random = new Random();
		board = new Board("board.csv");

		int murderCard = random.nextInt(NUM_CHARACTERS);		
		//initialise characters and character cards
		for (int i = 0; i < NUM_CHARACTERS; i++){
			int x = 0;
			int y = 0;			
			Tile loc = board.getTile(x, y);
			Token t = new Token(CHARACTER_NAME[i], loc);				
			Card c = new Card(CHARACTER_NAME[i]);
			characters.add(t);
			
			if (i == murderCard){
				envelope[0] = c;
			} else {
				cards.add(c);
			}
		}
		
		murderCard = random.nextInt(NUM_WEAPONS);
		//initialise weapons and weapon cards
		for (int i = 0; i < NUM_WEAPONS; i++){
			Weapon w = new Weapon(WEAPON_NAME[i]);
			Card c = new Card(WEAPON_NAME[i]);
			weapons.add(w);
			cards.add(c);
			
			if (i == murderCard){
				envelope[1] = c;
			} else {
				cards.add(c);
			}
		}
		
		murderCard = random.nextInt(NUM_ROOMS);
		//initialise rooms and room cards
		for (int i = 0; i < NUM_ROOMS; i++){
			Room r = new Room(ROOM_NAME[i]);
			Card c = new Card(ROOM_NAME[i]);
			rooms.add(r);
			cards.add(c);
			
			if (i == murderCard){
				envelope[2] = c;
			} else {
				cards.add(c);
			}
		}	
	}
	
	/**
	 * Handles the creating of players and them picking their characters.
	 */
	private static void assignPlayers() {
		// TODO Auto-generated method stub
		//use numPlayers variable
	}
	
	/**
	 * Deal out the clue cards to the players.
	 */
	private static void dealCards() {
		// TODO Auto-generated method stub
		//use numPlayers variable and cards set
		
	}
}
