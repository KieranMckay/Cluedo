package view;

import java.util.*;

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
	 * @param min
	 *            - the minimum number of players allowed in the game
	 * @param max
	 *            - the minimum number of players allowed in the game
	 * 
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

		// input.close(); //Cant close scanners because System.in will not
		// read......
		return numPlayers;
	}

	/**
	 * Console asks the user to select a character from a list of available
	 * characters
	 * 
	 * @param playerNum
	 *            - the player number of the person choosing their character
	 * @param availableCharacters
	 *            - a List of remaining available characters
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

		// input.close(); //Cant close scanners because System.in will not
		// read......

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
	 * Draws the game in its current state. Should be called before every other
	 * menu interaction so it is constantly drawn at the top. Clears the
	 * graphics every time its called.
	 */
	public void drawGame() {
		// clear(); //not currently working
		println("draw board here"); // testing method call
		// draw board here
	}

	public String userSelection(Set<String> options) {		
		HashMap<Integer, String> choice = new HashMap<Integer, String>();
		
		while (true) {
			println("Select an option from 1 to " + options.size()+1);
			
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
	 * TODO get the next char entered by the user
	 */
	public char getChar() {
		input = new Scanner(System.in);
		return input.next(".").charAt(0);
	}

	/**
	 * Utility method for clearing console screen. Because of system dependence
	 * on how to do this code taken from stackoverflow
	 */
	private void clear() {
		// currently not working
		try {
			String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (Exception e) {
			println("Exception thrown trying to clear console: " + e);
		}
	}

	public void println(String string) {
		System.out.println(string);
	}

	/**
	 * Ask the user if they want to make an accusation
	 * 
	 * @return Boolean confirm or reject
	 */
	public boolean promptAccusation() {
		println("Make an accusation? y/n");
		char answer = getChar();
		if (answer == 'y' || answer == 'Y') {
			return true;
		}
		return false;
	}

	/**
	 * Ask the user if they want to make a suggestion
	 * 
	 * @return
	 */
	public boolean promptSuggestion() {
		println("Make a suggestion? y/n");
		char answer = getChar();
		if (answer == 'y' || answer == 'Y') {
			return true;
		}
		return false;
	}
}
