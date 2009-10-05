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
