package Control;

import Model.Player;
import View.Menu;

/**
 * A basic class for handling a players move
 * @author Johnny
 *
 */
public class Turn {
	
	Player player;
	Menu menu;
	public Turn(Player player, Menu menu) {
		super();
		this.player = player;
		this.menu = menu;
	}
	
}
