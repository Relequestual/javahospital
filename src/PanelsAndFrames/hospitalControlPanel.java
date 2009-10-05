package PanelsAndFrames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	FlowLayout thisLayout = new FlowLayout();
	this.setLayout(thisLayout);
	setSize(CPanelDim);

	drawRD = new JButton("Draw RD");
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
		gamePanel.SetMouseListener(new BuildItemMouse(ItemType.ReceptionDesk));
		gamePanel.SetMouseMotionListener(new BuildItemMouse(ItemType.ReceptionDesk));
	    }
	    if (e.getSource() == drawGP) {
		Game.getGame().clearBuildingPreview();
		System.out.println("clicked draw gp");
		gamePanel.SetMouseListener(new BuildRoomMouse(RoomType.GPOffice));
		gamePanel.SetMouseMotionListener(new BuildRoomMouse(RoomType.GPOffice));
	    }
	}
    }
}