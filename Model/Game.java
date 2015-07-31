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
	private static Map<Integer, Player> players = new HashMap<Integer, Player>();
	
	public static Board board;
	public static Card[] envelope = new Card[3];
	private static boolean gameOver = false;
		
	public static Map<String, Token> characters = new HashMap<String, Token>();
	public static Map<String, Card> cards = new HashMap<String, Card>();
	public static Map<String, Weapon> weapons = new HashMap<String, Weapon>();
	public static Map<String, Room> rooms = new HashMap<String, Room>();

	public static void main(String[] args){
		Menu menu = new Menu();
		numPlayers = menu.promptNumberPlayers(MIN_PLAYERS, MAX_PLAYERS);
		
		initialise();
		assignPlayers(menu);
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
	
	/**
	 * Initialises the Tokens, Weapons, Rooms and Cards
	 * Also populates the envelope with one character, one weapon and one room card
	 */
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
			characters.put(t.toString(), t);
			
			if (i == murderCard){
				envelope[0] = c;
			} else {
				cards.put(c.toString(), c);
			}
		}
		
		murderCard = random.nextInt(NUM_WEAPONS);
		//initialise weapons and weapon cards
		for (int i = 0; i < NUM_WEAPONS; i++){
			Weapon w = new Weapon(WEAPON_NAME[i]);
			Card c = new Card(WEAPON_NAME[i]);
			weapons.put(w.toString(), w);
			
			if (i == murderCard){
				envelope[1] = c;
			} else {
				cards.put(c.toString(), c);
			}
		}
		
		murderCard = random.nextInt(NUM_ROOMS);
		//initialise rooms and room cards
		for (int i = 0; i < NUM_ROOMS; i++){
			Room r = new Room(ROOM_NAME[i]);
			Card c = new Card(ROOM_NAME[i]);
			rooms.put(r.toString(), r);
			
			if (i == murderCard){
				envelope[2] = c;
			} else {
				cards.put(c.toString(), c);;
			}
		}	
	}
	
	
	/**
	 * Handles the creating of players and them picking their characters.
	 * 
	 * @param menu - used to interact with the user
	 */
	private static void assignPlayers(Menu menu) {
		List<String> availableCharacters = new ArrayList<String>();
		
		for(int i = 0; i < CHARACTER_NAME.length; i++){
			availableCharacters.add(CHARACTER_NAME[i]);
		}
		
		for(int i = 0; i < numPlayers; i++){
			String characterName = menu.newPlayer(i, availableCharacters);			
			Player p = new Player(characters.get(characterName));
			players.put(i, p);
		}
	}
	
	/**
	 * Deal out the clue cards to the players.
	 */
	private static void dealCards() {
		// TODO Auto-generated method stub
		//use numPlayers variable and cards set
		
	}
}
