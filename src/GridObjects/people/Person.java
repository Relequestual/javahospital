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
import level.LevelMap;
import level.GridSquare.SquareType;
import pathFinding.GameMap;
import pathFinding.Path;
import GridObjects.GridObject;
import GridObjects.Buildings.Room;
import GridObjects.Buildings.RoomFactory;
import GridObjects.Buildings.RoomFactory.RoomType;
import GridObjects.items.ItemFactory;
import GridObjects.items.UsableItem;
import GridObjects.items.ItemFactory.ItemType;

public class Person extends GridObject {
    Color gridObjectColour = Color.orange;
    Point nextTarget;
    Point nextPoint;
    Path currentPath = new Path();
    Integer personNo = null;
    Disease myDesease = new Disease();
    Boolean spawned = false;
    Boolean remove = false;
    Boolean wandering = false;

    public Integer getPersonNo() {
	return personNo;
    }

    public Person(Point topLeftEntered, Integer number) {
	topLeftPoint = topLeftEntered;
	personNo = number;
	nextPoint = topLeftEntered;
	// This line sets the standard desease order.
	myDesease.init();
    }

    public void setWandering(Boolean wandering) {
        this.wandering = wandering;
    }

    public Point getNextPoint() {
	return nextPoint;
    }

    public void setNextPoint(Point nextPoint) {
	this.nextPoint = nextPoint;
    }

    public Path getCurrentPath() {
	return currentPath;
    }

    public void setCurrentPath(Path currentPath) {
	this.currentPath = currentPath;
    }

    public Color getColor() {
	return gridObjectColour;
    }

    public Point getNextTarget() {
	return nextTarget;
    }

    public void setNextTarget(Point nextTarget) {
	this.nextTarget = nextTarget;
	recalcPath();
    }

    public void moveTowardsNextSquare() {

	System.out.println("is at end order? " + myDesease.isEndOrder());
	if (!myDesease.isEndOrder()) {
	    checkTargets();
	} else if (!wandering) {
	    System.out.println("On END ORDER!!!");
	    if (getTopLeftPoint().equals(Game.getGame().getLevelMap().getSpawnPoints().get(0).getSpawnFrom())) {
		// System.out.println();
		// System.out.println("Person should now die");
		// Game.getGame().getPeople().remove(this);
	    }
	    if (currentPath == null) {
		// Point spawnFrom = new
		// Point(LevelMap.getSpawnPoints().get(0).getSpawnFrom());
		// setNextPoint(new
		// Point(spawnFrom.x*Game.getGame().getGridSize(),spawnFrom.y*Game.getGame().getGridSize()));
	    } else if (nextPoint.equals(getTopLeftPoint())) {
		if (currentPath.getLength() < 1) {
		    setNextTarget(new Point(Game.getGame().getLevelMap().getSpawnPoints().get(0).getSpawnTo()));
		}
	    }
	}
	updateNextPoint();

	if (!nextPoint.equals(getTopLeftPoint())) {
	    // System.out.println("next point is not topLeft");
	    if (nextPoint.y < getTopLeftPoint().y) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX(), (int) getTopLeftPoint().getY() - 5));
	    }
	    if (nextPoint.y > getTopLeftPoint().y) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX(), (int) getTopLeftPoint().getY() + 5));
	    }
	    if (nextPoint.x < getTopLeftPoint().x) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX() - 5, (int) getTopLeftPoint().getY()));
	    }
	    if (nextPoint.x > getTopLeftPoint().x) {
		setTopLeftPoint(new Point((int) getTopLeftPoint().getX() + 5, (int) getTopLeftPoint().getY()));
	    }
	}
    }

    public void checkTargets() {
	if (!myDesease.isEndOrder()) {
	    System.out.println(currentPath.getLength() + " " + spawned + " " + myDesease.isEndOrder());
	    if (currentPath.getLength() < 1 && spawned) {
		System.out.println("targets being checked");
		String toCreate = (myDesease.getOrder().get(myDesease.getOnOrder()));
		GridObject thisRoom = null, thisItem = null;
		try {
		    thisRoom = RoomFactory.createRoom(RoomType.valueOf(toCreate));
		} catch (Exception exception) {
		    thisItem = ItemFactory.createItem(ItemType.valueOf(toCreate));
		} finally {

		    if (thisRoom != null) {
			goTo(thisRoom);
		    } else if(thisItem != null) {
			goTo(thisItem);
		    }
		}
	    } else {
		if (nextPoint.equals(getTopLeftPoint())) {
		    spawned = true;
		}
	    }
	}
    }

    public void updateNextPoint() {
	// System.out.println("is at target " + (getNextTarget() ==
	// getTopLeftPoint()));
	if (nextTarget != getTopLeftPoint() || nextTarget == null) {
	    // System.out.println("There is a next target! :)" + nextTarget +
	    // " currently at " + getTopLeftPoint());

	    // System.out.println("next step top left " + nextPoint + " " +
	    // getTopLeftPoint() + "path? " + (currentPath.getLength() > 0));
	    if (nextPoint.equals(getTopLeftPoint())) {
		Point spawnTo = new Point(Game.getGame().getLevelMap().getSpawnPoints().get(0).getSpawnTo());
		spawnTo = new Point(spawnTo.x * Game.getGame().getGridSize(), spawnTo.y * Game.getGame().getGridSize());
		Point spawnFrom = new Point(Game.getGame().getLevelMap().getSpawnPoints().get(0).getSpawnFrom());
		spawnFrom = new Point(spawnFrom.x * Game.getGame().getGridSize(), spawnFrom.y
			* Game.getGame().getGridSize());
		System.out.println("end check " + getTopLeftPoint() + " " + spawnTo);

		if (this.getTopLeftPoint().equals(spawnTo) && myDesease.isEndOrder()) {
		    setNextPoint(spawnFrom);
		} else if (this.getTopLeftPoint().equals(spawnFrom) && myDesease.isEndOrder()) {
		    System.out.println();
		    System.out.println("Person should now die");
		    remove = true;
		} else if (currentPath.getLength() > 0) {
		    // System.out.println("into the next point assign");
		    // System.out.println("next point will be " + new
		    // Point(currentPath.getStep(1).getX(),currentPath.getStep(1).getY()));
		    setNextPoint(new Point(currentPath.getStep(0).getX() * Game.getGame().getGridSize(), currentPath
			    .getStep(0).getY()
			    * Game.getGame().getGridSize()));
		    currentPath.nextStep();
		} else if (wandering) {
		    wander();
		}
	    }
	}
    }

    public void recalcPath() {
	System.out.println("target is " + getNextTarget() + " p is at " + getTopLeftPoint());
	Path path = Game.getGame().getFinder().findPath(getTopLeftPoint().x / Game.getGame().getGridSize(),
		getTopLeftPoint().y / Game.getGame().getGridSize(), getNextTarget().x, getNextTarget().y);
	setCurrentPath(path);
    }

    public Boolean getRemove() {
	return remove;
    }

    public void moveUp() {
	int x = nextPoint.x;
	int y = nextPoint.y - Game.getGame().getGridSize();
	nextPoint = new Point(x, y);
    }

    public void moveDown() {
	int x = nextPoint.x;
	int y = nextPoint.y + Game.getGame().getGridSize();
	nextPoint = new Point(x, y);
    }

    public void moveLeft() {
	int x = nextPoint.x - Game.getGame().getGridSize();
	int y = nextPoint.y;
	nextPoint = new Point(x, y);
    }

    public void moveRight() {
	int x = nextPoint.x + Game.getGame().getGridSize();
	int y = nextPoint.y;
	nextPoint = new Point(x, y);
    }

    public void goTo(GridObject dest) {
	if (dest instanceof Room) {
	    if (Game.getGame().getRooms().size() == 0) {
		wander();
	    } else {
		for (Room room : Game.getGame().getRooms()) {
		    if (room.getClass().isInstance(dest)) {
			wandering = false;
			System.out.println("Room " + dest.getClass() + " found");
			setNextTarget(new Point(room.getDoorPoint().x, room.getDoorPoint().y));
		    }
		}
		myDesease.nextInOrder();
	    }
	} else if (dest instanceof UsableItem) {
	    if (Game.getGame().getUsableItems().size() == 0) {
		wander();
	    } else {
		for (UsableItem item : Game.getGame().getUsableItems()) {
		    if (item.getClass().isInstance(dest)) {
			wandering = false;
			System.out.println("UsableItem " + dest.getClass() + " found");
			setNextTarget(new Point(item.getPointOfUsage().x, item.getPointOfUsage().y));
		    }
		}
		myDesease.nextInOrder();
	    }
	} else {
	    throw new IllegalArgumentException("The detination " + dest + " does not have a usage point");
	}
    }

    public void wander() {
	System.out.println("wander called---------" + wandering);
	if (wandering) {
	    Point personAt = this.getTopLeftPoint();
	    personAt = Game.getGame().screenToGame(personAt);
	    LinkedList<Point> thisSet = new LinkedList<Point>();
	    for (int x = personAt.x - 2; x < personAt.x + 3; x++) {
		for (int y = personAt.y - 2; y < personAt.y + 3; y++) {
		    if (!(x == personAt.x) && !(y == personAt.y)) {
			if (x > 0 && y > 0 && !Board.getBoard().getSquare(x, y).getSquareType().equals(SquareType.path)) {
			    if (!Game.getGame().getMap().blocked(x, y)) {
				thisSet.add(new Point(x, y));
			    }
			}
		    }
		}
	    }
	    Collections.shuffle(thisSet);
	    Random random = new Random();
	    Integer randomNo = random.nextInt(thisSet.size());
	    setNextTarget(thisSet.get(randomNo));
	} else {
	    if (Game.getGame().screenToGame(this.getTopLeftPoint()).equals(
		    Game.getGame().getLevelMap().getEndOfPathPoint()) || this.myDesease.getOnOrder()!=0) {
		wandering = true;
	    } else if (this.getNextTarget() != Game.getGame().getLevelMap().getEndOfPathPoint() && this.myDesease.getOnOrder()==0) {
		setNextTarget(Game.getGame().getLevelMap().getEndOfPathPoint());
	    }
	}
    }

    public void paint(Graphics graphic) {
	int gridSize = Game.getGame().getGridSize();
	graphic.setColor(this.getColor());
	graphic.fillOval(this.getX(), this.getY(), gridSize, gridSize);
	graphic.drawString(this.myDesease.getName(), this.getX() - 5, this.getY() - 5);

    }

}