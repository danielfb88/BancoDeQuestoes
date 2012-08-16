package app.dao.iterface;

import java.util.List;

import app.dto.AnoSemestre;

public interface IAnoSemestreDAO {
	public int adicionar(AnoSemestre anoSemestre);

	public int editar(AnoSemestre anoSemestre);

	public int excluir(AnoSemestre anoSemestre);

	public AnoSemestre getById(int id);

	public List<AnoSemestre> getAllBy(AnoSemestre anoSemestre);
}
