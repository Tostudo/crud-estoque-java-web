<%-- 
    Document   : formulario
    Created on : 19 de mai. de 2025, 11:19:12
    Author     : joao.campos
--%>

<%@page import="model.Setor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Produto</title>
        <link rel="stylesheet" href="css/formulario.css">
    </head>
    <body>
        <video autoplay muted loop playsinline id="bg-video">
            <source src="videos/solo-level.mp4" type="video/mp4">
            Seu navegador não suporta vídeo em background.
        </video>

        <!-- Cabeçalho -->
        <header>
            <h1>Cadastro de Produto</h1>
        </header>

        <!-- Conteúdo principal -->
        <main>
            <section class="form-container">
                <form name="f1" action="controle_produto" method="GET">
                    <!-- ÚNICA DIV para todos os campos -->
                    <div class="form-group">
                        <label for="descricao">Descrição:</label>
                        <input type="text" name="txtdescricao" id="descricao" required>

                        <label for="marca">Marca:</label>
                        <input type="text" name="txtmarca" id="marca" required>

                        <label for="quantidade">Quantidade:</label>
                        <input type="number" name="txtquantidade" id="quantidade" required>

                        <label for="preco">Preço:</label>
                        <input type="text" name="txtpreco" id="preco" required>

                        <label for="data">Última Atualização:</label>
                        <input type="date" name="txtultimaatualizacao" id="data" required>

                        <label for="setor">Setor:</label>
                        <select name="txtidsetor" id="setor" required>
                            <%
                                List<Setor> setores = (List<Setor>) request.getAttribute("setores");
                                if (setores != null) {
                                    for (Setor s : setores) {
                            %>
                            <option value="<%= s.getId()%>"><%= s.getNome()%> - Dir.: <%= s.getDiretor()%></option>
                            <%
                                }
                            } else {
                            %>
                            <option disabled selected>Setores não carregados</option>
                            <%
                                }
                            %>
                        </select>

                        <!-- Ações -->
                        <div class="form-actions">
                            <input type="submit" name="op" value="CADASTRAR">
                            <input type="submit" name="op" value="CONSULTAR TODOS">
                            <button type="button" onclick="window.location.href = 'index.html'">
                                Voltar à Página Inicial
                            </button>
                        </div>
                    </div>
                </form>
            </section>
        </main>
        
        <!-- Footer -->
        <footer>
            <p>&copy; 2025 - Seu Sistema de Estoque</p>
        </footer>

    </body>
</html>


