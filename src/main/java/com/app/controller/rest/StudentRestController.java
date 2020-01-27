package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.service.IStudentService;
import com.app.util.StudentUtil;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

	@Autowired
	private IStudentService service;
	
	@PostMapping("/insert")
	public ResponseEntity<String> saveStudent(@RequestBody Student student) {
		ResponseEntity<String> resp=null;
		try {
			Student st=service.saveStudent(student);
			resp=new ResponseEntity<String>("Student '"+st.getStdId()+"' created.",HttpStatus.OK);
		}
		catch(Exception ex) {
			resp=new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			ex.printStackTrace();
		}
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudent(){
		ResponseEntity<?> resp=null;
		try {
			List<Student> slist=service.getAllStudents();
			if(slist!=null && !slist.isEmpty()) {
				resp = new ResponseEntity<List<Student>>(slist,HttpStatus.OK); 
			}else {
				resp = new ResponseEntity<String>("No Records Found",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@GetMapping("/stdinfo/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Integer sid){
		
		ResponseEntity<?> resp=null;
		try {
			Student std=service.getStudentById(sid);
			if(std!=null){
				resp = new ResponseEntity<Student>(std,HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("No Record Found/Invalid Input",HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}		
		return resp;
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		ResponseEntity<?> response=null;
		boolean exist=service.studentExist(id);
		if(exist) {
			Student source=service.getStudentById(id);
			StudentUtil.mapNotNullValues(source,student);
			service.saveStudent(source);
			response=new ResponseEntity<String>(id+"-Updated", HttpStatus.OK);
		}else {
			response=new ResponseEntity<String>("RECORD NOT FOUND",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
