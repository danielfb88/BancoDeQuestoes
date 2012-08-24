package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.controller.Resposta;
import app.util.conexao.DAOUtil;

/**
 * Resposta DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 16-08-2012
 * 
 */
public class RespostaDAO {
	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	public RespostaDAO() {

	}

	public int adicionar(Integer id_pergunta, String descricao,
			Boolean correta, String observacao) {

		StringBuilder builder = new StringBuilder();
		int linhasAfetadas = 0;

		try {
			builder.append("INSERT INTO resposta ");
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

	public int editar(Integer id_resposta, Integer id_pergunta,
			String descricao, Boolean correta, String observacao) {

		StringBuilder builder = new StringBuilder();
		int linhasAfetadas = 0;

		try {
			builder.append("UPDATE resposta SET ");
			builder.append("id_pergunta = ?, ");
			builder.append("descricao = ?, ");
			builder.append("coorreta = ?, ");
			builder.append("observacao = ? ");
			builder.append("WHERE id_resposta = ? ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setInt(1, id_pergunta);
			preparedStatement.setString(2, descricao);
			preparedStatement.setBoolean(3, correta);
			preparedStatement.setString(4, observacao);
			preparedStatement.setInt(5, id_resposta);

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

			String sql = "DELETE FROM resposta WHERE id_resposta = ?;";
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

	public Resposta buscarPorId(Integer id) {
		Resposta resposta = new Resposta();

		String sql = "SELECT * FROM resposta WHERE id_resposta = ?;";

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

			resposta.setId_resposta(resultSet.getInt("id_resposta"));
			resposta.setPergunta(this.perguntaDAO.buscarPorId(resultSet
					.getInt("id_pergunta")));
			resposta.setDescricao(resultSet.getString("descricao"));
			resposta.setCorreta(resultSet.getBoolean("correta"));
			resposta.setObservacao(resultSet.getString("observacao"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resposta;
	}

	public List<Resposta> listarPor(Integer id_resposta, Integer id_pergunta,
			String descricao, Boolean correta, String observacao) {

		List<Resposta> respostas = new LinkedList<Resposta>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoIdResposta = 0;
		int ordemDoIdPergunta = 0;
		int ordemDaDescricao = 0;
		int ordemDaCorreta = 0;
		int ordemDaObservacao = 0;

		// Query
		builder.append("SELECT * FROM resposta WHERE true ");

		// ID
		if (id_resposta != null && id_resposta > 0) {
			builder.append("AND id_resposta = ? ");
			ordemDoIdResposta = ++count;
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
			if (ordemDoIdResposta > 0)
				preparedStatement.setInt(ordemDoIdResposta, id_resposta);

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
				Resposta respostaRetornado = new Resposta();
				respostaRetornado.setId_resposta(resultSet
						.getInt("id_resposta"));
				respostaRetornado.setPergunta(this.perguntaDAO
						.buscarPorId(resultSet.getInt("id_pergunta")));
				respostaRetornado
						.setDescricao(resultSet.getString("descricao"));
				respostaRetornado.setCorreta(resultSet.getBoolean("correta"));
				respostaRetornado.setObservacao(resultSet
						.getString("observacao"));

				// Adicionando o resposta à lista
				respostas.add(respostaRetornado);
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return respostas;
	}
}
