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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import level.GridSquare.SquareType;

public class LevelMap {

    ArrayList<SpawnPoint> spawnPoints = new ArrayList<SpawnPoint>();
    ArrayList<Rectangle> hospitalGround = new ArrayList<Rectangle>();
    ArrayList<Rectangle> path = new ArrayList<Rectangle>();
    Point endOfPathPoint = new Point();
    
    public LevelMap(){
	
    }

    public ArrayList<SpawnPoint> getSpawnPoints() {
	return spawnPoints;
    }

    public ArrayList<Rectangle> getHospitalGround() {
	return hospitalGround;
    }

    public ArrayList<Rectangle> getPath() {
	return path;
    }

    public void setTestLevel() {
	hospitalGround.add(new Rectangle(2, 2, 30, 20));
	path.add(new Rectangle(0, 23, 17, 1));
	path.add(new Rectangle(16, 22, 1, 1));
	endOfPathPoint = new Point(16,21);
	SpawnPoint spawnPoint = new SpawnPoint(new Point(0, 23));
	spawnPoint.setRotation(2);
	spawnPoints.add(spawnPoint);
	setInitialLevel();
	setBlocked();
    }
    
    public Point getEndOfPathPoint() {
        return endOfPathPoint;
    }

    public void setEndOfPathPoint(Point endOfPathPoint) {
        this.endOfPathPoint = endOfPathPoint;
    }

    public void setInitialLevel(){
	int gs = Game.getGame().getGridSize();
	for (Rectangle rect : getHospitalGround()) {
	    Rectangle thisRect = new Rectangle(rect.x * gs, rect.y * gs, rect.width * gs, rect.height * gs);
	    for (int i = 0; i < Game.getGame().getGridWidth(); i += 1) {
		for (int j = 0; j < Game.getGame().getGridHeight(); j += 1) {
		    GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		    if (thisRect.intersects(thisSquare)) {
			thisSquare.setSquareType(SquareType.ground);
		    }
		}
	    }
	}

	for (Rectangle rect : getPath()) {
	    Rectangle thisRect = new Rectangle(rect.x * gs, rect.y * gs, rect.width * gs, rect.height * gs);
	    for (int i = 0; i < Game.getGame().getGridWidth(); i += 1) {
		for (int j = 0; j < Game.getGame().getGridHeight(); j += 1) {
		    GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		    if (thisRect.intersects(thisSquare)) {
			thisSquare.setSquareType(SquareType.path);
		    }
		}
	    }
	}
    }

    // TODO: need a method to size up a square for checking, and store
    // gridsquares without gridsize
    public void setBlocked() {
	for (int i = 0; i < Game.getGame().getGridWidth(); i += 1) {
		for (int j = 0; j < Game.getGame().getGridHeight(); j += 1) {
		    GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		    
		    switch(thisSquare.getSquareType()){
		    case path:
			thisSquare.setFull(false);
			break;
		    case ground:
			thisSquare.setFull(false);
			break;
		    case grass:
			thisSquare.setFull(true);
			break;
		    }
		}
	}
    }

    public void clearWalls() {
	for (int i = 0; i < Game.getGame().getGridWidth(); i += 1) {
	    for (int j = 0; j < Game.getGame().getGridHeight(); j += 1) {
		GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		thisSquare.setOutDown(true);
		thisSquare.setOutUp(true);
		thisSquare.setOutLeft(true);
		thisSquare.setOutRight(true);
	    }
	}
    }

    public void paint(Graphics g) {
	int gs = Game.getGame().getGridSize();

	Color floorColour = new Color(225, 225, 225);
	Color pathColour = new Color(81, 81, 81);
	Color greenGrass = new Color(0, 153, 0);
	for (int i = 0; i < Game.getGame().getGridWidth(); i += 1) {
		for (int j = 0; j < Game.getGame().getGridHeight(); j += 1) {
		    GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		    
		    switch(thisSquare.getSquareType()){
		    case path:
			g.setColor(pathColour);
			break;
		    case ground:
			g.setColor(floorColour);
			break;
		    case grass:
			g.setColor(greenGrass);
			break;
		    }
		    g.fillRect(thisSquare.x, thisSquare.y, thisSquare.width, thisSquare.height);
		}
	}
    }
}
