package kr.co.flower.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.flower.service.UserService;
import kr.co.flower.vo.UserVo;

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
				return "redirect:/";
			}
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
	

}
