<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente == null || !"admin".equals(session.getAttribute("ruolo"))) {
        response.sendRedirect("../errore403.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Area Admin - BiblioSphere</title>
</head>
<body>
    <h1>Benvenuto, <%= utente.getNome() %> (Admin)</h1>

    <ul>
        <li><a href="gestioneProdotti.jsp">Gestione Prodotti</a></li>
        <li><a href="gestioneOrdini.jsp">Gestione Ordini</a></li>
        <li><a href="../LogoutServlet">Logout</a></li>
    </ul>
</body>
</html>
