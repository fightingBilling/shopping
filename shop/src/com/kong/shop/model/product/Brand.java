package com.kong.shop.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand extends BaseBean{
	private static final long serialVersionUID = 6417754934884647149L;
	// UUID as code
	private String code;
	// English and Chinese name together, format like nake/ÄÍ¿Ë
	private String name;
	// href of logo path like '/image/brand/2013/05/19/11/sss.gif'
	private String logopath;
	// visible
	private boolean visible = true;
	
	public Brand(String name, String logopath) {
		this.name = name;
		this.logopath = logopath;
	}
	
	public Brand() {
	}
	
	public Brand(String code) {
		this.code = code;
	}
	
	@Id @Column(length = 36, nullable = false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length = 80)
	public String getLogopath() {
		return logopath;
	}
	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "Brand [code=" + code + ", name=" + name + "]";
	}
	
}
