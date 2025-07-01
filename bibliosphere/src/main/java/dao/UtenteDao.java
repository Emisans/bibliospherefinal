package dao;

import model.Utente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtenteDao {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root"; // cambia se hai user diverso
    private static final String PASSWORD = ""; // cambia se hai una password

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
}