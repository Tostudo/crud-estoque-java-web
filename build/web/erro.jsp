<%-- 
    Document   : erro
    Created on : 22/05/2023, 09:38:26
    Author     : PTOLEDO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Falha na operação</title>
        <link rel="stylesheet" href="css/erro.css">
    </head>
    <body>
        <%
            String msg = (String) request.getAttribute("message");
        %>
        <h1><%out.println(msg);%> não realizado</h1>

        <!-- Botão para voltar à página inicial -->
        <button type="button" onclick="window.location.href = 'index.html'">
            Voltar à Página Inicial
        </button>

    </body>
</html>
