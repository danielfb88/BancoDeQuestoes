package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import app.controller.Grade;
import app.controller.Periodo;
import app.util.conexao.DAOUtil;

/**
 * Grade DAO Responsável pelas tabelas: - grade - grade_periodo -
 * grade_periodo_disciplina
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 20-08-2012
 * 
 */
public class GradeDAO {
	private CursoDAO cursoDAO = new CursoDAO();
	private AnoSemestreDAO anoSemestreDAO = new AnoSemestreDAO();

	public GradeDAO() {

	}

	public int adicionar(Integer id_curso, Integer id_anoSemestre_inicial,
			Integer id_anoSemestre_final, String descricao) {

		int linhasAfetadas = 0;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO grade ");
			builder.append("(id_curso, id_anosemestre_inicial, id_anosemestre_final, descricao) ");
			builder.append("VALUES ");
			builder.append("(?, ?, ?, ?) ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setInt(1, id_curso);
			preparedStatement.setInt(2, id_anoSemestre_inicial);
			preparedStatement.setInt(3, id_anoSemestre_final);
			preparedStatement.setString(4, descricao);

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	public int editar(Integer id_grade, Integer id_curso,
			Integer id_anoSemestre_inicial, Integer id_anoSemestre_final,
			String descricao) {

		int linhasAfetadas = 0;

		try {

			StringBuilder builder = new StringBuilder();
			builder.append("UPDATE grade ");
			builder.append("SET id_curso = ? ");
			builder.append("SET id_anosemestre_inicial = ? ");
			builder.append("SET id_anosemestre_final = ? ");
			builder.append("SET descricao = ? ");
			builder.append("WHERE id_grade = ? ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setInt(1, id_curso);
			preparedStatement.setInt(2, id_anoSemestre_inicial);
			preparedStatement.setInt(3, id_anoSemestre_final);
			preparedStatement.setString(4, descricao);
			preparedStatement.setInt(5, id_grade);

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

			String sql = "DELETE FROM grade WHERE id_grade = ?;";
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

	public Grade buscarPorId(Integer id) {
		Grade grade = new Grade();

		String sql = "SELECT * FROM grade WHERE id_grade = ?;";

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

			grade.setId_grade(resultSet.getInt("id_grade"));
			grade.setCurso(this.cursoDAO.buscarPorId(resultSet
					.getInt("id_curso")));
			grade.setAnoSemestre_inicial(this.anoSemestreDAO
					.buscarPorId(resultSet.getInt("id_anosemestre_inicial")));
			grade.setAnoSemestre_final(this.anoSemestreDAO
					.buscarPorId(resultSet.getInt("id_anosemestre_final")));
			grade.setDescricao(resultSet.getString("descricao"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grade;
	}

	public List<Grade> listarPor(Integer id_grade, Integer id_curso,
			Integer id_anoSemestre_inicial, Integer id_anoSemestre_final,
			String descricao) {

		List<Grade> grades = new LinkedList<Grade>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoId_grade = 0;
		int ordemDoId_curso = 0;
		int ordemDoId_anoSemestre_inicial = 0;
		int ordemDoId_anoSemestre_final = 0;
		int ordemDaDescricao = 0;

		// Query
		builder.append("SELECT * FROM grade WHERE true ");

		// ID
		if (id_grade != null && id_grade > 0) {
			builder.append("AND id_grade = ? ");
			ordemDoId_grade = ++count;
		}

		// ID_CURSO
		if (id_curso != null && id_curso > 0) {
			builder.append("AND id_curso = ? ");
			ordemDoId_curso = ++count;
		}

		// ID_ANOSEMESTRE_INICIAL
		if (id_anoSemestre_inicial != null && id_anoSemestre_inicial > 0) {
			builder.append("AND id_anosemestre_inicial = ? ");
			ordemDoId_anoSemestre_inicial = ++count;
		}

		// ID_ANOSEMESTRE_FINAL
		if (id_anoSemestre_final != null && id_anoSemestre_final > 0) {
			builder.append("AND id_anosemestre_final = ? ");
			ordemDoId_anoSemestre_final = ++count;
		}

		// Descricao
		if (descricao != null && !descricao.isEmpty()) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoId_grade > 0)
				preparedStatement.setInt(ordemDoId_grade, id_grade);

			if (ordemDoId_curso > 0)
				preparedStatement.setInt(ordemDoId_curso, id_curso);

			if (ordemDoId_anoSemestre_inicial > 0)
				preparedStatement.setInt(ordemDoId_anoSemestre_inicial,
						id_anoSemestre_inicial);

			if (ordemDoId_anoSemestre_final > 0)
				preparedStatement.setInt(ordemDoId_anoSemestre_final,
						id_anoSemestre_final);

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao, "%" + descricao
						+ "%");

			// Executando a quary e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados e inserindo-os em lista
			while (resultSet.next()) {
				grades.add(new Grade(
						resultSet.getInt("id_grade"),
						this.cursoDAO.buscarPorId(resultSet.getInt("id_curso")),
						this.anoSemestreDAO.buscarPorId(resultSet
								.getInt("id_anosemestre_inicial")),
						this.anoSemestreDAO.buscarPorId(resultSet
								.getInt("id_anosemestre_final")), resultSet
								.getString("descricao")));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grades;
	}

	/**
	 * Insere um periodo à grade. Tabela: grade_periodo
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	public boolean inserirPeriodo(int id_grade, int id_periodo) {
		boolean statusOk = false;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO grade_periodo ");
			builder.append("(id_grade, id_periodo) ");
			builder.append("VALUES ");
			builder.append("(" + id_grade + "," + id_periodo + ") ");
			builder.append(";");

			Statement statement = DAOUtil.getInstance().getStatement();

			statusOk = statement.execute(builder.toString());
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statusOk;
	}

	/**
	 * Remove um periodo da grade. Tabela: grade_periodo
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	public boolean removerPeriodo(int id_grade, int id_periodo) {
		boolean statusOk = false;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("DELETE FROM grade_periodo ");
			builder.append("WHERE ");
			builder.append("id_grade = " + id_grade + " ");
			builder.append("AND id_periodo = " + id_periodo + " ");
			builder.append(";");

			Statement statement = DAOUtil.getInstance().getStatement();

			statusOk = statement.execute(builder.toString());
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statusOk;
	}

	/**
	 * Lista todos os periodos da grade. Tabela: periodo, grade_periodo
	 * 
	 * @param id_grade
	 * @return
	 */
	public List<Periodo> listarPeriodos(int id_grade) {
		List<Periodo> periodos = new LinkedList<Periodo>();

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * ");
			builder.append("FROM periodo p ");
			builder.append("JOIN grade_periodo gp ON (p.id_periodo = gp.id_periodo) ");
			builder.append("WHERE ");
			builder.append("gp.id_grade = " + id_grade + " ");
			builder.append(";");

			Statement statement = DAOUtil.getInstance().getStatement();
			ResultSet resultSet = statement.executeQuery(builder.toString());

			while (resultSet.next()) {
				periodos.add(new Periodo(resultSet.getInt("id_periodo"),
						resultSet.getString("descricao"), resultSet
								.getInt("numero")));
			}

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return periodos;
	}
}
