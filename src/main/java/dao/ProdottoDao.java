package dao;

import model.Prodotto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDao {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public List<Prodotto> findAll() {
        List<Prodotto> prodotti = new ArrayList<>();
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prodotti")
        ) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    public Prodotto findById(int id) {
        String sql = "SELECT * FROM prodotti WHERE id = ?";
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Prodotto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getDouble("prezzo"),
                        rs.getDouble("iva"),
                        rs.getInt("quantita"),
                        rs.getString("immagine")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
