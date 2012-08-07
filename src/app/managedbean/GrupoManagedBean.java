package app.managedbean;

import java.util.LinkedList;
import java.util.List;

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
	private List<Grupo> grupos = new LinkedList<Grupo>();

	public GrupoManagedBean() {

	}
		
	public List<Grupo> getGrupos() {
		this.grupos = grupoController.getAllBy(this.grupo);
        return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public String adicionar() {
		if (this.grupoController.adicionar(this.grupo)) 
			return "listarGrupos";
		else
			return "adicionarGrupo";
	}

	public String deletar() {
		this.grupoController.excluir(this.grupo);
		return "listarGrupos";
	}

	public String alterar() {
		if (this.grupoController.alterar(this.grupo))
			return "listarGrupos";
		else
			return "adicionarGrupo";
	}

}
