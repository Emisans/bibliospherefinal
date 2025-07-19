package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Utente;

public class UtenteDAO {

    // Caricamento del driver JDBC (opzionale ma consigliato)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL non trovato!");
            e.printStackTrace();
        }
    }

    // Recupera un utente dal database dato lo username
    public static Utente doRetrieveByUsername(String username) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bibliosphere";
        String user = "root";
        String pass = "admin";

        Utente utente = null;

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti WHERE username = ?")) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("ERRORE SQL in doRetrieveByUsername:");
            e.printStackTrace();
        }

        return utente;
    }

    // Salva un nuovo utente nel database
    public static boolean doSave(Utente u) {
        String url = "jdbc:mysql://localhost:3306/bibliosphere";
        String user = "root";
        String pass = "admin";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement("INSERT INTO utenti (nome, email, username, password) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getPassword());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("ERRORE SQL in doSave:");
            e.printStackTrace();
            return false;
        }
    }
}

