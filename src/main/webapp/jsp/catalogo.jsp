<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Catalogo Prodotti</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <p><a href="CarrelloServlet"><b>Visualizza carrello</b></a></p>
    <h1>Catalogo Prodotti</h1>
    <%
        List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
        if (prodotti != null && !prodotti.isEmpty()) {
            for (Prodotto p : prodotti) {
    %>
        <div class="prodotto">
            <h2><%= p.getNome() %></h2>
            <p><%= p.getDescrizione() %></p>
            <p>Prezzo: €<%= p.getPrezzo() %> + IVA: <%= p.getIva() %>%</p>
            <p>Disponibili: <%= p.getQuantita() %></p>
            <form action="AggiungiCarrello" method="post">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <input type="number" name="quantita" value="1" min="1" max="<%= p.getQuantita() %>">
                <button type="submit">Aggiungi al carrello</button>
            </form>
        </div>
        <hr>
    <%
            }
        } else {
    %>
        <p>Nessun prodotto trovato.</p>
    <%
        }
    %>
</body>
</html>


