package model;

import javax.management.RuntimeErrorException;

import org.hamcrest.core.IsInstanceOf;

public class Door extends Tile{
	public Room room;
	public Location location;
	private String name;

	public Door(String name){
		this.name = name;
	}
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
	public void addNeighbour(String direction, Tile t) {
		if(! (t instanceof Location)) throw new RuntimeErrorException(new Error(), "Shouldn't be parsing with anything other than location" + t.getClass().toString());
		this.location = (Location)t;
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

	@Override
	public String toString() {
		return this.name;
	}
}
