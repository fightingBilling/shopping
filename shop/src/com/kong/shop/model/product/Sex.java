package com.kong.shop.model.product;

public enum Sex {
	NONE{

		@Override
		public String getName() {
			return "男女不限";
		}},
	MAN {
		@Override
		public String getName() {
			return "限男生";
		}
	},
	WOMAN {
		@Override
		public String getName() {
			return "限女生";
		}
	};
	
	public abstract String getName(); 
}
