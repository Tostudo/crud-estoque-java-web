<%-- 
    Document   : filtrosetor
    Created on : 21 de mai. de 2025, 11:22:53
    Author     : joao.campos
--%>

<%@page import="model.Setor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Filtrar Produtos por Setor</title>
    <link rel="stylesheet" href="css/filtrosetor.css">
</head>
<body>
    <h1>Filtrar Produtos por Setor</h1>

    <form action="controle_produto" method="GET">
        <input type="hidden" name="op" value="FILTRAR_POR_SETOR">
        
        <label for="idSetor">Escolha o Setor:</label>
        <select name="idSetor">
            
            <!-- Opção para todos os setores -->
                
            <option value="0">Todos os Setores</option>
            
            
            <% 
                List<Setor> setor = (List<Setor>) request.getAttribute("setor");
                if (setor != null) {
                    for (Setor s : setor) {
            %>
                        <option value="<%= s.getId() %>">
                            <%= s.getNome() %> - Dir.: <%= s.getDiretor() %>
                        </option>
            <%
                    }
                } else {
            %>
                <option disabled>Setores não carregados.</option>
            <%
                }
            %>
        </select>

        <input type="submit" value="Filtrar">
    </form>

    <br>
    <button onclick="window.location.href='index.html'">Voltar</button>
</body>
</html>