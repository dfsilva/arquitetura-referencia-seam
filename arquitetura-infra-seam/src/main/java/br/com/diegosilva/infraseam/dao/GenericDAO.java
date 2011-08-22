package br.com.diegosilva.infraseam.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.ejb.EntityManagerImpl;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.diegosilva.infraseam.dao.exception.DaoException;
import br.com.diegosilva.infraseam.dao.iface.IGenericDAO;
import br.com.diegosilva.infraseam.entity.iface.IDefaultEntity;

@Name(value = "genericDao")
@AutoCreate
public class GenericDAO implements IGenericDAO {

	@In(value = "entityManager", create = true)
	private EntityManager entityManager;

	/**
	 * Retorna uma instância do entityManager do hibernate.
	 * 
	 * @return
	 */
	private EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = (EntityManager) Component.getInstance(
					"entityManager", true);
		}
		return entityManager;
	}

	/**
	 * Retorna uma sessao a partir de um EntityManager.
	 * 
	 * @param em
	 *            EntityManager
	 * @return Session
	 */
	protected Session getSession() {
		Session retorno;
		if (getEntityManager().getDelegate() instanceof EntityManagerImpl) {
			EntityManagerImpl entityManagerImpl = (EntityManagerImpl) getEntityManager()
					.getDelegate();
			retorno = entityManagerImpl.getSession();
		} else {
			retorno = (Session) getEntityManager().getDelegate();
		}

		return retorno;
	}

	public <E extends IDefaultEntity<?>> E save(E obj) throws DaoException {
		try {
			if (obj.getId() == null) {
				getEntityManager().persist(obj);
			} else {
				getEntityManager().merge(obj);
			}
			return obj;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public <E extends IDefaultEntity<?>> void delete(E obj) throws DaoException {
		try {
			getEntityManager().remove(obj);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <E extends IDefaultEntity<?>> E findByPk(E obj) throws DaoException {
		try {
			return (E) getEntityManager().find(obj.getClass(), obj.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <E extends IDefaultEntity<?>> Collection<E> findAll(E type)
			throws DaoException {
		try {
			return (Collection<E>) getEntityManager().createQuery(
					"from " + type.getClass().getCanonicalName(),
					type.getClass()).getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <E extends IDefaultEntity<?>> Collection<E> findAll(E type,
			String where, String orderBy) throws DaoException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("from " + type.getClass().getCanonicalName());
			if (!StringUtils.isBlank(where)) {
				sql.append(" where " + where);
			}
			if (!StringUtils.isBlank(orderBy)) {
				sql.append(" order by " + orderBy);
			}

			return (Collection<E>) getEntityManager().createQuery(
					sql.toString(), type.getClass()).getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <E extends IDefaultEntity<?>> Collection<E> findByExample(E type)
			throws DaoException {
		Collection<E> retorno = null;
		Criteria criteria = getSession().createCriteria(type.getClass());
		if (criteria != null) {
			criteria.add(Example.create(type));
			retorno = criteria.list();
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public <E extends IDefaultEntity<?>> Collection<E> findByExampleWithParams(
			E type, boolean enableLike, boolean excludeNone, boolean ignoreCase)
			throws DaoException {
		Collection<E> retorno = null;
		Criteria criteria = getSession().createCriteria(type.getClass());
		if (criteria != null) {
			Example ex = Example.create(type);
			if (enableLike) {
				ex.enableLike(MatchMode.ANYWHERE);
			}
			if (excludeNone) {
				ex.excludeNone();
			}
			if (ignoreCase) {
				ex.ignoreCase();
			}
			criteria.add(ex);
			retorno = criteria.list();
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public <E extends IDefaultEntity<?>> List<Object[]> getRevisions(E obj)
			throws DaoException {
		try {
			List<Object[]> revisoes = getAuditReader()
					.createQuery()
					.forRevisionsOfEntity(obj.getClass(), false, true)
					.add(AuditEntity.id().eq(obj.getId()))
					.addOrder(
							AuditEntity.revisionProperty("dhAtualizacao")
									.desc()).getResultList();
			return revisoes;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * @return
	 */
	private AuditReader getAuditReader() {
		return AuditReaderFactory.get(getEntityManager());
	}
}
