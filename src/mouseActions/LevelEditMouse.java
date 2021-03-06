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
package mouseActions;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import level.Board;
import level.Game;
import level.GridSquare;
import level.LevelMap;
import level.GridSquare.SquareType;

public class LevelEditMouse implements MouseListener {
    
    SquareType squareType;
    public LevelEditMouse(SquareType squareType){
	this.squareType = squareType;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	Point mouseAt = new Point(e.getX(),e.getY());
	for (int i = 0; i < Game.getGame().getGridWidth(); i += 1) {
		for (int j = 0; j < Game.getGame().getGridHeight(); j += 1) {
		    GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		    if(thisSquare.contains(mouseAt)){
			thisSquare.setSquareType(squareType);
		    }
		}
	}
	Game.getGame().getLevelMap().setBlocked();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

}
