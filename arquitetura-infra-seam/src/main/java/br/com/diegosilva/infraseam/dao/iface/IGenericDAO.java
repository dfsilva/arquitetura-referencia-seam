package br.com.diegosilva.infraseam.dao.iface;

import java.util.Collection;
import java.util.List;

import br.com.diegosilva.infraseam.dao.exception.DaoException;
import br.com.diegosilva.infraseam.entity.iface.IDefaultEntity;

public interface IGenericDAO {

	<E extends IDefaultEntity<?>> E save(E obj) throws DaoException;

	<E extends IDefaultEntity<?>> void delete(E obj) throws DaoException;

	<E extends IDefaultEntity<?>> E findByPk(E obj) throws DaoException;

	<E extends IDefaultEntity<?>> Collection<E> findAll(E type)
			throws DaoException;

	<E extends IDefaultEntity<?>> Collection<E> findAll(E type, String where,
			String orderBy) throws DaoException;

	<E extends IDefaultEntity<?>> Collection<E> findByExample(E type)
			throws DaoException;

	<E extends IDefaultEntity<?>> Collection<E> findByExampleWithParams(E type,
			boolean enableLike, boolean excludeNone, boolean ignoreCase)
			throws DaoException;

	<E extends IDefaultEntity<?>> List<Object[]> getRevisions(E obj)
			throws DaoException;

}
