package ucb.login.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // private static final String URL = "jdbc:sqlite:src/main/resources/db/sqlite/login.db";
    private static final String URL = "jdbc:sqlite:/opt/login.db";

    // Metodo para obter uma conexão
    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        
            return DriverManager.getConnection(URL);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Driver SQLite não encontrado!", e);
        }
    }
    
}
