package com.kong.shop.model.product;

public enum Sex {
	NONE{

		@Override
		public String getName() {
			return "��Ů����";
		}},
	MAN {
		@Override
		public String getName() {
			return "������";
		}
	},
	WOMAN {
		@Override
		public String getName() {
			return "��Ů��";
		}
	};
	
	public abstract String getName(); 
}
