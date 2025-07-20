package Dao;

import java.sql.*;

import Model.Utente;

public class UtenteDAO {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL non trovato!");
            e.printStackTrace();
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root";
    private static final String PASS = "admin";

    // 🔎 Recupera un utente dato lo username
    public static Utente doRetrieveByUsername(String username) {
        Utente utente = null;

        String sql = "SELECT * FROM utenti WHERE username = ?";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setRuolo(rs.getString("ruolo")); // ✅ campo ruolo
            }

        } catch (SQLException e) {
            System.out.println("ERRORE SQL in doRetrieveByUsername:");
            e.printStackTrace();
        }

        return utente;
    }

    // 💾 Salva un nuovo utente nel database
    public static boolean doSave(Utente u) {
        String sql = "INSERT INTO utenti (nome, email, username, password, ruolo) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getRuolo()); // ✅ campo ruolo

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("ERRORE SQL in doSave:");
            e.printStackTrace();
            return false;
        }
    }
}

