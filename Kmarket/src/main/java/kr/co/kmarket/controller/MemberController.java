package kr.co.kmarket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.TermsVo;

@SessionAttributes("sessMember")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;

	// 최초에 sessMember값 초기화하는 메서드 
	@ModelAttribute("sessMember")
	public MemberVo setMemberVo() {
		return null;
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}
	
	// 로그인 페이지
	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}
	
	// 로그인정보 입력
	@PostMapping("/member/login")
	public String login(MemberVo vo, Model model) {
		
		MemberVo member = service.selectMember(vo);
		
		if(member == null) {
			return "redirect:/member/login?success=100"; 
		}else {
			model.addAttribute("sessMember", member);
			return "redirect:/";
		}
	}
	
	// 회원가입 폼 페이지
	@GetMapping("/member/register")
	public String register() {
		return "/member/register";
	}
	
	// 회원가입 정보 입력
	@PostMapping("/member/register")
	public String register(MemberVo vo, HttpServletRequest req) {
		
		String ip = req.getRemoteAddr();
		vo.setIp(ip);
		service.insertMember(vo);
		
		return "redirect:/member/login";
	}
	
	// 회원가입 타입 선택 페이지
	@GetMapping("/member/join")
	public String join() {
		return "/member/join";
	}
	
	// 약관 페이지
	@GetMapping("/member/signup")
	public String signup(Model model) {
		
		TermsVo terms = service.selectTerms();
		model.addAttribute("terms", terms);
		return "/member/signup";
	}
	
	// 판매자 회원가입
	@GetMapping("/member/register-seller")
	public String registerSeller() {
		return "/member/register-seller";
	}
	
	
}