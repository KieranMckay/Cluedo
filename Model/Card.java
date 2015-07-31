package Model;

/**
 * An interface for representing a card in the Cluedo game
 * 
 * @author Kieran Mckay
 */
public class Card {
	String name;

	public Card(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the card as a String
	 * 
	 * @return String - the name of the card
	 */
	public String toString(){
		return name;
	}
}
