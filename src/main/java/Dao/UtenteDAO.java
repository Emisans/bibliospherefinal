package Dao;

import java.sql.*;
import java.util.*;

public class UtenteDAO {

    public static Utente doRetrieveByUsername(String username) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliosphere", "root", "password");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti WHERE username = ?");
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        Utente utente = null;
        if (rs.next()) {
            utente = new Utente();
            utente.setId(rs.getInt("id"));
            utente.setNome(rs.getString("nome"));
            utente.setEmail(rs.getString("email"));
            utente.setUsername(rs.getString("username"));
            utente.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        con.close();

        return utente;
    }

    public static boolean doSave(Utente u) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliosphere", "root", "password");
        PreparedStatement ps = con.prepareStatement("INSERT INTO utenti (nome, email, username, password) VALUES (?, ?, ?, ?)");
        ps.setString(1, u.getNome());
        ps.setString(2, u.getEmail());
        ps.setString(3, u.getUsername());
        ps.setString(4, u.getPassword());

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result > 0;
    }
}


