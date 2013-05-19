package com.kong.shop.service.product;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kong.shop.model.product.Brand;

public class BrandServiceTest {
	private static IBrandService brandService;
	private static ApplicationContext ctx;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		brandService = (IBrandService) ctx.getBean("brandServiceImpl");

	}

	@Test
	public void testSave() {
		brandService.save(new Brand("nake/ÄÍ¿Ë", "/image/brand/2013/5/16/10/asdsfsdf.gif"));
	}

}
