<%-- 
    Document   : resultadoconsultartodos
    Created on : 22/05/2023, 10:40:19
    Author     : PTOLEDO
--%>

<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado Consultar Todos</title>
    </head>
    <body>
        <h1>Todos os Produtos</h1>

        <!-- Link para o filtro por setor -->
        <a href="controle_produto?op=abrir_filtro">Filtrar por Setor</a>


        <%
            List<Produto> lprod = (List<Produto>) request.getAttribute("lprod");
        %>

        <%
            if (lprod == null) {
                out.println("<p style='color:red;'>A lista de produtos está nula. Verifique se o atributo 'lprod' foi definido corretamente no controller.</p>");
            } else {
                out.println("<p style='color:green;'>Foram encontrados " + lprod.size() + " produtos.</p>");
            }
        %>


        <table border="1">
            <tr>
                <th>ID</th>
                <th>Descrição</th>
                <th>Marca</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th>Ultima Atualização</th>
                <th>Setor</th>
                <th>Remover</th>
                <th>Editar</th>
            </tr>

            <%for (Produto p : lprod) {%>
            <tr>                
                <td><%=p.getId()%></td>
                <td><%=p.getDescricao()%></td>
                <td><%=p.getMarca()%></td>
                <td><%=p.getQuantidade()%></td>
                <td><%=p.getPreco()%></td>
                <td><%=p.getUltimaatualizacao()%></td>
                <td><%=p.getNomeSetor()%></td>

                <td align="center"><a href="controle_produto?op=DELETAR&txtid=<%out.print(p.getId());%>"><img src="images/lixeira01.png" width="25" height="25"></a></td>

                <td align="center">
                    <a href="controle_produto?op=ATUALIZAR&txtid=<%=p.getId()%>">
                        <img src="images/editar01.png" width="25" height="25">
                    </a>
                </td>

            </tr>
            <%}%>

        </table>

        <!-- Botão para voltar à página inicial -->
        <button type="button" onclick="window.location.href = 'index.html'">
            Voltar à Página Inicial
        </button>

    </body>
</html>
