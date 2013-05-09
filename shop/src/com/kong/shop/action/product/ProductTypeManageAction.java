package com.kong.shop.action.product;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.kong.shop.model.product.ProductType;
import com.kong.shop.service.product.IProductTypeService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductTypeManageAction extends ActionSupport {
	private static final long serialVersionUID = -6956692923392743452L;

	private IProductTypeService productTypeService;
	private ProductType type;
	private Integer parentid;

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public IProductTypeService getProductTypeService() {
		return productTypeService;
	}

	@Resource
	public void setProductTypeService(IProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public String addUI() {
		return "addUI";
	}

	public String add() {
		if (type.getName() == null || "".equals(type.getName())) {
			addActionError("Please give one name for product type which you defined.");
		} else {
			if(parentid > 1) {
				ProductType parent = productTypeService.find(ProductType.class, parentid);
				type.setParent(parent);
			}
			productTypeService.save(type);
			addActionMessage("Add Product Type Passed!");
		}
		return "message";
	}
}
