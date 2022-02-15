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

import com.example.Repository.StudentService;
import com.example.model.Student;


@RestController
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	StudentService studentService;


	@PostMapping(value = "/student/save")
	public ResponseEntity<?> save(@RequestBody Student entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Student student = studentService.save(entity);
			map.put("message", "Data save successfully");
			map.put("Data", student);
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
	
	
	
	@GetMapping(value = "/student/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Student student = studentService.findById(id).get();
			map.put("message", "Data get successfully");
			map.put("Data", student);
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
	
	
	
	@GetMapping(value = "/student/getAll")
	public ResponseEntity<?> getStudents() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Student> student =(List<Student>) studentService.findAll();
			map.put("message", "Data get successfully");
			map.put("Data", student);
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

	
	

	@PostMapping(value = "/student/update")
	public ResponseEntity<?> update(@RequestBody Student entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Student student = studentService.save(entity);
			map.put("message", "Data updated successfully");
			map.put("Data", student);
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

	
	
	@GetMapping(value = "/student/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Student student = studentService.findById(id).get();
		try {
			studentService.delete(student);
			map.put("message", "Data deleted successfully");
			map.put("Data", student);
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

