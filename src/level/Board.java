/*******************************************************************************
 * Copyright (c) 2009 Ben Hutton
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/
package level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

public class Board {
    private static Board ref;
    private GridSquare[][] gridSquares;
    private int gridSize = Game.getGame().getGridSize();
    private int gridHeight = Game.getGame().getGridHeight();
    private int gridWidth = Game.getGame().getGridWidth();

    private Board() {
	gridSquares = new GridSquare[Game.getGame().getPanelWidth() / Game.getGame().getGridSize()][Game.getGame().getPanelHeight()/ Game.getGame().getGridSize()];

	for (int i = 0; i < Game.getGame().getPanelWidth() / Game.getGame().getGridSize(); i++) {
	    for (int j = 0; j < Game.getGame().getPanelHeight() / Game.getGame().getGridSize(); j++) {
		GridSquare thisRect = new GridSquare((i * gridSize), (j * gridSize), gridSize, gridSize);
		//System.out.println("gridsquares " + i + " " + j + " " + thisRect);
		gridSquares[i][j] = thisRect;
	    }
	}
	System.out.println(gridSquares.length);
    }

    public static synchronized Board getBoard() {
	if (ref == null)
	    // it's ok, we can call this constructor
	    ref = new Board();
	return ref;
    }

    public Object clone() throws CloneNotSupportedException {
	throw new CloneNotSupportedException();
	// that'll teach 'em. prevents clone method.
    }

    public GridSquare getSquare(int x, int y) {
	// System.out.println("no of squares " + gridSquares.length);
	return gridSquares[x][y];
    }

    // This method returns an arraylist of the gridsquares opposed to a 2d
    // array.
    // Not yet used (8/8/09), but will be implemented soon!
    // TODO: implement this! :)
    public ArrayList<GridSquare> getAllSquares() {
	ArrayList<GridSquare> thisList = new ArrayList<GridSquare>();
	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		thisList.add(Board.getBoard().getSquare(i, j));
	    }
	}
	return thisList;
    }
}
