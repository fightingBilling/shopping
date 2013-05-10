package com.kong.shop.model.product;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kong.shop.model.QueryResult;
import com.kong.shop.service.product.IProductTypeService;

public class testProductTypeService {
	private static IProductTypeService productService;
	private static ApplicationContext ctx;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		productService = (IProductTypeService) ctx.getBean("productService");

	}

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		productService = (IProductTypeService) ctx.getBean("productService");
		productService.save(new ProductType());
	}

	@Test
	public void testProductType() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		productService = (IProductTypeService) ctx.getBean("productService");
		ProductType type = new ProductType();
		type.setName("Youga");
		type.setNote("YY");
		productService.save(type);
	}

	@Test
	public void testSave() {
		ProductType type = new ProductType();
		type.setName("testSave");
		type.setNote("save");
		productService.save(type);
	}

	@Test
	public void testFind() {
		ProductType type = productService.find(ProductType.class, 5);
		Assert.assertNotNull("product not null searching by id", type);
	}

	@Test
	public void testUpdate() {
		ProductType type = productService.find(ProductType.class, 5);
		type.setName("testUpdate");
		type.setNote("update");
		productService.update(type);
	}

	@Test
	public void testDelete() {
		productService.delete(ProductType.class, 5);
	}

	@Test
	public void testSaveMoreProductTypes() {
		for (int i = 0; i < 20; i++) {
			ProductType type = new ProductType();
			type.setName(i + 1 + "football");
			type.setNote(i + 1 + "save");
			productService.save(type);
		}
	}

	@Test
	public void testScollDataOnlyLimit() {
		QueryResult<ProductType> qr = productService.getScrollData(ProductType.class, 5, 5);
		for(ProductType item : qr.getResultList()) {
			System.out.println(item.getName());
		}
		System.out.println(qr.getTotalRecord());
	}
	
	@Test
	public void testDeleteType23() {
		productService.delete(ProductType.class, 23);
	}
	
	@Test
	public void testScollDataWithAllCondition() {
		String wherejpql = "o.visible = ?1";
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("typeid", "desc");
		QueryResult<ProductType> qr = productService.getScrollData(ProductType.class, 0, 5, wherejpql, new Object[]{true}, orderby);
		for(ProductType item : qr.getResultList()) {
			System.out.println(item.getName());
		}
		System.out.println(qr.getTotalRecord());
	}
	
	@Test
	public void testScollDataWithOrderBy() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("typeid", "desc");
		QueryResult<ProductType> qr = productService.getScrollData(ProductType.class, 0, 5, orderby);
		for(ProductType item : qr.getResultList()) {
			System.out.println(item.getName());
		}
		System.out.println(qr.getTotalRecord());
	}
	
	@Test
	public void testScollDataWithWhere() {
		String wherejpql = "o.visible = ?1";
		QueryResult<ProductType> qr = productService.getScrollData(ProductType.class, 0, 5, wherejpql, new Object[]{true});
		for(ProductType item : qr.getResultList()) {
			System.out.println(item.getName());
		}
		System.out.println(qr.getTotalRecord());
	}
	
	@Test
	public void testScollDataAllData() {
		QueryResult<ProductType> qr = productService.getScrollData(ProductType.class);
		for(ProductType item : qr.getResultList()) {
			System.out.println(item.getName());
		}
		System.out.println(qr.getTotalRecord());
	}
	
	@Test
	public void testCheckNameExist() {
		Assert.assertTrue(productService.checkNameExist("28test"));
	}
}
