package pathFinding;

import level.Board;
import level.Game;

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
