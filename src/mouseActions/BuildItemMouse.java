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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import level.Board;
import level.Game;
import level.GridSquare;
import GridObjects.GridObject;
import GridObjects.items.Item;
import GridObjects.items.ItemFactory;
import GridObjects.items.ReceptionDesk;
import GridObjects.items.UsableItem;
import GridObjects.items.ItemFactory.ItemType;
import PanelsAndFrames.hospitalGamePanel;

public class BuildItemMouse implements MouseListener, MouseMotionListener {

    static int gridSize = Game.getGame().getGridSize();
    static int gridHeight = Game.getGame().getGridHeight();
    static int gridWidth = Game.getGame().getGridWidth();

    // This NEEDS to be static
    static Item thisBuild;

    // Wow I like this factory method thing!
    public BuildItemMouse(ItemType itemType) {
	thisBuild = ItemFactory.createItem(itemType);
	Game.getGame().addBuildingPreview(thisBuild);
    }

    /*
     * This needs to be redone really... hummmTrying to think of a way to allow
     * it to be used when creating a room and then adding items to that room...
     * OK, an idea. make a second constructor with an items array which is from
     * room class, then check in thisclass to see if the array is null or not,
     * possibly.
     */
    public BuildItemMouse() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	Point mouseAt = new Point(e.getX(), e.getY());

	switch (e.getModifiers()) {
	case InputEvent.BUTTON1_MASK: {
	    leftClick(mouseAt);
	    System.out.println("That's the LEFT button");
	    break;
	}
	case InputEvent.BUTTON2_MASK: {
	    // middleClick(mouseAt);
	    System.out.println("That's the MIDDLE button");
	    break;
	}
	case InputEvent.BUTTON3_MASK: {
	    rightClick(mouseAt);
	    System.out.println("That's the RIGHT button");
	    break;
	}
	}

    }

    private void rightClick(Point mouseAt) {

	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		if (Board.getBoard().getSquare(i, j).contains(mouseAt)) {
		    Point gridTopLeft = new Point(Board.getBoard().getSquare(i, j).getLocation());
		    thisBuild.setTopLeftPoint(Game.getGame().screenToGame(gridTopLeft));
		}
	    }
	}

	if (thisBuild instanceof UsableItem) {
	    thisBuild.rotateMe();
	    thisBuild.findPointOfUse();
	    System.out.println("RD has been rotated");
	}
	resetBuildingPreview();
    }

    private void leftClick(Point mouseAt) {

	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		if (Board.getBoard().getSquare(i, j).contains(mouseAt)) {
		    Point gridTopLeft = new Point(Board.getBoard().getSquare(i, j).getLocation());
		    thisBuild.setTopLeftPoint(Game.getGame().screenToGame(gridTopLeft));
		}
	    }
	}
	thisBuild.findPointOfUse();

	// This loop sets the building squares to full.
	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		if (Board.getBoard().getSquare(i, j).getBuildPreview()) {
		    Board.getBoard().getSquare(i, j).setFull(true);
		}
		Board.getBoard().getSquare(i, j).setBuildPreview(false);
		Board.getBoard().getSquare(i, j).setPointOfUsagePreview(false);
	    }
	}

	Game.getGame().addUsableItem(thisBuild);
	// This single line solved half a problem that plagued me for 2days!
	// YAY!
	Game.getGame().clearBuildingPreview();
	hospitalGamePanel.getGamePanel().SetMouseListener(new standardGameMouse());
	hospitalGamePanel.getGamePanel().SetMouseMotionListener(new standardGameMouse());
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
		GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		if (thisSquare.contains(mouseAt)) {
		    Point gridTopLeft = new Point(thisSquare.getLocation());
		    thisBuild.setTopLeftPoint(Game.getGame().screenToGame(gridTopLeft));
		    if (thisBuild instanceof UsableItem) {
			thisBuild.findPointOfUse();
		    }
		}
	    }
	}
	resetBuildingPreview();
    }

    void resetBuildingPreview() {
	Dimension buildingPreviewDimentions = new Dimension(gridSize * thisBuild.getWidth(), gridSize
		* thisBuild.getHeight());
	// System.out.println(thisBuild.getRotation());
	Rectangle checker = new Rectangle(Game.getGame().gameToScreen(thisBuild.getTopLeftPoint()), buildingPreviewDimentions);
	// System.out.println("mouse moved and building and contains");
	// System.out.println("dimentions " +
	// buildingPreviewDimentions);

	// DO NOT REMOVE THIS LOOP. DO NOT TRY AND INCLUDE IT IN
	// A PREVIOUS LOOP!
	// IT WILL SCREW UP! BELIEVE ME!
	for (int k = 0; k < gridWidth; k += 1) {
	    for (int l = 0; l < gridHeight; l += 1) {
		if (Board.getBoard().getSquare(k, l).intersects(checker)) {
		    Board.getBoard().getSquare(k, l).setBuildPreview(true);
		} else {
		    Board.getBoard().getSquare(k, l).setBuildPreview(false);
		}

		UsableItem toBuildTemp = (UsableItem) thisBuild;
		// toBuildTemp.findPointOfUse();
		// System.out.println(mouseAt + " " +
		// desk.getPointOfUsage());
		if (Board.getBoard().getSquare(k, l).contains(Game.getGame().gameToScreen(toBuildTemp.getPointOfUsage()))) {
		    Board.getBoard().getSquare(k, l).setPointOfUsagePreview(true);
		} else {
		    Board.getBoard().getSquare(k, l).setPointOfUsagePreview(false);
		}
	    }
	}
    }

}
