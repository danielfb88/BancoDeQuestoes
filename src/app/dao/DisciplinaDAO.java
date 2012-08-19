package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.controller.Disciplina;
import app.dao.iterface.IDisciplinaDAO;
import app.util.DAOUtil;

/**
 * Disciplina DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 08-08-2012
 * 
 */
public class DisciplinaDAO implements IDisciplinaDAO {
	CursoDAO cursoDAO = new CursoDAO();

	public DisciplinaDAO() {

	}

	@Override
	public int adicionar(Disciplina disciplina) {
		int linhasAfetadas = 0;

		try {
			String sql = "INSERT INTO disciplina (id_curso, descricao, sigla) VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, disciplina.getCurso().getId_curso());
			preparedStatement.setString(2, disciplina.getDescricao());
			preparedStatement.setString(3, disciplina.getSigla());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int editar(Disciplina disciplina) {
		int linhasAfetadas = 0;

		try {
			String sql = "UPDATE disciplina SET id_curso = ?, descricao = ?, sigla = ? WHERE id_disciplina = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, disciplina.getCurso().getId_curso());
			preparedStatement.setString(2, disciplina.getDescricao());
			preparedStatement.setString(3, disciplina.getSigla());
			preparedStatement.setInt(4, disciplina.getId_disciplina());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int excluir(Disciplina disciplina) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM disciplina WHERE id_disciplina = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, disciplina.getId_disciplina());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public Disciplina getById(int id) {
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
			disciplina.setCurso(cursoDAO.getById(resultSet.getInt("id_curso")));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return disciplina;
	}

	public List<Disciplina> getAllBy(Disciplina disciplina) {
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
		if (disciplina.getId_disciplina() != null
				&& disciplina.getId_disciplina() > 0) {
			builder.append("AND id_disciplina = ? ");
			ordemDoIdDisciplina = ++count;
		}

		// ID Curso
		if (disciplina.getCurso() != null) {
			if (disciplina.getCurso().getId_curso() != null
					&& disciplina.getCurso().getId_curso() > 0) {
				builder.append("AND id_curso = ? ");
				ordemDoIdCurso = ++count;
			}
		}

		// Descricao
		if (disciplina.getDescricao() != null) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// Sigla
		if (disciplina.getSigla() != null) {
			builder.append("AND sigla = ? ");
			ordemDaSigla = ++count;
		}

		// Fechando a instrução
		builder.append(";");

		// System.out.println(builder.toString());

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoIdDisciplina > 0)
				preparedStatement.setInt(ordemDoIdDisciplina,
						disciplina.getId_disciplina());

			if (ordemDoIdCurso > 0)
				preparedStatement.setInt(ordemDoIdCurso, disciplina.getCurso()
						.getId_curso());

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao,
						"%" + disciplina.getDescricao() + "%");

			if (ordemDaSigla > 0)
				preparedStatement
						.setString(ordemDaSigla, disciplina.getSigla());

			// Executando a query e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados
			while (resultSet.next()) {
				Disciplina disciplinaRetornado = new Disciplina();
				disciplinaRetornado.setId_disciplina(resultSet
						.getInt("id_disciplina"));
				disciplinaRetornado.setCurso(cursoDAO.getById(resultSet
						.getInt("id_curso")));
				disciplinaRetornado.setDescricao(resultSet.getString("descricao"));
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
