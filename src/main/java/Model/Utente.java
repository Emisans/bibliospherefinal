package Model;

public class Utente {
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    private String ruolo; // "utente" o "admin"

    public Utente() {
        this.ruolo = "utente";
    }

    public Utente(String nome, String cognome, String email, String username, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email +
                ", username=" + username + ", ruolo=" + ruolo + "]";
    }
}
