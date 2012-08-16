package app.dao.postgresdialect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.dao.iterface.IGrupoDAO;
import app.dto.Grupo;
import app.util.DAOUtil;

/**
 * Grupo DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
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
	public int editar(Grupo grupo) {
		int linhasAfetadas = 0;

		try {

			String sql = "UPDATE grupo SET descricao = ?, tipo = ? WHERE id_grupo = ?;";
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

	public Grupo getById(int id) {
		Grupo grupo = new Grupo();

		String sql = "SELECT * FROM grupo WHERE id_grupo = ?;";

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				return null;
			}

			grupo.setId_grupo(resultSet.getInt("id_grupo"));
			grupo.setDescricao(resultSet.getString("descricao"));
			grupo.setTipo(resultSet.getString("tipo").toCharArray()[0]);

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grupo;
	}

	/**
	 * Retorna todos em uma lista encadeada dos Grupos que satisfaçam as
	 * caracteristicas informadas no objeto grupo.
	 * 
	 * @param grupo
	 *            Grupo com as informações relevantes para a pesquisa
	 *            preenchidas.
	 * @return Lista encadeada com os grupos encontrados.
	 */
	public List<Grupo> getAllBy(Grupo grupo) {
		List<Grupo> grupos = new LinkedList<Grupo>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoId = 0;
		int ordemDaDescricao = 0;
		int ordemDoTipo = 0;

		// Query
		builder.append("SELECT * FROM grupo WHERE true ");

		// ID
		if (grupo.getId_grupo() != null && grupo.getId_grupo() > 0) {
			builder.append("AND id_grupo = ? ");
			ordemDoId = ++count;
		}

		// Descricao
		if (grupo.getDescricao() != null) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// Tipo
		if (grupo.getTipo() != null) {
			builder.append("AND tipo = ? ");
			ordemDoTipo = ++count;
		}

		// Fechando a instrução
		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoId > 0)
				preparedStatement.setInt(ordemDoId, grupo.getId_grupo());

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao,
						"%" + grupo.getDescricao() + "%");

			if (ordemDoTipo > 0)
				preparedStatement.setString(ordemDoTipo, grupo.getTipo()
						.toString());

			// Executando a quary e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados e inserindo-os em objetos
			// Grupo e depois
			// na lista grupos
			while (resultSet.next()) {
				grupos.add(new Grupo(resultSet.getInt("id_grupo"), resultSet
						.getString("descricao"), resultSet.getString("tipo")
						.charAt(0)));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grupos;
	}
}
