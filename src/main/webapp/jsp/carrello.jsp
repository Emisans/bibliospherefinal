<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Carrello" %>
<%@ page import="model.CarrelloItem" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Il tuo carrello</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <h1>Il tuo carrello</h1>
    <%
        Carrello carrello = (Carrello) request.getAttribute("carrello");
        if (carrello != null && !carrello.isEmpty()) {
            List<CarrelloItem> items = carrello.getItems();
    %>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Nome</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Totale</th>
                <th>Azioni</th>
            </tr>
            <%
                for (CarrelloItem item : items) {
                    int id = item.getProdotto().getId();
            %>
            <tr>
                <td><%= item.getProdotto().getNome() %></td>
                <td>€<%= item.getProdotto().getPrezzo() %></td>
                <td>
                    <form action="AggiornaCarrello" method="post">
                        <input type="hidden" name="id" value="<%= id %>">
                        <input type="number" name="quantita" value="<%= item.getQuantita() %>" min="1">
                        <button type="submit">Aggiorna</button>
                    </form>
                </td>
                <td>€<%= item.getProdotto().getPrezzo() * item.getQuantita() %></td>
                <td>
                    <form action="RimuoviCarrello" method="post">
                        <input type="hidden" name="id" value="<%= id %>">
                        <button type="submit">Rimuovi</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>

        <p><strong>Totale carrello: €<%= carrello.getTotale() %></strong></p>

        <form action="ConfermaOrdine" method="post">
            <button type="submit">Conferma Ordine</button>
        </form>
    <% } else { %>
        <p>Il carrello è vuoto.</p>
    <% } %>

    <p><a href="CatalogoServlet">Torna al catalogo</a></p>
</body>
</html>