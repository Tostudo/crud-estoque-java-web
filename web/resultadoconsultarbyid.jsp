<%-- 
    Document   : resultadocosultarbyid
    Created on : 22/05/2023, 10:01:24
    Author     : PTOLEDO
--%>

<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado Cosultar By ID</title>
        <link rel="stylesheet" href="css/consultarbyid.css">
    </head>
    <body>
        <h1>Resultado Consultar By ID</h1>
        <%
            Produto p = (Produto) request.getAttribute("p");
        %>
        <%if (p.getDescricao() != null) {%>
        ID..........: <%out.print(p.getId());%> <BR>
        Descrição...: <%out.print(p.getDescricao());%> <BR>
        Preço.......: <%out.print(p.getPreco());%> <BR>
        <%} else {%>
        <h2>Produto não encontrado.</h2>
        <%}%>
    </body>
</html>
