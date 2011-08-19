package br.com.diegosilva.infraseam.model;

import java.util.Collection;
import java.util.List;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

import br.com.diegosilva.infraseam.dao.iface.IGenericDAO;
import br.com.diegosilva.infraseam.entity.DefaultEntity;
import br.com.diegosilva.infraseam.exception.ValidateException;
import br.com.diegosilva.infraseam.model.exception.ModelException;
import br.com.diegosilva.infraseam.model.iface.IGenericSeamModel;
import br.com.diegosilva.infraseam.util.ValidateUtils;

@Name("genericSeamModel")
@AutoCreate
public class GenericSeamModel implements IGenericSeamModel {

	/**
	 * Variavel serialVersionUID do tipo long.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variavel genericDao do tipo IGenericDAO<E,T>.
	 */
	@In("genericDao")
	private IGenericDAO genericDao;

	@Transactional(TransactionPropagationType.REQUIRED)
	public <E extends DefaultEntity<?>> void save(E obj) throws ModelException {
		try {
			getGenericDao().save(obj);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	@Transactional(TransactionPropagationType.REQUIRED)
	public <E extends DefaultEntity<?>> void saveWithValidation(E obj)
			throws ModelException, ValidateException {
		try {
			ValidateUtils.validate(obj);
			save(obj);
		} catch (ValidateException e) {
			throw e;
		} catch (ModelException e) {
			throw e;
		}
	}

	@Transactional(TransactionPropagationType.REQUIRED)
	public <E extends DefaultEntity<?>> void delete(E obj)
			throws ModelException {
		try {
			getGenericDao().delete(obj);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public <E extends DefaultEntity<?>> E findByPk(E obj) throws ModelException {
		try {
			return getGenericDao().findByPk(obj);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public <E extends DefaultEntity<?>> Collection<E> findAll(E obj)
			throws ModelException {
		try {
			return getGenericDao().findAll(obj);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public <E extends DefaultEntity<?>> Collection<E> findByExample(E obj)
			throws ModelException {
		try {
			return (Collection<E>) getGenericDao().findByExample(obj);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public <E extends DefaultEntity<?>> Collection<E> findByExampleWithParams(
			E obj, boolean enableLike, boolean excludeNone, boolean ignoreCase)
			throws ModelException {
		try {
			return getGenericDao().findByExampleWithParams(obj, enableLike,
					excludeNone, ignoreCase);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public <E extends DefaultEntity<?>> Collection<E> findAll(E obj,
			String where, String orderBy) throws ModelException {
		try {
			return getGenericDao().findAll(obj, where, orderBy);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public <E extends DefaultEntity<?>> Collection<E> findAll(
			Class<E> entityClass, String where, String orderBy)
			throws ModelException {
		try {
			return getGenericDao().findAll(entityClass.newInstance(), where,
					orderBy);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public <E extends DefaultEntity<?>> List<Object[]> getRevisions(E obj)
			throws ModelException {
		try {
			return getGenericDao().getRevisions(obj);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	/**
	 * Método getter para o campo genericDao
	 * 
	 * @return the genericDao
	 */
	public IGenericDAO getGenericDao() {
		return genericDao;
	}

}
