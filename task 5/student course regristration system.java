import java.util.*;

// Class to represent a course
class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public boolean isFull() {
        return registeredStudents.size() >= capacity;
    }

    public void registerStudent(String studentId) {
        if (!isFull()) {
            registeredStudents.add(studentId);
        } else {
            System.out.println("Course is full, cannot register.");
        }
    }

    public void removeStudent(String studentId) {
        registeredStudents.remove(studentId);
    }

    @Override
    public String toString() {
        return String.format("Course Code: %s\nTitle: %s\nDescription: %s\nCapacity: %d\nSchedule: %s\nAvailable Slots: %d",
                code, title, description, capacity, schedule, capacity - registeredStudents.size());
    }
}

// Class to represent a student
class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerForCourse(Course course) {
        if (!course.isFull()) {
            course.registerStudent(id);
            registeredCourses.add(course);
            System.out.println("Successfully registered for the course: " + course.title);
        } else {
            System.out.println("Cannot register, the course is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.removeStudent(id);
            registeredCourses.remove(course);
            System.out.println("Successfully dropped the course: " + course.title);
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    @Override
    public String toString() {
        return String.format("Student ID: %s\nName: %s\nRegistered Courses: %s", id, name, registeredCourses);
    }
}

// Class to manage the course management system
public class CourseManagementSystem {
    private static Map<String, Course> courseDatabase = new HashMap<>();
    private static Map<String, Student> studentDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        while (true) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerStudentForCourse();
                    break;
                case 3:
                    removeStudentFromCourse();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeData() {
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "Mon 10-12"));
        courseDatabase.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to calculus", 25, "Tue 10-12"));
        courseDatabase.put("ENG101", new Course("ENG101", "English Literature", "Study of English literature", 20, "Wed 10-12"));

        studentDatabase.put("S001", new Student("S001", "Alice"));
        studentDatabase.put("S002", new Student("S002", "Bob"));
        studentDatabase.put("S003", new Student("S003", "Charlie"));
    }

    private static void displayMenu() {
        System.out.println("\nCourse Management System");
        System.out.println("1. Display Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. Drop a Course");
        System.out.println("4. Display Students");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
            System.out.println();
        }
    }

    private static void registerStudentForCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        student.registerForCourse(course);
    }

    private static void removeStudentFromCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        student.dropCourse(course);
    }

    private static void displayStudents() {
        System.out.println("\nRegistered Students:");
        for (Student student : studentDatabase.values()) {
            System.out.println(student);
            System.out.println();
        }
    }
}
