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

import com.app.model.Customer;
import com.app.service.ICustomerService;
import com.app.util.CustomerUtil;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

	@Autowired
	private ICustomerService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){
		ResponseEntity<String> resp=null;
		try {
			Customer custm=service.saveCustomer(customer);
			if(custm!=null) {
				resp = new ResponseEntity<String>("Customer '"+custm.getcId()+"' created!!",HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Customer not created, please try again!!",HttpStatus.BAD_REQUEST);
			}
		}catch(Exception ex) {
			resp = new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			ex.printStackTrace();
			
		}
		return resp;
	}
	
	@GetMapping("/view-customer/{cid}")
	public ResponseEntity<?> getCustomerById(@PathVariable("cid") Integer id){
		ResponseEntity<?> resp=null;
		try {
			Customer customer=service.getCustomerById(id);
			if(customer!=null) {
				resp= new ResponseEntity<Customer>(customer,HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Record Not Found",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	} 
	
	@GetMapping("/all-customer")
	public ResponseEntity<?> getAlls(){
		ResponseEntity<?> resp=null;
		try {
			List<Customer> customers=service.getAllCustomers();
			if(customers!=null && !customers.isEmpty()) {
				resp =new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Record Not Found",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@DeleteMapping("/delete-customer/{cid}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("cid") Integer id){
		ResponseEntity<String> resp=null;
		if(service.CustomerExist(id)) {
			service.deleteCustomerById(id);
			resp = new ResponseEntity<String>("Customer '"+id+"' deleted!!",HttpStatus.OK);
		}else {
			resp = new ResponseEntity<String>("Please select/enter vaild Customer ",HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@PutMapping("/update-customer/{cid}")
	public ResponseEntity<?> updateCustomer(@PathVariable("cid") Integer id,@RequestBody Customer customer){
		ResponseEntity<?> response=null;
		if(service.CustomerExist(id)) {
			Customer source=service.getCustomerById(id);
			CustomerUtil.mapNotNullValues(source, customer);
			service.saveCustomer(source);
			response=new ResponseEntity<String>(id+"-Updated", HttpStatus.OK);
		}else {
			response=new ResponseEntity<String>("RECORD NOT FOUND",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
