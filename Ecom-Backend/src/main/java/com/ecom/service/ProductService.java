package com.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.model.Categories;
import com.ecom.model.Product;
import com.ecom.payload.CategoryDao;
import com.ecom.payload.ProductDao;
import com.ecom.payload.ProductResponse;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CategoryRepository catRepo;
	//create product
	public ProductDao createProduct(ProductDao productDao, int catId) {
		//fetch categories available or not
		Categories cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("this category id not found"));
		//productDao to product
		Product product=toEntity(productDao);
		product.setCategories(cat);
		Product save=this.productRepo.save(product);
//		Product save=productRepo.save(entity);
		
		//product to productDao
		ProductDao dao= toDao(save);
		return dao;
	}
	//view product
	public ProductResponse viewAllProduct(int pageNo, int pageSize, String sortBy, String sortDir){
		Sort sort=null;
		if(sortDir.trim().toLowerCase().equals("asc")) {
			sort=Sort.by(sortBy).ascending();
			System.out.println(sort);
		}else {
			sort=Sort.by(sortBy).descending();
			System.out.println(sort);
		}
		
		Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
		Page<Product>page=this.productRepo.findAll(pageable);
		List<Product>pageProduct=page.getContent();
//		List<Product>product=pageProduct.stream().filter(p ->p.isLive()).collect(Collectors.toList());
		List<ProductDao>productDao=pageProduct.stream().map(p ->this.toDao(p)).collect(Collectors.toList());
		ProductResponse productResponse= new ProductResponse();
		productResponse.setContent(productDao);
		productResponse.setPageNo(page.getNumber());
		productResponse.setPageSize(page.getSize());
		productResponse.setTotalPages(page.getTotalPages());
		productResponse.setLastPage(page.isLast());
		//productDao to product
//		List<Product>findAll=productRepo.findAll();
		//product to productDao
//		List<ProductDao>findAllDao=findAll.stream().map(product ->this.toDao(product)).collect(Collectors.toList());
		return productResponse;
	}
	//view product by id
	public ProductDao viewProductById(int pid) {
		Product viewProduct= productRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException(+pid+" from this id product not found"));
		ProductDao dao= this.toDao(viewProduct);
		return dao;
	}
	//delete product
	public void deleteProductById(int pid) {
		Product byId= productRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException(+pid+" this product is not exist"));
		productRepo.delete(byId);
		
	}
	//update product
	public ProductDao updateProductById(int pid, ProductDao newP) {
		
		Product oldP= productRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException(+pid+" product not found "));
		
		oldP.setProduct_imageName(newP.getProduct_imageName());
		oldP.setProduct_name(newP.getProduct_name());
		oldP.setLive(newP.isLive());
		oldP.setStock(newP.isStock());
		oldP.setProduct_quantity(newP.getProduct_quantity());
		oldP.setProduct_prize(newP.getProduct_prize());
		oldP.setProduct_desc(newP.getProduct_desc());
		
		Product save=productRepo.save(oldP);
		ProductDao dao= toDao(save);
		return dao;
	}
	//find product by category
	public List<ProductDao>findProductByCategory(int catId){
		Categories cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("this category not exist"));
		List<Product>findByCategory=this.productRepo.findByCategories(cat);
		List<ProductDao>collect=findByCategory.stream().map(p->toDao(p)).collect(Collectors.toList());
		return collect;
		
	}
	//productDao to product
	public Product toEntity(ProductDao pDao) {
		Product p= new Product();
		p.setProductId(pDao.getProductId());
		p.setProduct_name(pDao.getProduct_name());
		p.setLive(pDao.isLive());
		p.setStock(pDao.isStock());
		p.setProduct_desc(pDao.getProduct_desc());
		p.setProduct_imageName(pDao.getProduct_imageName());
		p.setProduct_prize(pDao.getProduct_prize());
		p.setProduct_quantity(pDao.getProduct_quantity());
		return p;
	}
	public ProductDao toDao(Product product) {
		ProductDao pDao= new ProductDao();
		pDao.setProductId(product.getProductId());
		pDao.setProduct_name(product.getProduct_name());
		pDao.setLive(product.isLive());
		pDao.setStock(product.isStock());
		pDao.setProduct_desc(product.getProduct_desc());
		pDao.setProduct_imageName(product.getProduct_imageName());
		pDao.setProduct_prize(product.getProduct_prize());
		pDao.setProduct_quantity(product.getProduct_quantity());
		
		//change category to categoryDao
		CategoryDao catDao= new CategoryDao();
		catDao.setCategory_id(product.getCategories().getCategory_id());
		catDao.setTitle(product.getCategories().getTitle());
		pDao.setCategories(catDao);
		return pDao;
	}
}
