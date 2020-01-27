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

import com.app.model.Item;
import com.app.service.IItemService;
import com.app.util.ItemUtil;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {


	@Autowired
	private IItemService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveItem(@RequestBody Item item){
		ResponseEntity<String> resp=null;
		try {
			Item itm=service.saveItem(item);
			if(itm!=null) {
				resp = new ResponseEntity<String>("Item '"+itm.getItemId()+"' created!!",HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Item not created, please try again!!",HttpStatus.BAD_REQUEST);
			}
		}catch(Exception ex) {
			resp = new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			ex.printStackTrace();
			
		}
		return resp;
	}
	
	@GetMapping("/view-item/{id}")
	public ResponseEntity<?> getItemById(@PathVariable("id") Integer id){
		ResponseEntity<?> resp=null;
		try {
			Item item=service.getItemById(id);
			if(item!=null) {
				resp= new ResponseEntity<Item>(item,HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Record Not Found",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	} 
	
	@GetMapping("/all-items")
	public ResponseEntity<?> getAlls(){
		ResponseEntity<?> resp=null;
		try {
			List<Item> items=service.getAllItems();
			if(items!=null && !items.isEmpty()) {
				resp =new ResponseEntity<List<Item>>(items,HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("Record Not Found",HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@DeleteMapping("/delete-item/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") Integer id){
		ResponseEntity<String> resp=null;
		if(service.ItemExist(id)) {
			service.deleteItemById(id);
			resp = new ResponseEntity<String>("Item '"+id+"' deleted!!",HttpStatus.OK);
		}else {
			resp = new ResponseEntity<String>("Please select/enter vaild Item ",HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@PutMapping("/update-item/{id}")
	public ResponseEntity<?> updateItem(@PathVariable("id") Integer id,@RequestBody Item item){
		ResponseEntity<?> response=null;
		if(service.ItemExist(id)) {
			Item source=service.getItemById(id);
			ItemUtil.mapNotNullValues(source, item);
			service.saveItem(source);
			response=new ResponseEntity<String>(id+"-Updated", HttpStatus.OK);
		}else {
			response=new ResponseEntity<String>("RECORD NOT FOUND",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	
}
