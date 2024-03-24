package com.ecom.payload;

public class ProductDao {
	private int productId;
	private String product_name;
	private double product_prize; 
	private boolean stock=true;
	private int product_quantity;
	private boolean live;
	private String product_imageName;
	private String product_desc;
	private CategoryDao categories;
	public ProductDao() {
		super();
	}
	public ProductDao(int productId, String product_name, double product_prize, boolean stock, int product_quantity,
			boolean live, String product_imageName, String product_desc) {
		super();
		this.productId = productId;
		this.product_name = product_name;
		this.product_prize = product_prize;
		this.stock = stock;
		this.product_quantity = product_quantity;
		this.live = live;
		this.product_imageName = product_imageName;
		this.product_desc = product_desc;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getProduct_prize() {
		return product_prize;
	}
	public void setProduct_prize(double product_prize) {
		this.product_prize = product_prize;
	}
	public boolean isStock() {
		return stock;
	}
	public void setStock(boolean stock) {
		this.stock = stock;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public String getProduct_imageName() {
		return product_imageName;
	}
	public void setProduct_imageName(String product_imageName) {
		this.product_imageName = product_imageName;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public CategoryDao getCategories() {
		return categories;
	}
	public void setCategories(CategoryDao catDao) {
		this.categories = catDao;
	}
	
	
}
