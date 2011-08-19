package br.com.diegosilva.infraseam.exception;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.ApplicationException;

import br.com.diegosilva.infraseam.model.exception.ModelException;
import br.com.diegosilva.infraseam.util.FacesUtil;

/**
 * @author diego.dba
 * 
 */
@ApplicationException(rollback = true)
public class ValidateException extends ModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<String> violacoes;

	/**
	 * 
	 */
	public ValidateException() {
	}

	/**
	 * @param violacoes
	 *            - viola��es.
	 */
	public ValidateException(List<String> violacoes) {
		this.violacoes = violacoes;
	}

	/**
	 * @param message
	 *            - mensagem.
	 */
	public ValidateException(String message) {
		super(message);
		violacoes = new ArrayList<String>();
		violacoes.add(message);
	}

	/**
	 * @param cause
	 *            - causa.
	 */
	public ValidateException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            - mensagem.
	 * @param cause
	 *            - causa.
	 */
	public ValidateException(String message, Throwable cause) {
		super(message, cause);
		violacoes = new ArrayList<String>();
		violacoes.add(message);
	}

	/**
	 * @return violacoes - viola��es.
	 */
	public List<String> getViolacoes() {
		return violacoes;
	}

	/**
	 * Monta a mensagem jsf com os erros de validacoes dos campos.
	 */
	public void montarMensagenJsf() {
		for (String msg : violacoes) {
			FacesUtil.addErrorMessage(msg);
		}
	}

}
