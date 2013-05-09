package com.kong.shop.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.kong.shop.model.QueryResult;

public interface IDAO<T> {
	/**
	 * Save one entity to DB
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * Delete entities by ids for model
	 * 
	 * @param entityClass
	 * @param ids
	 */
	public void delete(Class<T> entityClass, Serializable... ids);

	/**
	 * Update one entity into DB
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * Find or get that entity by id
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T find(Class<T> entityClass, Serializable id);

	/**
	 * Query all results
	 * 
	 * @param entityClass
	 * @return
	 */
	public QueryResult<T> getScrollData(Class<T> entityClass);

	/**
	 * Query scroll data without any condition or order by child statement
	 * 
	 * @param entityClass
	 * @param firstindex
	 * @param maxresult
	 * @return
	 */
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult);

	/**
	 * Query scroll data with where statements but no order by
	 * 
	 * @param entityClass
	 * @param firstindex
	 * @param maxresult
	 * @param wherejpsql
	 * @param queryPositionParams
	 * @return
	 */
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] queryPositionParams);

	/**
	 * Query scroll data with order by child statements but no where statements
	 * @param entityClass
	 * @param firstindex
	 * @param maxresult
	 * @param orderby
	 * @return
	 */
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby);

	/**
	 * Query scroll data with where statements and order by
	 * 
	 * @param entityClass
	 * @param firstindex
	 * @param maxresult
	 * @param wherejpsql
	 * @param queryPositionParams
	 * @param orderby
	 * @return
	 */
	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] queryPositionParams,
			LinkedHashMap<String, String> orderby);
}
