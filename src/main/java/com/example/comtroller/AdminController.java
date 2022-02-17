package com.example.comtroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.AdminService;
import com.example.model.Admin;
import com.example.request.PayLoad;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/")
	public String hello() {
		return "Hello World!";
	}

	@PostMapping(value = "/admin/save")
	public ResponseEntity<?> save(@RequestBody Admin entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Admin admin = adminService.save(entity);
			map.put("message", "Data save successfully");
			map.put("data", admin);
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
	
	
	
	@GetMapping(value = "/admin/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Admin admin = adminService.findById(id).get();
			map.put("message", "Data get successfully");
			map.put("data", admin);
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
	
	
	
	@GetMapping(value = "/admin/getAll")
	public ResponseEntity<?> getUsers() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Admin> admin =(List<Admin>) adminService.findAll();
			map.put("message", "Data get successfully");
			map.put("data", admin);
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

	
	

	@PostMapping(value = "/admin/update")
	public ResponseEntity<?> update(@RequestBody Admin entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Admin admin = adminService.save(entity);
			map.put("message", "Data updated successfully");
			map.put("data", admin);
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

	
	
	@GetMapping(value = "/admin/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Admin admin = adminService.findById(id).get();
		try {
			adminService.delete(admin);
			map.put("message", "Data deleted successfully");
			map.put("data", admin);
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
	
	
	
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody PayLoad payload) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<Admin> adminList = (List<Admin>) adminService.findAll();

			if (adminList != null && adminList.size() > 0) {
				Admin admin = adminList.get(0);

				if (admin.getPassword().equals(payload.getPassword())) {
					map.put("message", "Login Successful");
					map.put("status", "Success");
					map.put("data", admin);
					return ResponseEntity.ok(map);
				} else {
					map.put("message", "Username or password doesn't match");
					map.put("status", "failed");
					map.put("data", null);
					return ResponseEntity.status(412).body(map);
				}
				
			}else {
				map.put("message", "Data not found");
				map.put("status", "failed");
				map.put("data", null);
				return ResponseEntity.status(412).body(map);
			}

		} catch (Exception e) {
			map.put("message", e.getLocalizedMessage());
			map.put("status", "Failed");
			map.put("data", null);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}

}
