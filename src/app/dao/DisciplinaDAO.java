package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.controller.Disciplina;
import app.util.DAOUtil;

/**
 * Disciplina DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 08-08-2012
 * 
 */
public class DisciplinaDAO {
	CursoDAO cursoDAO = new CursoDAO();

	public DisciplinaDAO() {

	}

	public int adicionar(Integer id_curso, String descricao, String sigla) {
		int linhasAfetadas = 0;

		try {
			String sql = "INSERT INTO disciplina (id_curso, descricao, sigla) VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id_curso);
			preparedStatement.setString(2, descricao);
			preparedStatement.setString(3, sigla);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int editar(Integer id_disciplina, Integer id_curso,
			String descricao, String sigla) {
		int linhasAfetadas = 0;

		try {
			String sql = "UPDATE disciplina SET id_curso = ?, descricao = ?, sigla = ? WHERE id_disciplina = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, id_curso);
			preparedStatement.setString(2, descricao);
			preparedStatement.setString(3, sigla);
			preparedStatement.setInt(4, id_disciplina);

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

			String sql = "DELETE FROM disciplina WHERE id_disciplina = ?;";
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

	public Disciplina buscarPorId(Integer id) {
		Disciplina disciplina = new Disciplina();

		String sql = "SELECT * FROM disciplina WHERE id_disciplina = ?;";

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

			disciplina.setId_disciplina(resultSet.getInt("id_disciplina"));
			disciplina.setDescricao(resultSet.getString("descricao"));
			disciplina.setSigla(resultSet.getString("sigla"));
			disciplina.setCurso(cursoDAO.buscarPorId(resultSet
					.getInt("id_curso")));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return disciplina;
	}

	public List<Disciplina> listarPor(Integer id_disciplina, Integer id_curso,
			String descricao, String sigla) {
		List<Disciplina> disciplinas = new LinkedList<Disciplina>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoIdDisciplina = 0;
		int ordemDoIdCurso = 0;
		int ordemDaDescricao = 0;
		int ordemDaSigla = 0;

		// Query
		builder.append("SELECT * FROM disciplina WHERE true ");

		// ID
		if (id_disciplina != null && id_disciplina > 0) {
			builder.append("AND id_disciplina = ? ");
			ordemDoIdDisciplina = ++count;
		}

		// ID Curso
		if (id_curso != null && id_curso > 0) {
			builder.append("AND id_curso = ? ");
			ordemDoIdCurso = ++count;
		}

		// Descricao
		if (descricao != null) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// Sigla
		if (sigla != null) {
			builder.append("AND sigla = ? ");
			ordemDaSigla = ++count;
		}

		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoIdDisciplina > 0)
				preparedStatement.setInt(ordemDoIdDisciplina, id_disciplina);

			if (ordemDoIdCurso > 0)
				preparedStatement.setInt(ordemDoIdCurso, id_curso);

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao, "%" + descricao
						+ "%");

			if (ordemDaSigla > 0)
				preparedStatement.setString(ordemDaSigla, sigla);

			// Executando a query e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados
			while (resultSet.next()) {
				Disciplina disciplinaRetornado = new Disciplina();
				disciplinaRetornado.setId_disciplina(resultSet
						.getInt("id_disciplina"));
				disciplinaRetornado.setCurso(cursoDAO.buscarPorId(resultSet
						.getInt("id_curso")));
				disciplinaRetornado.setDescricao(resultSet
						.getString("descricao"));
				disciplinaRetornado.setSigla(resultSet.getString("sigla"));

				// Adicionando o disciplina à lista
				disciplinas.add(disciplinaRetornado);
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return disciplinas;
	}

}
