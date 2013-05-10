package com.kong.shop.service.base.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kong.shop.model.QueryResult;
import com.kong.shop.service.base.IDAO;

@Service
@Transactional
public abstract class DaoSupport<T> implements IDAO<T> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Override
	public void delete(Class<T> entityClass, Serializable... ids) {
		for (Object id : ids) {
			em.remove(em.getReference(entityClass, id));
		}
	}

	@Override
	public void update(T entity) {
		em.merge(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	/* No need transaction */
	public T find(Class<T> entityClass, Serializable id) {
		return em.find(entityClass, id);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public QueryResult<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass, -1, -1);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] queryPositionParams) {
		return getScrollData(entityClass, firstindex, maxresult, wherejpql, queryPositionParams, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, orderby);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	/* No need transaction
	 * WHy LinkedHashMap? has order to list all order by condition.
	 *  */
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] queryPositionParams,
			LinkedHashMap<String, String> orderby) {
		String entityName = getEntityName(entityClass);
		QueryResult<T> qr = new QueryResult<T>();
		Query query = em.createQuery("select o from " + entityName + " o "
				+ (wherejpql == null ? "" : "where " + wherejpql)
				+ buildOrderby(orderby));
		setWhereParams(query, queryPositionParams);
		
		/*If firstindex and maxresult = -1, then it will not trigger scroll data in DB. No limit for sql*/
		if(firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		
		qr.setResultList(query.getResultList());
		query = em.createQuery("select count(o) from " + entityName + " o "
				+ (wherejpql == null ? "" : "where " + wherejpql));
		setWhereParams(query, queryPositionParams);
		qr.setTotalRecord((Long) query.getSingleResult());
		return qr;
	}
	
	/*Set parameter like this: query.setParameter(1, true)
	 * It refers to wherejpql: where o.visible = ?1
	 * */
	protected void setWhereParams(Query query, Object[] queryPositionParams) {
		if (queryPositionParams == null || queryPositionParams.length < 1) {
			return;
		}

		for (int i = 0; i < queryPositionParams.length; i++) {
			query.setParameter(i + 1, queryPositionParams[i]);
		}
	}
	
	/*Build order by statement like this: order by typeid desc, name asc*/
	protected String buildOrderby(LinkedHashMap<String, String> orderby) {
		if (orderby == null || orderby.size() < 1) {
			return "";
		}

		StringBuffer orderbyBuffer = new StringBuffer();
		orderbyBuffer.append(" order by");
		for (Entry<String, String> item : orderby.entrySet()) {
			orderbyBuffer.append(" ").append(item.getKey()).append(" ")
					.append(item.getValue()).append(",");
		}
		return orderbyBuffer.deleteCharAt(orderbyBuffer.length() - 1).toString();
	}

	protected String getEntityName(Class<T> entityClass) {
		String entityName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name())) {
			entityName = entity.name();
		}
		return entityName;
	}

}
