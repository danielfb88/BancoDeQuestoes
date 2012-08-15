package app.dao.postgresdialect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.dao.iterface.IAssuntoDAO;
import app.dto.Assunto;
import app.util.DAOUtil;

public class AssuntoDAO implements IAssuntoDAO {

	public AssuntoDAO() {

	}

	@Override
	public int adicionar(Assunto assunto) {
		int linhasAfetadas = 0;

		try {

			String sql = "INSERT INTO assunto (descricao) VALUES (?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setString(1, assunto.getDescricao());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int editar(Assunto assunto) {
		int linhasAfetadas = 0;

		try {

			String sql = "UPDATE assunto SET descricao = ? WHERE id_assunto = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setString(1, assunto.getDescricao());
			preparedStatement.setInt(2, assunto.getId_assunto());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int excluir(Assunto assunto) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM assunto WHERE id_assunto = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(sql);

			preparedStatement.setInt(1, assunto.getId_assunto());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public Assunto getById(int id) {
		Assunto assunto = new Assunto();

		String sql = "SELECT * FROM assunto WHERE id_assunto = ?;";

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

			assunto.setId_assunto(resultSet.getInt("id_assunto"));
			assunto.setDescricao(resultSet.getString("descricao"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return assunto;
	}

	@Override
	public List<Assunto> getAllBy(Assunto assunto) {
		List<Assunto> assuntos = new LinkedList<Assunto>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoId_assunto = 0;
		int ordemDaDescricao = 0;

		// Query
		builder.append("SELECT * FROM assunto WHERE true ");

		// ID
		if (assunto.getId_assunto() != null && assunto.getId_assunto() > 0) {
			builder.append("AND id_assunto = ? ");
			ordemDoId_assunto = ++count;
		}

		// Descricao
		if (assunto.getDescricao() != null) {
			builder.append("AND descricao LIKE ? ");
			ordemDaDescricao = ++count;
		}

		// Fechando a instrução
		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoId_assunto > 0)
				preparedStatement.setInt(ordemDoId_assunto,
						assunto.getId_assunto());

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao,
						"%" + assunto.getDescricao() + "%");

			// Executando a quary e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados e inserindo-os em objetos
			while (resultSet.next()) {
				assuntos.add(new Assunto(resultSet.getInt("id_assunto"),
						resultSet.getString("descricao")));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return assuntos;
	}

}
