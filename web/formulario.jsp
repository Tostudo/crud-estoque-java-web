<%-- 
    Document   : formulario
    Created on : 19 de mai. de 2025, 11:19:12
    Author     : joao.campos
--%>

<%@page import="model.Setor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Produto</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/formulario.css"><!-- comment -->
    </head>
    <body>



        <form name="f1" action="controle_produto" method="GET">
            <div><h1>CADASTRO DE PRODUTO</h1></div>

            DESCRIÇÃO <input type="text" name="txtdescricao"><br>
            MARCA<input type="text" name="txtmarca"><br>
            QUANTIDADE<input type="number" name="txtquantidade"><br>
            PREÇO<input type="text" name="txtpreco"><br>
            ÚLTIMA ATUALIZAÇÃO<input type="date" name="txtultimaatualizacao"><br><br>


            <%
                List<Setor> setores = (List<Setor>) request.getAttribute("setores");
                if (setores != null) {
            %>

            SETOR..............:

            <select name="txtidsetor">



                <% for (Setor s : setores) {%>
                <option value="<%= s.getId()%>">
                    <%= s.getNome()%> - Dir.: <%= s.getDiretor()%>
                </option>
                <% } %>
            </select>
            <%
            } else {
            %>
            <p style="color:red;">Setores não carregados. Acesse pela opção correta do menu.</p>
            <%
                }
            %>




            <input type="submit" name="op" value="CADASTRAR">
            <input type="submit" name="op" value="CONSULTAR TODOS">
        </form>

        <!-- Botão para voltar à página inicial -->
        <button type="button" onclick="window.location.href = 'index.html'">
            Voltar à Página Inicial
        </button>

    </body>
</html>
