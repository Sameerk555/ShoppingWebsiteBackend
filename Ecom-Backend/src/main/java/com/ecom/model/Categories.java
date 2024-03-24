package com.ecom.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int category_id;
	private String title;
	@OneToMany(mappedBy="categories", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Product> product = new HashSet<>() ;
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categories(int category_id, String title) {
		super();
		this.category_id = category_id;
		this.title = title;
		  
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
	
	
}
