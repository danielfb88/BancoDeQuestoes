package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import app.controller.CoordenadorCurso;
import app.util.DAOUtil;

/**
 * CoordenadorCurso DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 23-08-2012
 * 
 */
public class CoordenadorCursoDAO {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private CursoDAO cursoDAO = new CursoDAO();

	public CoordenadorCursoDAO() {

	}

	public int adicionar(Integer id_usuario, Integer id_curso,
			Date data_entrada, Date data_saida) {

		StringBuilder builder = new StringBuilder();
		int linhasAfetadas = 0;

		try {
			builder.append("INSERT INTO coordenadorcurso ");
			builder.append("(id_usuario, id_curso, data_entrada, data_saida) ");
			builder.append("VALUES ");
			builder.append("(?, ?, ?, ?) ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setInt(1, id_usuario);
			preparedStatement.setInt(2, id_curso);
			preparedStatement.setDate(3, data_entrada);
			preparedStatement.setDate(4, data_saida);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int editar(Integer id_coordenadorCurso, Integer id_usuario,
			Integer id_curso, Date data_entrada, Date data_saida) {

		StringBuilder builder = new StringBuilder();
		int linhasAfetadas = 0;

		try {
			builder.append("UPDATE coordenadorcurso SET ");
			builder.append("id_usuario = ?, ");
			builder.append("id_curso = ?, ");
			builder.append("data_entrada = ?, ");
			builder.append("data_saida = ?, ");
			builder.append("WHERE id_coordenadorcurso = ? ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setInt(1, id_usuario);
			preparedStatement.setInt(2, id_curso);
			preparedStatement.setDate(3, data_entrada);
			preparedStatement.setDate(4, data_saida);
			preparedStatement.setInt(5, id_coordenadorCurso);

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

			String sql = "DELETE FROM coordenadorCurso WHERE id_coordenadorCurso = ?;";
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

	public CoordenadorCurso buscarPorId(Integer id) {
		CoordenadorCurso coordenadorCurso = new CoordenadorCurso();

		String sql = "SELECT * FROM coordenadorCurso WHERE id_coordenadorCurso = ?;";

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

			coordenadorCurso.setId_coordenadorCurso(resultSet
					.getInt("id_coordenadorcurso"));
			coordenadorCurso.setCoordenador(this.usuarioDAO
					.buscarPorId(resultSet.getInt("id_usuario")));
			coordenadorCurso.setCurso(this.cursoDAO.buscarPorId(resultSet
					.getInt("id_curso")));
			coordenadorCurso.setDataEntrada(resultSet.getDate("data_entrada"));
			coordenadorCurso.setDataSaida(resultSet.getDate("data_saida"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coordenadorCurso;
	}

	public List<CoordenadorCurso> listarPor(Integer id_coordenadorCurso,
			Integer id_usuario, Integer id_curso, Date data_entrada,
			Date data_saida) {

		List<CoordenadorCurso> coordenadorCursos = new LinkedList<CoordenadorCurso>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoId = 0;
		int ordemDoIdUsuario = 0;
		int ordemDoIdCurso = 0;
		int ordemDaData_entrada = 0;
		int ordemDaData_saida = 0;

		// Query
		builder.append("SELECT * FROM coordenadorcurso WHERE true ");

		// ID
		if (id_coordenadorCurso != null && id_coordenadorCurso > 0) {
			builder.append("AND id_coordenadorcurso = ? ");
			ordemDoId = ++count;
		}

		// ID_USUARIO
		if (id_usuario != null && id_usuario > 0) {
			builder.append("AND id_usuario = ? ");
			ordemDoIdUsuario = ++count;
		}

		// ID_CURSO
		if (id_curso != null && id_curso > 0) {
			builder.append("AND id_curso = ? ");
			ordemDoIdCurso = ++count;
		}

		// DATA_ENTRADA
		if (data_entrada != null) {
			builder.append("AND data_entrada = ? ");
			ordemDaData_entrada = ++count;
		}

		// DATA_SAIDA
		if (data_saida != null) {
			builder.append("AND data_saida = ? ");
			ordemDaData_entrada = ++count;
		}

		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoId > 0)
				preparedStatement.setInt(ordemDoId, id_coordenadorCurso);

			if (ordemDoIdUsuario > 0)
				preparedStatement.setInt(ordemDoIdUsuario, id_usuario);

			if (ordemDoIdCurso > 0)
				preparedStatement.setInt(ordemDoIdCurso, id_curso);

			if (ordemDaData_entrada > 0)
				preparedStatement.setDate(ordemDaData_entrada, data_entrada);

			if (ordemDaData_saida > 0)
				preparedStatement.setDate(ordemDaData_saida, data_saida);

			// Executando a quary e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coordenadorCursos
						.add(new CoordenadorCurso(resultSet
								.getInt("id_coordenadorcurso"), this.usuarioDAO
								.buscarPorId(resultSet.getInt("id_usuario")),
								this.cursoDAO.buscarPorId(resultSet
										.getInt("id_curso")), resultSet
										.getDate("data_entrada"), resultSet
										.getDate("data_saida")));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coordenadorCursos;
	}
}
