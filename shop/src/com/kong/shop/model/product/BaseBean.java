package com.kong.shop.model.product;

import java.io.Serializable;

/**
 * Used for extends for children beans as they all need paging.
 * @author v0cn385
 *
 */
public class BaseBean implements Serializable{
	private static final long serialVersionUID = 5785731755903321871L;
	private int page = 1;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page < 1 ? 1 : page;
	}
}
