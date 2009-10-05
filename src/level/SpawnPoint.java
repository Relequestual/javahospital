package level;

import java.awt.Graphics;
import java.awt.Point;

import GridObjects.GridObject;

public class SpawnPoint {

    Point spawnTo;
    private int rotation;

    public SpawnPoint(Point spawnTo) {
	this.spawnTo = spawnTo;
    }

    public int getRotation() {
	return rotation;
    }

    public void setRotation(int rotation) {
	this.rotation = rotation;
    }

    public Point getSpawnFrom() {
	Point spawnFrom = null;
	System.out.println("spawnTo is " + spawnTo);
	System.out.println("rotation is " + rotation);
	switch (rotation) {
	case 1:
	    spawnFrom = new Point(spawnTo.x, spawnTo.y + 1);
	    break;
	case 2:
	    spawnFrom = new Point((spawnTo.x - 1), spawnTo.y);
	    break;
	case 3:
	    spawnFrom = new Point(spawnTo.x, spawnTo.y - 1);
	    break;
	case 4:
	    spawnFrom = new Point(spawnTo.x + 1, spawnTo.y);
	    break;
	}
	System.out.println("spawnFrom is " + spawnFrom);
	return spawnFrom;
    }

    public Point getSpawnTo() {
	return new Point(spawnTo.x, spawnTo.y);
    }

    public void rotateMe() {
	System.out.println("rotation called");
	if (rotation != 4) {
	    rotation++;
	} else {
	    rotation = 1;
	}
    }

}
