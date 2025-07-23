package Model;

public class Prodotto {
    private int id;
    private String titolo;
    private String autore;
    private String nome;
    private String descrizione;
    private double prezzo;
    private double iva;
    private int quantita;

    public Prodotto() {}

    // Costruttore usato in OrdineDao
    public Prodotto(int id, String titolo, String autore, double prezzo, double iva, int quantita, String nome) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.iva = iva;
        this.quantita = quantita;
        this.nome = nome;
    }

    // Costruttore completo
    public Prodotto(int id, String titolo, String autore, String nome, String descrizione,
                    double prezzo, double iva, int quantita) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.iva = iva;
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "Prodotto [id=" + id + ", titolo=" + titolo + ", autore=" + autore + ", nome=" + nome +
               ", descrizione=" + descrizione + ", prezzo=" + prezzo + ", iva=" + iva + ", quantita=" + quantita + "]";
    }
}
