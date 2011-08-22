package br.com.diegosilva.infraseam.entity;

import java.io.Serializable;

import br.com.diegosilva.infraseam.entity.iface.IDefaultEntity;

public abstract class DefaultEntity<E extends Serializable> implements
		IDefaultEntity<E> {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

}
