package model;

import java.time.LocalDateTime;
import java.util.List;

public class Ordine {
    private int id;
    private int utenteId;
    private LocalDateTime dataOrdine;
    private double totale;
    private List<CarrelloItem> dettagli;

    // ⚠️ Costruttore vuoto richiesto da Eclipse
    public Ordine() {
    }

    public Ordine(int id, int utenteId, LocalDateTime dataOrdine, double totale) {
        this.id = id;
        this.utenteId = utenteId;
        this.dataOrdine = dataOrdine;
        this.totale = totale;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public List<CarrelloItem> getDettagli() {
        return dettagli;
    }

    public void setDettagli(List<CarrelloItem> dettagli) {
        this.dettagli = dettagli;
    }
}

