package com.kong.shop.action.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.kong.shop.model.PageView;
import com.kong.shop.model.QueryResult;
import com.kong.shop.model.product.ProductType;
import com.kong.shop.service.product.IProductTypeService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductTypeAction extends ActionSupport {

	private static final long serialVersionUID = 2288996909474769773L;

	private IProductTypeService productTypeService;
	private ProductType type;
	private PageView<ProductType> pageView;
	// For query page
	private boolean query;

	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
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
		// TODO accessing this action, page will not initialize. default value
		// is 0. Even change value in set method.
		// If directly accessing this action, it will set page as 0, so
		// exception appears as firstindex will be -12
		if (type == null) {
			type = new ProductType();
		}
		pageView = new PageView<ProductType>(type.getPage(), 12);
		int firstindex = (pageView.getCurrentPage() - 1)
				* pageView.getMaxResult();

		StringBuffer wherejpql = new StringBuffer("o.visible = ?1");
		List<Object> queryPositionParams = new ArrayList<Object>();
		queryPositionParams.add(true);

		// First time to access list page, type is null object. It will throw
		// nullpointexception
		

		if (query) {
			wherejpql.append(" and o.name like ?"
					+ (queryPositionParams.size() + 1));
			queryPositionParams.add("%" + type.getName() + "%");
		} else {
			// Judge parentid has value or not
			if (type.getTypeid() != null && type.getTypeid() > 0) {
				wherejpql.append(" and o.parent = ?"
						+ (queryPositionParams.size() + 1));
				queryPositionParams.add(type);
			} else {
				// If no parentid then display root type names
				wherejpql.append(" and o.parent is null");
			}
		}

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("typeid", "desc");
		QueryResult<ProductType> qr = productTypeService.getScrollData(
				ProductType.class, firstindex, pageView.getMaxResult(),
				wherejpql.toString(), queryPositionParams.toArray(), orderby);
		pageView.setQr(qr);
		System.out.println("total result" + pageView.getQr().getTotalRecord());
		return SUCCESS;
	}
}
