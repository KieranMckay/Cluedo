package tests;

import static org.junit.Assert.*;
import model.Board;
import model.Player;
import model.Tile;
import model.Token;

import org.junit.Test;

public class TestBoard {

	@Test
	public void test1() {
		Board board = new Board("board.csv");
		Tile loc = board.getTile(10, 10);
		assert loc != null;
		Player player = new Player(1, new Token("@man",loc));
		System.out.println(board.toString());
		assert player.move(Tile.direction.SOUTH);
		System.out.println(board.toString());
	}

	@Test
	public void testMoving() {
		Board board = new Board("board.csv");
		Tile loc = board.getTile(9, 10);
		assert loc != null;
		Player player = new Player(1, new Token("@man",board.getFreeSpawn()));
		System.out.println(board.toString());
		assert player.move(Tile.direction.SOUTH);
		System.out.println(board.toString());
		assert player.move(Tile.direction.SOUTH);
		assert player.move(Tile.direction.SOUTH);
		assert player.move(Tile.direction.EAST);
		System.out.println(board.toString());
	}

	@Test
	public void testBoardConstruction(){ // TODO something not quite right about adjacency
		Board board = new Board("board.csv");
		System.out.println(board.debugString(Tile.direction.WEST));
		System.out.println(board.toString());
		System.out.println(board.debugString(Tile.direction.NORTH));
		System.out.println(board.toString());
		System.out.println(board.debugString(Tile.direction.SOUTH));
		System.out.println(board.toString());
		System.out.println(board.debugString(Tile.direction.EAST));
		System.out.println(board.toString());
	}

}
