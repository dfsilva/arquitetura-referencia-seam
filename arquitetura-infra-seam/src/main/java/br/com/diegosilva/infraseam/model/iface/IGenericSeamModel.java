package br.com.diegosilva.infraseam.model.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.com.diegosilva.infraseam.entity.DefaultEntity;
import br.com.diegosilva.infraseam.exception.ValidateException;
import br.com.diegosilva.infraseam.model.exception.ModelException;

public interface IGenericSeamModel extends Serializable {

	<E extends DefaultEntity<?>> void save(E obj) throws ModelException;

	<E extends DefaultEntity<?>> void saveWithValidation(E obj)
			throws ModelException, ValidateException;

	<E extends DefaultEntity<?>> void delete(E obj) throws ModelException;

	<E extends DefaultEntity<?>> E findByPk(E obj) throws ModelException;

	<E extends DefaultEntity<?>> Collection<E> findAll(E obj)
			throws ModelException;

	<E extends DefaultEntity<?>> Collection<E> findByExample(E obj)
			throws ModelException;

	<E extends DefaultEntity<?>> Collection<E> findByExampleWithParams(E obj,
			boolean enableLike, boolean excludeNone, boolean ignoreCase)
			throws ModelException;

	<E extends DefaultEntity<?>> Collection<E> findAll(E obj, String where,
			String orderBy) throws ModelException;

	<E extends DefaultEntity<?>> Collection<E> findAll(Class<E> entityClass,
			String where, String orderBy) throws ModelException;

	<E extends DefaultEntity<?>> List<Object[]> getRevisions(E obj)
			throws ModelException;

}
