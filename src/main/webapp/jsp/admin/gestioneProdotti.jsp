<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>
<%
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
    String messaggio = (String) request.getAttribute("messaggio");
    Prodotto daModificare = (Prodotto) request.getAttribute("prodottoDaModificare");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Prodotti</title>
</head>
<body>
    <h1>Gestione Prodotti</h1>

    <% if (messaggio != null) { %>
        <p><%= messaggio %></p>
    <% } %>

    <h2>Lista Prodotti</h2>
    <table border="1">
        <tr>
            <th>ID</th><th>Titolo</th><th>Autore</th><th>Prezzo</th><th>IVA</th><th>Azioni</th>
        </tr>
        <% for (Prodotto p : prodotti) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getTitolo() %></td>
                <td><%= p.getAutore() %></td>
                <td><%= p.getPrezzo() %></td>
                <td><%= p.getIva() %></td>
                <td>
                    <form action="ProdottoAdminServlet" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= p.getId() %>">
                        <input type="submit" name="azione" value="Modifica">
                    </form>
                    <form action="ProdottoAdminServlet" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= p.getId() %>">
                        <input type="submit" name="azione" value="Cancella" onclick="return confirm('Confermi la cancellazione?');">
                    </form>
                </td>
            </tr>
        <% } %>
    </table>

    <h2>Nuovo Prodotto</h2>
    <form action="ProdottoAdminServlet" method="post">
        <input type="hidden" name="azione" value="Aggiungi">
        Titolo: <input type="text" name="titolo"><br>
        Autore: <input type="text" name="autore"><br>
        Nome: <input type="text" name="nome"><br>
        Descrizione: <input type="text" name="descrizione"><br>
        Prezzo: <input type="number" step="0.01" name="prezzo"><br>
        IVA: <input type="number" step="0.01" name="iva"><br>
        Quantità: <input type="number" name="quantita"><br>
        <input type="submit" value="Inserisci">
    </form>

    <% if (daModificare != null) { %>
        <h2>Modifica Prodotto</h2>
        <form action="ProdottoAdminServlet" method="post">
            <input type="hidden" name="azione" value="Aggiorna">
            <input type="hidden" name="id" value="<%= daModificare.getId() %>">
            Titolo: <input type="text" name="titolo" value="<%= daModificare.getTitolo() %>"><br>
            Autore: <input type="text" name="autore" value="<%= daModificare.getAutore() %>"><br>
            Nome: <input type="text" name="nome" value="<%= daModificare.getNome() %>"><br>
            Descrizione: <input type="text" name="descrizione" value="<%= daModificare.getDescrizione() %>"><br>
            Prezzo: <input type="number" step="0.01" name="prezzo" value="<%= daModificare.getPrezzo() %>"><br>
            IVA: <input type="number" step="0.01" name="iva" value="<%= daModificare.getIva() %>"><br>
            Quantità: <input type="number" name="quantita" value="<%= daModificare.getQuantita() %>"><br>
            <input type="submit" value="Salva modifiche">
        </form>
    <% } %>
</body>
</html>
