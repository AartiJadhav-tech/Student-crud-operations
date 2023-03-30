package com.example.studentcrudoperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentcrudoperations.dao.StudentDto;
import com.example.studentcrudoperations.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/create/one")
	private ResponseEntity<String> create(@Valid @RequestBody StudentDto dto) {
		service.create(dto);
		return new ResponseEntity<String>("Student created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	private ResponseEntity<List<StudentDto>> getall(@RequestParam(name = "id", required = false) Integer id) {
		List<StudentDto> dto = service.getall(id);
		return new ResponseEntity<List<StudentDto>>(dto, HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	private ResponseEntity<String> delete(@RequestParam Integer id) {
		service.delete(id);
		return new ResponseEntity<String>("student deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/update")
	private ResponseEntity<String> update(@Valid @RequestBody StudentDto dto) {
		service.update(dto);
		return new ResponseEntity<String>("student updated successfully", HttpStatus.OK);
	}

}
