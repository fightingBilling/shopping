package com.kong.shop.service.product.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kong.shop.model.product.ProductInfo;
import com.kong.shop.service.base.impl.DaoSupport;
import com.kong.shop.service.product.IProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl extends DaoSupport<ProductInfo> implements IProductService {

}
