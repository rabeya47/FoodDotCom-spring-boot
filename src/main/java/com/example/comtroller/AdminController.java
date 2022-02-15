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

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/")
	public String hello() {
		return "Hello World!";
	}

	@PostMapping(value = "/user/save")
	public ResponseEntity<?> save(@RequestBody Admin entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Admin admin = adminService.save(entity);
			map.put("message", "Data save successfully");
			map.put("Data", admin);
			map.put("Status code", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data saved failed");
			map.put("Data", null);
			map.put("Status code", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	
	
	@GetMapping(value = "/user/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Admin admin = adminService.findById(id).get();
			map.put("message", "Data get successfully");
			map.put("Data", admin);
			map.put("Status code", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data fetch failed");
			map.put("Data", null);
			map.put("Status code", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	
	
	@GetMapping(value = "/user/getAll")
	public ResponseEntity<?> getUsers() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Admin> admin =(List<Admin>) adminService.findAll();
			map.put("message", "Data get successfully");
			map.put("Data", admin);
			map.put("Status code", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data fetch failed");
			map.put("Data", null);
			map.put("Status code", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}

	
	

	@PostMapping(value = "/user/update")
	public ResponseEntity<?> update(@RequestBody Admin entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Admin admin = adminService.save(entity);
			map.put("message", "Data updated successfully");
			map.put("Data", admin);
			map.put("Status code", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data updated failed");
			map.put("Data", null);
			map.put("Status code", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}

	
	
	@GetMapping(value = "/user/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Admin admin = adminService.findById(id).get();
		try {
			adminService.delete(admin);
			map.put("message", "Data deleted successfully");
			map.put("Data", admin);
			map.put("Status code", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data deletation failed");
			map.put("Data", null);
			map.put("Status code", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	

}
