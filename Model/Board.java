package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A class representing the Board in the game Cluedo
 * 
 * @author Johnny Denford, Kieran Mckay
 *
 */
public class Board {

	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;

	Tile board[][];
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
					System.out.println("room: "+ room[0]);
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
						// TODO
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
						board[i][j].addNeighbour(Tile.direction.WEST, board[i-1][j]);
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
		System.out.println(BOARD_WIDTH + " " + BOARD_HEIGHT);
		String line = sc.nextLine();
		String initialBoard[][] = new String[BOARD_HEIGHT][BOARD_WIDTH];

		for (int i = 0; i < initialBoard.length; i++) { // scan lines vertically
			String lineTiles[] = line.split(",");
			initialBoard[i] = lineTiles;
			line = sc.nextLine();
		}
		return initialBoard;
	}
	/**
	 * returns a string representation of the board
	 */
	public String toString(){
		String boardString = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] !=null){
					boardString += board[i][j].toChar();
				}else{
					boardString += "x";
				}
				boardString+= " ";
			}
			boardString += "\n";
		}
		return boardString;
	}

	/**
	 * fail parsing
	 * 
	 * @param row
	 * @param col
	 */
	private void fail(int row, int col, String data) {
		throw new RuntimeException("Parse Exception: " + data + "@ row: " + row
				+ " col: " + col);
	}
	
	/*
	public static void main(String [] args){
		System.out.println(new Board("board.csv").toString());;
	}
	*/
}
