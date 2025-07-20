package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Utente;
import Util.DriverManagerConnectionPool;

public class UtenteDAO {

    public static boolean doSave(Utente utente) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManagerConnectionPool.getConnection();
            String sql = "INSERT INTO utente (nome, email, username, password, ruolo) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getUsername());
            ps.setString(4, utente.getPassword());
            ps.setString(5, utente.getRuolo());

            int rows = ps.executeUpdate();
            return rows > 0;

        } finally {
            if (ps != null) ps.close();
            if (con != null) DriverManagerConnectionPool.releaseConnection(con);
        }
    }

    public static Utente doRetrieveByEmail(String email) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DriverManagerConnectionPool.getConnection();
            String sql = "SELECT * FROM utente WHERE email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                Utente u = new Utente();
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRuolo(rs.getString("ruolo"));
                return u;
            }

            return null;

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) DriverManagerConnectionPool.releaseConnection(con);
        }
    }
}
