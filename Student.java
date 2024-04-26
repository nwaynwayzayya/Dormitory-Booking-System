// Student class
class Student {
    private String name;
    private int id;
    private Dorm bookedDorm;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.bookedDorm = null;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Dorm getBookedDorm() {
        return bookedDorm;
    }

    public void bookDorm(Dorm dorm) throws DormNotAvailableException {
        if (dorm.isAvailable()) {
            this.bookedDorm = dorm;
            dorm.bookDorm();
        } else {
            throw new DormNotAvailableException("Dorm " + dorm.getName() + " is not available.");
        }
    }

    public void releaseDorm() {
        if (bookedDorm != null) {
            bookedDorm.releaseDorm();
            bookedDorm = null;
        }
    }
}
