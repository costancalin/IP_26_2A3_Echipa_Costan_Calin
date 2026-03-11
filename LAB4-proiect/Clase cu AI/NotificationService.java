package IP_LAB4;

import java.util.List;
import java.util.ArrayList;
import java.time.Instant;

public class NotificationService {
    public List<String> history = new ArrayList<>();
    public boolean smtpActive;
    public String serverEndpoint;

    // --- Metode Existente ---
    public void setServer(String url) {
        this.serverEndpoint = url;
    }

    public boolean sendSecureAlert(int targetId, String msg, int severity) {
        String finalMessage = msg;
        if (severity == 3) finalMessage = "[ALERTA FRAUDA] " + msg;
        String timestamp = Instant.now().toString();
        String jsonPayload = String.format(
                "{\"targetId\": %d, \"timestamp\": \"%s\", \"severity\": %d, \"message\": \"%s\"}",
                targetId, timestamp, severity, finalMessage.replace("\"", "\\\"")
        );
        boolean sendSuccess = true;
        if (sendSuccess) {
            this.history.add(jsonPayload);
            while (this.history.size() > 100) this.history.remove(0);
        }
        return sendSuccess;
    }

    // --- METODE NOI (Medii) ---

    // 1. Schimbă starea serviciului de email (SMTP)
    public void toggleSmtp(boolean status) {
        this.smtpActive = status;
        System.out.println("Serviciul SMTP a fost " + (status ? "ACTIVAT" : "DEZACTIVAT") + ".");
    }

    // 2. Curăță istoricul notificărilor (util pentru mentenanță/admin)
    public void clearHistory() {
        this.history.clear();
        System.out.println("Istoricul notificărilor a fost șters complet.");
    }

    // 3. Numără câte alerte de fraudă au fost înregistrate
    public int countCriticalAlerts() {
        int count = 0;
        for (String alert : this.history) {
            if (alert.contains("[ALERTA FRAUDA]")) {
                count++;
            }
        }
        return count;
    }

    // 4. Afișează cele mai recente 'N' notificări
    public void printRecentAlerts(int limit) {
        System.out.println("--- Ultimele Notificări ---");
        if (this.history.isEmpty()) {
            System.out.println("Nicio notificare în istoric.");
            return;
        }
        int start = Math.max(0, this.history.size() - limit);
        for (int i = start; i < this.history.size(); i++) {
            System.out.println("-> " + this.history.get(i));
        }
    }
}