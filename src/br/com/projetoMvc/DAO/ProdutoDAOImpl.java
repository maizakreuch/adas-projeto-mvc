package br.com.projetoMvc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projetoMvc.model.Produto;
import br.com.projetoMvc.util.ConnectionFactory;

public class ProdutoDAOImpl implements GenericDAO {

	private Connection conn;

	public ProdutoDAOImpl() throws Exception {
		try {
			this.conn = ConnectionFactory.getConnection();
			System.out.println("Conectado com sucesso!");
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<Object> listarTodos() {
		List<Object> lista = new ArrayList<Object>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produto ORDER BY descricao";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				lista.add(produto);
			}
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar Produto! Erro" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar a conexão! Erro:" + ex.getLocalizedMessage());
			}
		}

		return lista;
	}

	@Override
	public Object listarPorId(int id) {
		Produto produto = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produto WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		
		if(rs.next()) {
			produto = new Produto();
			produto.setId(rs.getInt("id"));
			produto.setDescricao(rs.getString("descricao"));
		}
		}catch (SQLException ex) {
			System.out.print("Problemas na DAO ao carregar Produto! Erro: " + ex.getMessage());
			ex.printStackTrace();
		}finally{
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
			}catch(Exception e) {
				System.out.print("Problemas ao fechar conexão! Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return produto;
	}

	@Override
	public Boolean cadastrar(Object object) {
		Produto produto = (Produto) object;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO produto (descricao)" + "VALUES (?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getDescricao());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			System.out.print("Problemas na DAO ao cadastrar Produto! Erro: " + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt);
			} catch (Exception ex) {
				System.out.print("Problemas ao fechar conexão! Erro:" + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Boolean alterar(Object object) {
			Produto produto = (Produto) object;
			PreparedStatement stmt = null;
			String sql = "UPDATE produto SET descricao = ? WHERE id = ?";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, produto.getDescricao());
				stmt.setInt(2, produto.getId());
				stmt.execute();
				return true;
			} catch (SQLException ex) {
				System.out.println("Problemas na DAO ao alterar Produto: " + ex.getMessage());
				ex.printStackTrace();
				return false;
			} finally {
				try {
					ConnectionFactory.closeConnection(conn, stmt);
				} catch (Exception e) {
					System.out.println("Problemas para fechar conexão!");
					e.printStackTrace();
				}
			}
		}
		

	@Override
	public void excluir(int id) {
	PreparedStatement stmt = null;
	String sql = "DELETE FROM produto WHERE id = ?";
	try {
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}catch(SQLException ex){
		System.out.print("Problemas na DAO ao excluir Produto! Erro: "+ ex.getMessage());
	}finally{
		try {
			ConnectionFactory.closeConnection(conn, stmt);
	}catch(Exception e){
		System.out.print("Problemas ao fechar conexão! Erro:" + e.getMessage());
	}

}
}
}