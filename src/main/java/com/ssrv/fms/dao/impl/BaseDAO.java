package com.ssrv.fms.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.ssrv.fms.dao.IBaseDAO;
import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

@Component(value = "baseDao")
public class BaseDAO implements IBaseDAO {
	@PersistenceUnit
	EntityManagerFactory factory;

	@PersistenceContext
	private EntityManager entityManager;

	protected final <T> EasyCriteria<T> createCriteria(Class<T> resultClass) {
		return EasyCriteriaFactory.createQueryCriteria(getEntityManager(),
				resultClass);
	}

	// {
	// CriteriaQuery<T> q = getCriteriaBuilder().createQuery(resultClass);
	// Root<T> c = q.from(resultClass);
	// return q.select(c);
	// }

	protected final EntityManager getEntityManager() {
		return entityManager;
	}

	protected final CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	@Override
	public final <T> T findById(Class<T> resultClass, Serializable primaryKey) {
		return getEntityManager().find(resultClass, primaryKey);
	}

	@Override
	public final <T> List<T> getAll(Class<T> resultClass) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(resultClass);
		Root<T> variableRoot = query.from(resultClass);
		query.select(variableRoot);
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public <T> T save(T t) {
		getEntityManager().persist(t);
		return t;
	}

	@Override
	public <T> void delete(T t) {
		getEntityManager().remove(t);
	}
}
