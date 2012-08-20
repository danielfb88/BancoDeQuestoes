package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.controller.Periodo;
import app.util.DAOUtil;

/**
 * Periodo DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-08-2012
 * 
 */
public class PeriodoDAO {

	public PeriodoDAO() {

	}

	public int adicionar(String descricao, Integer numero) {
		int linhasAfetadas = 0;

		try {

			String sql = "INSERT INTO periodo (descricao, numero) VALUES (?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setString(1, descricao);
			preparedStatement.setInt(2, numero);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int editar(Integer id_periodo, String descricao, Integer numero) {
		int linhasAfetadas = 0;

		try {

			String sql = "UPDATE periodo SET descricao = ?, numero = ? WHERE id_periodo = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setString(1, descricao);
			preparedStatement.setInt(2, numero);
			preparedStatement.setInt(3, id_periodo);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int excluir(Integer id) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM periodo WHERE id_periodo = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public Periodo buscarPorId(Integer id) {
		Periodo periodo = new Periodo();

		String sql = "SELECT * FROM periodo WHERE id_periodo = ?;";

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

			periodo.setId_periodo(resultSet.getInt("id_periodo"));
			periodo.setDescricao(resultSet.getString("descricao"));
			periodo.setNumero(resultSet.getInt("numero"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return periodo;
	}

	public List<Periodo> listarPor(Integer id_periodo, String descricao,
			Integer numero) {
		List<Periodo> periodos = new LinkedList<Periodo>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoId = 0;
		int ordemDaDescricao = 0;
		int ordemDoNumero = 0;

		// Query
		builder.append("SELECT * FROM periodo WHERE true ");

		// ID
		if (id_periodo != null && id_periodo > 0) {
			builder.append("AND id_periodo = ? ");
			ordemDoId = ++count;
		}

		// Descricao
		if (descricao != null && !descricao.isEmpty()) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// Numero
		if (numero != null) {
			builder.append("AND numero = ? ");
			ordemDoNumero = ++count;
		}

		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoId > 0)
				preparedStatement.setInt(ordemDoId, id_periodo);

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao, "%" + descricao
						+ "%");

			if (ordemDoNumero > 0)
				preparedStatement.setInt(ordemDoNumero, numero);

			// Executando a quary e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados e inserindo-os em objetos
			// Periodo e depois
			// na lista periodos
			while (resultSet.next()) {
				periodos.add(new Periodo(resultSet.getInt("id_periodo"),
						resultSet.getString("descricao"), resultSet
								.getInt("numero")));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return periodos;
	}
}
