package app.util;

import app.util.exceptions.CamposObrigatoriosInvalidosException;
import app.util.exceptions.EstadoInvalidoException;
import app.util.exceptions.IDInvalidoException;

/**
 * Classe abstrata que torna os seus objetos 'Persistíveis'. Ou seja, auxilia na 
 * validação dos campos essenciais para operações realizadas no banco.
 * 
 * @author Daniel Bonfim <daniel.fb88@mail.com>
 *
 */
public abstract class ObjetoPersistente {
	
	/**
	 * Verifica se o ID foi preenchido corretamente
	 * 
	 * @return
	 */
	protected abstract boolean idPreenchidoCorretamente();

	/**
	 * Verifica se os campos obrigatórios foram preenchidos corretamente
	 * 
	 * @return
	 */
	protected abstract boolean camposObrigatoriosPreenchidosCorretamente();

	/**
	 * Verifica estado para ADIÇÃO
	 * 
	 * @return
	 */
	protected boolean verificarEstadoParaAdicao() {
		try {
			if (!this.camposObrigatoriosPreenchidosCorretamente())
				throw new CamposObrigatoriosInvalidosException();

		} catch (EstadoInvalidoException e) {
			Logger.log(e.getMessage(), e.getStackTrace());
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Verifica estado para EDIÇÃO
	 * 
	 * @return
	 */
	protected boolean verificarEstadoParaEdicao() {
		try {
			if (!this.idPreenchidoCorretamente())
				throw new IDInvalidoException();

			if (!this.camposObrigatoriosPreenchidosCorretamente())
				throw new CamposObrigatoriosInvalidosException();

		} catch (EstadoInvalidoException e) {
			Logger.log(e.getMessage(), e.getStackTrace());
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Verifica estado para CARREGAR / EXCLUIR
	 * 
	 * @return
	 */
	protected boolean verificarEstadoParaCarregar_Excluir() {
		try {
			if (!this.idPreenchidoCorretamente())
				throw new IDInvalidoException();

		} catch (EstadoInvalidoException e) {
			Logger.log(e.getMessage(), e.getStackTrace());
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
