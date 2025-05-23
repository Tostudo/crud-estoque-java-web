/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProdutoDAO;
import DAO.SetorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import model.Setor;
import util.ValidadorProduto;

/**
 *
 * @author PTOLEDO
 */
@WebServlet(name = "controle_produto", urlPatterns = {"/controle_produto"})
public class controle_produto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your pageh ere. You may use following sample code. */

            String op = request.getParameter("op");
            ProdutoDAO pdao = new ProdutoDAO();
            Produto p = new Produto();
            String msg = "";

            if (op != null && op.trim().equals("CADASTRAR")) {

                String descricao = request.getParameter("txtdescricao");
                String marca = request.getParameter("txtmarca");
                String dataStr = request.getParameter("txtultimaatualizacao");
                String idSetorStr = request.getParameter("txtidsetor");
                int quantidade = Integer.parseInt(request.getParameter("txtquantidade"));
                double preco = Double.parseDouble(request.getParameter("txtpreco"));

                // Conversão da data
                java.sql.Date dataConvertida;
                try {
                    dataConvertida = java.sql.Date.valueOf(dataStr);
                } catch (IllegalArgumentException ex) {
                    msg = "Data inválida. Use o formato AAAA-MM-DD.";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                    return;
                }

                int idSetor = Integer.parseInt(idSetorStr);

                try {

                    // Preenche o objeto
                    p.setDescricao(descricao);
                    p.setMarca(marca);
                    p.setQuantidade(quantidade);
                    p.setPreco(preco);
                    p.setUltimaatualizacao(dataConvertida);
                    p.setIdSetor(idSetor);

                    // ✅ Validações usando a classe ValidadorProduto
                    String erroCampos = ValidadorProduto.validarCamposObrigatorios(p);
                    String erroNumeros = ValidadorProduto.validarValoresNumericos(p);

                    if (erroCampos != null) {
                        request.setAttribute("message", erroCampos);
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                        return; // Impede que o programa continue cadastrando se cair em alguma exceção
                    }

                    if (erroNumeros != null) {
                        request.setAttribute("message", erroNumeros);
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                        return; // Impede que o programa continue cadastrando se cair em alguma exceção
                    }

                    // Cadastra
                    pdao.cadastrar(p);

                    msg = "Produto cadastrado com sucesso!";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);

                } catch (NumberFormatException e) {
                    msg = "Quantidade ou preço inválidos (devem ser números).";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                } catch (Exception ex) {
                    System.out.println("Erro ao cadastrar: " + ex.getMessage());
                    request.setAttribute("message", "Erro ao cadastrar: " + ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (op != null && op.trim().equals("DELETAR")) {
                int id = Integer.parseInt(request.getParameter("txtid"));
                p.setId(id);
                msg = "Deletar";
                try {

                    pdao.deletar(p);
                    List<Produto> lprod = pdao.consultarTodos();
                    request.setAttribute("lprod", lprod);
                    request.getRequestDispatcher("resultadoconsultartodos.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (op != null && op.trim().equals("CONSULTAR BY ID")) {
                int id = Integer.parseInt(request.getParameter("txtid"));
                p.setId(id);
                try {

                    p = pdao.consultarById(p);

                    request.setAttribute("p", p);
                    request.getRequestDispatcher("resultadoconsultarbyid.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                }
            } else if (op != null && op.trim().equals("CONSULTAR TODOS")) {

                try {
                    List<Produto> lprod = pdao.consultarTodos();
                    request.setAttribute("lprod", lprod);
                    request.getRequestDispatcher("resultadoconsultartodos.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                }

            } else if (op != null && op.trim().equals("ATUALIZAR")) {

                int id = Integer.parseInt(request.getParameter("txtid"));
                p.setId(id);

                try {

                    p = pdao.consultarById(p);

                    // Carrega lista de setores para o combobox
                    SetorDAO sdao = new SetorDAO();
                    List<Setor> setor = sdao.listarTodos();

                    // Encaminha para tela de atualização com os dados do produto
                    request.setAttribute("p", p);
                    request.setAttribute("setor", setor);
                    request.getRequestDispatcher("resultadoconsultaratualizar.jsp").forward(request, response);

                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ao consultar produto para atualização: " + ex.getMessage());
                    request.setAttribute("message", "Erro ao consultar produto.");
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (op != null && op.trim().equals("CONFIRMAR ATUALIZAÇÃO")) {

                int id = Integer.parseInt(request.getParameter("txtid"));
                String descricao = request.getParameter("txtdescricao");
                String marca = request.getParameter("txtmarca");
                String dataStr = request.getParameter("txtultimaatualizacao");
                String idSetorStr = request.getParameter("comboboxsetor");
                int quantidade = Integer.parseInt(request.getParameter("txtquantidade"));
                double preco = Double.parseDouble(request.getParameter("txtpreco"));
                // Conversão da data
                java.sql.Date dataConvertida;
                try {
                    dataConvertida = java.sql.Date.valueOf(dataStr);
                } catch (IllegalArgumentException ex) {
                    msg = "Data inválida. Use o formato AAAA-MM-DD.";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                    return;
                }
                int idSetor = Integer.parseInt(idSetorStr);
                try {

                    // Preenchendo o produto
                    p.setId(id);
                    p.setDescricao(descricao);
                    p.setMarca(marca);
                    p.setQuantidade(quantidade);
                    p.setPreco(preco);
                    p.setUltimaatualizacao(dataConvertida);
                    p.setIdSetor(idSetor);

                    // ✅ Validações usando a classe ValidadorProduto
                    String erroCampos = ValidadorProduto.validarCamposObrigatorios(p);
                    String erroNumeros = ValidadorProduto.validarValoresNumericos(p);

                    if (erroCampos != null) {
                        request.setAttribute("message", erroCampos);
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                        return; // Impede que o programa continue cadastrando se cair em alguma exceção
                    }

                    if (erroNumeros != null) {
                        request.setAttribute("message", erroNumeros);
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                        return; // Impede que o programa continue cadastrando se cair em alguma exceção
                    }

                    // Atualiza no banco
                    pdao.atualizar(p);
                    System.out.println("Atualizado com sucesso!!");
                    request.setAttribute("message", "Produto atualizado com sucesso!");
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);

                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Quantidade ou preço inválidos.");
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                } catch (Exception ex) {
                    System.out.println("Erro ao atualizar: " + ex.getMessage());
                    request.setAttribute("message", "Erro ao atualizar: " + ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }

            } else if (op != null && op.trim().equals("FILTRAR_POR_SETOR")) {
                int idSetor = Integer.parseInt(request.getParameter("idSetor"));
                List<Produto> lprod;
                try {
                    if (idSetor == 0) {
                        lprod = pdao.consultarTodos();
                    } else {
                        lprod = pdao.consultarPorSetor(idSetor);

                    }

                    request.setAttribute("lprod", lprod);
                    request.getRequestDispatcher("resultadoconsultartodos.jsp").forward(request, response);

                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ao filtrar por setor: " + ex.getMessage());
                }
            } else if (op != null && op.trim().equals("abrir_filtro")) {
                try {
                    SetorDAO sdao = new SetorDAO();
                    List<Setor> setor = sdao.listarTodos();

                    request.setAttribute("setor", setor);
                    request.getRequestDispatcher("filtrosetor.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                    request.setAttribute("message", "Erro ao carregar setores.");
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
