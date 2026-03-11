package IP_LAB4;

public class QuizEngine {
    public int quizId;
    public int studentId;
    public int tabSwitches;
    public boolean isDisqualified;
    public long sessionStartTime;
    public double currentScore;

    // --- Metode Existente ---
    public void logViolation() {
        this.tabSwitches++;
    }

    public double evaluateFinalPerformance(int correctAnswers, int totalQuestions) {
        if (totalQuestions <= 0) return 1.0;
        if (this.tabSwitches > 5) {
            this.isDisqualified = true;
            this.currentScore = 1.0;
            return this.currentScore;
        }
        double baseScore = 1.0 + (9.0 * ((double) correctAnswers / totalQuestions));
        double penalty = (this.tabSwitches / 2) * 1.0;

        long elapsedMillis = System.currentTimeMillis() - this.sessionStartTime;
        if (elapsedMillis > (long) totalQuestions * 60 * 1000) penalty += 2.0;

        double finalScore = baseScore - penalty;
        this.currentScore = Math.max(1.0, Math.min(finalScore, 10.0));
        return this.currentScore;
    }

    // --- METODE NOI (Medii) ---

    // 1. Pornește sesiunea și resetează contoarele de fraudă
    public void startSession() {
        this.sessionStartTime = System.currentTimeMillis();
        this.tabSwitches = 0;
        this.isDisqualified = false;
        this.currentScore = 0.0;
    }

    // 2. Verifică proactiv dacă timpul a expirat (fără a calcula nota finală)
    public boolean hasExceededTimeLimit(int totalQuestions) {
        if (this.sessionStartTime == 0) return false;
        long elapsedMillis = System.currentTimeMillis() - this.sessionStartTime;
        long maxAllowedMillis = (long) totalQuestions * 60 * 1000;
        return elapsedMillis > maxAllowedMillis;
    }

    // 3. Aplică un bonus profesorului (cu validare să nu depășească nota 10)
    public void applyBonusPoints(double bonus) {
        if (!this.isDisqualified && bonus > 0) {
            this.currentScore += bonus;
            if (this.currentScore > 10.0) {
                this.currentScore = 10.0;
            }
        }
    }

    // 4. Generează un rezumat text al performanței pentru dashboard
    public String getQuizSummary() {
        if (this.isDisqualified) {
            return "Studentul " + this.studentId + " a fost DESCALIFICAT. (Tab switches: " + this.tabSwitches + ")";
        }
        return "Student: " + this.studentId + " | Scor: " + String.format("%.2f", this.currentScore) +
                " | Încălcări regulament: " + this.tabSwitches;
    }
}