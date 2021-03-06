package game;

import java.util.*;


public class Location extends Tile {
	public Location(int x, int y) {
		super(x, y);
	}
	private Token member;

	@Override
	public List<String> neighbourNames() {
		return new ArrayList<String>(neighbours.keySet());
	}

	@Override
	public boolean hasSpace() {
		return member == null;
	}

	@Override
	public void moveTo(Token t) {
		if(t != null){
			t.getPosition().removeToken(t);
			member = t;
			t.setLocation(this);
		}
	}

	@Override
	public void addToken(Token t) {
		member = t;
	}

	@Override
	public void removeToken(Token t) {
		if(t.equals(member))member = null;
	}

	@Override
	public boolean isRoom() {
		return false;
	}
	@Override
	public String toString(){
			return member.toString();
	}
}
