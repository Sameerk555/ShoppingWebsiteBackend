package com.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.model.Categories;
import com.ecom.payload.CategoryDao;
import com.ecom.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository catRepo;
	@Autowired
	private ModelMapper mapper;
	public CategoryDao create(CategoryDao dao) {
		//CategoryDao to category
		Categories cat=this.mapper.map(dao, Categories.class);
		Categories save=catRepo.save(cat);
		//category to categoryDao
		return this.mapper.map(save, CategoryDao.class);
	}
	
	public CategoryDao update(CategoryDao newCat, int catId) {
		Categories oldCat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
//		oldCat.setCategory_id(newCat.getCategory_id());
		oldCat.setTitle(newCat.getTitle());
		Categories save=this.catRepo.save(oldCat);
		return this.mapper.map(save, CategoryDao.class);
	}
	public void delete(int catId) {
		Categories oldCat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
		this.catRepo.delete(oldCat);
	}
	public CategoryDao getById(int catId) {
		Categories oldCat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
		return this.mapper.map(oldCat, CategoryDao.class);
	}
	public List<CategoryDao> getAll(){
		List<Categories>findAll=this.catRepo.findAll();
		List<CategoryDao>allDao=findAll.stream().map(cat->this.mapper.map(cat, CategoryDao.class)).collect(Collectors.toList());
		return allDao;
		
	}
}
