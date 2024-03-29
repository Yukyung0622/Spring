package kr.co.flower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	@GetMapping("/product/subscription")
	public String subscription() {
		return "/product/subscription";
	}
	
	@GetMapping("/product/shop")
	public String shop() {
		return "/product/shop";
	}
	
	@GetMapping("/product/shopdetail")
	public String shopdetail() {
		return "/product/shopdetail";
	}
	
	@GetMapping("/product/cart")
	public String cart() {
		return "/product/cart";
	}
	
	@GetMapping("/product/express")
	public String express() {
		return "/product/express";
	}
	
	@GetMapping("/product/onedayclass")
	public String onedayclass() {
		return "/product/onedayclass";
	}
	
	
	@GetMapping("/product/about")
	public String about() {
		return "/product/about";
	}
	
	@GetMapping("/product/api")
	public String api() {
		return "/product/api";
	}
	
	

}
