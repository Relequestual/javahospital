/*******************************************************************************
 * Copyright (c) 2009, Kevin Glass
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *     * Neither the name of CokeAndCode nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package pathFinding;

import level.Board;
import level.Game;

/**
 * The data map from our example game. This holds the state and context of each tile
 * on the map. It also implements the interface required by the path finder. It's implementation
 * of the path finder related methods add specific handling for the types of units
 * and terrain in the example game.
 * 
 * The above comments and original class was by Kevin Glass, however 
 * I rewrote this class for my purposes.
 * 
 * @author Ben Hutton
 */


public class GameMap implements TileBasedMap {
    static int gridSize = Game.getGame().getGridSize();
    static int gridHeight = Game.getGame().getGridHeight();
    static int gridWidth = Game.getGame().getGridWidth();

    public GameMap() {
	// No set up needed as the blocked squares will be
	// returned dynamically using Board class
    }

    // This method is used to check to see if the target square is blocked
    // While the following method is used to check the path, as it requires the
    // current square
    public boolean blocked(int x, int y) {
	System.out.println("checking to see if final square is blocked");
	return Board.getBoard().getSquare(x, y).getFull();
    }

    public boolean blocked(int sx, int sy, int x, int y) {
	System.out.println("checking block movement from " + sx + "," + sy + " to " + x + "," + y);
	// System.out.println("is blocked? " + x + " " + y);
	boolean blocked = false;
	blocked = Board.getBoard().getSquare(x, y).getFull();
	if (!blocked) {
	    if (sx == x) {
		if (sy > y) {
		    blocked = !Board.getBoard().getSquare(sx, sy).isOutUp();
		} else if (sy < y) {
		    blocked = !Board.getBoard().getSquare(sx, sy).isOutDown();
		}
	    } else {
		if (sy == y) {
		    if (sx > x) {
			blocked = !Board.getBoard().getSquare(sx, sy).isOutRight();
		    } else if (sx < x) {
			blocked = !Board.getBoard().getSquare(sx, sy).isOutLeft();
		    }
		}
	    }
	}
	System.out.println("blocked result is " + blocked);
	return blocked;
    }

    public float getCost(int sx, int sy, int tx, int ty) {
	return 1;
    }

    public int getHeightInTiles() {
	return gridHeight;
    }

    public int getWidthInTiles() {
	return gridWidth;
    }

    // This method is used for setting a visited boolean for debugging.
    public void pathFinderVisited(int x, int y) {
	Board.getBoard().getSquare(x, y).setVisited(true);
    }

}
