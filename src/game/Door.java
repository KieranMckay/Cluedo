package game;

public class Door extends Tile{
	public Room room;
	public Location location;
	private String name;

	public Door(String name,int x, int y){
		super(x,y);
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
		this.location = (Location)t;
	}

	@Override
	public void addToken(Token t) {
	}

	@Override
	public void removeToken(Token t) {
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
