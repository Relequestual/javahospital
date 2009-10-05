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

import GridObjects.GridObject;
import GridObjects.items.ItemFactory;
import GridObjects.items.ItemFactory.ItemType;

public class Disease {

    ArrayList<String> order = new ArrayList<String>();
    int onOrder = 0;
    boolean endOrder = false;
    String name = "Illness";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init() {
	order.add("ReceptionDesk");
	order.add("GPOffice");
    }

    public ArrayList<String> getOrder() {
	return order;
    }

    public int getOnOrder() {
	return onOrder;
    }

    public void setOnOrder(int onOrder) {
	this.onOrder = onOrder;
    }

    public boolean isEndOrder() {
	return endOrder;
    }

    public void setEndOrder(boolean endOrder) {
	this.endOrder = endOrder;
    }

    public void nextInOrder() {

	onOrder += 1;
	System.out.println("next in order called.. now " + onOrder + " of  " + (order.size()));
	if (onOrder == order.size()) {
	    endOrder = true;
	}

    }

    public void addSubOrder(String subOrder) {
	order.add(onOrder, subOrder);
    }

}
