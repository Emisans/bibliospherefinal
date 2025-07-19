<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <h1>Registrati a BiblioSphere</h1>

    <% String errore = (String) request.getAttribute("errore"); %>
    <% if (errore != null) { %>
        <p style="color:red;">
            <% if (errore.equals("password")) { %>
                Le password non coincidono.
            <% } else if (errore.equals("emailEsistente")) { %>
                Email già registrata.
            <% } else { %>
                Errore durante la registrazione.
            <% } %>
        </p>
    <% } %>

    <form action="<%= request.getContextPath() %>/RegistrazioneServlet" method="post">
        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <label for="confirm">Conferma password:</label><br>
        <input type="password" id="confirm" name="confirm" required><br><br>

        <input type="submit" value="Registrati">
    </form>
</body>
</html>
