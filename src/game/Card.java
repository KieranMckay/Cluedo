package game;

/**
 * A Class for representing a Card in the Cluedo game
 *
 * @author Kieran Mckay
 */
public class Card {
	private String name;

	public Card(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the card as a String
	 *
	 * @return String - the name of the card
	 */
	@Override
	public String toString(){
		return name;
	}
}