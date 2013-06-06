package com.kong.shop.service.product;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kong.shop.model.product.Brand;
import com.kong.shop.model.product.ProductInfo;
import com.kong.shop.model.product.ProductStyle;
import com.kong.shop.model.product.ProductType;
import com.kong.shop.model.product.Sex;

public class ProductServiceTest {
	private static IProductService productService = null;
	private static ApplicationContext ctx;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		productService = (IProductService) ctx.getBean("productServiceImpl");

	}

	@Test
	public void testSave() {
		ProductInfo product = new ProductInfo();
		product.setName("×ãÇòsss");
		product.setBaseprice(100f);
		product.setBrand(new Brand("8bed5854-87c3-478b-9859-5ef75251c543"));
		product.setCode("UI002");
		product.setDescription("ºÃ²úÆ·");
		product.setMarketprice(600f);
		product.setModel("T60");
		product.setSalesprice(300f);
		product.setSexrequest(Sex.NONE);
		product.setProducttype(new ProductType(3));
		product.addProductStyle(new ProductStyle("red", "xxx.gif"));
		product.setWeight(50);
		productService.save(product);
		System.out.println(product.getId());
	}
}
