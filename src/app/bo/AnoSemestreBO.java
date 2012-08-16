package app.bo;

import java.util.List;

import app.dao.iterface.IAnoSemestreDAO;
import app.dto.AnoSemestre;
import app.util.FactoryDAO;

public class AnoSemestreBO {
	private IAnoSemestreDAO anoSemestreDAO;

	public AnoSemestreBO() {
		this.anoSemestreDAO = FactoryDAO.criarAnoSemestreDAO();
	}

	public boolean adicionar(AnoSemestre anoSemestre) {
		return this.anoSemestreDAO.adicionar(anoSemestre) > 0;
	}

	public boolean editar(AnoSemestre anoSemestre) {
		return this.anoSemestreDAO.editar(anoSemestre) > 0;
	}

	public boolean excluir(AnoSemestre anoSemestre) {
		return this.anoSemestreDAO.excluir(anoSemestre) > 0;
	}

	public AnoSemestre getById(int id) {
		return this.anoSemestreDAO.getById(id);
	}

	public List<AnoSemestre> getAllBy(AnoSemestre anoSemestre) {
		return this.anoSemestreDAO.getAllBy(anoSemestre);
	}
}
