package com.example.comtroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Repository.ProductService;
import com.example.model.Product;
import com.example.storage.service.FileStorageService;



@RestController
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	
	//for save product without file
	@PostMapping(value = "/product/save")
	public ResponseEntity<?> save(@RequestBody Product entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Product product = productService.save(entity);
			map.put("message", "Data save successfully");
			map.put("data", product);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data saved failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	
	
	
	
	//for save products with file.
	
	@PostMapping("/saveproduct_withfile")
	public ResponseEntity<Map> saveFormData(@ModelAttribute Product product,
			@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(fileName).toUriString();
			product.setImages(fileName);
			product.setImagesUri(fileDownloadUri);
			//Product pro = productService.findById(id).get();
			//product.setId(id);
			
			product = productService.save(product);
			map.put("status", "Success");
			map.put("data", product);
			map.put("message", "Data saved successfully");
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}

	}
	
	
	
	@GetMapping(value = "/product/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Product product = productService.findById(id).get();
			map.put("message", "Data get successfully");
			map.put("data", product);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data fetch failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	
	
	
	
	
	//for category id find out.
	
	@GetMapping(value = "/product/category/{categoryId}")
	public ResponseEntity<?> category(@PathVariable(value = "categoryId") Integer categoryId) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Product> product =(List<Product>) productService.findByCetegoryId(categoryId);
			map.put("message", "Data get successfully");
			map.put("data", product);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data fetch failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	
	
	//for get all products
	
	@GetMapping(value = "/product/getAll")
	public ResponseEntity<?> getproducts() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Product> product =(List<Product>) productService.findAll();
			map.put("message", "Data get successfully");
			map.put("data", product);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data fetch failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}

	
	

	//for update products with file
	
	@PostMapping(value = "/product/update")
	public ResponseEntity<Map> updateFormData(@ModelAttribute Product product,
			@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(fileName).toUriString();
			product.setImages(fileName);
			product.setImagesUri(fileDownloadUri);
			
			
			product = productService.save(product);
			map.put("status", "Success");
			map.put("data", product);
			map.put("message", "Data update successfully");
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}
	}

	
	
	//for delete product by id.
	
	@GetMapping(value = "/product/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Product product = productService.findById(id).get();
		try {
			productService.delete(product);
			map.put("message", "Data deleted successfully");
			map.put("data", product);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data deletation failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	

	
	
	//For product search
	@GetMapping("/product/search")
	public ResponseEntity<?> search(@RequestParam(value = "searchText") String searchText) {
		Map<String, Object> map = new HashMap<String, Object>();	
		try {
			List<Product> productList = productService.searchProduct(searchText);
			map.put("message", "Data deleted successfully");
			map.put("data", productList);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data deletation failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
	}
	
	
	
	
	
}
