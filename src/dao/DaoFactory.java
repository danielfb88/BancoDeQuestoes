package dao;

import dao.hibernate.AnoSemestreDAO;
import dao.hibernate.AssuntoDAO;
import dao.hibernate.CoordenadorCursoDAO;
import dao.hibernate.CursoDAO;
import dao.hibernate.DisciplinaDAO;
import dao.hibernate.GradeDAO;
import dao.hibernate.GradePeriodoDAO;
import dao.hibernate.GrupoDAO;
import dao.hibernate.PerguntaDAO;
import dao.hibernate.PeriodoDAO;
import dao.hibernate.ProvaDAO;
import dao.hibernate.RespostaDAO;
import dao.hibernate.UsuarioDAO;

/**
 * Fábrica de DAOs
 * 
 * @author Daniel Bonfim (daniel.fb88@gmail.com)
 * 
 */
public class DaoFactory {
	private static GrupoDAO grupoDAO;
	private static UsuarioDAO usuarioDAO;
	private static AnoSemestreDAO anoSemestreDAO;
	private static AssuntoDAO assuntoDAO;
	private static CoordenadorCursoDAO coordenadorCursoDAO;
	private static CursoDAO cursoDAO;
	private static DisciplinaDAO disciplinaDAO;
	private static GradeDAO gradeDAO;
	private static PeriodoDAO periodoDAO;
	private static GradePeriodoDAO gradePeriodoDAO;
	private static PerguntaDAO perguntaDAO;
	private static ProvaDAO provaDAO;
	private static RespostaDAO respostaDAO;

	public static GrupoDAO getGrupoDAO() {
		if (grupoDAO == null)
			grupoDAO = new GrupoDAO();

		return grupoDAO;
	}

	public static UsuarioDAO getUsuarioDAO() {
		if (usuarioDAO == null)
			usuarioDAO = new UsuarioDAO();

		return usuarioDAO;
	}

	public static AnoSemestreDAO getAnoSemestreDAO() {
		if (anoSemestreDAO == null)
			anoSemestreDAO = new AnoSemestreDAO();

		return anoSemestreDAO;
	}

	public static AssuntoDAO getAssuntoDAO() {
		if (assuntoDAO == null)
			assuntoDAO = new AssuntoDAO();

		return assuntoDAO;
	}

	public static CoordenadorCursoDAO getCoordenadorCursoDAO() {
		if (coordenadorCursoDAO == null)
			coordenadorCursoDAO = new CoordenadorCursoDAO();
		return coordenadorCursoDAO;
	}

	public static CursoDAO getCursoDAO() {
		if (cursoDAO == null)
			cursoDAO = new CursoDAO();

		return cursoDAO;
	}

	public static DisciplinaDAO getDisciplinaDAO() {
		if (disciplinaDAO == null)
			disciplinaDAO = new DisciplinaDAO();

		return disciplinaDAO;
	}

	public static GradeDAO getGradeDAO() {
		if (gradeDAO == null)
			gradeDAO = new GradeDAO();

		return gradeDAO;
	}

	public static PeriodoDAO getPeriodoDAO() {
		if (periodoDAO == null)
			periodoDAO = new PeriodoDAO();

		return periodoDAO;
	}

	public static GradePeriodoDAO getGradePeriodoDAO() {
		if (gradePeriodoDAO == null)
			gradePeriodoDAO = new GradePeriodoDAO();

		return gradePeriodoDAO;
	}

	public static PerguntaDAO getPerguntaDAO() {
		if (perguntaDAO == null)
			perguntaDAO = new PerguntaDAO();

		return perguntaDAO;
	}

	public static ProvaDAO getProvaDAO() {
		if (provaDAO == null)
			provaDAO = new ProvaDAO();

		return provaDAO;
	}

	public static RespostaDAO getRespostaDAO() {
		if (respostaDAO == null)
			respostaDAO = new RespostaDAO();

		return respostaDAO;
	}
}
