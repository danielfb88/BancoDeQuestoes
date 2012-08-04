package app.dao.postgresDialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import app.dao.iterface.IGrupoDAO;
import app.dto.Grupo;
import app.util.DAOUtil;

/**
 * Grupo DAO
 * 
 * @author Daniel Bonfim
 * @since 03-08-2012
 * 
 */
public class GrupoDAO implements IGrupoDAO {

	public GrupoDAO() {

	}

	@Override
	public int adicionar(Grupo grupo) {
		int linhasAfetadas = 0;

		try {

			String sql = "INSERT INTO grupo (descricao, tipo) VALUES (?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setString(1, grupo.getDescricao());
			preparedStatement.setString(2, grupo.getTipo().toString());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int alterar(Grupo grupo) {
		int linhasAfetadas = 0;

		try {

			String sql = "UPDATE TABLE grupo SET descricao = ?, tipo = ? WHERE id_grupo = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setString(1, grupo.getDescricao());
			preparedStatement.setString(2, grupo.getTipo().toString());
			preparedStatement.setInt(3, grupo.getId_grupo());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int excluir(Grupo grupo) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM grupo WHERE id_grupo = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, grupo.getId_grupo());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	/**
	 * Problema. Decidir qual a ordem das interrogações.
	 * 
	 * @return Grupo
	 */
	public void getGrupoBy(int id, String descricao, Character tipo) {
		StringBuilder builder = new StringBuilder();
		// analize a possibilidade de usar um hashMap par aesta solução
		Map<Object,String> mp = new HashMap<Object, String>();

		builder.append("SELECT * FROM grupo WHERE true ");
		
		// Verificando se id informado
		if (id > 0) {
			builder.append("AND id_grupo = ? ");
		}
		// Verificando descrição informada
		if (!descricao.equals("")) {
			builder.append("AND descricao = ? ");
		}
		// Verificando tipo informado
		if (!tipo.toString().equals("")) {
			builder.append("AND tipo = ? ");
		}
		
		
		// PreparedStatement preparedStatment =
		// DAOUtil.getInstance().getPreparedStatement(builder.toString());

		/*
		 * 
		 * 
		 * 
		 * preparedStatment.setInt(1, codigo); ResultSet rs =
		 * preparedStatment.executeQuery(); if (!rs.next()) { rs.close();
		 * preparedStatment.close(); return null; }
		 * 
		 * dtoProduto produto = new dtoProduto();
		 * produto.setCodigo(rs.getInt("CODIGO"));
		 * produto.setNome(rs.getString("NOME"));
		 * produto.setEstoque(rs.getInt("ESTOQUE"));
		 * produto.setPreco(rs.getFloat("PRECO")); rs.close();
		 * preparedStatment.close();
		 * 
		 * return produto;
		 */
	}

}
