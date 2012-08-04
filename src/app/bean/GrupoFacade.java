package app.bean;

import java.util.List;

import app.dao.postgresDialect.GrupoDAO;
import app.dto.Grupo;

public class GrupoFacade {
	private GrupoDAO grupoDAO = new GrupoDAO();
    private Grupo grupoSelecionado = new Grupo();
    private List<Grupo> grupos = null;

	public GrupoFacade() {

	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}
	
	public String adicionar() {
		this.grupoDAO.adicionar(this.grupoSelecionado);
		return "adicionou";
	}
	
	public String deletar() {
        this.grupoDAO.excluir(this.grupoSelecionado);
        return "inicio";
    }

    public String alterar() {
    	this.grupoDAO.alterar(grupoSelecionado);
        return "consultarGrupo";
    }

}
