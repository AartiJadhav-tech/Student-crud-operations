package com.example.studentcrudoperations.dao;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {

	private int id;

	@Size(min = 2, max = 30, message = "name should be between  min 2 or max 20 characters ")
	private String name;

	@Positive(message = "age must be positive and not null")
	private int age;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birth_date;

	@Size(min = 10, max = 10, message = "mobile number must be of 10 digits")
	private String mobile_number;

	private String address;
}
