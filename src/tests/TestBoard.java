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
		Player player = new Player(new Token("Xman",loc));
		System.out.println(board.toString());
		assert player.move(Tile.direction.SOUTH);
		System.out.println(board.toString());
	}

}
