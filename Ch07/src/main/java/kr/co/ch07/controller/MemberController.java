package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch07.service.MemberService;
import kr.co.ch07.vo.MemberVo;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("/member/register")
	private String register(){
		return "/member/register";
	}
	
	@PostMapping("/member/register")
	private String register(MemberVo vo) {
		service.insertMember(vo);
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/list")
	private String list(Model model) {
		
		List<MemberVo> members = service.selectMembers();
		model.addAttribute("members", members);
		
		return "/member/list";
	}
	
	@GetMapping("/member/modify")
	public String modify(String uid, Model model) {
		
		MemberVo member = service.selectMember(uid);
		model.addAttribute("member", member);
		return "/member/modify";
	}
	
	@PostMapping("/member/modify")
	public String modify(MemberVo vo) {
		
		service.updateMember(vo);
		
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/delete")
	public String delete(String uid) {
		
		service.deleteMember(uid);
		
		return "redirect:/member/list";
	}
}
