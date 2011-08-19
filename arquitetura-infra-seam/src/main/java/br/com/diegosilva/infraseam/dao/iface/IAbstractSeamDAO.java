package br.com.diegosilva.infraseam.dao.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.com.diegosilva.infraseam.dao.exception.DaoException;
import br.com.diegosilva.infraseam.entity.DefaultEntity;

public interface IAbstractSeamDAO extends Serializable {

	<E extends DefaultEntity<?>> Collection<E> findByExampleWithParams(E type,
			boolean enableLike, boolean excludeNone, boolean ignoreCase);

	<E extends DefaultEntity<?>> Collection<E> findAll(E type, String where,
			String orderBy);

	<E extends DefaultEntity<?>> List<Object[]> getRevisions(E obj)
			throws DaoException;

	<E extends DefaultEntity<?>> void insert(E obj);

	<E extends DefaultEntity<?>> E save(E obj);

	<E extends DefaultEntity<?>> E update(E obj);

	<E extends DefaultEntity<?>> void delete(E obj);

	<E extends DefaultEntity<?>> E findByPk(E obj);

	<E extends DefaultEntity<?>> Collection<E> findAll(E type);

	<E extends DefaultEntity<?>> Collection<E> findByExample(E type);
}
