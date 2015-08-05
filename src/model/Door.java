package model;

import javax.management.RuntimeErrorException;

import org.hamcrest.core.IsInstanceOf;

public class Door extends Tile{
	private Room room;
	private Location location;

	@Override
	public boolean hasSpace() {
		return room.hasSpace();
	}

	@Override
	public void moveTo(Token t) {
		if(t.getPosition().isRoom()){
			location.moveTo(t);
		}
		else{ //must be a location
			room.moveTo(t);
		}
	}

	@Override
	public void addToken(Token t) {
		throw new RuntimeErrorException(new Error(),"Shouldnt call add Token on a Door");
	}

	@Override
	public void removeToken(Token t) {
		throw new RuntimeErrorException(new Error(),"Shouldnt call add Token on a Door");
	}

	@Override
	public boolean isRoom() {
		return false;
	}

}
