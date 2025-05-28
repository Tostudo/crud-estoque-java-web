/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.SetorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setor;

/**
 *
 * @author joao.campos
 */
@WebServlet(name = "controle_setor", urlPatterns = {"/controle_setor"})
public class controle_setor extends HttpServlet {
    
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
            
            String op = request.getParameter("op");
            SetorDAO sdao = new SetorDAO();
            Setor s = new Setor();
            String msg = "";
            
            if(op != null && op.trim().equals("comboboxsetor")){
                
                
                try{
                    List<Setor> setores = new SetorDAO().listarTodos();
                    request.setAttribute("setores", setores);
                    request.getRequestDispatcher("formulario.jsp").forward(request, response);
                }catch(ClassNotFoundException | SQLException ex){
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (op != null && op.trim().equals("CADASTRAR")){
                
                String nome = request.getParameter("txtnome");
                String diretor = request.getParameter("txtdiretor");
                int qtdFuncionarios = Integer.parseInt(request.getParameter("txtqtdfuncionarios"));
                
                // preencher o objeto
                
                s.setNome(nome);
                s.setDiretor(diretor);
                s.setQtdfuncionarios(qtdFuncionarios);
                
                try{
                    sdao.cadastrar(s);
                    msg = "Setor cadastrado com sucesso!";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                }catch(ClassNotFoundException | SQLException ex){
                    msg = ex.getMessage();
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if(op != null && op.trim().equals("DELETAR")){
                int id = Integer.parseInt(request.getParameter("txtid"));
                
                // preencher o objeto
                
                s.setId(id);
                
                try{
                    sdao.deletar(s);
                    msg = "Deletado com sucesso!";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                }catch(ClassNotFoundException | SQLException ex){
                    msg = ex.getMessage();
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (op != null && op.trim().equals("CONSULTARBYID")){
                int id = Integer.parseInt(request.getParameter("txtid"));
                
                // preenche o objeto
                s.setId(id);
                try{
                    s = sdao.consultarById(s);
                    
                    request.setAttribute("setor", s);
                    request.getRequestDispatcher("formulario_setor.jsp").forward(request, response);
                }catch(ClassNotFoundException | SQLException ex){
                    msg = ex.getMessage();
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (op != null && op.trim().equals("ATUALIZAR")){
                int id = Integer.parseInt(request.getParameter("txtid"));
                
                //preenche o objeto
                
                s.setId(id);
                
                try{
                    sdao.consultarById(s);
                    request.setAttribute("s", s);
                    request.getRequestDispatcher("formulario_setor.jsp").forward(request, response);
                }catch(ClassNotFoundException | SQLException ex){
                    msg = ex.getMessage();
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (op != null && op.trim().equals("CONFIRMARATUALIZAÇÃO")){
                int id = Integer.parseInt(request.getParameter("txtid"));
                String nome = request.getParameter("txtnome");
                String diretor = request.getParameter("txtdiretor");
                int qtdfuncionarios = Integer.parseInt(request.getParameter("txtqtdfuncionarios"));
                
                // preenche o objeto
                
                s.setId(id);
                s.setNome(nome);
                s.setDiretor(diretor);
                s.setQtdfuncionarios(qtdfuncionarios);
                
                try{
                    sdao.atualizar(s);
                    msg = "Setor Atualizado com sucesso";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                }catch(ClassNotFoundException | SQLException ex){
                    msg = ex.getMessage();
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (op != null && op.trim().equals("CONSULTAR TODOS")){
                try{
                    List<Setor> setores = new SetorDAO().listarTodos();
                    request.setAttribute("setores", setores);
                    request.getRequestDispatcher("listar_setores.jsp").forward(request, response);
                }catch(ClassNotFoundException | SQLException ex){
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    request.setAttribute("message", msg);
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
