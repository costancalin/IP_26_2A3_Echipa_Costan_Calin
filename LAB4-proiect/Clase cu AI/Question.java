package IP_LAB4;

import java.util.Random;

public class Question {
    public int questionId;
    public String questionText;
    public String[] options;
    public int correctIndex;
    public int difficultyLevel;

    // --- Metode Existente ---
    public boolean checkAnswer(int userChoice) {
        return userChoice == this.correctIndex;
    }

    public void shuffleAndReindex() {
        if (this.options == null || this.options.length <= 1) return;
        String correctAnswerText = this.options[this.correctIndex];
        Random rand = new Random();
        for (int i = this.options.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = this.options[i];
            this.options[i] = this.options[j];
            this.options[j] = temp;
        }
        for (int i = 0; i < this.options.length; i++) {
            if (this.options[i].equals(correctAnswerText)) {
                this.correctIndex = i;
                break;
            }
        }
    }

    // --- METODE NOI (Medii) ---

    // 1. Returnează direct textul răspunsului corect
    public String getCorrectAnswerText() {
        if (this.options != null && this.correctIndex >= 0 && this.correctIndex < this.options.length) {
            return this.options[this.correctIndex];
        }
        return "Eroare: Răspuns indisponibil";
    }

    // 2. Modifică o opțiune existentă (folosit de profesori la editare curs)
    public boolean editOption(int index, String newText) {
        if (this.options != null && index >= 0 && index < this.options.length && newText != null) {
            this.options[index] = newText;
            return true;
        }
        return false;
    }

    // 3. Actualizează dificultatea cu validare (trebuie să fie strict între 1 și 5)
    public void updateDifficulty(int newLevel) {
        if (newLevel >= 1 && newLevel <= 5) {
            this.difficultyLevel = newLevel;
        }
    }

    // 4. Afișează întrebarea în consolă într-un format prietenos
    public void printQuestionDetails() {
        System.out.println("Întrebare (" + this.difficultyLevel + " stele): " + this.questionText);
        if (this.options != null) {
            for (int i = 0; i < this.options.length; i++) {
                System.out.println(" " + i + ") " + this.options[i]);
            }
        }
    }
}