package com.app.service;

import java.util.List;

import com.app.model.Student;

public interface IStudentService {
	
	public Student saveStudent(Student s);
	public Student getStudentById(Integer id);
	public List<Student> getAllStudents();
	public void deleteStudentById(Integer id);
	public boolean studentExist(Integer id);	
}
