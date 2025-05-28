<%-- 
    Document   : listar_setores
    Created on : 28 de mai. de 2025, 11:38:48
    Author     : joao.campos
--%>

<%@page import="java.util.List"%>
<%@page import="model.Setor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Setores</title>
        <link rel="stylesheet" href="css/consultatodos.css">
    </head>
    <body>
        <h1>Todos os Setores</h1>

        <%
            List<Setor> setores = (List<Setor>) request.getAttribute("setores");
        %>

        <%
            if (setores == null) {
                out.println("<p style='color:red;'>A lista de setores está nula. Verifique se o atributo 'setores' foi definido corretamente no controller.</p>");
            } else {
                out.println("<p style='color:green;'>Foram encontrados " + setores.size() + " setores.</p>");
            }
        %>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Diretor</th>
                <th>Qtd. Funcionários</th>
                <th>Remover</th>
                <th>Editar</th>
            </tr>

            <% if (setores != null) {
                    for (Setor s : setores) {%>
            <tr>
                <td><%= s.getId()%></td>
                <td><%= s.getNome()%></td>
                <td><%= s.getDiretor()%></td>
                <td><%= s.getQtdfuncionarios()%></td>

                <td align="center">
                    <a href="controle_setor?op=DELETAR&txtid=<%= s.getId()%>">
                        <img src="images/lixeira01.png" width="25" height="25" alt="Excluir">
                    </a>
                </td>

                <td align="center">
                    <a href="controle_setor?op=CONSULTARBYID&txtid=<%= s.getId()%>">
                        <img src="images/editar01.png" width="25" height="25" alt="Editar">
                    </a>
                </td>
            </tr>
            <% }
                }%>
        </table>

        <!-- Botão para voltar à página inicial -->
        <button type="button" onclick="window.location.href = 'index.html'">
            Voltar à Página Inicial
        </button>
    </body>
</html>

