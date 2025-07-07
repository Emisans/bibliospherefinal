package dao;

import model.Prodotto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDao {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public List<Prodotto> findAll() throws SQLException {
        List<Prodotto> prodotti = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM prodotti";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Prodotto p = new Prodotto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descrizione"),
                    rs.getDouble("prezzo"),
                    rs.getDouble("iva"),
                    rs.getInt("quantita"),
                    rs.getString("immagine")
                );
                prodotti.add(p);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return prodotti;
    }
}
