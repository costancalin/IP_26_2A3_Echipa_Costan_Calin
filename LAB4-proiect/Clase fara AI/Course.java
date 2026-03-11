package clasa1;
import java.util.*;

public class Course {
    private int courseId;
    private String title;
    private int externalTeacherId;
    private String subject;
    private double basePrice;
    private List<String> lessonTitles;
    private boolean isVerified;

    public Course(int courseId, String title, int externalTeacherId, String subject, double basePrice) {
        this.courseId = courseId;
        this.title = title;
        this.externalTeacherId = externalTeacherId;
        this.subject = subject;
        this.basePrice = basePrice;
        this.lessonTitles = new ArrayList<>();
        this.isVerified = false;
    }

    public void addLesson(String title) {
        if (title != null && !title.isEmpty()) {
            this.lessonTitles.add(title);
        }
    }

    public void verifyTeacher(String registryName) {
        if (registryName != null) {
            this.isVerified = true;
        }
    }

    public double calculateDynamicPrice(double demandMultiplier) {
        if (demandMultiplier <= 0) demandMultiplier = 1.0;

        double currentPrice = this.basePrice;

        if (this.isVerified) {
            double trustBonus = 0.15;
            if ("IT".equalsIgnoreCase(this.subject)) {
                trustBonus += 0.05;
            }
            currentPrice *= (1 + trustBonus);
        }

        double effectiveMultiplier = Math.min(demandMultiplier, 2.5);
        currentPrice *= effectiveMultiplier;

        int lessonCount = this.lessonTitles.size();
        if (lessonCount > 20) {
            currentPrice += 50.0;
        } else if (lessonCount > 10) {
            currentPrice += 20.0;
        }

        if (this.externalTeacherId >= 100 && this.externalTeacherId <= 110) {
            currentPrice = Math.max(currentPrice - 10.0, this.basePrice * 0.5);
        }

        currentPrice += 5.99;

        return Math.round(currentPrice * 100.0) / 100.0;
    }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getExternalTeacherId() { return externalTeacherId; }
    public void setExternalTeacherId(int externalTeacherId) { this.externalTeacherId = externalTeacherId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public List<String> getLessonTitles() { return lessonTitles; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
}