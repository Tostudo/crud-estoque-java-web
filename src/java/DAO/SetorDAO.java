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
import util.Conexao;
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

        while (rs.next()) {
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

    public void cadastrar(Setor s) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "INSERT into setor (nome, diretor_departamento, qtd_funcionarios) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, s.getNome());
        stmt.setString(2, s.getDiretor());
        stmt.setInt(3, s.getQtdfuncionarios());

        stmt.execute();
        con.close();
    }

    public void deletar(Setor s) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "DELETE from setor WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, s.getId());

        stmt.execute();

        con.close();
    }

    public void atualizar(Setor s) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "update setor set nome = ?, diretor_departamento = ?, qtd_funcionarios = ? WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, s.getNome());
        stmt.setString(2, s.getDiretor());
        stmt.setInt(3, s.getQtdfuncionarios());
        stmt.setInt(4, s.getId());

        stmt.execute();

        con.close();
    }

    public Setor consultarById(Setor s) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "SELECT * FROM setor WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, s.getId());
        ResultSet rs = stmt.executeQuery();

        Setor seto = new Setor();
        if (rs.next()) {
            seto.setId(rs.getInt("id"));
            seto.setNome(rs.getString("nome"));
            seto.setDiretor(rs.getString("diretor_departamento"));
            seto.setQtdfuncionarios(rs.getInt("qtd_funcionarios"));
        }
        con.close();
        return seto;

    }

}
