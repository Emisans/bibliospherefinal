<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordine Confermato</title>
</head>
<body>
    <h2>Grazie per il tuo ordine!</h2>
    <p>Il tuo ordine è stato registrato con successo.</p>
    <p>ID Ordine: <%= request.getAttribute("ordineId") %></p>

    <p><a href="CatalogoServlet">Torna al catalogo</a></p>
    <p><a href="OrdiniUtenteServlet">Visualizza i tuoi ordini</a></p>
</body>
</html>