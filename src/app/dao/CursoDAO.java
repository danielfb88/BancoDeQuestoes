package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import app.controller.Curso;
import app.util.AbstractDAO;
import app.util.conexao.DAOUtil;

/**
 * Grupo DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class CursoDAO extends AbstractDAO {

	public CursoDAO() {
		super.nomeDaTabela = "curso";
		super.campos = new String[]{"id_curso", "descricao", "sigla", "tipo_graduacao"};
	}

	public int adicionar(String descricao, String sigla, String tipo_graduacao) {
		// Criando um hashMap com 4 posiçoes
		Map<String, Object> campoValor = new HashMap<String, Object>(4);
		// Mapeando as chave aos valores no hashmap
		campoValor.put(this.campos[1], descricao);
		campoValor.put(this.campos[2], sigla);
		campoValor.put(this.campos[3], tipo_graduacao);
		// executando
		return super._adicionar(campoValor);
	}

	public int editar(Integer id_curso, String descricao, String sigla,
			String tipo_graduacao) {

		StringBuilder builder = new StringBuilder();
		int linhasAfetadas = 0;

		try {
			builder.append("UPDATE curso SET ");
			builder.append("descricao = ?, ");
			builder.append("sigla = ?, ");
			builder.append("tipo_graduacao = ? ");
			builder.append("WHERE ");
			builder.append("id_curso = ? ");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			preparedStatement.setString(1, descricao);
			preparedStatement.setString(2, sigla);
			preparedStatement.setString(3, tipo_graduacao);
			preparedStatement.setInt(4, id_curso);

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

			String sql = "DELETE FROM curso WHERE id_curso = ?;";
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

	public Curso buscarPorId(Integer id) {
		Curso curso = new Curso();

		String sql = "SELECT * FROM curso WHERE id_curso = ?;";

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

			curso.setId_curso(resultSet.getInt("id_curso"));
			curso.setDescricao(resultSet.getString("descricao"));
			curso.setSigla(resultSet.getString("sigla"));
			curso.setTipo_graduacao(resultSet.getString("tipo_graduacao"));

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return curso;
	}

	public List<Curso> listarPor(Integer id_curso, String descricao,
			String sigla, String tipo_graduacao) {
		List<Curso> cursos = new LinkedList<Curso>();
		StringBuilder builder = new StringBuilder();

		// Organizador dos parâmetros para o preparedStatement
		int count = 0;
		int ordemDoId_curso = 0;
		int ordemDaDescricao = 0;
		int ordemDaSigla = 0;
		int ordemDoTipo_graduacao = 0;

		// Query
		builder.append("SELECT * FROM curso WHERE true ");

		// ID
		if (id_curso != null && id_curso > 0) {
			builder.append("AND id_curso = ? ");
			ordemDoId_curso = ++count;
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

		// Tipo_Graduacao
		if (tipo_graduacao != null) {
			builder.append("AND tipo_graduacao = ? ");
			ordemDoTipo_graduacao = ++count;
		}

		builder.append(";");

		try {
			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// Verificando a ordem dos parâmetros
			if (ordemDoId_curso > 0)
				preparedStatement.setInt(ordemDoId_curso, id_curso);

			if (ordemDaDescricao > 0)
				preparedStatement.setString(ordemDaDescricao, "%" + descricao
						+ "%");

			if (ordemDaSigla > 0)
				preparedStatement.setString(ordemDaSigla, sigla);

			if (ordemDoTipo_graduacao > 0)
				preparedStatement.setString(ordemDoTipo_graduacao,
						tipo_graduacao);

			// Executando a quary e retornando em um ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterando entre os objetos retornados e inserindo-os em objetos
			while (resultSet.next()) {
				cursos.add(new Curso(resultSet.getInt("id_curso"), resultSet
						.getString("descricao"), resultSet.getString("sigla"),
						resultSet.getString("tipo_graduacao")));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cursos;
	}
}
