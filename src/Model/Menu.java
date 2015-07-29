package Model;

import java.util.Scanner;

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
}
