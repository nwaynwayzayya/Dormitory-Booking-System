public class Room {
    private int roomId;
    private String roomType; // Single, Double, etc.
    private boolean availability; // True if available, False if booked

    public Room(int roomId, String roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.availability = true; // Initially available
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

public class User {
    private String name;
    private String studentId;

    public User(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }
}
