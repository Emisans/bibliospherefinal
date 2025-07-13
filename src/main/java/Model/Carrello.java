package Model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private List<CarrelloItem> items;

    public Carrello() {
        items = new ArrayList<>();
    }

    public void aggiungi(Prodotto prodotto, int quantita) {
        for (CarrelloItem item : items) {
            if (item.getProdotto().getId() == prodotto.getId()) {
                item.setQuantita(item.getQuantita() + quantita);
                return;
            }
        }
        items.add(new CarrelloItem(prodotto, quantita));
    }

    public void aggiorna(int prodottoId, int nuovaQuantita) {
        for (CarrelloItem item : items) {
            if (item.getProdotto().getId() == prodottoId) {
                if (nuovaQuantita <= 0) {
                    items.remove(item);
                } else {
                    item.setQuantita(nuovaQuantita);
                }
                return;
            }
        }
    }

    public void rimuovi(int prodottoId) {
        items.removeIf(item -> item.getProdotto().getId() == prodottoId);
    }

    public List<CarrelloItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void svuota() {
        items.clear();
    }

    public double getTotale() {
        double totale = 0;
        for (CarrelloItem item : items) {
            double prezzoConIva = item.getProdotto().getPrezzo() * (1 + item.getProdotto().getIva() / 100);
            totale += prezzoConIva * item.getQuantita();
        }
        return Math.round(totale * 100.0) / 100.0;
    }
}

