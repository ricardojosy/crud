package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class Dao {

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	public Dao() {
		conn = new ConnectionFactory().getConexao();
	}
	
	public void inserir(Produto produto) {
		String sql = "insert into produto (nome) values (?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, produto.getNome());
			pstmt.execute();
			pstmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro na inclusão: " + e);
		}
	}
	
	public void alterar(Produto produto) {
		String sql = "update produto set nome = ? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, produto.getNome());
			pstmt.setLong(2, produto.getId());
			pstmt.execute();
			pstmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro na alteração: " + e);
		}
	}
	
	public void excluir(Long id) {
		String sql = "delete from produto where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.execute();
			pstmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro na exclusão: " + e);
		}
	}

	public Produto consultar(Long id) {
		String sql = "select * from produto where id = " + id;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if  (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				return p;
			} 
			return null;
		} catch (Exception e) {
			throw new RuntimeException("Erro na inclusão: " + e);
		}
	}
	
	public List<Produto> listar() {
		String sql = "select * from produto";
		List<Produto> lista = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				lista.add(p);
			}
			return lista;
		} catch (Exception e) {
			throw new RuntimeException("Erro na inclusão: " + e);
		}
	}
	
}
