package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.IEmployeeService;
import com.app.util.EmployeeUtil;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {


	@Autowired
	private IEmployeeService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
		ResponseEntity<String> resp=null;
		try {
			Employee emp=service.saveEmployee(employee);
			if(emp!=null) {
				resp = new ResponseEntity<String>("Employee '"+emp.geteId()+"' created!!",HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Employee not created, please try again!!",HttpStatus.BAD_REQUEST);
			}
		}catch(Exception ex) {
			resp = new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			ex.printStackTrace();
			
		}
		return resp;
	}
	
	@GetMapping("/view-employee/{eid}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("eid") Integer id){
		ResponseEntity<?> resp=null;
		try {
			Employee employee=service.getEmployeeById(id);
			if(employee!=null) {
				resp= new ResponseEntity<Employee>(employee,HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Record Not Found",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	} 
	
	@GetMapping("/all-employee")
	public ResponseEntity<?> getAlls(){
		ResponseEntity<?> resp=null;
		try {
			List<Employee> employees=service.getAllEmployees();
			if(employees!=null && !employees.isEmpty()) {
				resp =new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Record Not Found",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@DeleteMapping("/delete-employee/{eid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("eid") Integer id){
		ResponseEntity<String> resp=null;
		if(service.EmployeeExist(id)) {
			service.deleteEmployeeById(id);
			resp = new ResponseEntity<String>("Employee '"+id+"' deleted!!",HttpStatus.OK);
		}else {
			resp = new ResponseEntity<String>("Please select/enter vaild Employee ",HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@PutMapping("/update-employee/{eid}")
	public ResponseEntity<?> updateEmployee(@PathVariable("eid") Integer id,@RequestBody Employee employee){
		ResponseEntity<?> response=null;
		if(service.EmployeeExist(id)) {
			Employee source=service.getEmployeeById(id);
			EmployeeUtil.mapNotNullValues(source, employee);
			service.saveEmployee(source);
			response=new ResponseEntity<String>(id+"-Updated", HttpStatus.OK);
		}else {
			response=new ResponseEntity<String>("RECORD NOT FOUND",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	
}
