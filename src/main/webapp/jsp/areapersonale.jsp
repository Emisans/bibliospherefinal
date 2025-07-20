<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente == null) {
        response.sendRedirect("${pageContext.request.contextPath}/jsp/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Personale</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>Benvenuto, <%= utente.getNome() %>!</h2>
    <p>Email: <%= utente.getEmail() %></p>
    <p>Username: <%= utente.getUsername() %></p>

    <h3>Cosa vuoi fare?</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/CatalogoServlet">Vai al Catalogo</a></li>
        <li><a href="${pageContext.request.contextPath}/CarrelloServlet">Visualizza Carrello</a></li>
        <li><a href="${pageContext.request.contextPath}/OrdiniUtenteServlet">I tuoi Ordini</a></li>
        <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
    </ul>
</body>
</html>
