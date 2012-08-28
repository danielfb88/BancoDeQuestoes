package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.controller.Usuario;
import app.util.conexao.DAOUtil;
sasas
/**
 * Usuario DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 08-08-2012
 * 
 */
public class UsuarioDAO {
	GrupoDAO grupoDAO = new GrupoDAO();

	public UsuarioDAO() {

	}

	public int adicionar(Integer id_grupo, String nome, String login,
			String senha) {
		int linhasAfetadas = 0;

		try {
			String sql = "INSERT INTO usuario (id_grupo, nome, login, senha) VALUES (?, ?, ?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id_grupo);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, login);
			preparedStatement.setString(4, senha);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int editar(Integer id_usuario, Integer id_grupo, String nome,
			String login, String senha) {
		int linhasAfetadas = 0;

		try {
			String sql = "UPDATE usuario SET id_grupo = ?, nome = ?, login = ?, senha = ? WHERE id_usuario = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id_grupo);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, login);
			preparedStatement.setString(4, senha);
			preparedStatement.setInt(5, id_usuario);

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

			String sql = "DELETE FROM usuario WHERE id_usuario = ?;";
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

	public Usuario buscarPorId(Integer id) {
		Usuario usuario = new Usuario();

		String sql = "SELECT * FROM usuario WHERE id_usuario = ?;";

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

			usuario.setId_usuario(resultSet.getInt("id_usuario"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setGrupo(grupoDAO.buscarPorId(resultSet.getInt("id_grupo")));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public List<Usuario> listarPor(Integer id_usuario, Integer id_grupo,
			String nome, String login, String senha) {
		List<Usuario> usuarios = new LinkedList<Usuario>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoIdUsuario = 0;
		int ordemDoIdGrupo = 0;
		int ordemDoNome = 0;
		int ordemDoLogin = 0;
		int ordemDaSenha = 0;

		// Query
		builder.append("SELECT * FROM usuario WHERE true ");

		// ID
		if (id_usuario != null && id_usuario > 0) {
			builder.append("AND id_usuario = ? ");
			ordemDoIdUsuario = ++count;
		}

		// ID Grupo
		if (id_grupo != null && id_grupo > 0) {
			builder.append("AND id_grupo = ? ");
			ordemDoIdGrupo = ++count;
		}

		// Nome
		if (nome != null) {
			builder.append("AND nome LIKE ? ");
			ordemDoNome = ++count;
		}

		// Login
		if (login != null) {
			builder.append("AND login = ? ");
			ordemDoLogin = ++count;
		}

		// Senha
		if (senha != null) {
			builder.append("AND senha = ? ");
			ordemDaSenha = ++count;
		}

		// Fechando a instrução
		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoIdUsuario > 0)
				preparedStatement.setInt(ordemDoIdUsuario, id_usuario);

			if (ordemDoIdGrupo > 0)
				preparedStatement.setInt(ordemDoIdGrupo, id_grupo);

			if (ordemDoNome > 0)
				preparedStatement.setString(ordemDoNome, "%" + nome + "%");

			if (ordemDoLogin > 0)
				preparedStatement.setString(ordemDoLogin, login);

			if (ordemDaSenha > 0)
				preparedStatement.setString(ordemDaSenha, senha);

			// Executando a query e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId_usuario(resultSet.getInt("id_usuario"));
				usuario.setGrupo(grupoDAO.buscarPorId(resultSet
						.getInt("id_grupo")));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));

				// Adicionando o usuario à lista
				usuarios.add(usuario);
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

}
