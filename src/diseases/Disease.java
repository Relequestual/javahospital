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
package diseases;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Disease.
 * 
 * @author Relequestual
 */
public class Disease {

    /** The order in which the patient must go to rooms or 
     * locations in order for the disease to be cured.  */
    ArrayList<String> order = new ArrayList<String>();
    
    /** The on order determines at what position in 
     * the ArrayList the patient must go to next.  */
    int onOrder = 0;
    
    /** The end order is the boolean which shows if the 
     * patient is on the last order or not.  */
    boolean endOrder = false;
    
    /** The name of the illness. */
    String name = "Illness";

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Initiates a standard test disease of reception desk and then GPs office.
     */
    public void init() {
	order.add("ReceptionDesk");
	order.add("GPOffice");
    }

    /**
     * Gets the orders ArrayList.
     * 
     * @return the order
     */
    public ArrayList<String> getOrder() {
	return order;
    }

    /**
     * Gets the on order.
     * 
     * @return the on order
     */
    public int getOnOrder() {
	return onOrder;
    }

    /**
     * Sets the on order.
     * 
     * @param onOrder the new on order
     */
    public void setOnOrder(int onOrder) {
	this.onOrder = onOrder;
    }

    /**
     * Checks if is end order.
     * 
     * @return true, if is end order
     */
    public boolean isEndOrder() {
	return endOrder;
    }

    /**
     * Sets the end order.
     * 
     * @param endOrder the new end order
     */
    public void setEndOrder(boolean endOrder) {
	this.endOrder = endOrder;
    }

    /**
     * Sets the next order to be the current order.
     */
    public void nextInOrder() {

	onOrder += 1;
	System.out.println("next in order called.. now " + onOrder + " of  " + (order.size()));
	if (onOrder == order.size()) {
	    endOrder = true;
	}

    }

    /**
     * Adds the sub order.
     * 
     * @param subOrder the sub order
     */
    public void addSubOrder(String subOrder) {
	order.add(onOrder, subOrder);
    }

}
