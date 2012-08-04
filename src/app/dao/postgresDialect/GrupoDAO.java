package app.dao.postgresDialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.dao.iterface.IGrupoDAO;
import app.dto.Grupo;
import app.util.DAOUtil;

/**
 * Grupo DAO
 * 
 * @author Daniel Bonfim
 * @since 03-08-2012
 *
 */
public class GrupoDAO implements IGrupoDAO {

	public GrupoDAO() {

	}

	@Override
	public int adicionar(Grupo grupo) {
		int linhasAfetadas = 0;

		try {

			String sql = "INSERT INTO grupo (descricao, tipo) VALUES (?, ?);";
			PreparedStatement preparedStatement = DAOUtil.getInstance().getPreparedStatement(sql);

			preparedStatement.setString(1, grupo.getDescricao());
			preparedStatement.setString(2, grupo.getTipo());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int alterar(Grupo grupo) {
		int linhasAfetadas = 0;

		try {

			String sql = "UPDATE TABLE grupo SET descricao = ?, tipo = ? WHERE id_grupo = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance().getPreparedStatement(sql);

			preparedStatement.setString(1, grupo.getDescricao());
			preparedStatement.setString(2, grupo.getTipo());
			preparedStatement.setInt(3, grupo.getId_grupo());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

	@Override
	public int excluir(Grupo grupo) {
		int linhasAfetadas = 0;

		try {

			String sql = "DELETE FROM grupo WHERE id_grupo = ?;";
			PreparedStatement preparedStatement = DAOUtil.getInstance().getPreparedStatement(sql);

			preparedStatement.setInt(1, grupo.getId_grupo());

			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return linhasAfetadas;
	}

}
