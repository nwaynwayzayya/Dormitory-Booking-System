// Dorm class
class Dorm {
    private String name;
    private int capacity;
    private boolean available;

    public Dorm(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void bookDorm() {
        this.available = false;
    }

    public void releaseDorm() {
        this.available = true;
    }
}
