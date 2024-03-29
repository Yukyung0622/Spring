package kr.co.farmstory.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVo;
import kr.co.farmstory.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	// 처음 sessUser값을 초기화하는 메서드 
	@ModelAttribute("sessUser")
	public UserVo setUserVo() {
		return null;
	}
	
	
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/user/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/user/login?success=103";
	}
	
	@PostMapping("/user/login")
	public String login(UserVo vo, Model model) {
		
		UserVo user = service.selectUser(vo);
		
		if(user == null) {
			// 로그인 실패
			return "redirect:/user/login?success=100";	
		}else {
			// 로그인 성공
			model.addAttribute("sessUser", user);
			return "redirect:/index";
		}
	}
	
	@GetMapping("/user/terms")
	public String terms(Model model) {
		
		TermsVo terms = service.selectTerms();
		model.addAttribute(terms);
		
		return "/user/terms";
	}
	
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	
	@PostMapping("/user/register")
	public String register(UserVo vo, HttpServletRequest req) {
		
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		
		
		service.insertUser(vo);
		return "redirect:/user/login";
	}
	
	
	@ResponseBody
	@GetMapping("/user/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		
		int result = service.selectCountUserUid(uid);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
}