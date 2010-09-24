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
package PanelsAndFrames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import level.Board;
import level.Game;
import level.LevelMap;
import mouseActions.BuildItemMouse;
import mouseActions.BuildRoomMouse;
import GridObjects.Buildings.Room;
import GridObjects.Buildings.RoomFactory.RoomType;
import GridObjects.items.UsableItem;
import GridObjects.items.ItemFactory.ItemType;
import GridObjects.people.Person;
import java.awt.BorderLayout;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class hospitalControlPanel extends JPanel {
    private JButton drawGP;

    private static JButton drawRD;

    private JButton checkPOU, spawnPerson, personMover, lvlMaker;

    static int gridHeight = Game.getGame().getGridHeight();
    static int gridWidth = Game.getGame().getGridWidth();
    static int gridSize = Game.getGame().getGridSize();

    public hospitalControlPanel() {
	setLocation(0, 750);
	Dimension CPanelDim = new Dimension(1020, 60);
	GridLayout thisLayout = new GridLayout(1, 1);
	thisLayout.setColumns(1);
	thisLayout.setHgap(5);
	thisLayout.setVgap(5);
	this.setLayout(thisLayout);
	this.setPreferredSize(CPanelDim);

	drawRD = new JButton("Draw RD");
	drawRD.setLayout(null);
	drawGP = new JButton("Draw GP");
	checkPOU = new JButton("POU?");
	spawnPerson = new JButton("Spawn");
	lvlMaker = new JButton("Level maker");
	personMover = new JButton("Person Mover");

	drawRD.addActionListener(new drawRoomButtonListener());
	drawGP.addActionListener(new drawRoomButtonListener());

	lvlMaker.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		hospital.levelMaker.setVisible(true);
	    }
	});

	checkPOU.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		checkPOU();
	    }
	});
	spawnPerson.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		spawnTestPerson();
	    }
	});

	personMover.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		hospital.personMover.setVisible(true);
	    }
	});

	this.add(drawRD);
	this.add(drawGP);
	this.add(checkPOU);
	this.add(spawnPerson);
	this.add(personMover);
	this.add(lvlMaker);

	setBackground(Color.lightGray);
	this.setMinimumSize(new java.awt.Dimension(500, 60));
    }

    public void spawnTestPerson() {
	Point spawnPoint = new Point(Game.getGame().getLevelMap().getSpawnPoints().get(0).getSpawnFrom());
	Person testPerson = new Person(new Point(spawnPoint.x * Game.getGame().getGridSize(), spawnPoint.y * Game.getGame().getGridSize()),
		Game.getGame().getPeople().size());
	System.out.println("new person with number " + testPerson.getPersonNo());
	System.out.println(testPerson.getTopLeftPoint() + " " + testPerson.getNextPoint());
	Point spawnTo = new Point(Game.getGame().getLevelMap().getSpawnPoints().get(0).getSpawnTo());
	testPerson.setNextPoint(new Point(spawnTo.x * Game.getGame().getGridSize(), spawnTo.y * Game.getGame().getGridSize()));
	Game.getGame().addPerson(testPerson);
	System.out.println(Game.getGame().getPerson(Game.getGame().getPeople().size() - 1).getTopLeftPoint() + " "
		+ Game.getGame().getPerson(Game.getGame().getPeople().size() - 1).getNextPoint());

    }

    public void checkPOU() {
	for (Room room : Game.getGame().getRooms()) {
	    Point door = room.getDoorPoint();
	    for (int j = 0; j < gridWidth; j++) {
		for (int k = 0; k < gridHeight; k++) {
		    if (new Point(j,k).equals(door)) {
			Board.getBoard().getSquare(j, k).setPointOfUsagePreview(true);
		    }
		}
	    }
	}
	for (UsableItem item : Game.getGame().getUsableItems()) {
	    Point point = item.getPointOfUsage();
	    for (int j = 0; j < gridWidth; j++) {
		for (int k = 0; k < gridHeight; k++) {
		    if (new Point(j,k).equals(point)) {
			Board.getBoard().getSquare(j, k).setPointOfUsagePreview(true);
		    }
		}
	    }
	}

    }

    private class drawRoomButtonListener implements ActionListener {
	hospitalGamePanel gamePanel = hospitalGamePanel.getGamePanel();

	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == drawRD) {
		Game.getGame().clearBuildingPreview();
		System.out.println("clicked draw rd");
		gamePanel.setMouseListener(new BuildItemMouse(ItemType.ReceptionDesk));
		gamePanel.setMouseMotionListener(new BuildItemMouse(ItemType.ReceptionDesk));
	    }
	    if (e.getSource() == drawGP) {
		Game.getGame().clearBuildingPreview();
		System.out.println("clicked draw gp");
		gamePanel.setMouseListener(new BuildRoomMouse(RoomType.GPOffice));
		gamePanel.setMouseMotionListener(new BuildRoomMouse(RoomType.GPOffice));
	    }
	}
    }
}
