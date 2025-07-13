<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordine" %>
<%@ page import="model.CarrelloItem" %>
<html>
<head>
    <title>I tuoi ordini</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <h1>I tuoi ordini</h1>

    <%
        List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
        if (ordini != null && !ordini.isEmpty()) {
            for (Ordine ordine : ordini) {
    %>
        <div class="ordine">
            <h3>Ordine #<%= ordine.getId() %> - <%= ordine.getDataOrdine() %></h3>
            <ul>
                <% for (CarrelloItem item : ordine.getDettagli()) { %>
                    <li>
                        <strong><%= item.getProdotto().getNome() %></strong> - 
                        Quantità: <%= item.getQuantita() %> - 
                        Prezzo unitario: €<%= item.getProdotto().getPrezzo() %> + IVA: <%= item.getProdotto().getIva() %>%
                    </li>
                <% } %>
            </ul>
            <p><strong>Totale:</strong> €<%= ordine.getTotale() %></p>
        </div>
        <hr>
    <% 
            }
        } else { 
    %>
        <p>Non hai ancora effettuato ordini.</p>
    <% } %>

    <p><a href="CatalogoServlet">Torna al catalogo</a></p>
</body>
</html>