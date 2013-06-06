package com.kong.shop.model.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class ProductStyle implements Serializable {
	private static final long serialVersionUID = -1890185880268028495L;
	private Integer id;
	private String name;
	private String imagename;
	private Boolean visible = true;

	private ProductInfo product;

	public ProductStyle() {
		super();
	}

	public ProductStyle(String name, String imagename) {
		this.name = name;
		this.imagename = imagename;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 30, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 40)
	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	@Column(nullable = false)
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ProductStyle other = (ProductStyle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return the product
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "productId")
	public ProductInfo getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(ProductInfo product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductStyle [id=" + id + ", name=" + name + ", imagename="
				+ imagename + ", product id=" + product.getId() + "]";
	}

}
