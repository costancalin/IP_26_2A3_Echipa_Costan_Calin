package IP_LAB4;

public class Main {
    // Atribute păstrate din versiunea anterioară (logica de CourseProgress)
    public int studentId;
    public String courseName;
    public int totalLessons;
    public int completedLessons;
    public long lastStudyTimestamp;
    public int currentStreak;

    public Main(int studentId, String courseName, int totalLessons) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.totalLessons = totalLessons;
        this.completedLessons = 0;
        this.lastStudyTimestamp = 0;
        this.currentStreak = 0;
    }

    public double recordStudySession(int newLessonsCompleted) {
        long currentTimestamp = System.currentTimeMillis();
        if (this.lastStudyTimestamp > 0) {
            long hoursSinceLastStudy = (currentTimestamp - this.lastStudyTimestamp) / (1000 * 60 * 60);
            if (hoursSinceLastStudy >= 24 && hoursSinceLastStudy <= 48) this.currentStreak++;
            else if (hoursSinceLastStudy > 48) this.currentStreak = 1;
        } else {
            this.currentStreak = 1;
        }
        this.lastStudyTimestamp = currentTimestamp;
        this.completedLessons += newLessonsCompleted;
        if (this.completedLessons > this.totalLessons) this.completedLessons = this.totalLessons;
        return ((double) this.completedLessons / this.totalLessons) * 100.0;
    }

    // --- Punctul principal de rulare al aplicației ---
    public static void main(String[] args) {
        System.out.println("=== INIȚIALIZARE EDUCONNECT ===\n");

        // 1. Testare Clasa Question
        Question q1 = new Question();
        q1.questionId = 10;
        q1.questionText = "Care este cel mai rapid algoritm de sortare in medie?";
        q1.options = new String[]{"Bubble Sort", "Merge Sort", "Quick Sort", "Insertion Sort"};
        q1.correctIndex = 2; // Quick Sort
        q1.updateDifficulty(4); // Metodă nouă

        System.out.println("[Modul Profesor] - Afisare întrebare:");
        q1.printQuestionDetails(); // Metodă nouă
        System.out.println("Răspunsul corect inițial este: " + q1.getCorrectAnswerText() + "\n"); // Metodă nouă

        // 2. Testare Clasa QuizEngine
        QuizEngine engine = new QuizEngine();
        engine.quizId = 505;
        engine.studentId = 101;
        engine.startSession(); // Metodă nouă

        // Simulăm că studentul a ieșit din tab de 3 ori (tentativă de fraudă)
        engine.logViolation();
        engine.logViolation();
        engine.logViolation();

        // Calculăm scorul (8 corecte din 10)
        engine.evaluateFinalPerformance(8, 10);
        engine.applyBonusPoints(0.5); // Metodă nouă (îi dăm jumatate de punct bonus)

        System.out.println("[Modul Testare] - Rezultat Quiz:");
        System.out.println(engine.getQuizSummary() + "\n"); // Metodă nouă

        // 3. Testare Clasa NotificationService
        NotificationService notifier = new NotificationService();
        notifier.setServer("https://api.educonnect.com/notify");
        notifier.toggleSmtp(true); // Metodă nouă

        // Trimitem o notificare normală și una de fraudă
        notifier.sendSecureAlert(101, "Ai obținut nota " + engine.currentScore + " la test.", 1);
        if (engine.tabSwitches > 2) {
            notifier.sendSecureAlert(101, "Activitate suspectă: Multiple tab switches!", 3);
        }

        System.out.println("[Modul Notificări] - Istoric Alerte:");
        notifier.printRecentAlerts(2); // Metodă nouă
        System.out.println("Total alerte critice (fraudă) înregistrate: " + notifier.countCriticalAlerts() + "\n"); // Metodă nouă

        // 4. Integrarea clasei curente (Main - logica de curs)
        System.out.println("[Modul Dashboard Student]");
        Main myProgress = new Main(101, "Structuri de Date", 15);
        double progressPct = myProgress.recordStudySession(3);
        System.out.println("Sesiune de studiu finalizată cu succes.");
        System.out.printf("Progres curs: %.2f%% | Zile consecutive (Streak): %d\n", progressPct, myProgress.currentStreak);
    }
}