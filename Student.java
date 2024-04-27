// Student class
class Student implements Accommodation{
    private String name;
    private long id;
    private Room bookedRoom;

    public Student(String name, long id) {
        this.name = name;
        this.id = id;
        this.bookedRoom = null;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    @Override
    public void bookRoom(Room room) throws RoomNotAvailableException {
        if (room.isAvailable()) {
            this.bookedRoom = room;
            room.bookRoom();
        } else {
            throw new RoomNotAvailableException("Dorm " + room.getName() + " is not available.");
        }
    }

    @Override
    public void releaseRoom() {
        if (bookedRoom != null) {
            bookedRoom.releaseRoom();
            bookedRoom = null;
        }
    }
}
