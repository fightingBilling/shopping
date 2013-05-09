package com.kong.shop.action.product;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.kong.shop.model.PageIndex;
import com.kong.shop.model.PageView;
import com.kong.shop.model.QueryResult;
import com.kong.shop.model.product.ProductType;
import com.kong.shop.service.product.IProductTypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductTypeAction extends ActionSupport {

	private static final long serialVersionUID = 2288996909474769773L;

	private IProductTypeService productTypeService;
	private ProductType type;
	private PageView<ProductType> pageView;
	private int page;
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageView<ProductType> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<ProductType> pageView) {
		this.pageView = pageView;
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

	@Resource(name = "productService")
	public void setProductTypeService(IProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	@Override
	public String execute() throws Exception {
		// TODO accessing this action, page will not initialize. default value is 0. Even change value in set method. 
		// If directly accessing this action, it will set page as 0, so
		// exception appears as firstindex will be -12
		page = page < 1 ? 1 : page;
		pageView = new PageView<ProductType>(page);
		int firstindex = (pageView.getCurrentPage() - 1) * pageView.getMaxResult();
		String wherejpql = "o.visible = ?1";
		Object[] queryPositionParams = new Object[] { true };
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("typeid", "desc");
		QueryResult<ProductType> qr = productTypeService.getScrollData(ProductType.class, firstindex,
				pageView.getMaxResult(), wherejpql, queryPositionParams, orderby);
		pageView.setQr(qr);
		return SUCCESS;
	}
}
