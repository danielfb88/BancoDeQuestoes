package app.dao.postgresdialect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.dao.iterface.IPerguntaDAO;
import app.dto.Pergunta;
import app.util.DAOUtil;

/**
 * Pergunta DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 16-08-2012
 * 
 */
public class PerguntaDAO implements IPerguntaDAO {
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	public PerguntaDAO() {

	}

	@Override
	public int adicionar(Pergunta pergunta) {
		int linhasAfetadas = 0;

		try {
			String sql = "INSERT INTO pergunta (id_usuario, descricao, tipo_pergunta, nivel_pergunta, enunciado, comentario) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, pergunta.getUsuario().getId_usuario());
			preparedStatement.setString(2, pergunta.getDescricao());
			preparedStatement.setString(3, pergunta.getTipo_pergunta()
					.toString());
			preparedStatement.setString(4, pergunta.getNivel_pergunta()
					.toString());
			preparedStatement.setString(5, pergunta.getEnunciado());
			preparedStatement.setString(6, pergunta.getComentario());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int editar(Pergunta pergunta) {
		int linhasAfetadas = 0;

		try {
			String sql = "UPDATE pergunta SET id_usuario = ?, descricao = ?, tipo_pergunta = ?, nivel_pergunta = ?, enunciado = ?, comentario = ? WHERE id_pergunta = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, pergunta.getUsuario().getId_usuario());
			preparedStatement.setString(2, pergunta.getDescricao());
			preparedStatement.setString(3, pergunta.getTipo_pergunta()
					.toString());
			preparedStatement.setString(4, pergunta.getNivel_pergunta()
					.toString());
			preparedStatement.setString(5, pergunta.getEnunciado());
			preparedStatement.setString(6, pergunta.getComentario());
			preparedStatement.setInt(7, pergunta.getId_pergunta());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int excluir(Pergunta pergunta) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM pergunta WHERE id_pergunta = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, pergunta.getId_pergunta());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public Pergunta getById(int id) {
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
			pergunta.setUsuario(usuarioDAO.getById(resultSet
					.getInt("id_usuario")));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pergunta;
	}

	public List<Pergunta> getAllBy(Pergunta pergunta) {
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
		if (pergunta.getId_pergunta() != null && pergunta.getId_pergunta() > 0) {
			builder.append("AND id_pergunta = ? ");
			ordemDoIdPergunta = ++count;
		}

		// ID Usuario
		if (pergunta.getUsuario() != null) {
			if (pergunta.getUsuario().getId_usuario() != null
					&& pergunta.getUsuario().getId_usuario() > 0) {
				builder.append("AND id_usuario = ? ");
				ordemDoIdUsuario = ++count;
			}
		}

		// Descricao
		if (pergunta.getDescricao() != null) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// Tipo_Pergunta
		if (pergunta.getTipo_pergunta() != null) {
			builder.append("AND tipo_pergunta = ? ");
			ordemDoTipoPergunta = ++count;
		}

		// Nivel_Pergunta
		if (pergunta.getNivel_pergunta() != null) {
			builder.append("AND nivel_pergunta = ? ");
			ordemDoNivelPergunta = ++count;
		}

		// Enunciado
		if (pergunta.getEnunciado() != null) {
			builder.append("AND enunciado = ? ");
			ordemDoEnunciado = ++count;
		}

		// Comentario
		if (pergunta.getComentario() != null) {
			builder.append("AND comentario = ? ");
			ordemDoComentario = ++count;
		}

		// Fechando a instrução
		builder.append(";");

		// System.out.println(builder.toString());

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoIdPergunta > 0)
				preparedStatement.setInt(ordemDoIdPergunta,
						pergunta.getId_pergunta());

			if (ordemDoIdUsuario > 0)
				preparedStatement.setInt(ordemDoIdUsuario, pergunta
						.getUsuario().getId_usuario());

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao,
						"%" + pergunta.getDescricao() + "%");

			if (ordemDoTipoPergunta > 0)
				preparedStatement.setString(ordemDoTipoPergunta, pergunta
						.getTipo_pergunta().toString());

			if (ordemDoNivelPergunta > 0)
				preparedStatement.setString(ordemDoNivelPergunta, pergunta
						.getNivel_pergunta().toString());

			if (ordemDoEnunciado > 0)
				preparedStatement.setString(ordemDoEnunciado,
						"%" + pergunta.getEnunciado() + "%");

			if (ordemDoComentario > 0)
				preparedStatement.setString(ordemDoComentario,
						"%" + pergunta.getComentario() + "%");

			// Executando a query e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados
			while (resultSet.next()) {
				Pergunta perguntaRetornado = new Pergunta();
				perguntaRetornado.setId_pergunta(resultSet
						.getInt("id_pergunta"));
				perguntaRetornado.setUsuario(usuarioDAO.getById(resultSet
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
