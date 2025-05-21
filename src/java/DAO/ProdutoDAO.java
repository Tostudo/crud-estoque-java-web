/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import util.Conexao;

/**
 *
 * @author PTOLEDO
 */
public class ProdutoDAO {

    public void cadastrar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "insert into produtos (descricao, marca, quantidade, preco, data, id_setor) values (?,?,?,?,?,?)";
        PreparedStatement comando = con.prepareStatement(sql);

        comando.setString(1, p.getDescricao());
        comando.setString(2, p.getMarca());
        comando.setInt(3, p.getQuantidade());
        comando.setDouble(4, p.getPreco());
        comando.setDate(5, p.getUltimaatualizacao());
        comando.setInt(6, p.getIdSetor());

        comando.execute();
        con.close();
    }

    public void deletar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "delete from produtos where id = ?";
        PreparedStatement comando = con.prepareStatement(sql);
        comando.setInt(1, p.getId());
        comando.execute();
        con.close();
    }

    public void atualizar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "update produtos set descricao = ?, marca = ?, quantidade = ?, preco = ?, data = ?, id_setor = ? where id = ?";
        PreparedStatement comando = con.prepareStatement(sql);

        comando.setString(1, p.getDescricao());
        comando.setString(2, p.getMarca());
        comando.setInt(3, p.getQuantidade());
        comando.setDouble(4, p.getPreco());
        comando.setDate(5, p.getUltimaatualizacao());
        comando.setInt(6, p.getIdSetor());
        comando.setInt(7, p.getId());
        comando.execute();

        con.close();
    }

    public Produto consultarById(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "SELECT p.*, s.nome AS nome_setor "
                + "FROM produtos p "
                + "JOIN setor s ON p.id_setor = s.id "
                + "WHERE p.id = ?";
        
        PreparedStatement comando = con.prepareStatement(sql);
        
        comando.setInt(1, p.getId());
        ResultSet rs = comando.executeQuery();
        
        Produto prod = new Produto();
        if (rs.next()) {
            prod.setId(rs.getInt("id"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setMarca(rs.getString("marca"));
            prod.setQuantidade(rs.getInt("quantidade"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setUltimaatualizacao(rs.getDate("data"));
            prod.setIdSetor(rs.getInt("id_setor"));
            prod.setNomeSetor(rs.getString("nome_setor"));
        }
        return prod;
    }

    public List<Produto> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "SELECT p.*, s.nome AS nome_setor "
                + "FROM produtos p "
                + "JOIN setor s ON p.id_setor = s.id";

        PreparedStatement comando = con.prepareStatement(sql);
        ResultSet rs = comando.executeQuery();

        List<Produto> lprod = new ArrayList<Produto>();
        while (rs.next()) {
            Produto prod = new Produto();
            prod.setId(rs.getInt("id"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setMarca(rs.getString("marca"));
            prod.setQuantidade(rs.getInt("quantidade"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setUltimaatualizacao(rs.getDate("data"));
            prod.setIdSetor(rs.getInt("id_setor"));
            prod.setNomeSetor(rs.getString("nome_setor"));

            lprod.add(prod);
        }
        return lprod;
    }

    public List<Produto> consultarPorSetor(int idSetor) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String sql = "SELECT p.*, s.nome AS nome_setor FROM produtos p "
                + "JOIN setor s ON p.id_setor = s.id "
                + "WHERE p.id_setor = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idSetor);
        ResultSet rs = stmt.executeQuery();

        List<Produto> lprod = new ArrayList<>();
        while (rs.next()) {
            Produto prod = new Produto();
            prod.setId(rs.getInt("id"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setMarca(rs.getString("marca"));
            prod.setQuantidade(rs.getInt("quantidade"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setUltimaatualizacao(rs.getDate("data"));
            prod.setNomeSetor(rs.getString("nome_setor"));
            lprod.add(prod);
        }

        con.close();
        return lprod;
    }

}
