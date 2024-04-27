import java.io.*;
import java.util.*;

// DormitoryBookingSystem
public class DormitoryBookingSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read rooms from file
        readRoomsFromFile("rooms.txt");

        // Interact with the system
        while (true) {
            System.out.println("1. Book a room");
            System.out.println("2. Release a room");
            System.out.println("3. View available rooms");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    bookRoom(scanner);
                    break;
                case 2:
                    releaseRoom(scanner);
                    break;
                case 3:
                    viewAvailableRooms();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void readRoomsFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
        
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim(); // Trim the line
                String[] parts = line.split(",\\s*"); // Split using comma and optional spaces
                if (parts.length >= 3) { // Ensure at least three parts exist
                    String name = parts[0];
                    int capacity = Integer.parseInt(parts[1]);
                    double rent = Double.parseDouble(parts[2]);
                    Room room = new Room(name, capacity, rent);
                    rooms.add(room);
                } else {
                    System.out.println("Invalid format in rooms.txt: " + line);
                }
            }
        
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading rooms file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing room data: Invalid format in rooms.txt");
        }
    }
    

    private static void bookRoom(Scanner scanner) {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Student student = new Student(studentName, studentId);
        students.add(student);

        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();

        Room room = findRoombyNumber(roomNumber);
        if (room != null) {
            try {
                student.bookRoom(room);
                System.out.println("Room " + room.getName() + " booked successfully.");
            } catch (RoomNotAvailableException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Room " + roomNumber + " not found.");
        }
    }

    private static void releaseRoom(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Student student = findStudentById(studentId);
        if (student != null) {
            Room bookedRoom = student.getBookedRoom();
            if (bookedRoom != null) {
                student.releaseRoom();
                System.out.println("Room " + bookedRoom.getName() + " released successfully.");
            } else {
                System.out.println("Student " + student.getName() + " has not booked a room.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static void viewAvailableRooms() {
        System.out.println("Available rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room.getName() + " (Capacity: " + room.getCapacity() + ", Rent: Baht " + room.getRent() + ")");
            }
        }
    }

private static Room findRoombyNumber(String name) {
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(name)) {
                return room;
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

    public static double calculateRoomRent(Student student) {
        if (student.getBookedRoom() != null) {
          return student.getBookedRoom().getRent(); // Return rent of booked room
        } else {
          System.out.println("Student " + student.getName() + " has not booked a room.");
          return 0.0; // Or handle the case where no room is booked
        }
      }
}
