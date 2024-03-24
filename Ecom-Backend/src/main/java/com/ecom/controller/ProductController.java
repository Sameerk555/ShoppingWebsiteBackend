package com.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.payload.AppConstants;
import com.ecom.payload.ProductDao;
import com.ecom.payload.ProductResponse;
import com.ecom.service.FileUpload;
import com.ecom.service.ProductService;
@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
	@Autowired   
	private ProductService productService;
	@Autowired
	private FileUpload fileUpload;
	@Value("${product.path.images}")
	private String imagePath;
	
	@PostMapping("product/images/{productId}")
	public ResponseEntity<?> uploadImageOfProduct(@PathVariable int productId,
			@RequestParam("product_image")MultipartFile file) {
		
		ProductDao product = this.productService.viewProductById(productId);
		String imageName=null;
		try {
			String uploadImage = this.fileUpload.uploadImage(imagePath, file);
			product.setProduct_imageName(uploadImage);
			ProductDao updateProduct = this.productService.updateProductById(productId, product);
			return new ResponseEntity<>(updateProduct, HttpStatus.ACCEPTED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(Map.of("Message", "file not upload in server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	//create product 
	@PostMapping("/create/{catId}")
	@ResponseBody
	public ResponseEntity<ProductDao> createProduct(@RequestBody ProductDao p, @PathVariable int  catId) {
//		System.out.println(p.getProduct_name());   
		ProductDao createProduct=productService.createProduct(p, catId);
		return new ResponseEntity<ProductDao>(createProduct, HttpStatus.CREATED);
	}
	//view all product
	@GetMapping("/view")
	public ProductResponse viewAllProduct(@RequestParam(value="pageNo", defaultValue=AppConstants.PAGE_NUMBER_STRING, required = false) int pageNo, 
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE_STRING, required=false)int pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY_STRING, required=false)String sortBy,
			@RequestParam(value="sortDir", defaultValue=AppConstants.SORT_DIR_STRING, required=false)String sortDir){	
		ProductResponse productResponse= productService.viewAllProduct(pageNo, pageSize, sortBy, sortDir);
		
		return productResponse;
	}
	//view particular product by id  
	@ResponseBody
	@GetMapping("view/{ProductId}")
	public ResponseEntity<ProductDao> viewProductById(@PathVariable int ProductId) {
		
		ProductDao product= productService.viewProductById(ProductId);
		return new ResponseEntity<ProductDao>(product, HttpStatus.OK);
	}
	//delete product
	@DeleteMapping("del/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
		System.out.println("product deleted.... of id  "+productId);
		productService.deleteProductById(productId);
		return new ResponseEntity<String>("Product deleted", HttpStatus.OK);
	}
	
	//update product
	@PutMapping("update/{productId}")
	public ResponseEntity<ProductDao> updateProduct(@PathVariable int productId, @RequestBody ProductDao newProduct) {
		ProductDao updateProduct= productService.updateProductById(productId, newProduct);
		return new ResponseEntity<ProductDao>(updateProduct, HttpStatus.ACCEPTED);
	}
	
	//category wise find product
	@GetMapping("/category/{catId}")
	public ResponseEntity<List<ProductDao>>getProductByCategory(@PathVariable int catId){
		List<ProductDao>byCategory=this.productService.findProductByCategory(catId);
		return new ResponseEntity<List<ProductDao>>(byCategory, HttpStatus.ACCEPTED);
		
	}
}
