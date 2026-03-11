package clasa1;
import java.util.*;

public class DatabaseSimulator {
    private static List<Course> coursesTable = new ArrayList<>();

    static {
        coursesTable.add(new Course(1, "Java", 102, "IT", 500.0));
        coursesTable.add(new Course(2, "Python", 105, "IT", 100.0));
        coursesTable.add(new Course(3, "Photoshop Basics", 200, "Design", 200.0));
        coursesTable.add(new Course(4, "Antreprenoriat", 108, "Business", 300.0));
    }

    public static List<Course> getAllCourses() {
        return coursesTable;
    }

    public static Course findCourseById(int id) {
        for (Course c : coursesTable) {
            if (c.getCourseId() == id) return c;
        }
        return null;
    }
}