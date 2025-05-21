/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Setor;
import static util.Conexao.getConexao;



/**
 *
 * @author joao.campos
 */
public class SetorDAO {
    public List<Setor> listarTodos() throws ClassNotFoundException, SQLException {
        
        List<Setor> setores = new ArrayList<>();
        
        String sql = "SELECT * FROM setor";
        Connection con = getConexao();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Setor s = new Setor();
            s.setId(rs.getInt("id"));
            s.setNome(rs.getString("nome"));
            s.setDiretor(rs.getString("diretor_departamento"));
            s.setQtdfuncionarios(rs.getInt("qtd_funcionarios"));
            setores.add(s);
            
        }
        con.close();
        return setores;
    }
}
