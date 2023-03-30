package com.example.studentcrudoperations.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentcrudoperations.dao.StudentDto;
import com.example.studentcrudoperations.entity.Student;
import com.example.studentcrudoperations.exception.ResourceNotFound;
import com.example.studentcrudoperations.repository.StudentRepository;
import com.example.studentcrudoperations.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;

	@Override
	public void create(StudentDto dto) {
		Student student = new Student();
		BeanUtils.copyProperties(dto, student);
		repository.save(student);
	}

	@Override
	public List<StudentDto> getall(Integer id) {
		List<StudentDto> dtos = new ArrayList<>();
		if (id != null) {
			Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFound("resource not found with this id", id));
			BeanUtils.copyProperties(student, dtos);
		} else {
			List<Student> students = repository.findAll();

			dtos = students.stream().map((i) -> {
				StudentDto studentdto = new StudentDto();
				BeanUtils.copyProperties(i, studentdto);
				return studentdto;
			}).collect(Collectors.toList());
		}
		return dtos;
	}

	@Override
	public void delete(Integer id) {
		Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFound("student not found with this id", id));
		repository.deleteById(id);
	}

	@Override
	public void update(StudentDto dto) {
		Student student = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFound("student not found with this id", dto.getId()));
		if (dto.getName() != null)
			student.setName(dto.getName());
		if (dto.getAddress() != null)
			student.setAddress(dto.getAddress());
		if (dto.getAge() != 0)
			student.setAge(dto.getAge());
		if (dto.getBirth_date() != null)
			student.setBirth_date(dto.getBirth_date());
		if (dto.getMobile_number() != null)
			student.setMobile_number(dto.getMobile_number());
		repository.save(student);
	}

}
