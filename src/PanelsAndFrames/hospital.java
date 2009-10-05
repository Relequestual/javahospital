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

import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import level.Game;
import PanelsAndFrames.controlPanels.levelMaker;
import PanelsAndFrames.controlPanels.personMover;

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
/**
 * @author Ben
 * 
 */
public class hospital {
    static JScrollPane jScrollPane2 = new JScrollPane();
    static hospitalControlPanel hospitalControlPanel = new hospitalControlPanel();
    static JFrame gameWindow = new JFrame();
    static JDesktopPane mainPane = new JDesktopPane();

    static levelMaker levelMaker = new levelMaker();
    static personMover personMover = new personMover();

    // static JPanel mainPanel = new JPanel();
    // TODO: need to make panels ontop of maine panel which needs to be ontop of
    // mainpane!

    /**
     * @param args
     */
    public static void main(String[] args) {
	gameWindow = new JFrame("Java Hospital");
	gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gameWindow.setPreferredSize(new Dimension(1036, 840));
	gameWindow.setLayout(null);
	mainPane.setBounds(0, 0, 1036, 840);
	mainPane.setLayout(null);
	hospitalGamePanel.getGamePanel().setPreferredSize(
		new java.awt.Dimension(Game.getGame().getPanelWidth(), Game.getGame().getPanelHeight()));
	gameWindow.setContentPane(mainPane);
	{
	    mainPane.add(jScrollPane2);
	    jScrollPane2.setBounds(0, 0, 1020, 750);
	    jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	    jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jScrollPane2.setViewportView(hospitalGamePanel.getGamePanel());
	}
	mainPane.add(hospitalControlPanel);

	levelMaker.pack();
	levelMaker.setBounds(20, 20, 200, 300);
	mainPane.add(levelMaker);
	personMover.pack();
	personMover.setBounds(20, 20, 200, 300);
	mainPane.add(personMover);

	gameWindow.pack();
	gameWindow.setVisible(true);
    }
}
