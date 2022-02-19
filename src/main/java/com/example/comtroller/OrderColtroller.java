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
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.OrderService;
import com.example.model.Order;
import com.example.model.Product;


@RestController
@CrossOrigin(origins = "*")
public class OrderColtroller {
 
	@Autowired
	OrderService orderService;
	
	
	@PostMapping(value = "/order/save")
	public ResponseEntity<?> save(@RequestBody Order entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			entity.setStatus("pendding");
			entity.setId(0);
			Order order = orderService.save(entity);
			
			map.put("message", "Data save successfully");
			map.put("data", order);
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
	
	
	
	@GetMapping(value = "/order/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Order order = orderService.findById(id).get();
			map.put("message", "Data get successfully");
			map.put("data", order);
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
	
	
	
	@GetMapping(value = "/order/getAll")
	public ResponseEntity<?> getOrders() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Order> order =(List<Order>) orderService.findAll();
			map.put("message", "Data get successfully");
			map.put("data", order);
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

	
	

	@PostMapping(value = "/order/update")
	public ResponseEntity<?> update(@RequestBody Order entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Order order = orderService.save(entity);
			map.put("message", "Data updated successfully");
			map.put("data", order);
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

	
	
	@GetMapping(value = "/order/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Order order = orderService.findById(id).get();
		try {
			orderService.delete(order);
			map.put("message", "Data deleted successfully");
			map.put("data", order);
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
	

	

	
	//for order cancel
	@GetMapping(value = "/order/cancel/{id}")
	public ResponseEntity<?> cancel( @PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Order order = orderService.findById(id).get();
		try {
			order.setStatus("cancel");
			 order = orderService.save(order);
			map.put("message", "Data updated successfully");
			map.put("data", order);
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


	
	//for order delivered
	@GetMapping(value = "/order/delivered/{id}")
	public ResponseEntity<?> delivered( @PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Order order = orderService.findById(id).get();
		try {
			order.setStatus("delivered");
			 order = orderService.save(order);
			map.put("message", "Data updated successfully");
			map.put("data", order);
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



}

