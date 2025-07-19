<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente == null) {
        response.sendRedirect("jsp/login.jsp"); // ✅ reindirizza se non loggato
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Personale</title>
</head>
<body>
    <h2>Benvenuto, <%= utente.getNome() %>!</h2>
    <p>Email: <%= utente.getEmail() %></p>
    <p>Username: <%= utente.getUsername() %></p>
    <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> <!-- ✅ logout corretto -->
</body>
</html>
