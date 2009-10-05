package GridObjects.Buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import level.Board;
import level.Game;
import GridObjects.GridObject;

public class Room extends GridObject {
    Point door;
    int doorRotation;
    int nextStageTime;
    Color gridObjectColour = new Color(206, 255, 155);

    public Color getColor() {
	return gridObjectColour;
    }

    public Room(Point topLeftEntered, int widthEntered, int heightEntered) {
	this.topLeftPoint = topLeftEntered;
	this.width = widthEntered;
	this.height = heightEntered;
    }

    public Room() {
	// TODO Auto-generated constructor stub
    }

    public int getDoorRotation() {
        return doorRotation;
    }

    public void setDoorRotation(int doorRotation) {
        this.doorRotation = doorRotation;
    }

    public void setDoorPoint(Point point) {
	door = point;
    }

    public Point getDoorPoint() {
	return door;
    }
    
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

    public void paint(Graphics graphic) {
	int gridSize = Game.getGame().getGridSize();
	graphic.setColor(this.getColor());
	graphic.fillRect(this.getX()*gridSize, this.getY()*gridSize, gridSize * this.getWidth(), gridSize * this.getHeight());
    }

}
