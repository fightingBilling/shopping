package com.kong.shop.action.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.kong.shop.model.PageView;
import com.kong.shop.model.QueryResult;
import com.kong.shop.model.product.Brand;
import com.kong.shop.model.product.ProductInfo;
import com.kong.shop.service.product.IBrandService;
import com.kong.shop.service.product.IProductService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductInfoAction extends ActionSupport {
	private static final long serialVersionUID = 8658503817058185616L;
	private IProductService productService;
	private ProductInfo product;
	private PageView<ProductInfo> pageView;
	private Integer page = 1;

	public IProductService getProductService() {
		return productService;
	}

	@Resource(name = "productService")
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}

	/**
	 * @return the pageView
	 */
	public PageView<ProductInfo> getPageView() {
		return pageView;
	}

	/**
	 * @param pageView
	 *            the pageView to set
	 */
	public void setPageView(PageView<ProductInfo> pageView) {
		this.pageView = pageView;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String execute() throws Exception {
		if (product == null) {
			product = new ProductInfo();
		}
		product.setPage(page > 0 ? page : 1);
		pageView = new PageView<ProductInfo>(product.getPage(), 12);
		int firstindex = (pageView.getCurrentPage() - 1)
				* pageView.getMaxResult();

		StringBuffer wherejpql = new StringBuffer("o.visible = ?1");
		List<Object> queryPositionParams = new ArrayList<Object>();
		queryPositionParams.add(true);
		if (product.getName() != null && !"".equals(product.getName())) {
			wherejpql.append(" and o.name like ?"
					+ (queryPositionParams.size() + 1));
			queryPositionParams.add("%" + product.getName() + "%");
		}
		QueryResult<ProductInfo> qr = productService.getScrollData(
				ProductInfo.class, firstindex, pageView.getMaxResult(),
				wherejpql.toString(), queryPositionParams.toArray());
		/*
		 * if(qr.getTotalRecord() < 1) {
		 * addActionError("Did not find your brand: " + brand.getName()); return
		 * "message"; }
		 */
		pageView.setQr(qr);

		return SUCCESS;
	}

}
