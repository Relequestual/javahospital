package mouseActions;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import level.Game;

import GridObjects.people.Person;
import PanelsAndFrames.hospitalGamePanel;

public class standardGameMouse implements MouseListener, MouseMotionListener {
    
    public standardGameMouse(){
	System.out.println("standard mouse ");
	for(Person person : Game.getGame().getPeople()){
	    person.setWandering(false);
	}
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub

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
	hospitalGamePanel.setMouseAt(new Point(e.getX(), e.getY()));
    }

}
