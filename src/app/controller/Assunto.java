package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.AssuntoDAO;
import app.dao.Rel_AssuntoPerguntaDAO;

/**
 * Assunto
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
public class Assunto {
	private Integer id_assunto;
	private String descricao;

	private AssuntoDAO assuntoDAO = new AssuntoDAO();
	private Rel_AssuntoPerguntaDAO rel_assuntoPerguntaDAO = new Rel_AssuntoPerguntaDAO();

	private String[] assuntoPrimaryKey = this.assuntoDAO.getPrimaryKey();
	private String[] rel_assuntoPerguntaPK = this.rel_assuntoPerguntaDAO
			.getPrimaryKey();
	private String[] assuntoCampos = this.assuntoDAO.getCampos();

	/**
	 * Assunto
	 */
	public Assunto() {

	}

	/**
	 * Assunto
	 * 
	 * @param id_assunto
	 * @param descricao
	 */
	public Assunto(Integer id_assunto, String descricao) {
		super();
		this.id_assunto = id_assunto;
		this.descricao = descricao;
	}

	/**
	 * Cria objeto baseado no HashMap de entrada
	 * 
	 * @param map
	 */
	Assunto novoObjeto(Map<String, Object> map) {

		Assunto assunto = new Assunto();
		assunto.setId_assunto((Integer) map.get(assuntoPrimaryKey[0]));
		assunto.setDescricao((String) map.get(assuntoCampos[0]));

		return assunto;
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada
	 * 
	 * @param map
	 */
	void carregarObjeto(Map<String, Object> map) {

		this.id_assunto = (Integer) map.get(assuntoPrimaryKey[0]);
		this.descricao = (String) map.get(assuntoCampos[0]);
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.assuntoDAO.adicionar(descricao) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		Map<String, Object> map = this.assuntoDAO.buscarPorId(this.id_assunto);

		if (map != null) {
			this.carregarObjeto(map);

			return true;
		}
		return false;
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		return this.assuntoDAO.editar(id_assunto, descricao) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.assuntoDAO.excluir(id_assunto) > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<Assunto> listar() {
		// buscando a lista de Mapas recuperados pelos parametros
		List<Map<String, Object>> listMap = this.assuntoDAO
				.listarPor(descricao);

		// lista
		List<Assunto> assuntos = new ArrayList<Assunto>();

		// Iterando
		for (Map<String, Object> map : listMap) {
			// preenchendo o objeto
			Assunto assunto = this.novoObjeto(map);

			// inserindo à lista
			assuntos.add(assunto);
		}
		// retornando a lista
		return assuntos;
	}

	/**
	 * Listar Disciplinas
	 * 
	 * @return
	 */
	public List<Disciplina> listarDisciplinas() {
		// TODO: Desenvolver
		return null;
	}

	/**
	 * Inserir Pergunta
	 * 
	 * @param pergunta
	 * @return
	 */
	public boolean inserirPergunta(Pergunta pergunta) {
		return this.rel_assuntoPerguntaDAO.adicionar(this.id_assunto,
				pergunta.getId_pergunta()) > 0;
	}

	/**
	 * Remover Pergunta
	 * 
	 * @param pergunta
	 * @return
	 */
	public boolean removerPergunta(Pergunta pergunta) {
		Map<String, Object> map = this.rel_assuntoPerguntaDAO.listarPor(
				this.id_assunto, pergunta.getId_pergunta()).get(0);

		if (map != null) {
			// pegando o id
			Integer id = (Integer) map.get(this.rel_assuntoPerguntaPK[0]);
			// excluindo
			return this.rel_assuntoPerguntaDAO.excluir(id) > 0;
		}
		return false;
	}

	/**
	 * Listar Perguntas
	 * 
	 * @return
	 */
	public List<Pergunta> listarPerguntas(boolean carregarRelacionamentos) {
		List<Map<String, Object>> listMap = this.rel_assuntoPerguntaDAO
				.listarPerguntasPorAssunto(id_assunto);

		List<Pergunta> perguntas = new ArrayList<Pergunta>();

		for (Map<String, Object> map : listMap) {
			perguntas.add(new Pergunta().novoObjeto(map,
					carregarRelacionamentos));
		}
		return perguntas;
	}

	public Integer getId_assunto() {
		return id_assunto;
	}

	public void setId_assunto(Integer id_assunto) {
		this.id_assunto = id_assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_assunto == null) ? 0 : id_assunto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assunto other = (Assunto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_assunto == null) {
			if (other.id_assunto != null)
				return false;
		} else if (!id_assunto.equals(other.id_assunto))
			return false;
		return true;
	}

}
