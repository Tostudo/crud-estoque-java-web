<%-- 
    Document   : resultadocosultaratualizar
    Created on : 22/05/2023, 10:54:41
    Author     : PTOLEDO
--%>

<%@page import="model.Setor"%>
<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Produto</title>
    </head>
    <body>
        <h1>Atualizar Produto</h1>
        <%
            Produto p = (Produto) request.getAttribute("p");
            
            String dataFormatada = "";
            if (p.getUltimaatualizacao() != null) {
                dataFormatada = p.getUltimaatualizacao().toString();
            }
        %>
        <% if (p.getDescricao() != null) {%>

        <form name="f1" action="controle_produto" method="GET">
            ID: <%= p.getId()%> 
            <input type="hidden" name="txtid" value="<%= p.getId()%>"> <br>

            Descrição: <input type="text" name="txtdescricao" value="<%= (p.getDescricao() != null ? p.getDescricao() : "")%>"> <br>
            Marca: <input type="text" name="txtmarca" value="<%= (p.getMarca() != null ? p.getMarca() : "")%>"> <br>
            Quantidade: <input type="number" name="txtquantidade" value="<%= p.getQuantidade()%>"> <br>
            Preço: <input type="text" name="txtpreco" value="<%= p.getPreco()%>"> <br>
            Última Atualização: <input type="date" name="txtultimaatualizacao" value="<%= dataFormatada%>"> <br>

            <%
                List<Setor> setor = (List<Setor>) request.getAttribute("setor");
                int idSetorAtual = p.getIdSetor();
            %>

            Setor:
            <select name="comboboxsetor">
                <% for (Setor s : setor) {%>
                <option value="<%= s.getId()%>" <%= (s.getId() == idSetorAtual ? "selected" : "")%>>
                    <%= s.getNome()%> - Dir.: <%= s.getDiretor()%>
                </option>
                <% } %>
            </select>
            <br>

            <input type="submit" name="op" value="CONFIRMAR ATUALIZAÇÃO">
        </form>

        <% } else { %>
        <h2>Produto não encontrado.</h2>
        <% }%>
    </body>
</html>
