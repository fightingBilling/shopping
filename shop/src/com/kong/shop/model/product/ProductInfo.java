package com.kong.shop.model.product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity
public class ProductInfo extends BaseBean{

	private static final long serialVersionUID = -2788589112443056306L;
	private Integer id;
	/** 货号 **/
	private String code;
	/** 产品名称 **/
	private String name;
	/** 品牌 **/
	private Brand brand;
	/** 型号 **/
	private String model;
	/** 底价 **/
	private Float baseprice;
	/** 市场价 **/
	private Float marketprice;
	/** 销售价 **/
	private Float salesprice;
	/** 重量 **/
	private Float weight;
	/** 产品简介 **/
	private String description;
	/** 购买说明 **/
	private String buyexplain;
	/** 是否可见 **/
	private Boolean visible = true;
	/** 产品类型 **/
	private ProductType producttype;
	/** 上架日期 **/
	private Date createdate = new Date();
	/** 人气指数 **/
	private Long clickcount = 1L;
	/** 销售量 **/
	private Long salescount = 0L;
	/** 是否推荐 **/
	private boolean commend = false;
	/** 性别要求 **/
	private Sex sexrequest = Sex.NONE;
	
	
	private Set<ProductStyle> productstyles = new HashSet<ProductStyle>();
	
	@Column(length=30)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="brandId")
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	@Column(length=30)
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(nullable=false)
	public float getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	@Column(nullable=false)
	public float getMarketprice() {
		return marketprice;
	}
	public void setMarketprice(float marketprice) {
		this.marketprice = marketprice;
	}
	public float getSalesprice() {
		return salesprice;
	}
	public void setSalesprice(float salesprice) {
		this.salesprice = salesprice;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
//	@Lob
	@Column(nullable=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(length=30)
	public String getBuyexplain() {
		return buyexplain;
	}
	public void setBuyexplain(String buyexplain) {
		this.buyexplain = buyexplain;
	}
	
	@Column(nullable=false)
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="productTypeId")
	public ProductType getProducttype() {
		return producttype;
	}
	public void setProducttype(ProductType producttype) {
		this.producttype = producttype;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	@Column(nullable=false)
	public long getClickcount() {
		return clickcount;
	}
	public void setClickcount(long clickcount) {
		this.clickcount = clickcount;
	}
	
	@Column(nullable=false)
	public long getSalescount() {
		return salescount;
	}
	public void setSalescount(long salescount) {
		this.salescount = salescount;
	}
	
	@Column(nullable=false)
	public boolean isCommend() {
		return commend;
	}
	public void setCommend(boolean commend) {
		this.commend = commend;
	}
	
	@Enumerated(EnumType.STRING)
	public Sex getSexrequest() {
		return sexrequest;
	}
	public void setSexrequest(Sex sexrequest) {
		this.sexrequest = sexrequest;
	}
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
		ProductInfo other = (ProductInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * @return the productstyles
	 */
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.PERSIST},mappedBy="product")
	public Set<ProductStyle> getProductstyles() {
		return productstyles;
	}
	/**
	 * @param productstyles the productstyles to set
	 */
	public void setProductstyles(Set<ProductStyle> productstyles) {
		this.productstyles = productstyles;
	}
	
	public void addProductStyle(ProductStyle style) {
		if(!this.productstyles.contains(style)) {
			this.productstyles.add(style);
			style.setProduct(this);
		}
	}
	
	public void removeProductStyle(ProductStyle style) {
		if(this.productstyles.contains(style)) {
			this.productstyles.remove(style);
			style.setProduct(null);
		}
	}
}
