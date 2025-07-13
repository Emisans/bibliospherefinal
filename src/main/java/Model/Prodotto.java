package Model;

public class Prodotto {
    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private double iva;
    private int quantita;
    private String immagine;

    public Prodotto(int id, String nome, String descrizione, double prezzo, double iva, int quantita, String immagine) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.iva = iva;
        this.quantita = quantita;
        this.immagine = immagine;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public double getPrezzo() { return prezzo; }
    public double getIva() { return iva; }
    public int getQuantita() { return quantita; }
    public String getImmagine() { return immagine; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }
    public void setIva(double iva) { this.iva = iva; }
    public void setQuantita(int quantita) { this.quantita = quantita; }
    public void setImmagine(String immagine) { this.immagine = immagine; }
}

