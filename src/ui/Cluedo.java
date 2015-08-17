package ui;

import java.util.*;
import java.io.*;

public class Cluedo {
	private final int width;
	private final int height;

	public Cluedo(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Get the board width.
	 * @return
	 */
	public int width() {
		return width;
	}

	/**
	 * Get the board height.
	 * @return
	 */
	public int height() {
		return height;
	}

	/**
	 * The UID is a unique identifier for all characters in the game. This is
	 * required in order to synchronise the movements of different players
	 * across boards.
	 */
	private static int uid = 0;

	public boolean canMoveUp(MovingCharacter p) {
		int realX = p.realX();
		int realY = p.realY();
		realY -= p.speed();
		int ny = realY / 30;
		int nx = (realX+15)/30;
		return !isWall(nx,ny);
	}

	public boolean canMoveDown(MovingCharacter p) {
		int realX = p.realX();
		int realY = p.realY();
		realY += 5;
		int ny = realY / 30;
		if(realY % 30 != 0) { ny++; }
		int nx = (realX+15)/30;
		return !isWall(nx,ny);
	}

	public boolean canMoveLeft(MovingCharacter p) {
		int realX = p.realX();
		int realY = p.realY();
		realX -= 5;
		int nx = realX / 30;
		int ny = (realY+15)/30;
		return !isWall(nx,ny);
	}

	public boolean canMoveRight(MovingCharacter p) {
		int realX = p.realX();
		int realY = p.realY();
		realX += 5;
		int nx = realX / 30;
		if(realX % 30 != 0) { nx++; }
		int ny = (realY+15)/30;
		return !isWall(nx,ny);
	}

	/**
	 * The clock tick is essentially a clock trigger, which allows the board to
	 * update the current state. The frequency with which this is called
	 * determines the rate at which the game state is updated.
	 *
	 * @return
	 */
	public synchronized void clockTick() {
		if (state != PLAYING && state != GAMEOVER) {
			return; // do nothing unless the game is active.
		}

		ArrayList<Character> ghosts = new ArrayList<Character>();

		int nplayers = 0;
		for(int i=0;i!=characters.size();++i) {
			Character p = characters.get(i);
			p.tick(this);

			// reread p, since it might be gone now ...
			p = characters.get(i);
			if(p == null) {
				// dead character encountered, remove it.
				characters.remove(i--);
				continue;
			}
			if (p instanceof Ghost) {
				ghosts.add(p);
			}
		}

		// Now, perform collision detection to see if any PACMEN have collided
		// with ghosts.
		for (int i = 0; i != characters.size(); ++i) {
			Character c = characters.get(i);
			int px = (c.realX() + 15) / 30;
			int py = (c.realY() + 15) / 30;
			if (c instanceof Pacman) {
				Pacman p = (Pacman) c;
				if(!p.isDead()) { nplayers++; }
				if(p.isDead() || p.isDying()) { continue; }
				for (Character g : ghosts) {
					int gx = (g.realX() + 15) / 30;
					int gy = (g.realY() + 15) / 30;
					if (px == gx && py == gy) {
						// pacman and ghost have collided ...
						// So, replace pacman with disappearing character
						p.markAsDying();
					}
				}
			}
		}

		if (nplayers == 0) {
			state = GAMEOVER;
		} else if(nPillsRemaining == 0) {
			state = GAMEWON;
		}
	}
}
