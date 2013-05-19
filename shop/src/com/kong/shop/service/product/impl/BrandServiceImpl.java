package com.kong.shop.service.product.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kong.shop.model.product.Brand;
import com.kong.shop.service.base.impl.DaoSupport;
import com.kong.shop.service.product.IBrandService;

@Service
public class BrandServiceImpl extends DaoSupport<Brand> implements IBrandService {

	@Override
	public void save(Brand entity) {
		entity.setCode(UUID.randomUUID().toString());
		super.save(entity);
	}
}
