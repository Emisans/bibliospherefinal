package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Prodotto;
import Util.DriverManagerConnectionPool;

public class ProdottoDao {

    public void insert(Prodotto p) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO prodotto (titolo, autore, nome, descrizione, prezzo, iva, quantita) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getAutore());
            ps.setString(3, p.getNome());
            ps.setString(4, p.getDescrizione());
            ps.setDouble(5, p.getPrezzo());
            ps.setDouble(6, p.getIva());
            ps.setInt(7, p.getQuantita());
            ps.executeUpdate();
        }
    }

    public void update(Prodotto p) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE prodotto SET titolo=?, autore=?, nome=?, descrizione=?, prezzo=?, iva=?, quantita=? WHERE id=?")) {

            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getAutore());
            ps.setString(3, p.getNome());
            ps.setString(4, p.getDescrizione());
            ps.setDouble(5, p.getPrezzo());
            ps.setDouble(6, p.getIva());
            ps.setInt(7, p.getQuantita());
            ps.setInt(8, p.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Prodotto doRetrieveById(int id) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM prodotto WHERE id = ?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prodotto p = new Prodotto();
                    p.setId(rs.getInt("id"));
                    p.setTitolo(rs.getString("titolo"));
                    p.setAutore(rs.getString("autore"));
                    p.setNome(rs.getString("nome"));
                    p.setDescrizione(rs.getString("descrizione"));
                    p.setPrezzo(rs.getDouble("prezzo"));
                    p.setIva(rs.getDouble("iva"));
                    p.setQuantita(rs.getInt("quantita"));
                    return p;
                }
                return null;
            }
        }
    }

    public List<Prodotto> doRetrieveAll() throws SQLException {
        List<Prodotto> lista = new ArrayList<>();

        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM prodotto ORDER BY id DESC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id"));
                p.setTitolo(rs.getString("titolo"));
                p.setAutore(rs.getString("autore"));
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setIva(rs.getDouble("iva"));
                p.setQuantita(rs.getInt("quantita"));
                lista.add(p);
            }
        }

        return lista;
    }

    // Alias compatibili con altre servlet
    public List<Prodotto> findAll() throws SQLException {
        return doRetrieveAll();
    }

    public Prodotto findById(int id) throws SQLException {
        return doRetrieveById(id);
    }
}
