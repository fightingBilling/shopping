package com.kong.shop.action.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.kong.shop.model.product.Brand;
import com.kong.shop.model.product.ProductInfo;
import com.kong.shop.model.product.ProductType;
import com.kong.shop.model.product.Sex;
import com.kong.shop.service.product.IBrandService;
import com.kong.shop.service.product.IProductService;
import com.kong.shop.service.product.IProductTypeService;
import com.kong.shop.utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductInfoManageAction extends ActionSupport {
	private static final long serialVersionUID = -6956692923392743452L;
	private IProductService productInfoService;
	private ProductInfo productInfo;
	private String productTypeId;
	private String urladdress = SiteUrl.read("control.product.list");
	private List<ProductInfo> products = new ArrayList<ProductInfo>();
	private Sex[] sex = Sex.values();
	private List<Brand> brands = new ArrayList<Brand>();
	private IBrandService brandService;
	private String selectedBrandId;

	private Integer parentTypeId;
	private IProductTypeService productTypeService;
	private List<ProductType> types = new ArrayList<ProductType>();

	List<ProductType> menutypes = new ArrayList<ProductType>();

	public List<ProductType> getMenutypes() {
		return menutypes;
	}

	public void setMenutypes(List<ProductType> menutypes) {
		this.menutypes = menutypes;
	}

	public IProductTypeService getProductTypeService() {
		return productTypeService;
	}

	@Resource
	public void setProductTypeService(IProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public List<ProductType> getTypes() {
		return types;
	}

	public void setTypes(List<ProductType> types) {
		this.types = types;
	}

	public Integer getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(Integer parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	public Sex[] getSex() {
		return sex;
	}

	public void setSex(Sex[] sex) {
		this.sex = sex;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public List<ProductInfo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductInfo> products) {
		this.products = products;
	}

	public String getUrladdress() {
		return urladdress;
	}

	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}
	
	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public IBrandService getBrandService() {
		return brandService;
	}

	@Resource
	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}

	public IProductService getProductInfoService() {
		return productInfoService;
	}

	@Resource
	public void setProductInfoService(IProductService productInfoService) {
		this.productInfoService = productInfoService;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	/**
	 * @return the selectedBrandId
	 */
	public String getSelectedBrandId() {
		return selectedBrandId;
	}

	/**
	 * @param selectedBrandId the selectedBrandId to set
	 */
	public void setSelectedBrandId(String selectedBrandId) {
		this.selectedBrandId = selectedBrandId;
	}

	public String addUI() {
		brands = brandService.getScrollData(Brand.class)
				.getResultList();
		return "addUI";
	}

	public String selectUI() {
		String jpql = " o.parent.typeid is null";
		Object[] params = new Object[0];
		if (parentTypeId != null && parentTypeId > 0) {
			jpql = " o.parent.typeid = ?1";
			params = new Object[] { parentTypeId };

			// For navigator bar in productTypeSelect.jsp
			ProductType type = productTypeService.find(ProductType.class,
					parentTypeId);
			menutypes.add(type);
			while (type.getParent() != null) {
				menutypes.add(type.getParent());
				type = type.getParent();
			}
		}
		types = productTypeService.getScrollData(ProductType.class, -1, -1,
				jpql, params).getResultList();
		return "selectUI";
	}

	public String add() throws IOException {
		ProductType type = null;
		if (productInfo == null) {
			productInfo = new ProductInfo();
		}
		System.out.println("type id " + productTypeId);
		if (productTypeId != null && Integer.parseInt(productTypeId) > 0) {
			type = productTypeService.find(ProductType.class,
					Integer.parseInt(productTypeId));
			productInfo.setProducttype(type);
		} 
		productInfo.setBrand(brandService.find(Brand.class, selectedBrandId));
		productInfoService.save(productInfo);
		addActionMessage("Add product successfully.");
		return "message";
	}

	public String editUI() {
		Integer id = productInfo.getId();
		productInfo = productInfoService.find(ProductInfo.class, id);
		return "editUI";
	}

	public String edit() {
		if (productInfo == null) {
			addActionError("No product object found");
			return "message";
		}
		// If name not define, go to global message page
		if (productInfo.getName() == null || "".equals(productInfo.getName())) {
			addActionError("Please give one name for product which you defined.");
			return "message";
		}

		productInfoService.update(productInfo);
		addActionMessage("Update product Passed!");
		return "message";
	}

	public String queryUI() {
		return "queryUI";
	}
}
