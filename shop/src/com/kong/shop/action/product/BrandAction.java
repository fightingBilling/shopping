package com.kong.shop.action.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.kong.shop.model.PageView;
import com.kong.shop.model.QueryResult;
import com.kong.shop.model.product.Brand;
import com.kong.shop.service.product.IBrandService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class BrandAction extends ActionSupport {

	private static final long serialVersionUID = -1267488545089517313L;
	private IBrandService brandService;
	private Brand brand;
	private PageView<Brand> pageView;

	public IBrandService getBrandService() {
		return brandService;
	}

	@Resource
	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	/**
	 * @return the pageView
	 */
	public PageView<Brand> getPageView() {
		return pageView;
	}

	/**
	 * @param pageView
	 *            the pageView to set
	 */
	public void setPageView(PageView<Brand> pageView) {
		this.pageView = pageView;
	}

	@Override
	public String execute() throws Exception {
		if (brand == null) {
			brand = new Brand();
			System.out.println("OK");
		}
		pageView = new PageView<Brand>(brand.getPage(), 12);
		int firstindex = (pageView.getCurrentPage() - 1)
				* pageView.getMaxResult();

		StringBuffer wherejpql = new StringBuffer("o.visible = ?1");
		List<Object> queryPositionParams = new ArrayList<Object>();
		queryPositionParams.add(true);
		if (brand.getName() != null && !"".equals(brand.getName())) {
			wherejpql.append(" and o.name like ?"
					+ (queryPositionParams.size() + 1));
			queryPositionParams.add("%" + brand.getName() + "%");
		}
		QueryResult<Brand> qr = brandService.getScrollData(Brand.class,
				firstindex, pageView.getMaxResult(), wherejpql.toString(),
				queryPositionParams.toArray());
		/*
		 * if(qr.getTotalRecord() < 1) {
		 * addActionError("Did not find your brand: " + brand.getName()); return
		 * "message"; }
		 */
		pageView.setQr(qr);

		return SUCCESS;
	}

}
