package GridObjects.Buildings;

public class RoomFactory {

    public enum RoomType {
	GPOffice
    }

    public static Room createRoom(RoomType roomType) {

	switch (roomType) {
	case GPOffice:
	    return new Room();
	}
	return null;

	// throw new IllegalArgumentException("The room type " + roomType +
	// " is not recognized.");
    }
}