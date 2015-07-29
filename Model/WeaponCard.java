package Model;

/**
 * A class for a Card representing a Weapon "clue" in the game Cluedo
 * 
 * @author Kieran Mckay
 *
 */
public class WeaponCard implements Card{
	String name;

	public WeaponCard(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
