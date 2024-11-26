package ucb.login.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    // Formata a data em um outro formato
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public void info(String msg) {
        // Obtem a data de agora
        LocalDateTime now = LocalDateTime.now();

        // Combina a msg com a data atual
        String logMessage = "[" + now.format(formatter) + "] [INFO] - " + msg;

        // Print the log message
        System.out.println(logMessage);
    }

}
