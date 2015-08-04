package control;

import java.util.*;

import model.*;
import view.*;

/**
 * Wrapper class for cluedo game.
 * Sets the game up.
 * Runs the game loop which will continue until the game is over.
 *
 * @author Kieran Mckay
 *
 */
public class Game {
	//All the characters names
	private final static String[] CHARACTER_LIST = { "Shrek", "David Bain", "Kieran", "Johnny", "Batman", "Hannible" };
	//all the weapons names
	private final static String[] WEAPONS_LIST = { "Nokia", "Chainsaw", "Assault Rifle", "Chair", "Cucumber", "Brevel 600W Blender" };
	//all the rooms names
	private final static String[] ROOM_LIST = { "Swamp", "Dungeon","Lab X", "The Hub", "Watchtower", "Boat House",
		"Cardboard Box", "Mancave", "Patio" };

	private final static int MIN_PLAYERS = 3;  	//minimum number of human players
	private final static int MAX_PLAYERS = CHARACTER_LIST.length;	//maximum number of human players

	private static int numPlayers;  //number of human players in the game
	private static Map<Integer, Player> players = new HashMap<Integer, Player>(); //map where key is the players number to the player

	private static Board board; //holds the board object of the game
	private static Envelope murderEnvelope; //contains the murder scene cards (one weapon, one character, one room)

	public static Map<String, Token> characters = new HashMap<String, Token>(); 	//all of the playable characters/suspects
	public static Map<String, Weapon> weapons = new HashMap<String, Weapon>();		//all of the weapons
	public static Map<String, Room> rooms = new HashMap<String, Room>();			//all of the rooms in the game
	public static Map<String, Card> cards = new HashMap<String, Card>();			//all of the clue cards (excluding murder envelope cards)
	private static Set<Card> allCards = new HashSet<Card>();						//all of the clue cards (including murder envelope cards)

	/**
	 * Start point for the game, calls initial methods then the game loop method
	 *
	 * @param args
	 */
	public static void main(String[] args){
		Menu menu = new Menu();
		numPlayers = menu.promptNumberPlayers(MIN_PLAYERS, MAX_PLAYERS);

		initialise();
		assignPlayers(menu);
		dealCards();
		gameLoop(menu);
	}

	/**
	 * Controls the game logic in a loop until the game is over
	 */
	private static void gameLoop(Menu menu){
		int pTurn = new Random().nextInt(numPlayers)+1; //which players turn it is, initialised with a random player
		boolean gameOver = false;
		while(true){
			Player p = players.get(pTurn);	//the current player whos turn it is
			while (p == null){ 				//handles case of if players have been removed
				if(pTurn < players.size()){
					pTurn++;
				} else {
					pTurn = 1;
				}
				p = players.get(pTurn);
			}

			Turn turn = new Turn(p, board, menu, murderEnvelope);  //turn object for interfacing a player and the menu to control their turn

			//perform turn methods here!!!!!!!!!!!!
			Suggestion mySuggestion = turn.takeTurn();

			if(mySuggestion != null){  //the player has made either a suggestion or accusation this turn
				if(mySuggestion.isAccusation()){ // the player has made an accusation
					//process accusation logic here
					Accusation myAccusation = (Accusation) mySuggestion;
					Player accuser = myAccusation.getPlayer();

					if(myAccusation.isCorrect()){
						menu.printWinner(accuser, murderEnvelope);
						return;
					} else {
						playerRemove(accuser);
						if(players.size() == 1){
							for ( Player winner : players.values() ){
								menu.printWinner(winner, murderEnvelope);
							}
							return;
						}
					}
				} else { //player made a suggestion
					//TODO process refuting suggestion
					Card refuted = handleRefute(pTurn, mySuggestion);
					if (refuted == null){
						menu.println("No one could refute your suggestion.");
					} else {
						p.removeSuspect(refuted);
					}
				}
			}

			//increment pTurn so next loop will have next player
			if(pTurn < players.size()){
				pTurn++;
			} else {
				pTurn = 1;
			}
		}
	}

	private static Card handleRefute(int pTurn, Suggestion mySuggestion) {
		for(int i= 0; i < players.size(); i++){
			Player p;
			do {
				if(pTurn < players.size()){
					pTurn++;
				} else {
					pTurn = 1;
				}
				p = players.get(pTurn);
			} while (p == null);
		}
		return null;
	}

	private static void playerRemove(Player removed) {
		//TODO print message about player being removed
		players.remove(removed.getPlayerNumber());
		//TODO distribute cards to other players
		removed.getHand();
	}

	/**
	 * Initialises the Tokens, Weapons, Rooms and Cards
	 * Also populates the envelope with one character, one weapon and one room card
	 */
	private static void initialise() {
		Random random = new Random();
		board = new Board("board.csv");
		Card[] envelope = new Card[3];

		int murderCard = random.nextInt(CHARACTER_LIST.length);
		//initialise characters and character cards
		for (int i = 0; i < CHARACTER_LIST.length; i++){

			Tile loc = board.getFreeSpawn();
			Token t = new Token(CHARACTER_LIST[i], loc);
			Card c = new Card(CHARACTER_LIST[i]);
			characters.put(t.toString(), t);

			//put one random character card into murder envelope, the rest into the "deck"
			if (i == murderCard){
				envelope[0] = c;
			} else {
				cards.put(c.toString(), c);
			}
		}

		murderCard = random.nextInt(WEAPONS_LIST.length);
		//initialise weapons and weapon cards
		for (int i = 0; i < WEAPONS_LIST.length; i++){
			Weapon w = new Weapon(WEAPONS_LIST[i]);
			Card c = new Card(WEAPONS_LIST[i]);
			weapons.put(w.toString(), w);

			//put one random weapon card into murder envelope, the rest into the "deck"
			if (i == murderCard){
				envelope[1] = c;
			} else {
				cards.put(c.toString(), c);
			}
		}

		murderCard = random.nextInt(ROOM_LIST.length);
		//initialise rooms and room cards
		for (int i = 0; i < ROOM_LIST.length; i++){
			Room r = new Room(ROOM_LIST[i]);
			Card c = new Card(ROOM_LIST[i]);
			rooms.put(r.toString(), r);

			//put one random room card into murder envelope, the rest into the "deck"
			if (i == murderCard){
				envelope[2] = c;
			} else {
				cards.put(c.toString(), c);;
			}
		}
		murderEnvelope = new Envelope(envelope[0], envelope[1], envelope[2]);

		//populate the allCards Set
		for(Card card : cards.values()){
			allCards.add(card);
		}
		allCards.add(envelope[0]);
		allCards.add(envelope[1]);
		allCards.add(envelope[2]);
	}

	/**
	 * Handles the creating of players and them picking their characters.
	 *
	 * @param menu - used to interact with the user
	 */
	private static void assignPlayers(Menu menu) {
		List<String> availableCharacters = new ArrayList<String>();//List of all the available characters to choose from
		for(int i = 0; i < CHARACTER_LIST.length; i++){
			availableCharacters.add(CHARACTER_LIST[i]);
		}

		//each player gets to choose a character from the remaining list
		for(int i = 0; i < numPlayers; i++){
			String characterName = menu.newPlayer(i, availableCharacters);
			Player p = new Player(i+1, characters.get(characterName), allCards);
			players.put(i+1, p);
		}
	}

	/**
	 * Deal out the clue cards to the players.
	 */
	private static void dealCards() {
		int i = 0;
		for (Card card : cards.values()){
			int playernum = i % numPlayers;
			Player p = players.get(playernum+1);
			p.addCard(card);;
			i++;
		}
	}
}
