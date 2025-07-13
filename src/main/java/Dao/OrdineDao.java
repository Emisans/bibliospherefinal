package Dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Model.Carrello;
import Model.CarrelloItem;
import Model.Ordine;

public class OrdineDao {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliosphere";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public int salvaOrdine(Carrello carrello, String email) {
        int ordineId = -1;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            con.setAutoCommit(false);

            Integer utenteId = trovaIdUtente(con, email);

            String sqlOrdine = "INSERT INTO ordini (utente_id, totale) VALUES (?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sqlOrdine, Statement.RETURN_GENERATED_KEYS)) {
                if (utenteId != null) {
                    stmt.setInt(1, utenteId);
                } else {
                    stmt.setNull(1, Types.INTEGER);
                }
                stmt.setDouble(2, carrello.getTotale());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    ordineId = rs.getInt(1);
                }
            }

            String sqlDettaglio = "INSERT INTO dettagli_ordine (ordine_id, prodotto_id, quantita, prezzo, iva) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sqlDettaglio)) {
                for (CarrelloItem item : carrello.getItems()) {
                    stmt.setInt(1, ordineId);
                    stmt.setInt(2, item.getProdotto().getId());
                    stmt.setInt(3, item.getQuantita());
                    stmt.setDouble(4, item.getProdotto().getPrezzo());
                    stmt.setDouble(5, item.getProdotto().getIva());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordineId;
    }

    private Integer trovaIdUtente(Connection con, String email) throws SQLException {
        String sql = "SELECT id FROM utenti WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    public List<Ordine> trovaOrdiniPerEmail(String email) {
        List<Ordine> ordini = new ArrayList<>();
        String sql = "SELECT o.id, o.data_ordine, o.totale FROM ordini o JOIN utenti u ON o.utente_id = u.id WHERE u.email = ? ORDER BY o.data_ordine DESC";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ordine ordine = new Ordine( // Usa costruttore completo
                        rs.getInt("id"),
                        0, // utenteId non necessario qui
                        rs.getTimestamp("data_ordine").toLocalDateTime(),
                        rs.getDouble("totale")
                    );
                    ordine.setDettagli(recuperaDettagliOrdine(con, ordine.getId()));
                    ordini.add(ordine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordini;
    }

    private List<CarrelloItem> recuperaDettagliOrdine(Connection con, int ordineId) throws SQLException {
        List<CarrelloItem> dettagli = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.descrizione, p.prezzo, p.iva, p.quantita, p.immagine, d.quantita FROM dettagli_ordine d JOIN prodotti p ON d.prodotto_id = p.id WHERE d.ordine_id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, ordineId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CarrelloItem item = new CarrelloItem(
                        new Model.Prodotto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("descrizione"),
                            rs.getDouble("prezzo"),
                            rs.getDouble("iva"),
                            rs.getInt("quantita"),
                            rs.getString("immagine")
                        ),
                        rs.getInt("quantita")
                    );
                    dettagli.add(item);
                }
            }
        }
        return dettagli;
    }
}
