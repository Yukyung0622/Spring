package kr.co.ch09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.CustomerService;
import kr.co.ch09.vo.CustomerVo;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customer")
	public List<CustomerVo> list(){
		List<CustomerVo> customer = service.selectCustomers();
		return customer;
	}
	
	@PostMapping("/customer")
	public List<CustomerVo> register(CustomerVo vo){
		service.insertCustomer(vo);
		return service.selectCustomers();
	}
	
	@PutMapping("/customer/{uid}")
	public List<CustomerVo> modify(@PathVariable("custid") int custid, CustomerVo vo){
		vo.setCustid(custid);
		service.updateCustomer(vo);
		return service.selectCustomers();
	}
	
	@DeleteMapping("/customer/{uid}")
	public List<CustomerVo> delete(@PathVariable("custid") int custid){
		service.deleteCustomer(custid);
		return service.selectCustomers();
	}
	

}
