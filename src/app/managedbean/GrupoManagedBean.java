package app.managedbean;

import app.controller.GrupoController;
import app.dto.Grupo;

/**
 * ManagedBean para Grupo. Bean que será gerenciado pelo JSF atraves do arquivo
 * faces-config.xml.
 * 
 * @author Daniel Bonfim
 * @since 03-08-2012
 * 
 */
public class GrupoManagedBean {
	private GrupoController grupoController = new GrupoController();
	private Grupo grupo = new Grupo();

	public GrupoManagedBean() {

	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String adicionar() {
		int linhasAfetadas = this.grupoController.adicionar(this.grupo);
		if (linhasAfetadas > 0) 
			return "listarGrupos";
		else
			return "adicionarGrupo";
	}

	public String deletar() {
		this.grupoController.excluir(this.grupo);
		return "listarGrupos";
	}

	public String alterar() {
		this.grupoController.alterar(grupo);
		return "listarGrupos";
	}

}
