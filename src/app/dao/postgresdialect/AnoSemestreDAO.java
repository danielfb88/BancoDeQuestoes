package app.dao.postgresdialect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.dao.iterface.IAnoSemestreDAO;
import app.dto.AnoSemestre;
import app.util.DAOUtil;

public class AnoSemestreDAO implements IAnoSemestreDAO {

	public AnoSemestreDAO() {

	}

	@Override
	public int adicionar(AnoSemestre anoSemestre) {
		int linhasAfetadas = 0;

		try {

			String sql = "INSERT INTO anosemestre (ano, semestre) VALUES (?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, anoSemestre.getAno());
			preparedStatement.setInt(2, anoSemestre.getSemestre());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int editar(AnoSemestre anoSemestre) {
		int linhasAfetadas = 0;

		try {

			String sql = "UPDATE anosemestre SET ano = ?, semestre = ? WHERE id_anosemestre = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, anoSemestre.getAno());
			preparedStatement.setInt(2, anoSemestre.getSemestre());
			preparedStatement.setInt(3, anoSemestre.getId_anoSemestre());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int excluir(AnoSemestre anoSemestre) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM anosemestre WHERE id_anosemestre = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, anoSemestre.getId_anoSemestre());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public AnoSemestre getById(int id) {
		AnoSemestre anoSemestre = new AnoSemestre();

		String sql = "SELECT * FROM anosemestre WHERE id_anosemestre = ?;";

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

			anoSemestre.setId_anoSemestre(resultSet.getInt("id_anosemestre"));
			anoSemestre.setAno(resultSet.getInt("ano"));
			anoSemestre.setSemestre(resultSet.getInt("semestre"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anoSemestre;
	}

	@Override
	public List<AnoSemestre> getAllBy(AnoSemestre anoSemestre) {
		List<AnoSemestre> anoSemestres = new LinkedList<AnoSemestre>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoId_anoSemestre = 0;
		int ordemDoAno = 0;
		int ordemDoSemestre = 0;

		// Query
		builder.append("SELECT * FROM anosemestre WHERE true ");

		// ID
		if (anoSemestre.getId_anoSemestre() != null
				&& anoSemestre.getId_anoSemestre() > 0) {
			builder.append("AND id_anosemestre = ? ");
			ordemDoId_anoSemestre = ++count;
		}

		// ANO
		if (anoSemestre.getAno() != null) {
			builder.append("AND ano LIKE ? ");
			ordemDoAno = ++count;
		}

		// SEMESTRE
		if (anoSemestre.getSemestre() != null) {
			builder.append("AND semestre = ? ");
			ordemDoSemestre = ++count;
		}

		// Fechando a instrução
		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoId_anoSemestre > 0)
				preparedStatement.setInt(ordemDoId_anoSemestre,
						anoSemestre.getId_anoSemestre());

			if (ordemDoAno > 0)
				preparedStatement.setInt(ordemDoAno, anoSemestre.getAno());

			if (ordemDoSemestre > 0)
				preparedStatement.setInt(ordemDoSemestre,
						anoSemestre.getSemestre());

			// Executando a quary e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados e inserindo-os em objetos
			while (resultSet.next()) {
				anoSemestres.add(new AnoSemestre(resultSet
						.getInt("id_anosemestre"), resultSet.getInt("ano"),
						resultSet.getInt("semestre")));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anoSemestres;
	}

}
