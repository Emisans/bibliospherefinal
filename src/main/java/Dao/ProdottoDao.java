package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Prodotto;

public class ProdottoDao {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carica driver solo una volta
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Prodotto> findAll() {
        List<Prodotto> prodotti = new ArrayList<>();

        String sql = "SELECT * FROM prodotti";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
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
            System.err.println("Errore durante il recupero dei prodotti: " + e.getMessage());
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
            System.err.println("Errore durante il recupero del prodotto con ID " + id + ": " + e.getMessage());
        }

        return null;
    }
}

