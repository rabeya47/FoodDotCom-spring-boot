package com.example.comtroller;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Repository.CartRepo;
import com.example.model.Cart;
import com.example.model.Product;
import com.example.storage.service.FileStorageService;


@RestController
@CrossOrigin(origins = "*")
public class CartController {

	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	//for without image file
	@PostMapping(value = "/cart/save")
	public ResponseEntity<?> save(@RequestBody Cart entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Cart cart = cartRepo.save(entity);
			map.put("message", "Data save successfully");
			map.put("data", cart);
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
	
		@PostMapping("/addCart_withfile")
		public ResponseEntity<Map> saveFormData(@RequestBody Cart cart) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {

				
				
				cart = cartRepo.save(cart);
				map.put("status", "Success");
				map.put("data", cart);
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
		
	
	
	
	
	
	
	@GetMapping(value = "/cart/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Cart cart = cartRepo.findById(id).get();
			map.put("message", "Data get successfully");
			map.put("data", cart);
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
	
	
	
	
	@GetMapping(value = "/cart/getAll")
	public ResponseEntity<?> getcarts() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Cart> cart =(List<Cart>) cartRepo.findAll();
			map.put("message", "Data get successfully");
			map.put("data", cart);
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

	
	
	
	@PostMapping(value = "/cart/update")
	public ResponseEntity<?> update(@RequestBody Cart entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Cart cart = cartRepo.save(entity);
			map.put("message", "Data updated successfully");
			map.put("data", cart);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data updated failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	
	
	
	@GetMapping(value = "/cart/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Cart cart = cartRepo.findById(id).get();
		try {
			cartRepo.delete(cart);
			map.put("message", "Data deleted successfully");
			map.put("data", cart);
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
