package com.kong.shop.service.product.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kong.shop.model.product.ProductType;
import com.kong.shop.service.base.impl.DaoSupport;
import com.kong.shop.service.product.IProductTypeService;

@Service("productService")
@Transactional
public class ProductTypeServiceImpl extends DaoSupport<ProductType> implements
		IProductTypeService {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(Class<ProductType> entityClass, Serializable... ids) {
		if (ids == null || ids.length < 0) {
			throw new RuntimeException("Required more than one id to delete");
		}
		// Join all parameters of ids together as format ?2,?3,?4...
		StringBuffer jpql = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			jpql.append("?").append(i + 2).append(",");
		}
		jpql.deleteCharAt(jpql.length() - 1);
		
		Query query = em
				.createQuery("update ProductType o set o.visible=?1 where o.typeid in ("
						+ jpql.toString() + ")");
		query.setParameter(1, false);
		for (int i = 0; i < ids.length; i++) {
			query.setParameter(i + 2, ids[i]);
		}
		query.executeUpdate();
	}

}
