package testes;

import java.util.List;

import controller.AnoSemestre;

public class Lacaio {

	public static void main(String[] args) {
		AnoSemestre anoSemestre = new AnoSemestre();
		List<AnoSemestre> listAS = anoSemestre.listar();
		for (AnoSemestre as : listAS) {
			System.out.println("ID: " + as.getId_anoSemestre());
			System.out.println("ANO: " + as.getAno());
			System.out.println("SEMESTRE: " + as.getSemestre());
		}
	}

}
