package Model;

/**
 * A class for a Card representing a Character "clue" in the game Cluedo
 * 
 * @author Kieran Mckay
 *
 */
public class CharacterCard implements Card{
	String name;

	public CharacterCard(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
