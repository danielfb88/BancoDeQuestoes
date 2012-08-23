package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.controller.Prova;
import app.util.DAOUtil;

/**
 * Prova DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 16-08-2012
 * 
 */
public class ProvaDAO {
	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	public ProvaDAO() {

	}

	public int adicionar(Integer id_pergunta, String descricao,
			Boolean correta, String observacao) {

		StringBuilder builder = new StringBuilder();
		int linhasAfetadas = 0;

		try {
			builder.append("INSERT INTO prova ");
			builder.append("(id_pergunta, descricao, correta, observacao) ");
			builder.append("VALUES ");
			builder.append("(?, ?, ?, ?) ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setInt(1, id_pergunta);
			preparedStatement.setString(2, descricao);
			preparedStatement.setBoolean(3, correta);
			preparedStatement.setString(4, observacao);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int editar(Integer id_prova, Integer id_pergunta,
			String descricao, Boolean correta, String observacao) {

		StringBuilder builder = new StringBuilder();
		int linhasAfetadas = 0;

		try {
			builder.append("UPDATE prova SET ");
			builder.append("id_pergunta = ?, ");
			builder.append("descricao = ?, ");
			builder.append("coorreta = ?, ");
			builder.append("observacao = ? ");
			builder.append("WHERE id_prova = ? ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setInt(1, id_pergunta);
			preparedStatement.setString(2, descricao);
			preparedStatement.setBoolean(3, correta);
			preparedStatement.setString(4, observacao);
			preparedStatement.setInt(5, id_prova);

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

			String sql = "DELETE FROM prova WHERE id_prova = ?;";
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

	public Prova buscarPorId(Integer id) {
		Prova prova = new Prova();

		String sql = "SELECT * FROM prova WHERE id_prova = ?;";

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

			prova.setId_prova(resultSet.getInt("id_prova"));
			prova.setPergunta(this.perguntaDAO.buscarPorId(resultSet
					.getInt("id_pergunta")));
			prova.setDescricao(resultSet.getString("descricao"));
			prova.setCorreta(resultSet.getBoolean("correta"));
			prova.setObservacao(resultSet.getString("observacao"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prova;
	}

	public List<Prova> listarPor(Integer id_prova, Integer id_pergunta,
			String descricao, Boolean correta, String observacao) {

		List<Prova> provas = new LinkedList<Prova>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoIdProva = 0;
		int ordemDoIdPergunta = 0;
		int ordemDaDescricao = 0;
		int ordemDaCorreta = 0;
		int ordemDaObservacao = 0;

		// Query
		builder.append("SELECT * FROM prova WHERE true ");

		// ID
		if (id_prova != null && id_prova > 0) {
			builder.append("AND id_prova = ? ");
			ordemDoIdProva = ++count;
		}

		// Descricao
		if (descricao != null) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// CORRETA
		if (correta != null) {
			builder.append("AND correta = ? ");
			ordemDaCorreta = ++count;
		}

		// Observacao
		if (observacao != null) {
			builder.append("AND observacao LIKE ? ");
			ordemDaObservacao = ++count;
		}

		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoIdProva > 0)
				preparedStatement.setInt(ordemDoIdProva, id_prova);

			if (ordemDoIdPergunta > 0)
				preparedStatement.setInt(ordemDoIdPergunta, id_pergunta);

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao, "%" + descricao
						+ "%");

			if (ordemDaCorreta > 0)
				preparedStatement.setBoolean(ordemDaCorreta, correta);

			if (ordemDaObservacao > 0)
				preparedStatement.setString(ordemDaObservacao, "%" + observacao
						+ "%");

			// Executando a query e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados
			while (resultSet.next()) {
				Prova provaRetornado = new Prova();
				provaRetornado.setId_prova(resultSet
						.getInt("id_prova"));
				provaRetornado.setPergunta(this.perguntaDAO
						.buscarPorId(resultSet.getInt("id_pergunta")));
				provaRetornado
						.setDescricao(resultSet.getString("descricao"));
				provaRetornado.setCorreta(resultSet.getBoolean("correta"));
				provaRetornado.setObservacao(resultSet
						.getString("observacao"));

				// Adicionando o prova à lista
				provas.add(provaRetornado);
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return provas;
	}
}
