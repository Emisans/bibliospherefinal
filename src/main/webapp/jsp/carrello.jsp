<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Carrello" %>
<%@ page import="Model.CarrelloItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%
    DecimalFormat df = new DecimalFormat("0.00");
    Carrello carrello = (Carrello) request.getAttribute("carrello");
%>
<html>
<head>
    <title>Il tuo carrello</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp" />

    <h1>Il tuo carrello</h1>

    <c:if test="${not empty carrello and !carrello.isEmpty()}">
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Nome</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Totale</th>
                <th>Azioni</th>
            </tr>
            <%
                for (CarrelloItem item : carrello.getItems()) {
                    int id = item.getProdotto().getId();
                    double prezzo = item.getProdotto().getPrezzo();
                    int qta = item.getQuantita();
                    double totale = prezzo * qta;
            %>
            <tr>
                <td><%= item.getProdotto().getNome() %></td>
                <td>€<%= df.format(prezzo) %></td>
                <td>
                    <form action="AggiornaCarrello" method="post">
                        <input type="hidden" name="id" value="<%= id %>">
                        <input type="number" name="quantita" value="<%= qta %>" min="1">
                        <button type="submit">Aggiorna</button>
                    </form>
                </td>
                <td>€<%= df.format(totale) %></td>
                <td>
                    <form action="RimuoviCarrello" method="post">
                        <input type="hidden" name="id" value="<%= id %>">
                        <button type="submit">Rimuovi</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>

        <p><strong>Totale carrello: €<%= df.format(carrello.getTotale()) %></strong></p>

        <form action="ConfermaOrdine" method="post">
            <button type="submit">Conferma Ordine</button>
        </form>
    </c:if>

    <c:if test="${empty carrello or carrello.isEmpty()}">
        <p>Il carrello è vuoto.</p>
    </c:if>

    <p><a href="CatalogoServlet">⬅️ Torna al catalogo</a></p>

    <jsp:include page="fragments/footer.jsp" />
</body>
</html>
