package dao;

import model.Utente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDao {

    // Dati per la connessione al database (modifica secondo il tuo setup)
    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // Salva un nuovo utente nel database (registrazione)
    public void salvaUtente(Utente utente) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO utenti (nome, email, username, password) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, utente.getNome());
            stmt.setString(2, utente.getEmail());
            stmt.setString(3, utente.getUsername());
            stmt.setString(4, utente.getPassword());

            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    // Cerca un utente tramite email (per login)
    public Utente cercaUtentePerEmail(String email) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Utente utente = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM utenti WHERE email = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                utente = new Utente(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return utente;
    }
}
