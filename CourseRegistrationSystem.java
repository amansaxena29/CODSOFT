import java.io.*;
import java.util.*;

public class CourseRegistrationSystem {

    static class Course implements Serializable {
        private static final long serialVersionUID = 1L;
        String code;
        String title;
        String description;
        int capacity;
        int enrolled;
        String schedule;

        Course(String code, String title, String description, int capacity, String schedule) {
            this.code = code;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.enrolled = 0;
            this.schedule = schedule;
        }

        boolean isAvailable() {
            return enrolled < capacity;
        }

        void enroll() {
            if (isAvailable()) {
                enrolled++;
            }
        }

        void drop() {
            if (enrolled > 0) {
                enrolled--;
            }
        }

        @Override
        public String toString() {
            return String.format("Code: %s, Title: %s, Description: %s, Capacity: %d, Enrolled: %d, Schedule: %s",
                    code, title, description, capacity, enrolled, schedule);
        }
    }

    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;
        String id;
        String name;
        List<String> registeredCourses = new ArrayList<>();

        Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        void registerCourse(String courseCode) {
            registeredCourses.add(courseCode);
        }

        void dropCourse(String courseCode) {
            registeredCourses.remove(courseCode);
        }
    }

    private static final Map<String, Course> courses = new HashMap<>();
    private static final Map<String, Student> students = new HashMap<>();
    private static final String COURSES_FILE = "courses.dat";
    private static final String STUDENTS_FILE = "students.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            loadData();


            if (courses.isEmpty()) {
                initializeData();
            }

            while (true) {
                System.out.println("1. List of all the courses");
                System.out.println("2. Register for a course");
                System.out.println("3. Unregistered from the course");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        listCourses();
                        break;
                    case 2:
                        registerForCourse(scanner);
                        break;
                    case 3:
                        dropCourse(scanner);
                        break;
                    case 4:
                        saveData();
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error handling data: " + e.getMessage());
        }
    }

    private static void initializeData() {
        courses.put("BCA101", new Course("BCA101", "Discrete Mathematics", "Mathematics", 30, "Mon-Sat 10:00 - 12:00"));
        courses.put("BCA102", new Course("BCA102", "Computer Organization and Architecture", "It tells you about how Computer system behaves under various circumstances.", 30, "Mon-Sat 10:00 - 12:00"));
        courses.put("BCA103", new Course("BCA103", "Operating System", "Interface between the User and the Hardware.", 30, "Mon-Sat 10:00 - 12:00"));
        courses.put("BCA104", new Course("BCA104", "Database Management System", "How you store data", 30, "Mon-Sat 10:00 - 12:00"));
        courses.put("BCA105", new Course("BCA105", "Java Programming Basics", "", 30, "Mon-Sat 10:00 - 12:00"));

        students.put("A1", new Student("A1", "Saxena"));
        students.put("A2", new Student("A2", "Raj"));
        students.put("A3", new Student("A3", "Rajawat"));
    }

    private static void listCourses() {
        System.out.println("Available Courses too Enroll. :");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null || !course.isAvailable()) {
            System.out.println("Course not available or full.");
            return;
        }

        student.registerCourse(courseCode);
        course.enroll();
        System.out.println("Registered successfully.");
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        if (!student.registeredCourses.contains(courseCode)) {
            System.out.println("Student not registered for this course.");
            return;
        }

        student.dropCourse(courseCode);
        Course course = courses.get(courseCode);
        if (course != null) {
            course.drop();
        }
        System.out.println("Dropped successfully.");
    }

    private static void saveData() throws IOException {
        try (ObjectOutputStream oosCourses = new ObjectOutputStream(new FileOutputStream(COURSES_FILE));
             ObjectOutputStream oosStudents = new ObjectOutputStream(new FileOutputStream(STUDENTS_FILE))) {
            oosCourses.writeObject(courses);
            oosStudents.writeObject(students);
        }
    }

    private static void loadData() throws IOException, ClassNotFoundException {
        File coursesFile = new File(COURSES_FILE);
        File studentsFile = new File(STUDENTS_FILE);

        if (coursesFile.exists()) {
            try (ObjectInputStream oisCourses = new ObjectInputStream(new FileInputStream(coursesFile))) {
                @SuppressWarnings("unchecked")
                Map<String, Course> loadedCourses = (Map<String, Course>) oisCourses.readObject();
                courses.putAll(loadedCourses);
            }
        }

        if (studentsFile.exists()) {
            try (ObjectInputStream oisStudents = new ObjectInputStream(new FileInputStream(studentsFile))) {
                @SuppressWarnings("unchecked")
                Map<String, Student> loadedStudents = (Map<String, Student>) oisStudents.readObject();
                students.putAll(loadedStudents);
            }
        }
    }
}