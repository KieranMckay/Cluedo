package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import model.Tile.direction;

/**
 * A class representing the Board in the game Cluedo
 *
 * @author Johnny Denford, Kieran Mckay
 *
 */
public class Board {

	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;

	private Envelope envelope;
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
	 * Get the board solution envelope
	 * @return Envelope with solution
	 */
	public Envelope getSolution() {
		return this.envelope;
	}

	public Tile getFreeSpawn(){
		for ( Tile t : spawns){
			if (t.hasSpace()){
				return t;
			}
		}
		return null;
	}

	/**
	 * Move a given token in the provided direction
	 * @param token Token to move
	 * @param direction Direction to move
	 * @return Boolean true if move was successful
	 */
	/*public boolean moveToken(Token token, direction direction) {
		Tile startPosition = token.getLocation();
		Tile newLocation = startPosition.getNeighbour(direction);
		if(newLocation != null){ //can move there
			token.setLocation(newLocation);
			startPosition.removeToken(token);
			newLocation.addToken(token);
			return true;
		}
		return false;
	}*/

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

	/**
	 * Populate board field with Tiles
	 * TODO this doesnt yet block hallway to room if the room is adjacent
	 * @param stringBoard
	 */
	private void parseBoard(String[][] stringBoard) {
		Map<String, Tile> rooms = new HashMap<String, Tile>();
		board = new Tile[BOARD_HEIGHT][BOARD_WIDTH];

		// fill map of rooms
		for (int i = 0; i < stringBoard.length; i++) {
			for (int j = 0; j < stringBoard[i].length; j++) {
				if (!stringBoard[i][j].startsWith("t") && !stringBoard[i][j].equals("")) {// must be a room
					String room[] = stringBoard[i][j].split("-"); //may have a teleport
					rooms.put(room[0], new Room(room[0])); //map name to room object
				}
			}
		}

		// Process each square creating Tile objects
		for (int i = 0; i < stringBoard.length; i++) {
			for (int j = 0; j < stringBoard[i].length; j++) {
				String squareVal = stringBoard[i][j];
				if (squareVal.startsWith("t")) {
					board[i][j] = new Tile();
					if (squareVal.startsWith("t-")) {// also has a character
						spawns.add(board[i][j]);
						System.out.println("added spawn " + spawns.size());

					}
				} else if(!squareVal.equals("")){ // is room and square not empty
					String roomCouple[] = squareVal.split("-");

					board[i][j] = rooms.get(roomCouple[0]);
					if (roomCouple.length > 1) {// has a teleport
						board[i][j].addNeighbour(Tile.direction.TELE, rooms.get(roomCouple[0]));
					}
				}
			}
		}
		//Go through the board linkin neighbouring tiles
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] != null){
					if(i > 0 && board[i-1][j] != null){ //dont go over the edge
						board[i][j].addNeighbour(Tile.direction.NORTH, board[i-1][j]);
					}
					if(i < board.length-1 && board[i+1][j] != null){
						board[i][j].addNeighbour(Tile.direction.SOUTH, board[i+1][j]);
					}
					if(j > 0 && board[i][j-1] != null){ //dont go over the edge
						board[i][j].addNeighbour(Tile.direction.WEST, board[i][j-1]);
					}
					if(j < board[i].length-1 && board[i][j+1] != null){
						board[i][j].addNeighbour(Tile.direction.EAST, board[i][j+1]);
					}
				}
			}
		}
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
				{'|','_','_','_','_','_','_','_','_','_','_','_','_',' ','_','_','_','_','|',' ','|','_','_','_','_','_','_','_','|',' ','|','_','_','_','_',' ','_','_','_','_','_','_','_','_','_','_','_','_','|'},
				{'|',' ',' ','M','a','n','c','a','v','e',' ',' ','|','_','|',' ','.','_','.','_','|',' ','P','a','t','i','o',' ','|','_','.','_','.',' ','|','_','|',' ','C','a','r','d','b','o','a','r','d',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ','B','o','x',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_','_','|'},
				{'|','_','_','_','_','_','_','_','_',' ',' ','_','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|'},
				{'|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|','_',' ',' ','_','_','_','_','_','_','_','_','_',' ',' ','_','|',' ','.',' ','.','_','_','_','_','_','_','_','_','_','_','_','|'},
				{'|','_','_','_','_','_','_','_','_','_','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|',' ',' ',' ',' ',' ',' ','B','o','a','t',' ','|'},
				{'|',' ','T','h','e',' ','H','u','b',' ','|','_','_','_',' ',' ','.',' ','.',' ','.','_','_','_','_','_','_','_','_','_','.',' ','.',' ','.',' ',' ',' ',' ',' ',' ',' ',' ','H','o','u','s','e','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_','_','_','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','.','_','_','_',' ',' ','_','_','_','_','_','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.','_','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ','_','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ','W','a','t','c','h','t','o','w','e','r',' ','|'},
				{'|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_','_','|',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.','_','_','_','_',' ',' ',' ',' ','_','_','_','.',' ','.',' ','|','_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','_','_','_','_','_','_','_','_','_','_','_',' ',' ','.',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','.',' ','|','_','_','_','_','_','_','_','_','_','_','_','|'},
				{'|',' ',' ',' ',' ','S','w','a','m','p',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ','D','u','n','g','e','o','n',' ',' ','|',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','.',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ','.',' ',' ','_','_','_','_','_','_','_','_','_','_','_','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ','L','a','b',' ','X',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','.',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
				{'|','_','_','_','_','_','_','_','_','_','_','_','_','_','|','_','_','_','|','_','_','_','_','_','_','_','_','_','_','_','|','_','_','_','|','_','_','_','_','_','_','_','_','_','_','_','_','_','|'}
			};
	/**
	 * returns a string representation of the board
	 */
	public String toString(){
		String boardString = "";
		for(int k = 0; k < board[0].length; k++){
			boardString+=" _";
		}
		boardString += "\n";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(j ==0)boardString += "|";
				if(board[i][j] !=null){
					boardString += board[i][j].toChar();
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

	public String debugString(Tile.direction dir){
		String boardString = "";
		for(int k = 0; k < board[0].length; k++){
			boardString+=" _";
		}
		boardString += "\n";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(j ==0)boardString += "|";
				if(board[i][j] !=null && board[i][j].getNeighbour(dir)!= null){

					boardString += dir.name().charAt(0);
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
}