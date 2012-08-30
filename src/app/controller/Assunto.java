package app.controller;
dda
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.AssuntoDAO;

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

	/**
	 * Assunto
	 */
	public Assunto() {

	}

	/**
	 * Assunto
	 * @param id_assunto
	 * @param descricao
	 */
	public Assunto(Integer id_assunto, String descricao) {
		super();
		this.id_assunto = id_assunto;
		this.descricao = descricao;
	}

	/**
	 * Adicionar
	 * @return
	 */
	public boolean adicionar() {
		return this.assuntoDAO.adicionar(descricao) > 0;
	}

	/**
	 * Carregar
	 * @return
	 */
	public boolean carregar() {
		Map<String, Object> mapAssunto = this.assuntoDAO
				.buscarPorId(this.id_assunto);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.assuntoDAO.getPrimaryKey();
		String[] campos = this.assuntoDAO.getCampos();

		if (mapAssunto != null) {
			this.id_assunto = (Integer) mapAssunto.get(primaryKey[0]);
			this.descricao = (String) mapAssunto.get(campos[0]);

			return true;
		}
		return false;
	}

	/**
	 * Editar
	 * @return
	 */
	public boolean editar() {
		return this.assuntoDAO.editar(id_assunto, descricao) > 0;
	}

	/**
	 * Excluir
	 * @return
	 */
	public boolean excluir() {
		return this.assuntoDAO.excluir(id_assunto) > 0;
	}

	/**
	 * Listar
	 * @return
	 */
	public List<Assunto> listar() {
		// buscando a lista de Mapas recuperados pelos parametros
		List<Map<String, Object>> listMapAssunto = this.assuntoDAO
				.listarPor(descricao);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.assuntoDAO.getPrimaryKey();
		String[] campos = this.assuntoDAO.getCampos();

		// lista
		List<Assunto> assuntos = new ArrayList<Assunto>();

		// Iterando
		for (Map<String, Object> mapAssunto : listMapAssunto) {
			// preenchendo o objeto
			Assunto assunto = new Assunto();
			assunto.setId_assunto((Integer) mapAssunto.get(primaryKey[0]));
			assunto.setDescricao((String) mapAssunto.get(campos[0]));

			// inserindo à lista
			assuntos.add(assunto);
		}
		// retornando a lista
		return assuntos;
	}

	/**
	 * Listar Disciplinas
	 * @return
	 */
	public List<Disciplina> listarDisciplinas() {
		// TODO: Desenvolver
		return null;
	}

	/**
	 * Inserir Pergunta
	 * @param pergunta
	 * @return
	 */
	public boolean inserirPergunta(Pergunta pergunta) {
		// TODO: Desenvolver
		return false;
	}

	/**
	 * Remover Pergunta
	 * @param pergunta
	 * @return
	 */
	public boolean removerPergunta(Pergunta pergunta) {
		// TODO: Desenvolver
		return false;
	}

	/**
	 * Listar Perguntas
	 * @return
	 */
	public List<Pergunta> listarPerguntas() {
		// TODO: Desenvolver
		return null;
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
