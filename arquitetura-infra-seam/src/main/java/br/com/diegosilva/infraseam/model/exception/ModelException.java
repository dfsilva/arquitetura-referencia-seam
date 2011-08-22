package br.com.diegosilva.infraseam.model.exception;

import org.jboss.seam.annotations.ApplicationException;

@ApplicationException(rollback = true)
public class ModelException extends Exception {

	/**
	 * Variavel serialVersionUID do tipo long.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ModelException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ModelException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ModelException(Throwable cause) {
		super(cause);
	}

}
