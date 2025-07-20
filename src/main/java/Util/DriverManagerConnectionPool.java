package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DriverManagerConnectionPool {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bibliosphere?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // cambia se necessario
    private static final String PASSWORD = ""; // cambia se hai password

    private static final LinkedList<Connection> freeDbConnections = new LinkedList<>();

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Errore driver JDBC non trovato: " + e.getMessage());
        }
    }

    private DriverManagerConnectionPool() {
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection conn;

        if (!freeDbConnections.isEmpty()) {
            conn = freeDbConnections.removeFirst();

            if (conn.isClosed())
                conn = getConnection();
        } else {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }

        return conn;
    }

    public static synchronized void releaseConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                freeDbConnections.add(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
