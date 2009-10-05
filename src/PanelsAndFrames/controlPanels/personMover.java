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
package PanelsAndFrames.controlPanels;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import level.Game;
import pathFinding.Path;
import GridObjects.GridObject;
import GridObjects.Buildings.Room;
import GridObjects.Buildings.RoomFactory;
import GridObjects.Buildings.RoomFactory.RoomType;
import GridObjects.items.ItemFactory;
import GridObjects.items.ReceptionDesk;
import GridObjects.items.UsableItem;
import GridObjects.items.ItemFactory.ItemType;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class personMover extends JInternalFrame {
    private JPanel jPanel1;
    private JButton btnUp;
    private JButton goToGP;
    private JButton goToDesk;
    private JTextField txtPersonNo;
    private JButton btnRight;
    private JButton btnLeft;
    private JButton btnDown;

    public personMover() {
	super("Person Mover", false, true);
	setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	initGUI();
    }

    private void initGUI() {
	try {
	    {
		jPanel1 = new JPanel();
		getContentPane().add(jPanel1, BorderLayout.CENTER);
		jPanel1.setPreferredSize(new java.awt.Dimension(187, 271));
		{
		    txtPersonNo = new JTextField();
		    jPanel1.add(txtPersonNo);
		    txtPersonNo.setText("Person Number");
		    txtPersonNo.setPreferredSize(new java.awt.Dimension(169, 23));
		}
		{
		    btnUp = new JButton();
		    jPanel1.add(btnUp);
		    btnUp.setText("Up");
		    btnUp.setName("btnUp");
		    btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    btnUpActionPerformed(evt);
			}
		    });
		}
		{
		    btnDown = new JButton();
		    jPanel1.add(btnDown);
		    btnDown.setText("Down");
		    btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    btnDownActionPerformed(evt);
			}
		    });
		}
		{
		    btnLeft = new JButton();
		    jPanel1.add(btnLeft);
		    btnLeft.setText("Left");
		    btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    btnLeftActionPerformed(evt);
			}
		    });
		}
		{
		    btnRight = new JButton();
		    jPanel1.add(btnRight);
		    btnRight.setText("Right");
		    btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    btnRightActionPerformed(evt);
			}
		    });
		}
		{
		    goToDesk = new JButton();
		    jPanel1.add(goToDesk);
		    goToDesk.setText("Go To Desk");
		    goToDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    goToDeskActionPerformed(evt);
			}
		    });
		}
		{
		    goToGP = new JButton();
		    jPanel1.add(goToGP);
		    goToGP.setText("Go To GP's");
		    goToGP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    goToGPActionPerformed(evt);
			}
		    });
		}
	    }
	    pack();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void btnUpActionPerformed(ActionEvent evt) {
	System.out.println("btnUp.actionPerformed, event=" + evt);
	Game.getGame().getPerson(Integer.parseInt(txtPersonNo.getText())).moveUp();
    }

    private void btnDownActionPerformed(ActionEvent evt) {
	System.out.println("btnDown.actionPerformed, event=" + evt);
	Game.getGame().getPerson(Integer.parseInt(txtPersonNo.getText())).moveDown();
    }

    private void btnLeftActionPerformed(ActionEvent evt) {
	System.out.println("btnLeft.actionPerformed, event=" + evt);
	Game.getGame().getPerson(Integer.parseInt(txtPersonNo.getText())).moveLeft();
    }

    private void btnRightActionPerformed(ActionEvent evt) {
	System.out.println("btnRight.actionPerformed, event=" + evt);
	Game.getGame().getPerson(Integer.parseInt(txtPersonNo.getText())).moveRight();
    }

    private void goToDeskActionPerformed(ActionEvent evt) {
	System.out.println("goToDesk.actionPerformed, event=" + evt);

	int num = Integer.parseInt(txtPersonNo.getText());
	GridObject thisItem = ItemFactory.createItem(ItemType.ReceptionDesk);
	Game.getGame().getPerson(num).goTo(thisItem);
    }

    private void goToGPActionPerformed(ActionEvent evt) {
	System.out.println("goToGP.actionPerformed, event=" + evt);

	int num = Integer.parseInt(txtPersonNo.getText());
	GridObject thisRoom = RoomFactory.createRoom(RoomType.GPOffice);
	Game.getGame().getPerson(num).goTo(thisRoom);
    }

}
