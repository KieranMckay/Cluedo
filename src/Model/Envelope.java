package src.Model;
/**
 *  A class for representing a set of Cards
 *  
 *  note- I realise we have one inside game but this may make it easier if we include and equals method
 * 	for comparing if a guess is correct.
 * 
 * @author JTFM
 *
 */
public class Envelope {
	
	Card weapon;
	Card room;
	Card character;
	
	public Envelope(Card weapon, Card room, Card character) {
		super();
		this.weapon = weapon;
		this.room = room;
		this.character = character;
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
	/**  TODO
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
