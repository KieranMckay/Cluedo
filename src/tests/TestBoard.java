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
		Tile loc = board.getTile(10, 0);
		assert loc != null;
		Player player = new Player(new Token("Xman",loc));
		System.out.println(board.toString());
		assert(board.moveToken(player.getToken(), Tile.direction.SOUTH));
		System.out.println(board.toString());
	}

}
