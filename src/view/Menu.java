package view;

import java.util.*;

import model.Card;
import model.Envelope;
import model.Player;

/**
 * A class for talking to the user and receiving responses from the user through
 * console.
 *
 * @author Kieran Mckay
 *
 */
public class Menu {
	Scanner input;

	public Menu() {
	}

	/**
	 * Console asks the user to specify how many players there will be and
	 * returns that number.
	 *
	 * @param min - the minimum number of players allowed in the game
	 * @param max - the minimum number of players allowed in the game
	 * @return the number of players in the game
	 */
	public int promptNumberPlayers(int min, int max) {
		int numPlayers = -1;
		println("Welcome to Cluedo! How many players would you like?");
		input = new Scanner(System.in);

		while (numPlayers < min || numPlayers > max) {
			println("You may have between " + min + " and " + max + " players");

			if (input.hasNextInt()) {
				numPlayers = input.nextInt();
			} else {
				input.next();
			}
		}
		println("You have selected " + numPlayers + " players.");

		// input.close(); //Cant close scanners because System.in will not read......
		return numPlayers;
	}

	/**
	 * Console asks the user to select a character from a list of available
	 * characters
	 *
	 * @param playerNum - the player number of the person choosing their character
	 * @param availableCharacters - a List of remaining available characters
	 * @return a String representing the players choosen character
	 */
	public String newPlayer(int playerNum, List<String> availableCharacters) {
		int choice = -1;
		println("You are player number: " + (playerNum + 1)
				+ ". Please select a Character.");
		println("Remaining number of characters: " + availableCharacters.size());

		// loop to display all available players
		for (int i = 0; i < availableCharacters.size(); i++) {
			println("	" + (i + 1) + ". " + availableCharacters.get(i));
		}

		input = new Scanner(System.in);

		// loop while player has made an invalid choice
		while (choice < 1 || choice > availableCharacters.size()) {
			if (input.hasNext()) {
				choice = input.nextInt();
			} else {
				input.next();
			}
		}
		String character = availableCharacters.get(choice - 1);
		availableCharacters.remove(choice - 1);
		println("Player " + (playerNum + 1) + " is now playing as " + character);

		// input.close(); //Cant close scanners because System.in will not read......

		return character;
	}

	/**
	 * Utility method for pausing console. Program will pause until key has been
	 * input.
	 */
	public void pressToContinue() {
		input = new Scanner(System.in);
		println("Enter any key to continue: ");
		// loop while player has not made an input
		while (!input.hasNext()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				println("pressToContinue() sleep interupted");
			}
		}
	}

	/**
	 * Displays to the player the choice of murder items from either characters, rooms or weapons.
	 * The user must make a choice which is returned.	 *
	 *
	 * @param options - A set of all choices of murder items for this kind of murder item
	 * @return String - users choice of murder item
	 */
	public String selectMurderItem(Set<String> options) {
		HashMap<Integer, String> choice = new HashMap<Integer, String>();

		while (true) {
			println("Select an option from 1 to " + options.size());

			int i = 1;
			for (String option : options) {
				println(i + ". " + option);
				choice.put(i, option);
				i++;
			}
			try{
				int selection = Integer.parseInt(getChar()+"");//throws exception if invalid number
				if(selection > 0 && selection < options.size() +1){
					return choice.get(selection);
				}
			}catch(NumberFormatException e){
				println("Not a valid Number!");
			}
		}
	}

	/**
	 * Gets the next char entered by the user
	 */
	public char getChar() {
		input = new Scanner(System.in);
		return input.next(".").charAt(0);
	}

	/**
	 * Outputs the string passed in to console
	 * @param string - message to be displayed
	 */
	public void println(String string) {
		System.out.println(string);
	}

	/**
	 * Ask the user what they would like to do this turn
	 *
	 * @return int - indicating what player would like to do
	 */
	public int promptTurn() {
		int choice = -1;
		println("What would you like to do this turn");
		println("	1. Make an accusation.");
		println("	2. Move your character.");
		println("	3. Do Nothing.");

		input = new Scanner(System.in);

		// loop while player has made an invalid choice
		while (choice < 1 || choice > 3) {
			if (input.hasNext()) {
				choice = input.nextInt();
			} else {
				input.next();
			}
		}
		return choice;
	}

	/**
	 * Ask the user if they want to make a suggestion
	 *
	 * @return
	 */
	public boolean promptSuggestion() {
		int choice = -1;
		println("Would you like to make a suggestion including the current room?");
		println("	1. Yes.");
		println("	2. No.");

		input = new Scanner(System.in);

		// loop while player has made an invalid choice
		while (choice < 1 || choice > 2) {
			if (input.hasNext()) {
				choice = input.nextInt();
			} else {
				input.next();
			}
		}
		return choice == 1;
	}

	/**
	 * A method for displaying to screen all the information about the current player at the start of their turn.
	 *
	 * @param player - current player
	 */
	public void playerInfo(Player player) {
		println("It is " + player.getToken().toString() +"'s turn.");

		//TODO show remaining suspect cards here instead of looping through cards held in hand when implemented
		println("You're cards are: ");
		for(Card card : player.getHand() ){
			println("	"+card.toString());
		}
	}

	/**
	 * Displays the end of game winning scenario
	 * @param winner - Player who won the game
	 * @param murderEnvelope - the envelope containing the murder cards
	 */
	public void printWinner(Player winner, Envelope murderEnvelope) {
		println("The Mystery of the Murder has been Solved!!!");
		println("The Winner is "+winner.toString()+" playing as "+ winner.getToken().toString());
		println("The Murder was commited by the dastardly "+murderEnvelope.characterToString());
		println("The blood covered "+murderEnvelope.weaponToString()+" was the weapon used (funny how the blood never gave it away...)");
		println("The infamous act was commited in the "+murderEnvelope.roomToString());
		println("Who would've thought such a thing even possible?!");
		pressToContinue();

	}
}
