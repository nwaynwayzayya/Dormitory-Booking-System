// Dorm class
class Room {
    private String name;
    private int capacity;
    private boolean available;
    private double rent;

    public Room(String name, int capacity, double rent) {
        this.name = name;
        this.capacity = capacity;
        this.available = true;
        this.rent = rent;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRent(){
        return rent;
    }

    public boolean isAvailable() {
        return available;
    }

    public void bookRoom() {
        this.available = false;
    }

    public void releaseRoom() {
        this.available = true;
    }
}
