package br.com.diegosilva.infraseam.model.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.com.diegosilva.infraseam.entity.iface.IDefaultEntity;
import br.com.diegosilva.infraseam.exception.ValidateException;
import br.com.diegosilva.infraseam.model.exception.ModelException;

public interface IGenericSeamModel extends Serializable {

	<E extends IDefaultEntity<?>> void save(E obj) throws ModelException;

	<E extends IDefaultEntity<?>> void saveWithValidation(E obj)
			throws ModelException, ValidateException;

	<E extends IDefaultEntity<?>> void delete(E obj) throws ModelException;

	<E extends IDefaultEntity<?>> E findByPk(E obj) throws ModelException;

	<E extends IDefaultEntity<?>> Collection<E> findAll(E obj)
			throws ModelException;

	<E extends IDefaultEntity<?>> Collection<E> findByExample(E obj)
			throws ModelException;

	<E extends IDefaultEntity<?>> Collection<E> findByExampleWithParams(E obj,
			boolean enableLike, boolean excludeNone, boolean ignoreCase)
			throws ModelException;

	<E extends IDefaultEntity<?>> Collection<E> findAll(E obj, String where,
			String orderBy) throws ModelException;

	<E extends IDefaultEntity<?>> Collection<E> findAll(Class<E> entityClass,
			String where, String orderBy) throws ModelException;

	<E extends IDefaultEntity<?>> List<Object[]> getRevisions(E obj)
			throws ModelException;

}
