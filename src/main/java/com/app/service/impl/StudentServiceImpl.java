package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Student;
import com.app.repo.IStudentRepository;
import com.app.service.IStudentService;
@Service
@EnableTransactionManagement
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private IStudentRepository repo;

	@Override
	@Transactional
	public Student saveStudent(Student s) {
		Student sd=repo.save(s);
		return sd;
	}

	@Override
	@Transactional(readOnly=true)
	public Student getStudentById(Integer id) {
		
		return repo.findById(id).get();		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Student> getAllStudents() {
		List<Student> stdlist=repo.findAll();
		return stdlist;
	}

	@Override
	@Transactional
	public void deleteStudentById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean studentExist(Integer id) {
		return repo.existsById(id);
		 
	}

}
