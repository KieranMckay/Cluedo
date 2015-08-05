package model;

import java.util.*;

public class Location extends Tile {
	Token member;

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
			t.getLocation().removeToken(t);
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

}
