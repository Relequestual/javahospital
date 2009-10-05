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
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import level.Board;
import level.Game;
import level.GridSquare;

import GridObjects.Buildings.Room;
import PanelsAndFrames.hospitalGamePanel;

public class BuildRoomDoorMouse implements MouseListener, MouseMotionListener {

    static int gridSize = Game.getGame().getGridSize();
    static int gridHeight = Game.getGame().getGridHeight();
    static int gridWidth = Game.getGame().getGridWidth();

    static Room thisBuild;

    BuildRoomDoorMouse(Room thisBuild) {
	BuildRoomDoorMouse.thisBuild = thisBuild;

	/*
	 * TODO: The following lines of code need to be moved. The squares which
	 * represent the outer wall area of a room must be assigned to an array
	 * of some sort which is a member of the building class, as when the
	 * building is removed, so the blocked and wall square notes must also
	 * be removed.
	 */
	System.out.println("Room stuff...");
	System.out.println(thisBuild.getTopLeftPoint() + " " + thisBuild.getWidth() + " " + thisBuild.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	Point mouseAt = new Point(e.getX() / 30, e.getY() / 30);

	int doorRotation = isInEdgeSquare(mouseAt.x, mouseAt.y) - 3;
	Point doorToPlace = new Point();
	System.out.println("condition " + doorRotation);
	switch (doorRotation) {
	case 1:
	    doorToPlace = new Point(mouseAt.x, mouseAt.y - 1);
	    break;
	case 2:
	    doorToPlace = new Point(mouseAt.x + 1, mouseAt.y);
	    break;
	case 3:
	    doorToPlace = new Point(mouseAt.x, mouseAt.y + 1);
	    break;
	case 4:
	    doorToPlace = new Point(mouseAt.x - 1, mouseAt.y);
	    break;
	default:
	    System.err.println("Door placment error. rotation " + doorRotation + " is invalid");
	}
	if (doorRotation >= 0) {
	    for (int i = 0; i < gridWidth; i += 1) {
		for (int j = 0; j < gridHeight; j += 1) {
		    Board.getBoard().getSquare(i, j).setBuildPreview(false);
		    Board.getBoard().getSquare(i, j).setPointOfUsagePreview(false);
		}
	    }

	    System.out.println("mouse at during door build" + mouseAt);
	    thisBuild.setDoorRotation(doorRotation);
	    thisBuild.setDoorPoint(doorToPlace);
	    thisBuild.buildWalls();
	    thisBuild.buildDoor();

	    Game.getGame().addRoom(thisBuild);
	    hospitalGamePanel.getGamePanel().SetMouseListener(new standardGameMouse());
	    hospitalGamePanel.getGamePanel().SetMouseMotionListener(new standardGameMouse());
	}
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

    @Override
    public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
	Point mouseAt = new Point(e.getX(), e.getY());

	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		if (Board.getBoard().getSquare(i, j).contains(mouseAt)) {
		    if (isInEdgeSquare(i, j) != 0) {
			Board.getBoard().getSquare(i, j).setPointOfUsagePreview(true);
		    }
		} else {
		    Board.getBoard().getSquare(i, j).setPointOfUsagePreview(false);
		}
	    }
	}
    }

    public int isInEdgeSquare(int x, int y) {
	Point thisSPoint = Game.getGame().screenToGame(Board.getBoard().getSquare(x, y).getLocation());

	int num = 0;
	// The 3rd condition, no "or equal to" used as one less than the
	// original point plus width is needed.
	// Added 3 to numbers (1,2,3,4), so that the check at the end can be
	// done, as 1+2 !< 4
	if (thisBuild.getY() == thisSPoint.y && thisSPoint.x >= thisBuild.getX()
		&& thisSPoint.x < thisBuild.getX() + thisBuild.getWidth()) {
	    num += 4;
	}
	if (thisBuild.getX() + thisBuild.getWidth() - 1 == thisSPoint.x && thisSPoint.y >= thisBuild.getY()
		&& thisSPoint.y < thisBuild.getY() + thisBuild.getHeight()) {
	    num += 5;
	}
	if (thisBuild.getY() + thisBuild.getHeight() - 1 == thisSPoint.y && thisSPoint.x >= thisBuild.getX()
		&& thisSPoint.x < thisBuild.getX() + thisBuild.getWidth()) {
	    num += 6;
	}
	if (thisBuild.getX() == thisSPoint.x && thisSPoint.y >= thisBuild.getY()
		&& thisSPoint.y < thisBuild.getY() + thisBuild.getHeight()) {
	    num += 7;
	}
	// Using this check, doors cant't be placed on corners!
	if (num > 7) {
	    return 0;
	} else {
	    return num;
	}
    }
}
