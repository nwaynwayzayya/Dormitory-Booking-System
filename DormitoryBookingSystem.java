import java.io.*;
import java.util.*;

// DormitoryBookingSystem
public class DormitoryBookingSystem {
    private static List<Dorm> dorms = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read dorms from file
        readDormsFromFile("dorms.txt");

        // Interact with the system
        while (true) {
            System.out.println("1. Book a dorm");
            System.out.println("2. Release a dorm");
            System.out.println("3. View available dorms");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    bookDorm(scanner);
                    break;
                case 2:
                    releaseDorm(scanner);
                    break;
                case 3:
                    viewAvailableDorms();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void readDormsFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                int capacity = Integer.parseInt(parts[1]);
                Dorm dorm = new Dorm(name, capacity);
                dorms.add(dorm);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading dorms file: " + e.getMessage());
        }
    }

    private static void bookDorm(Scanner scanner) {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Student student = new Student(studentName, studentId);
        students.add(student);

        System.out.print("Enter dorm name: ");
        String dormName = scanner.nextLine();

        Dorm dorm = findDormByName(dormName);
        if (dorm != null) {
            try {
                student.bookDorm(dorm);
                System.out.println("Dorm " + dorm.getName() + " booked successfully.");
            } catch (DormNotAvailableException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Dorm " + dormName + " not found.");
        }
    }

    private static void releaseDorm(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Student student = findStudentById(studentId);
        if (student != null) {
            Dorm bookedDorm = student.getBookedDorm();
            if (bookedDorm != null) {
                student.releaseDorm();
                System.out.println("Dorm " + bookedDorm.getName() + " released successfully.");
            } else {
                System.out.println("Student " + student.getName() + " has not booked a dorm.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static void viewAvailableDorms() {
        System.out.println("Available dorms:");
        for (Dorm dorm : dorms) {
            if (dorm.isAvailable()) {
                System.out.println(dorm.getName() + " (Capacity: " + dorm.getCapacity() + ")");
            }
        }
    }

private static Dorm findDormByName(String name) {
        for (Dorm dorm : dorms) {
            if (dorm.getName().equalsIgnoreCase(name)) {
                return dorm;
            }
        }
        return null;
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
