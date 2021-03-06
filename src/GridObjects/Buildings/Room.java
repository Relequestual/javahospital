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
package GridObjects.Buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import level.Board;
import level.Game;
import GridObjects.GridObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Room.
 */
public class Room extends GridObject {
    
    /** The door. */
    Point door;
    
    /** The door rotation. */
    int doorRotation;
    
    /** The next stage time. */
    int nextStageTime;
    
    /** The grid object colour. */
    Color gridObjectColour = new Color(206, 255, 155);

    /* (non-Javadoc)
     * @see GridObjects.GridObject#getColor()
     */
    public Color getColor() {
	return gridObjectColour;
    }

    /**
     * Instantiates a new room.
     * 
     * @param topLeftEntered the top left entered
     * @param widthEntered the width entered
     * @param heightEntered the height entered
     */
    public Room(Point topLeftEntered, int widthEntered, int heightEntered) {
	this.topLeftPoint = topLeftEntered;
	this.width = widthEntered;
	this.height = heightEntered;
    }

    /**
     * Instantiates a new room.
     */
    public Room() {
	// TODO Auto-generated constructor stub
    }

    /**
     * Gets the door rotation.
     * 
     * @return the door rotation
     */
    public int getDoorRotation() {
        return doorRotation;
    }

    /**
     * Sets the door rotation.
     * 
     * @param doorRotation the new door rotation
     */
    public void setDoorRotation(int doorRotation) {
        this.doorRotation = doorRotation;
    }

    /**
     * Sets the door point.
     * 
     * @param point the new door point
     */
    public void setDoorPoint(Point point) {
	door = point;
    }

    /**
     * Gets the door point.
     * 
     * @return the door point
     */
    public Point getDoorPoint() {
	return door;
    }
    
    /**
     * Builds the walls.
     */
    public void buildWalls(){
	
	Point topLeft = new Point(getTopLeftPoint());
	for (int i = topLeft.x; i < topLeft.x + getWidth(); i++) {
	    Board.getBoard().getSquare(i, topLeft.y - 1).setOutDown(false);
	    Board.getBoard().getSquare(i, topLeft.y).setOutUp(false);
	    Board.getBoard().getSquare(i, topLeft.y + getHeight()).setOutUp(false);
	    Board.getBoard().getSquare(i, topLeft.y + getHeight() - 1).setOutDown(false);
	}
	
	for (int i = topLeft.y; i < topLeft.y + getHeight(); i++) {
	    Board.getBoard().getSquare(topLeft.x - 1, i).setOutLeft(false);
	    Board.getBoard().getSquare(topLeft.x, i).setOutRight(false);
	    Board.getBoard().getSquare(topLeft.x + getWidth(), i).setOutRight(false);
	    Board.getBoard().getSquare(topLeft.x + getWidth() - 1, i).setOutLeft(false);
	}
    }
    
    /**
     * Builds the door.
     */
    public void buildDoor(){
	Point doorEnterence = new Point();
	switch(doorRotation){
	case 1:doorEnterence = new Point(door.x,door.y+1);break;
	case 2:doorEnterence = new Point(door.x-1,door.y);break;
	case 3:doorEnterence = new Point(door.x,door.y-1);break;
	case 4:doorEnterence = new Point(door.x+1,door.y);break;
	default: System.err.println("Door placment error. rotation " + doorRotation + " is invalid");
	}
	
	Board.getBoard().getSquare(door.x, door.y).setOutDown(true);
	Board.getBoard().getSquare(door.x, door.y).setOutUp(true);
	Board.getBoard().getSquare(door.x, door.y).setOutLeft(true);
	Board.getBoard().getSquare(door.x, door.y).setOutRight(true);
	Board.getBoard().getSquare(doorEnterence.x, doorEnterence.y).setOutDown(true);
	Board.getBoard().getSquare(doorEnterence.x, doorEnterence.y).setOutUp(true);
	Board.getBoard().getSquare(doorEnterence.x, doorEnterence.y).setOutLeft(true);
	Board.getBoard().getSquare(doorEnterence.x, doorEnterence.y).setOutRight(true);
    }

    /* (non-Javadoc)
     * @see GridObjects.GridObject#paint(java.awt.Graphics)
     */
    public void paint(Graphics graphic) {
	int gridSize = Game.getGame().getGridSize();
	graphic.setColor(this.getColor());
	graphic.fillRect(this.getX()*gridSize, this.getY()*gridSize, gridSize * this.getWidth(), gridSize * this.getHeight());
    }

}
