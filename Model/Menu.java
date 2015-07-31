package Model;

import java.util.*;

/**
 * A class for talking to the user and receiving responses from the user through console.
 * 
 * @author Kieran Mckay
 *
 */
public class Menu {
	Scanner input;
	
	public Menu(){
	}
	
	/**
	 * Console asks the user to specify how many players there will be and returns that number.
	 * 
	 * @param min - the minimum number of players allowed in the game
	 * @param max - the minimum number of players allowed in the game
	 * 
	 * @return the number of players in the game
	 */
	public int promptNumberPlayers(int min, int max){		
		int numPlayers = -1;
		System.out.println("Welcome to Cluedo! How many players would you like?");
		Scanner input = new Scanner(System.in);
		
		
		while(numPlayers < min || numPlayers > max){
			System.out.println("You may have between "+min+" and "+max+" players");
			
			if(input.hasNextInt()){
				numPlayers = input.nextInt();
			}else{
				input.next();
			}
		}
		System.out.println("You have selected "+numPlayers+" players.");
		
		input.close();
		return numPlayers;
	}
	
	/**
	 * Draws the game in its current state. 
	 * Should be called before every other menu interaction so it is constantly drawn at the top.
	 * Clears the graphics every time its called.
	 */
	public void drawGame(){
		//clear();  //not currently working
		System.out.println("draw board here"); //testing method call
		//draw board here
	}
	
	/**
	 * Utility method for clearing console screen.
	 * Because of system dependence on how to do this code taken from stackoverflow
	 */
	private void clear()
	{
		//currently not working
	    try{
	        String os = System.getProperty("os.name");

	        if (os.contains("Windows")){
	            Runtime.getRuntime().exec("cls");
	        } else {
	            Runtime.getRuntime().exec("clear");
	        }
	    } catch (Exception e) {
	    	System.out.println("Exception thrown trying to clear console: "+e);
	    }
	}

	/**
	 * Console asks the user to select a character from a list of available characters
	 * 
	 * @param playerNum - the player number of the person choosing their character
	 * @param availableCharacters - a List of remaining available characters
	 * @return a String representing the players choosen character
	 */
	public String newPlayer(int playerNum, List<String> availableCharacters) {
		int choice = -1;
		System.out.println("You are player number: "+playerNum+". Please select a Character");
		
		//loop to display all available players
		for(int i = 0; i < availableCharacters.size(); i++){
			System.out.println("	"+(i+1)+". "+availableCharacters.get(i));
		}
		Scanner input = new Scanner(System.in);		
		
		//loop while player has made an invalid choice
		while(choice < 1 || choice > availableCharacters.size() ){			
			if(input.hasNextInt()){
				choice = input.nextInt();
			}else if(input.hasNext()){  //TODO fix loop control. currently not working
				input.next();
			}
		}
		String character = availableCharacters.get(choice-1);
		availableCharacters.remove(choice-1);
		System.out.println("Player "+playerNum+" is now playing as "+ character);		
		input.close();
		return character;
	}
}
