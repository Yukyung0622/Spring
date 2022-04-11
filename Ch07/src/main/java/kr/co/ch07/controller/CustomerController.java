package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch07.service.CustomerService;
import kr.co.ch07.vo.CustomerVo;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;
	//등록
	@GetMapping("/customer/register")
	private String register() {
		return "/customer/register";
	}
	
	@PostMapping("/customer/register")
	private String register(CustomerVo vo) {
		service.insertCustomer(vo);
		return "redirect:/customer/list";
	}
	//목록
	@GetMapping("/customer/list")
	private String list(Model model) {
		
		List<CustomerVo> customer = service.selectCustomers();
		model.addAttribute("customer", customer);
		
		return "/customer/list";
		
	}
	//수정
	@GetMapping("/customer/modify")
	public String modify(int custid, Model model) {
		CustomerVo customer = service.selectCustomer(custid);
		model.addAttribute("customer", customer);
		return "/customer/modify";
	}
	
	@PostMapping("/customer/modify")
	public String modify(CustomerVo vo) {
		service.updateCustomer(vo);
		//System.out.println("vo : "+vo);
		return "redirect:/customer/list";
	}

	//삭제
	@GetMapping("/customer/delete")
	public String delete(int custid) {
		service.deleteCustomer(custid);
		return "redirect:/customer/list";
	}
}
