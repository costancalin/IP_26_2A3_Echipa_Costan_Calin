package clasa2;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private double progress;
    private long enrollmentDate;
    private int completedLessonsCount;

    public Enrollment(int enrollmentId, int studentId, int courseId, long enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.progress = 0.0;
        this.completedLessonsCount = 0;
    }

    public void incrementProgress(int totalCourseLessons) {
        if (totalCourseLessons > 0) {
            this.completedLessonsCount++;
            this.progress = ((double) this.completedLessonsCount / totalCourseLessons) * 100;
        }
    }

    public String getCompletionForecast(int totalLessons) {
        if (this.progress > 90.0) {
            return "Aproape de certificare!";
        }

        long currentTime = System.currentTimeMillis();
        long diffInMillis = currentTime - this.enrollmentDate;

        double daysPassed = Math.max((double) diffInMillis / (1000 * 60 * 60 * 24), 0.1);
        double learningRate = this.completedLessonsCount / daysPassed;

        if (learningRate < 0.5) {
            int lessonsLeft = totalLessons - this.completedLessonsCount;
            double daysLeft;


            if (learningRate > 0) {
                daysLeft = lessonsLeft / learningRate;
            } else {
                daysLeft = 999;
            }

            double monthsLeft = Math.round((daysLeft / 30.0) * 10.0) / 10.0;
            return "Atenție: Ritm lent! Finalizare estimată în " + monthsLeft + " luni";
        }

        return "Ritm optim de învățare: " + Math.round(learningRate * 100.0) / 100.0 + " lecții/zi.";
    }


    public int getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(int enrollmentId) { this.enrollmentId = enrollmentId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public double getProgress() { return progress; }

    public long getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(long enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public int getCompletedLessonsCount() { return completedLessonsCount; }
}