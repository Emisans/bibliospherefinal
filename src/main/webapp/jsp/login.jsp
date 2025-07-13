<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <h1>Accedi a BiblioSphere</h1>
    <% String error = (String)request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>
    <form action="../LoginServlet" method="post">
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Accedi">
    </form>
</body>
</html>

