package app.dao.postgresdialect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.dao.iterface.IUsuarioDAO;
import app.dto.Usuario;
import app.util.DAOUtil;

/**
 * Usuario DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 08-08-2012
 * 
 */
public class UsuarioDAO implements IUsuarioDAO {
	GrupoDAO grupoDAO = new GrupoDAO();

	public UsuarioDAO() {

	}

	@Override
	public int adicionar(Usuario usuario) {
		int linhasAfetadas = 0;

		try {
			String sql = "INSERT INTO usuario (id_grupo, nome, login, senha) VALUES (?, ?, ?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, usuario.getGrupo().getId_grupo());
			preparedStatement.setString(2, usuario.getNome());
			preparedStatement.setString(3, usuario.getLogin());
			preparedStatement.setString(4, usuario.getSenha());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int editar(Usuario usuario) {
		int linhasAfetadas = 0;

		try {
			String sql = "UPDATE usuario SET id_grupo = ?, nome = ?, senha = ? WHERE id_usuario = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, usuario.getGrupo().getId_grupo());
			preparedStatement.setString(2, usuario.getNome());
			preparedStatement.setString(3, usuario.getSenha());
			preparedStatement.setInt(4, usuario.getId_usuario());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int excluir(Usuario usuario) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM usuario WHERE id_usuario = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, usuario.getId_usuario());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public Usuario getById(int id) {
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
			usuario.setGrupo(grupoDAO.getById(resultSet.getInt("id_grupo")));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public List<Usuario> getAllBy(Usuario usuario) {
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
		if (usuario.getId_usuario() != null && usuario.getId_usuario() > 0) {
			builder.append("AND id_usuario = ? ");
			ordemDoIdUsuario = ++count;
		}

		// ID Grupo
		if (usuario.getGrupo() != null) {
			if (usuario.getGrupo().getId_grupo() != null
					&& usuario.getGrupo().getId_grupo() > 0) {
				builder.append("AND id_grupo = ? ");
				ordemDoIdGrupo = ++count;
			}
		}

		// Nome
		if (usuario.getNome() != null) {
			builder.append("AND nome LIKE ? ");
			ordemDoNome = ++count;
		}

		// Login
		if (usuario.getLogin() != null) {
			builder.append("AND login = ? ");
			ordemDoLogin = ++count;
		}

		// Senha
		if (usuario.getSenha() != null) {
			builder.append("AND senha = ? ");
			ordemDaSenha = ++count;
		}

		// Fechando a instrução
		builder.append(";");

		// System.out.println(builder.toString());

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoIdUsuario > 0)
				preparedStatement.setInt(ordemDoIdUsuario,
						usuario.getId_usuario());

			if (ordemDoIdGrupo > 0)
				preparedStatement.setInt(ordemDoIdGrupo, usuario.getGrupo()
						.getId_grupo());

			if (ordemDoNome > 0)
				preparedStatement.setString(ordemDoNome,
						"%" + usuario.getNome() + "%");

			if (ordemDoLogin > 0)
				preparedStatement.setString(ordemDoLogin, usuario.getLogin());

			if (ordemDaSenha > 0)
				preparedStatement.setString(ordemDaSenha, usuario.getSenha());

			// Executando a query e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados
			while (resultSet.next()) {
				Usuario usuarioRetornado = new Usuario();
				usuarioRetornado.setId_usuario(resultSet.getInt("id_usuario"));
				usuarioRetornado.setGrupo(grupoDAO.getById(resultSet
						.getInt("id_grupo")));
				usuarioRetornado.setNome(resultSet.getString("nome"));
				usuarioRetornado.setLogin(resultSet.getString("login"));
				usuarioRetornado.setSenha(resultSet.getString("senha"));

				// Adicionando o usuario à lista
				usuarios.add(usuarioRetornado);
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

}
