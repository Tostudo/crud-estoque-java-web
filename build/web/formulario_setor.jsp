<%-- 
    Document   : formulario_setor
    Created on : 27 de mai. de 2025, 14:02:23
    Author     : joao.campos
--%>

<%@page import="model.Setor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cadastrar ou Editar Setor</title>
        <link rel="stylesheet" href="css/formulario_setor.css">
    </head>
    <body>
        <%
            Setor setor = (Setor) request.getAttribute("setor");
            boolean isEdicao = (setor != null);
        %>

        <h1><%= isEdicao ? "Editar Setor" : "Cadastrar Novo Setor" %></h1>

        <form action="controle_setor" method="GET">
            <input type="hidden" name="op" value="<%= isEdicao ? "CONFIRMARATUALIZAÇÃO" : "CADASTRAR" %>">
            <% if (isEdicao) { %>
                <input type="hidden" name="txtid" value="<%= setor.getId() %>">
            <% } %>

            Nome do Setor:
            <input type="text" name="txtnome" required value="<%= isEdicao ? setor.getNome() : "" %>"> <br><br>

            Diretor Responsável:
            <input type="text" name="txtdiretor" required value="<%= isEdicao ? setor.getDiretor() : "" %>"> <br><br>

            Quantidade de Funcionários:
            <input type="number" name="txtqtdfuncionarios" required min="1" value="<%= isEdicao ? setor.getQtdfuncionarios() : "" %>"> <br><br>

            <input type="submit" value="<%= isEdicao ? "Atualizar Setor" : "Cadastrar Setor" %>">
        </form>

        <br>
        <button onclick="window.location.href = 'index.html'">Voltar</button>
    </body>
</html>

