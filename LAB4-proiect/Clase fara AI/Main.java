package clasa1;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        for (Course c : DatabaseSimulator.getAllCourses()) {
            int numarLectii = rand.nextInt(31);

            for (int i = 0; i < numarLectii; i++) {
                c.addLesson("Lectia " + i);
            }
            if (c.getSubject() != null && c.getSubject().equalsIgnoreCase("IT")) {
                c.verifyTeacher("Validare Automata IT");
            }
            double pretFinal = c.calculateDynamicPrice(1.2);
            System.out.println("Curs: " + c.getTitle());
            System.out.println("Lecții adăugate: " + numarLectii);
            System.out.println("Profesor verificat: " + c.isVerified());
            System.out.println("Preț final (cu multiplier 1.2): " + pretFinal + " RON");
        }
    }
}