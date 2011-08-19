package br.com.diegosilva.infraseam.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Transactional;

import br.com.diegosilva.infraseam.dao.iface.IAbstractSeamDAO;
import br.com.diegosilva.infraseam.entity.DefaultEntity;

abstract class AbstractSeamDAO implements IAbstractSeamDAO {

	/**
	 * variavel serialVersionUID do tipo long.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variavel entityManager do tipo EntityManager.
	 */
	@In(value = "entityManager", create = true)
	private EntityManager entityManager;

	/**
	 * Metodo getter para a variavel entityManager.
	 * 
	 * @return the entityManager - entityManager.
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

	@Transactional
	public <E extends DefaultEntity<?>> void insert(E obj) {
		try {
			getEntityManager().persist(obj);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

	}

	public <E extends DefaultEntity<?>> E save(E obj) {
		try {
			if (obj.getId() == null) {
				getEntityManager().persist(obj);
			} else {
				getEntityManager().merge(obj);
			}
			return obj;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public <E extends DefaultEntity<?>> E update(E obj) {
		try {
			return getEntityManager().merge(obj);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public <E extends DefaultEntity<?>> void delete(E obj) {
		try {
			getEntityManager().remove(obj);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <E extends DefaultEntity<?>> E findByPk(E obj) {
		try {
			return (E) getEntityManager().find(obj.getClass(), obj.getId());
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <E extends DefaultEntity<?>> Collection<E> findAll(E type) {
		try {
			return (Collection<E>) getEntityManager().createQuery(
					"from " + type.getClass().getCanonicalName(),
					type.getClass()).getResultList();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <E extends DefaultEntity<?>> Collection<E> findAll(E type,
			String where, String orderBy) {
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
			throw new PersistenceException(e);
		}
	}

	public <E extends DefaultEntity<?>> Collection<E> findByExample(E type) {
		Collection<E> retorno = null;
		Criteria criteria = getSession().createCriteria(type.getClass());
		if (criteria != null) {
			criteria.add(Example.create(type));
			retorno = criteria.list();
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public <E extends DefaultEntity<?>> Collection<E> findByExampleWithParams(
			E type, boolean enableLike, boolean excludeNone, boolean ignoreCase) {
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
	@Override
	public <E extends DefaultEntity<?>> List<Object[]> getRevisions(E obj)
			throws PersistenceException {
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
			throw new PersistenceException(e);
		}
	}

	/**
	 * @return
	 */
	private AuditReader getAuditReader() {
		return AuditReaderFactory.get(getEntityManager());
	}

}
