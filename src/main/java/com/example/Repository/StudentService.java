package com.example.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Student;
@Repository
public interface StudentService extends CrudRepository<Student, Integer> {

}
