
package game;
/**
 *  A class for representing a set of Cards
 *
 *  note- I realise we have one inside game but this may make it easier if we include and equals method
 * 	for comparing if a guess is correct.
 *
 * @author Kieren, Johnny
 *
 */
public class Envelope {

	private Card weapon;
	private Card room;
	private Card character;

	public Envelope(Card character, Card weapon, Card room) {
		this.weapon = weapon;
		this.room = room;
		this.character = character;
	}

	public Card character(){
		return character;
	}

	public Card weapon(){
		return weapon;
	}

	public Card room(){
		return room;
	}

	public String characterToString(){
		return character.toString();
	}

	public String weaponToString(){
		return weapon.toString();
	}

	public String roomToString(){
		return room.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
		return result;
	}
	/**  TODO re-write me
	 * We may want to change this depending on our card implementation
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Envelope other = (Envelope) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}


}