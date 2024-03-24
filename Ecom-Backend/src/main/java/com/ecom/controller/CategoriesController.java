package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.CategoryDao;
import com.ecom.service.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoriesController {
	@Autowired
	private CategoryService categoryService;
	private CategoryDao create;
	
	//create category 
	@PostMapping("/create")
	public ResponseEntity<CategoryDao>create(@RequestBody CategoryDao catDao){
		CategoryDao create=this.categoryService.create(catDao);
		return new ResponseEntity<CategoryDao>(create, HttpStatus.CREATED);
	}
	
	//update category
	@PostMapping("update/{catId}")
	public ResponseEntity<CategoryDao>update(@RequestBody CategoryDao catDao, @PathVariable int catId){
		CategoryDao update=this.categoryService.update(catDao, catId);
		return new ResponseEntity<CategoryDao>(update, HttpStatus.OK);
		
	}
	
	//delete category
	@DeleteMapping("delete/{catId}")
	public ResponseEntity<ApiResponse>delete(@PathVariable int catId){
		this.categoryService.delete(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category delete successfully", true), HttpStatus.OK);
	}
	
	//get category by id
	@GetMapping("getById/{catId}")
	public ResponseEntity<CategoryDao>categoryGetById(@PathVariable int catId){
		CategoryDao getById= this.categoryService.getById(catId);
		return new ResponseEntity<CategoryDao>(getById, HttpStatus.OK);
		
	}
	
	//get all category
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDao>>getAll(){
		List<CategoryDao>all= this.categoryService.getAll();
		return new ResponseEntity<List<CategoryDao>>(all, HttpStatus.OK);
	}
}
