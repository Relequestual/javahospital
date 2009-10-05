package GridObjects.items;

public class ItemFactory {

    public enum ItemType {
	ReceptionDesk, VendMachine
    }

    public static Item createItem(ItemType itemType) {

	switch (itemType) {
	case ReceptionDesk:
	    return new ReceptionDesk();
	case VendMachine:
	    return new VendMachine();
	}
	return null;

	// throw new IllegalArgumentException("The item type " + itemType +
	// " is not recognized.");
    }
}