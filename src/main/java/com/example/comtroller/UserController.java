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

import com.example.Repository.UserRepo;

import com.example.model.User;
import com.example.request.PayLoad;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

	
	
	@Autowired
	UserRepo userRepo;

	@PostMapping(value = "/user/save")
	public ResponseEntity<?> save(@RequestBody User entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			User user = userRepo.save(entity);
			map.put("message", "Data save successfully");
			map.put("data", user);
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
	
	
	
	@GetMapping(value = "/user/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			User user = userRepo.findById(id).get();
			map.put("message", "Data get successfully");
			map.put("data", user);
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
	
	
	
	@GetMapping(value = "/user/getAll")
	public ResponseEntity<?> getUsers() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<User> user =(List<User>) userRepo.findAll();
			map.put("message", "Data get successfully");
			map.put("data", user);
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

	
	

	@PostMapping(value = "/user/update")
	public ResponseEntity<?> update(@RequestBody User entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			User user = userRepo.save(entity);
			map.put("message", "Data updated successfully");
			map.put("data", user);
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

	
	
	@GetMapping(value = "/user/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		User user = userRepo.findById(id).get();
		try {
			userRepo.delete(user);
			map.put("message", "Data deleted successfully");
			map.put("data", user);
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
	
	
	
	
	
	
	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody PayLoad payload) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<User> userList = (List<User>) userRepo.findAll();

			if (userList != null && userList.size() > 0) {
				User user = userList.get(0);

				if (user.getPassword().equals(payload.getPassword())) {
					map.put("message", "Login Successful");
					map.put("status", "success");
					map.put("data", user);
					map.put("statusCode", 200);
					return ResponseEntity.ok(map);
				} else {
					map.put("message", "email or password doesn't match");
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
