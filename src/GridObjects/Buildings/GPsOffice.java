package GridObjects.Buildings;

import java.awt.Point;

public class GPsOffice extends Room {
    
    /*/More objects needed for different rooms, eg
     * arraylist for staff allowed in, for default
     * items that are auto built and for items that 
     * can be built.
     */
    
    
    public GPsOffice(Point topLeftEntered, int widthEntered, int heightEntered){
	this.topLeftPoint = topLeftEntered;
	this.width = widthEntered;
	this.height = heightEntered;
	nextStageTime = 5;
    }
    
}
