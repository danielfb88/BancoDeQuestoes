package app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.controller.Curso;
import app.util.AbstractDAO;

/**
 * Grupo DAO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class CursoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public CursoDAO() {
		super.nomeDaTabela = "curso";
		super.primaryKey = new String[] { "id_curso" };
		super.campos = new String[] { "descricao", "sigla", "tipo_graduacao" };
	}

	public int adicionar(String descricao, String sigla, String tipo_graduacao) {
		this.campoValor = new HashMap<Object, Object>(3);

		this.campoValor.put(super.campos[0], descricao);
		this.campoValor.put(super.campos[1], sigla);
		this.campoValor.put(super.campos[2], tipo_graduacao);

		return super._adicionar(campoValor);
	}

	public int editar(Integer id_curso, String descricao, String sigla,
			String tipo_graduacao) {

		this.campoValor = new HashMap<Object, Object>(4);

		this.campoValor.put(super.primaryKey[0], id_curso);
		this.campoValor.put(super.campos[0], descricao);
		this.campoValor.put(super.campos[1], sigla);
		this.campoValor.put(super.campos[2], tipo_graduacao);

		return super._editar(campoValor);
	}

	public Curso buscarPorId(Integer id) {
		Map<String, Object> campoValorRetornado = super.buscarPorId(id);

		if (!campoValorRetornado.isEmpty())
			return new Curso(
					(Integer) campoValorRetornado.get(this.primaryKey[0]),
					(String) campoValorRetornado.get(this.campos[0]),
					(String) campoValorRetornado.get(this.campos[1]),
					(String) campoValorRetornado.get(this.campos[2]));
		else
			return null;
	}

	public List<Curso> listarPor(String descricao, String sigla,
			String tipo_graduacao) {

		this.campoValor = new HashMap<Object, Object>(3);
		this.campoValor.put(this.campos[0], descricao);
		this.campoValor.put(this.campos[1], sigla);
		this.campoValor.put(this.campos[2], tipo_graduacao);

		super._listarPor(campoValor);

		return null;

	}
}
