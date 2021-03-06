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
package GridObjects.people;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import diseases.Disease;

import level.Board;
import level.Game;
import level.GridSquare.SquareType;
import pathFinding.Path;
import GridObjects.GridObject;
import GridObjects.Buildings.Room;
import GridObjects.Buildings.RoomFactory;
import GridObjects.Buildings.RoomFactory.RoomType;
import GridObjects.items.ItemFactory;
import GridObjects.items.UsableItem;
import GridObjects.items.ItemFactory.ItemType;

/**
 * The Class Person.
 */
public class Person extends GridObject {

    /** The grid object colour. */
    Color gridObjectColour = Color.orange;

    /** The next target. */
    Point nextTarget;

    /** The next point. */
    Point nextPoint;

    /** The current path. */
    Path currentPath = new Path();

    /** The person no. */
    Integer personNo = null;

    /** The my desease. */
    Disease myDesease = new Disease();

    /** The spawned boolean. */
    Boolean spawned = false;

    /** The remove boolean. */
    Boolean remove = false;

    /** The wandering. */
    Boolean wandering = false;

    /**
     * Gets the person no.
     * 
     * @return the person no
     */
    public Integer getPersonNo() {
	return personNo;
    }

    /**
     * Instantiates a new person.
     * 
     * @param topLeftEntered
     *            the top left entered
     * @param number
     *            the number
     */
    public Person(Point topLeftEntered, Integer number) {
	topLeftPoint = topLeftEntered;
	personNo = number;
	nextPoint = topLeftEntered;
	// This line sets the standard desease order.
	myDesease.init();
    }

    /**
     * Sets the wandering.
     * 
     * @param wandering
     *            the new wandering
     */
    public void setWandering(Boolean wandering) {
	this.wandering = wandering;
    }

    /**
     * Gets the next point.
     * 
     * @return the next point
     */
    public Point getNextPoint() {
	return nextPoint;
    }

    /**
     * Sets the next point.
     * 
     * @param nextPoint
     *            the new next point
     */
    public void setNextPoint(Point nextPoint) {
	this.nextPoint = nextPoint;
    }

    /**
     * Gets the current path.
     * 
     * @return the current path
     */
    public Path getCurrentPath() {
	return currentPath;
    }

    /**
     * Sets the current path.
     * 
     * @param currentPath
     *            the new current path
     */
    public void setCurrentPath(Path currentPath) {
	this.currentPath = currentPath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see GridObjects.GridObject#getColor()
     */
    public Color getColor() {
	return gridObjectColour;
    }

    /**
     * Gets the next target.
     * 
     * @return the next target
     */
    public Point getNextTarget() {
	return nextTarget;
    }

    /**
     * Sets the next target.
     * 
     * @param nextTarget
     *            the new next target
     */
    public void setNextTarget(Point nextTarget) {
	this.nextTarget = nextTarget;
	recalcPath();
    }

    /**
     * This method is called for each person on a timer.
     */
    public void tick() {
	if (spawned) {
	    //System.out.println(" this tick its " + !this.getTopLeftPoint().equals(this.getNextPoint()));
	    if (!this.getTopLeftPoint().equals(this.getNextPoint())) {
		
		// No code needed, as moveTowardsNextSquare is outside of the if
		// statement.
	    } else {
		// Inside this if, means person is on a square. Not part way
		// between squares.
		if (!this.getTopLeftPoint().equals(
			Game.getGame().gameToScreen(this.getNextTarget())) && currentPath.getLength()>=1) {
		    // Inside this if, means person is on a square, but the
		    // square is not the end target square.
		    setNextPoint(new Point(currentPath.getStep(0).getX() * Game.getGame().getGridSize(), 
                            currentPath.getStep(0).getY()* Game.getGame().getGridSize()));
		    this.currentPath.nextStep();
		} else {
		    // Inside this else, means person has reached the end target
		    // square!
		    checkForNewTargets();
		}
	    }
	} else{
	    timeToSpawn();
	}
	//this.setNextPoint(new Point(currentPath.getStep(0).getX(),currentPath.getStep(0).getY()));

	moveTowardsNextSquare();
    }

    /**
     * If a person is on a the square which is its target,
     */
    private void checkForNewTargets() {
	if (!this.myDesease.isEndOrder()) {
	    updateNextTarget();
	} else {
	    timeToUnSpawn();
	}
    }

    /**
     * If a person has not spawned, this method will be called each tick, and it
     * will deal with spawning.
     */
    private void timeToSpawn() {
	// Code for working out spawnFrom and spawnTo...
	System.out.println("time to spanw :)");
	Point spawnTo = new Point(Game.getGame().getLevelMap().getSpawnPoints()
		.get(0).getSpawnTo());
	spawnTo = new Point(Game.getGame().gameToScreen(spawnTo));
	Point spawnFrom = new Point(Game.getGame().getLevelMap()
		.getSpawnPoints().get(0).getSpawnFrom());
	spawnFrom = new Point(Game.getGame().gameToScreen(spawnFrom));
	// end

	if (getTopLeftPoint().equals(spawnFrom)) {
	    setNextPoint(spawnTo);
	} else if (getTopLeftPoint().equals(spawnTo)) {
	    setNextTarget(Game.getGame().getLevelMap().getEndOfPathPoint());
	    spawned = true;
	    this.currentPath.nextStep();
	}
    }

    /**
     * A sad method :( Previously named timeToDie, however die might be used
     * elsewhere. This method deals with the reverse of spawning, which is
     * un-spawning?
     */
    private void timeToUnSpawn() {
	// Code for working out spawnFrom and spawnTo...
	Point spawnTo = new Point(Game.getGame().getLevelMap().getSpawnPoints()
		.get(0).getSpawnTo());
	spawnTo = new Point(Game.getGame().gameToScreen(spawnTo));
	Point spawnFrom = new Point(Game.getGame().getLevelMap()
		.getSpawnPoints().get(0).getSpawnFrom());
	spawnFrom = new Point(Game.getGame().gameToScreen(spawnFrom));
	// end

	if (!this.getTopLeftPoint().equals(spawnTo) && !this.getTopLeftPoint().equals(spawnFrom)) {
	    setNextTarget(Game.getGame().screenToGame(spawnTo));
	} else {
	    setNextPoint(spawnFrom);
	    if (getTopLeftPoint().equals(spawnFrom)) {
		this.remove = true;
	    }
	}
	// if above else if is false, no action is needed.
    }

    /**
     * Move towards next square.
     */
    public void moveTowardsNextSquare() {

	if (!nextPoint.equals(getTopLeftPoint())) {
	    // System.out.println("next point is not topLeft");
	    if (nextPoint.y < getTopLeftPoint().y) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX(),
			(int) getTopLeftPoint().getY() - 5));
	    }
	    if (nextPoint.y > getTopLeftPoint().y) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX(),
			(int) getTopLeftPoint().getY() + 5));
	    }
	    if (nextPoint.x < getTopLeftPoint().x) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX() - 5,
			(int) getTopLeftPoint().getY()));
	    }
	    if (nextPoint.x > getTopLeftPoint().x) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX() + 5,
			(int) getTopLeftPoint().getY()));
	    }
	}
    }

    /**
     * Check targets. Take the next item in the list, and make the person go to
     * the room or item.
     */
    public void updateNextTarget() {
	String toCreate = (myDesease.getOrder().get(myDesease.getOnOrder()));
	GridObject thisRoom = null, thisItem = null;
	try {
	    thisRoom = RoomFactory.createRoom(RoomType.valueOf(toCreate));
	} catch (Exception exception) {
	    thisItem = ItemFactory.createItem(ItemType.valueOf(toCreate));
	} finally {
	    if (thisRoom != null) {
		goTo(thisRoom);
	    } else if (thisItem != null) {
		goTo(thisItem);
	    }
	}
    }

    /**
     * Recalculates the path.
     */
    public void recalcPath() {
	System.out.println("target is " + getNextTarget() + " p is at "
		+ getTopLeftPoint());
	Path path = Game.getGame().getFinder().findPath(
		getTopLeftPoint().x / Game.getGame().getGridSize(),
		getTopLeftPoint().y / Game.getGame().getGridSize(),
		getNextTarget().x, getNextTarget().y);
	setCurrentPath(path);
    }

    /**
     * Gets the remove boolean.
     * 
     * @return remove
     */
    public Boolean getRemove() {
	return remove;
    }

    /**
     * Move up.
     */
    public void moveUp() {
	int x = nextPoint.x;
	int y = nextPoint.y - Game.getGame().getGridSize();
	nextPoint = new Point(x, y);
    }

    /**
     * Move down.
     */
    public void moveDown() {
	int x = nextPoint.x;
	int y = nextPoint.y + Game.getGame().getGridSize();
	nextPoint = new Point(x, y);
    }

    /**
     * Move left.
     */
    public void moveLeft() {
	int x = nextPoint.x - Game.getGame().getGridSize();
	int y = nextPoint.y;
	nextPoint = new Point(x, y);
    }

    /**
     * Move right.
     */
    public void moveRight() {
	int x = nextPoint.x + Game.getGame().getGridSize();
	int y = nextPoint.y;
	nextPoint = new Point(x, y);
    }

    /**
     * Go to a room or usable item
     * 
     * @param dest
     *            the destination object, ie room or usable item.
     */
    public void goTo(GridObject dest) {
	System.out.println(dest);
	Boolean found = false;
	if (dest instanceof Room) {
	    for (Room room : Game.getGame().getRooms()) {
		if (room.getClass().isInstance(dest)) {
		    found = true;
		    System.out.println("Room " + dest.getClass() + " found");
		    setNextTarget(new Point(room.getDoorPoint().x, room
			    .getDoorPoint().y));
		}
	    }
	} else if (dest instanceof UsableItem) {
	    for (UsableItem item : Game.getGame().getUsableItems()) {
		if (item.getClass().isInstance(dest)) {
		    found = true;
		    System.out.println("UsableItem " + dest.getClass()
			    + " found");
		    setNextTarget(new Point(item.getPointOfUsage().x, item
			    .getPointOfUsage().y));
		}
	    }
	} else {
	    throw new IllegalArgumentException("The detination " + dest
		    + " does not have a usage point");
	}
	if(found){
	    this.myDesease.nextInOrder();
	    this.moveTowardsNextSquare();
	} else {
	    wandering = true;
	    wander();
	}
    }

    /**
     * Wander. Called when a person has no location, and just wanders around.
     */
    public void wander() {
	System.out.println("wander called---------" + wandering);
	if (wandering) {
	    Point personAt = this.getTopLeftPoint();
	    personAt = Game.getGame().screenToGame(personAt);
	    LinkedList<Point> thisSet = new LinkedList<Point>();
	    for (int x = personAt.x - 2; x < personAt.x + 3; x++) {
		for (int y = personAt.y - 2; y < personAt.y + 3; y++) {
		    if (!(x == personAt.x && y == personAt.y)) {
			if (x > 0
				&& y > 0
				&& !Board.getBoard().getSquare(x, y)
					.getSquareType()
					.equals(SquareType.path)) {
			    if (!Game.getGame().getMap().blocked(x, y)) {
				thisSet.add(new Point(x, y));
			    }
			}
		    }
		}
	    }
	    Collections.shuffle(thisSet);
	    // Random random = new Random();
	    // Integer randomNo = random.nextInt(thisSet.size());
	    setNextTarget(thisSet.get(0));
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see GridObjects.GridObject#paint(java.awt.Graphics)
     */
    public void paint(Graphics graphic) {
	int gridSize = Game.getGame().getGridSize();
	graphic.setColor(this.getColor());
	graphic.fillOval(this.getX(), this.getY(), gridSize, gridSize);
	graphic.drawString(this.myDesease.getName(), this.getX() - 5, this
		.getY() - 5);

    }

}
