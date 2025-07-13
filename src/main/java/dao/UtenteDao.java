package dao;

import model.Carrello;
import model.CarrelloItem;
import model.Ordine;
import model.Prodotto;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public void salvaUtente(Utente utente) throws SQLException {
        String sql = "INSERT INTO utenti (nome, email, username, password) VALUES (?, ?, ?, ?)";
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, utente.getNome());
            stmt.setString(2, utente.getEmail());
            stmt.setString(3, utente.getUsername());
            stmt.setString(4, utente.getPassword());
            stmt.executeUpdate();
        }
    }

    public boolean emailEsistente(String email) throws SQLException {
        String sql = "SELECT id FROM utenti WHERE email = ?";
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Utente cercaUtentePerEmail(String email) throws SQLException {
        String sql = "SELECT * FROM utenti WHERE email = ?";
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Utente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null;
    }
}

