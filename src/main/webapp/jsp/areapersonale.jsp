<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Utente" %>
<html>
<head>
    <title>Area Personale</title>
</head>
<body>
<%
    Utente utente = (Utente) session.getAttribute("utenteLoggato");
    if (utente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
    <h1>Ciao, <%= utente.getNome() %>!</h1>
    <p>Email: <%= utente.getEmail() %></p>
    <p><a href="CatalogoServlet">Vai al catalogo</a></p>
    <p><a href="LogoutServlet">Logout</a></p>
</body>
</html>
