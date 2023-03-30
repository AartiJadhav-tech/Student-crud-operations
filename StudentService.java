package com.example.studentcrudoperations.service;

import java.util.List;

import com.example.studentcrudoperations.dao.StudentDto;

public interface StudentService {

	void create(StudentDto dto);

	List<StudentDto> getall(Integer id);

	void delete(Integer id);

	void update(StudentDto dto);

}
