package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;


/**
 * A class representing the Board in the game Cluedo
 *
 * @author Johnny Denford, Kieran Mckay
 *
 */
public class Board {

	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;

	private Tile board[][];
	private List<Tile> spawns = new ArrayList<Tile>();

	/**
	 * constructs new board given csv file
	 * @param boardFile
	 */
	public Board(String boardFile) {
		try {
			Scanner sc = new Scanner(new File(boardFile));
			parseBoard(csvToArray(sc));

		} catch (FileNotFoundException e) {
			System.err.println("Invalid Board File");
			e.printStackTrace();
		}
	}

	/**
	 * returns a Tile given the x and y coordinates
	 * null if no tile present
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTile(int x, int y){
		if(x < BOARD_WIDTH && x > -1 && y < BOARD_HEIGHT && y > -1 ){
			return board[y][x];
		}
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Returns the next available starting point for a character.
	 *
	 * @return Tile - next spawn point for characters which is not taken
	 */
	public Tile getFreeSpawn(){
		for ( Tile t : spawns){
			if (t.hasSpace()){
				return t;
			}
		}
		return null;
	}

	/**
	 * Add a token to the given coordinate
	 * @param token Token to add
	 * @param x x position
	 * @param y y position
	 * @return true if token placed successfully
	 */
	public boolean placeToken(Token token,int x, int y){
		Tile tile = getTile(x,y);
		if(tile == null || token == null)return false;
		tile.addToken(token);
		token.setLocation(tile);
		return true;
	}

	/**
	 * Adds a tile puts a token on a tile
	 * @param tile Tile to add the token to
	 * @param token Token to be added to the tile
	 */
	public void addToken(Tile tile, Token token){
		token.setLocation(tile);
		tile.addToken(token);
	}

	void parseBoard(String[][] stringBoard){
		//System.out.println(stringArray(stringBoard));

		Map<String, Room> rooms = new HashMap<String, Room>();
		board = new Tile[BOARD_HEIGHT][BOARD_WIDTH];

		for (int i = 0; i < stringBoard.length; i++) {
			for (int j = 0; j < stringBoard[i].length; j++) {
				String stringVal = stringBoard[i][j];
				if(stringVal.equals(""))continue;
				if(stringVal.charAt(0) == 'l'){ //is a location
					board[i][j] = new Location();
					if(stringVal.contains("char"))spawns.add(board[i][j]); //is a character spawn point
					//System.out.println("new Location "+ stringVal);
				}
				else if(Pattern.matches("/d",stringVal)){ //has a digit, must be a room
					if(!rooms.containsKey(stringVal.charAt(0))){ //the map doesn't contain this room
						rooms.put(stringVal.charAt(0)+"",new Room(stringVal.charAt(0)+"")); //add room to the map
						if(stringVal.contains("-")){ //the room has a teleport room
							String teleRoom = stringVal.split("-")[1]; //the name of the room to teleport to
							if(!rooms.containsKey(teleRoom)){ //the teleport room doesnt exist yet
								rooms.put(teleRoom,new Room(teleRoom)); //add the new room to the map
							}
							rooms.get(stringVal.charAt(0)).addNeighbour("Teleport to "+ teleRoom, rooms.get(teleRoom)); //connect this room to the teleroom
						}
					}
					Door newDoor = new Door();
					Room neighbourRoom = rooms.get(stringVal.charAt(0));
					newDoor.room = neighbourRoom;
					neighbourRoom.addNeighbour(stringVal.charAt(1)+"",newDoor); //add the door with the door number to the room
					board[i][j] = newDoor;
				}
			}
		}

		//Go through the board linking neighbouring tiles
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] != null && (board[i][j] instanceof Location)){
					if(i > 0 && board[i-1][j] != null){ //dont go over the edge
						if((board[i-1][j] instanceof Door && !stringBoard[i][j].contains("x"))||(board[i-1][j] instanceof Location && stringBoard[i-1][j].contains("x"))){
							board[i][j].addNeighbour("North", board[i-1][j]);
						}
					}
					if(i < board.length-1 && (board[i+1][j] != null)){
						if((board[i+1][j] instanceof Door && !stringBoard[i][j].contains("x"))||(board[i+1][j] instanceof Location && stringBoard[i+1][j].contains("x"))){
							board[i][j].addNeighbour("South", board[i+1][j]);
						}
					}
					if(j > 0 && board[i][j-1] != null){ //dont go over the edge
						if((board[i][j-1] instanceof Door && !stringBoard[i][j].contains("x"))||(board[i][j-1] instanceof Location && stringBoard[i][j-1].contains("x"))){
							board[i][j].addNeighbour("West", board[i][j-1]);
						}
					}
					if(j < board[i].length-1 && board[i][j+1] != null){
						if((board[i][j+1] instanceof Door && !stringBoard[i][j].contains("x"))||(board[i][j+1] instanceof Location && stringBoard[i][j+1].contains("x"))){
							board[i][j].addNeighbour("East", board[i][j+1]);
					}
					}
				}
			}
		}
	}



	private String stringArray(String[][] stringArray) {
		String string = "";
		for (int i = 0; i < stringArray.length; i++) {
			for (int j = 0; j < stringArray[i].length; j++) {
				if(!stringArray[i][j].equals("")){
					string+= stringArray[i][j];
				}else{
					string+= " ";
				}
				string+= '\t';
			}
			string+= '\n';
		}
		return string;
	}

	/**
	 * Pull csv board into 2D String Array
	 *
	 * @param sc
	 *            Scanner with csv file
	 * @return 2D string array from CSV
	 */
	private String[][] csvToArray(Scanner sc) {
		this.BOARD_WIDTH = sc.nextInt();
		this.BOARD_HEIGHT = sc.nextInt();

		String line = sc.nextLine();
		String initialBoard[][] = new String[BOARD_HEIGHT][BOARD_WIDTH];

		for (int i = 0; i < initialBoard.length; i++) { // scan lines vertically
			String lineTiles[] = line.split(",");
			initialBoard[i] = lineTiles;
			line = sc.nextLine();
		}
		return initialBoard;
	}

	char[][] boardChars = {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'|','_','_','_','_','_','_','_','_','_','_','_','_',' ','_','_','_','_','|',' ','|','_','_','_','_','_','_','_','|',' ','|','_','_','_','_',' ','_','_','_','_','_','_','_','_','_','_','_','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|','_','|',' ','.','_','.','_','|',' ','P','a','t','i','o',' ','|','_','.','_','.',' ','|','_','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ','C','a','r','d','b','o','a','r','d',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ','B','o','x',' ',' ',' ',' ','|'},
				{'|','M','a','n','C','a','v','e',' ',' ',' ',' ','|',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_','_','|'},
				{'|','_','_','_','_','_','_','_','_',' ',' ','_','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.','_','|'},
				{'|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|','_',' ',' ','_','_','_','_','_','_','_','_','_',' ',' ','_','|',' ','.',' ','.','_','_','_','_','_','_','_','_','_','|','_','|'},
				{'|','_','_','_','_','_','_','_','_','_','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|','_','_','_',' ',' ','.',' ','.',' ','.','_','_','_','_','_','_','_','_','_','.',' ','.',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|','B','o','a','t',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|','H','o','u','s','e',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_',' ',' ','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','.','_','_','_',' ',' ','_','_','_','_','_','_','|'},
				{'|','T','h','e',' ','H','u','b',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.','_','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ','_','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','_','|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_','_','|',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','_','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.','_','_','_','_',' ',' ',' ',' ','_','_','_','.',' ','.',' ','|','_',' ','W','a','t','c','h','t','o','w','e','r',' ','|'},
				{'|','_','|','_','_','_','_','_','_','_','_','_',' ',' ','.',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_','_','_','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ','.',' ',' ','_','_','_','_','_','_','_','_','_','|','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','S','w','a','m','p',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.','_','|','D','u','n','g','e','o','n',' ',' ',' ',' ','|','_','.',' ','|','L','a','b',' ','X',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','_','_','_','_','_','_','_','_','_','_','_','_','_','|','_','|','_','|','_','_','_','_','_','_','_','_','_','_','_','|','_','|','_','|','_','_','_','_','_','_','_','_','_','_','_','_','_','|'}
			};

	/**
	 * returns a string representation of the board
	 */
	public String toString(){
		String boardString = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				boardString += boardChars[i][j*2];
				if(board[i][j] != null && (!board[i][j].hasSpace()||board[i][j].isRoom())){
					boardString += board[i][j].toString().charAt(0);
				}
				else{
					boardString+= boardChars[i][j*2+1];
				}
				if(j == board[i].length-1){
					boardString+='|';
				}
			}
			boardString += '\n';
		}
		return boardString;
	}




	public String debugString(String dir){
		String boardString = "";
		for(int k = 0; k < board[0].length; k++){
			boardString+=" _";
		}
		boardString += "\n";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(j ==0)boardString += "|";
				if(board[i][j] !=null && board[i][j].getNeighbour(dir)!= null){

					boardString += dir.charAt(0);
				}else{
					boardString += " ";
				}
				boardString+= " ";
				if(j ==board[i].length-1)boardString += "|";
			}
			boardString += "\n";
		}
		for(int j = 0; j < board[0].length; j++){
			boardString+=" _";
		}
		return boardString;
	}

	/**
	 * fail parsing
	 * @param row
	 *
	 * @param col
	 */
	private void fail(int row, int col, String data) {
		throw new RuntimeException("Parse Exception: " + data + "@ row: " + row
				+ " col: " + col);
	}

	public static void main(String[] args){
		Board testBoard = new Board("board.csv");
		Tile tile = testBoard.getFreeSpawn();
		testBoard.addToken(tile,new Token("a",tile));
		System.out.println(testBoard.toString());
	}
}