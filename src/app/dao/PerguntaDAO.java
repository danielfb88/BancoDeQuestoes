package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.controller.Pergunta;
import app.util.DAOUtil;

/**
 * Pergunta DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 16-08-2012
 * 
 */
public class PerguntaDAO {
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	public PerguntaDAO() {

	}

	public int adicionar(Integer id_usuario, String descricao,
			Character tipo_pergunta, Character nivel_pergunta,
			String enunciado, String comentario) {
		int linhasAfetadas = 0;

		try {
			String sql = "INSERT INTO pergunta (id_usuario, descricao, tipo_pergunta, nivel_pergunta, enunciado, comentario) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id_usuario);
			preparedStatement.setString(2, descricao);
			preparedStatement.setString(3, tipo_pergunta.toString());
			preparedStatement.setString(4, nivel_pergunta.toString());
			preparedStatement.setString(5, enunciado);
			preparedStatement.setString(6, comentario);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int editar(Integer id_pergunta, Integer id_usuario,
			String descricao, Character tipo_pergunta,
			Character nivel_pergunta, String enunciado, String comentario) {
		int linhasAfetadas = 0;

		try {
			String sql = "UPDATE pergunta SET id_usuario = ?, descricao = ?, tipo_pergunta = ?, nivel_pergunta = ?, enunciado = ?, comentario = ? WHERE id_pergunta = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id_usuario);
			preparedStatement.setString(2, descricao);
			preparedStatement.setString(3, tipo_pergunta.toString());
			preparedStatement.setString(4, nivel_pergunta.toString());
			preparedStatement.setString(5, enunciado);
			preparedStatement.setString(6, comentario);
			preparedStatement.setInt(7, id_pergunta);

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

			String sql = "DELETE FROM pergunta WHERE id_pergunta = ?;";
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

	public Pergunta buscarPorId(Integer id) {
		Pergunta pergunta = new Pergunta();

		String sql = "SELECT * FROM pergunta WHERE id_pergunta = ?;";

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

			pergunta.setId_pergunta(resultSet.getInt("id_pergunta"));
			pergunta.setDescricao(resultSet.getString("descricao"));
			pergunta.setTipo_pergunta(resultSet.getString("tipo_pergunta")
					.charAt(0));
			pergunta.setNivel_pergunta(resultSet.getString("nivel_pergunta")
					.charAt(0));
			pergunta.setEnunciado(resultSet.getString("enunciado"));
			pergunta.setComentario(resultSet.getString("comentario"));
			pergunta.setUsuario(usuarioDAO.buscarPorId(resultSet
					.getInt("id_usuario")));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pergunta;
	}

	public List<Pergunta> listarPor(Integer id_pergunta, Integer id_usuario,
			String descricao, Character tipo_pergunta,
			Character nivel_pergunta, String enunciado, String comentario) {

		List<Pergunta> perguntas = new LinkedList<Pergunta>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoIdPergunta = 0;
		int ordemDoIdUsuario = 0;
		int ordemDaDescricao = 0;
		int ordemDoTipoPergunta = 0;
		int ordemDoNivelPergunta = 0;
		int ordemDoEnunciado = 0;
		int ordemDoComentario = 0;

		// Query
		builder.append("SELECT * FROM pergunta WHERE true ");

		// ID
		if (id_pergunta != null && id_pergunta > 0) {
			builder.append("AND id_pergunta = ? ");
			ordemDoIdPergunta = ++count;
		}

		// ID Usuario
		if (id_usuario != null && id_usuario > 0) {
			builder.append("AND id_usuario = ? ");
			ordemDoIdUsuario = ++count;
		}

		// Descricao
		if (descricao != null) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// Tipo_Pergunta
		if (tipo_pergunta != null) {
			builder.append("AND tipo_pergunta = ? ");
			ordemDoTipoPergunta = ++count;
		}

		// Nivel_Pergunta
		if (nivel_pergunta != null) {
			builder.append("AND nivel_pergunta = ? ");
			ordemDoNivelPergunta = ++count;
		}

		// Enunciado
		if (enunciado != null) {
			builder.append("AND enunciado LIKE ? ");
			ordemDoEnunciado = ++count;
		}

		// Comentario
		if (comentario != null) {
			builder.append("AND comentario LIKE ? ");
			ordemDoComentario = ++count;
		}

		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoIdPergunta > 0)
				preparedStatement.setInt(ordemDoIdPergunta, id_pergunta);

			if (ordemDoIdUsuario > 0)
				preparedStatement.setInt(ordemDoIdUsuario, id_usuario);

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao, "%" + descricao
						+ "%");

			if (ordemDoTipoPergunta > 0)
				preparedStatement.setString(ordemDoTipoPergunta,
						tipo_pergunta.toString());

			if (ordemDoNivelPergunta > 0)
				preparedStatement.setString(ordemDoNivelPergunta,
						perguntas.toString());

			if (ordemDoEnunciado > 0)
				preparedStatement.setString(ordemDoEnunciado, "%" + enunciado
						+ "%");

			if (ordemDoComentario > 0)
				preparedStatement.setString(ordemDoComentario, "%" + comentario
						+ "%");

			// Executando a query e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados
			while (resultSet.next()) {
				Pergunta perguntaRetornado = new Pergunta();
				perguntaRetornado.setId_pergunta(resultSet
						.getInt("id_pergunta"));
				perguntaRetornado.setUsuario(usuarioDAO.buscarPorId(resultSet
						.getInt("id_usuario")));
				perguntaRetornado
						.setDescricao(resultSet.getString("descricao"));
				perguntaRetornado.setTipo_pergunta(resultSet.getString(
						"tipo_pergunta").charAt(0));
				perguntaRetornado.setNivel_pergunta(resultSet.getString(
						"nivel_pergunta").charAt(0));
				perguntaRetornado
						.setEnunciado(resultSet.getString("enunciado"));
				perguntaRetornado.setComentario(resultSet
						.getString("comentario"));

				// Adicionando o pergunta à lista
				perguntas.add(perguntaRetornado);
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return perguntas;
	}
}
