<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente == null || !"admin".equals(utente.getRuolo())) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Dashboard Amministratore</title>
</head>
<body>
    <h1>Benvenuto, <%= utente.getNome() %> (Amministratore)</h1>
    <ul>
        <li><a href="prodotti.jsp">Gestione prodotti</a></li>
        <li><a href="ordini.jsp">Visualizza ordini</a></li>
        <li><a href="../LogoutServlet">Logout</a></li>
    </ul>
</body>
</html>
