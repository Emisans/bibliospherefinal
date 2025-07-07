<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utenteLoggato");
    if (utente == null) {
        response.sendRedirect("login.jsp"); // Se non loggato, torna al login
        return;
    }
%>
<html>
<head>
    <title>Area Personale</title>
</head>
<body>
    <h1>Benvenuto, <%= utente.getNome() %>!</h1>
    <p>Email: <%= utente.getEmail() %></p>
    <p>Username: <%= utente.getUsername() %></p>
    <a href="logout.jsp">Logout</a>
</body>
</html>
