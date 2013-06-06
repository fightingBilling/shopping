package com.kong.shop.model.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductType extends BaseBean {
	private static final long serialVersionUID = -2754227587882954443L;
	/* type id */
	private Integer typeid;
	/* type name */
	private String name;
	/* note for google description or search engine */
	private String note;
	/* visible in page, default to see in page */
	private Boolean visible = true;
	/* children types belong to this type */
	private Set<ProductType> childtypes = new HashSet<ProductType>();
	/* parent type, it can be only one or nothing */
	private ProductType parent;
	
	private Set<ProductInfo> products = new HashSet<ProductInfo>();

	public ProductType(Integer typeid) {
		this.typeid = typeid;
	}

	public ProductType() {
	}

	public ProductType(String name, String note) {
		this.name = name;
		this.note = note;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	@Column(length = 36, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 200)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(nullable = false)
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parent")
	public Set<ProductType> getChildtypes() {
		return childtypes;
	}

	public void setChildtypes(Set<ProductType> childtypes) {
		this.childtypes = childtypes;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "parentid")
	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
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
		ProductType other = (ProductType) obj;
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (parent != null) {
			return "ProductType [typeid=" + typeid + ", name=" + name
					+ ", visible=" + visible + ", parentid="
					+ parent.getTypeid() + "]";
		} else {
			return "ProductType [typeid=" + typeid + ", name=" + name
					+ ", visible=" + visible + ", parent is null" + "]";
		}
	}

	/**
	 * @return the products
	 */
	@OneToMany(mappedBy="producttype", cascade=CascadeType.REMOVE)
	public Set<ProductInfo> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Set<ProductInfo> products) {
		this.products = products;
	}

}
