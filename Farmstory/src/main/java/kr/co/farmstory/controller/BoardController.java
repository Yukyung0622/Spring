package kr.co.farmstory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.farmstory.service.BoardService;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.FileVo;
import kr.co.farmstory.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 처음 sessUser값을 초기화하는 메서드 
	@ModelAttribute("sessUser")
	public UserVo setUserVo() {
		return null;
	}
	
	//list
	@GetMapping("/board/list")
	public String list(Model model, String cate, String type, String pg) {
		
		int currentPage = service.getCurrentPage(pg); 
		int total = service.selectCountTotal(type); // <---- 게시판 종류(type)별로 전체 갯수를 구한다. 
		int lastPageNum = service.getLastPageNum(total);
		int start = service.getLimitStart(currentPage);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<ArticleVo> articles = service.selectArticles(type, start);
		
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		model.addAttribute("articles", articles);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		return "/board/list";
	}
	
	//write
	@GetMapping("/board/write")
	public String write(@ModelAttribute("sessUser") UserVo sessUser, Model model, String cate, String type) {
		// 로그인 여부 확인
		if(sessUser == null)
			return "redirect:/user/login?success=102";	
		
		
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		
		return "/board/write";
	}
	
	@PostMapping("/board/write")
	public String write(@ModelAttribute("sessUser") UserVo sessUser, ArticleVo vo, HttpServletRequest req) {
		// 로그인 여부 확인
		if(sessUser == null)
			return "redirect:/user/login?success=102";
		
		
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		
		if(vo.getFname().isEmpty()) {
			// 파일 첨부 안했을 때
			vo.setFile(0);
			service.insertArticle(vo);
		}else {
			// 파일 첨부 했을 때
			// 글 등록
			vo.setFile(1);
			int no = service.insertArticle(vo);
			
			// 파일 업로드
			FileVo fvo = service.fileUpload(vo.getFname());
			
			// 파일 등록
			fvo.setParent(no);
			service.insertFile(fvo);
			//System.out.println("fvo :"+fvo.getNName());
		}
		return "redirect:/board/list?cate="+vo.getCate()+"&type="+vo.getType();
	}
	
	//view
	@GetMapping("/board/view")
	public String view(@ModelAttribute("sessUser") UserVo sessUser, Model model, String cate, String type, int no) {
		// 로그인 여부 확인
		if(sessUser == null)
			return "redirect:/user/login?success=102";
		
		ArticleVo article = service.selectArticle(no);
		
		
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		model.addAttribute("article", article);
		
		return "/board/view";
	}
	
	@GetMapping("/board/modify")
	public String modify(@ModelAttribute("sessUser") UserVo sessUser, String cate, String type, int no, Model model) {
		// 로그인 여부 확인
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		
		ArticleVo article = service.selectArticle(no);
		
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		model.addAttribute("article", article);
		
		return "/board/modify";
	}
	
	@PostMapping("/board/modify")
	public String modify(@ModelAttribute("sessUser") UserVo sessUser, ArticleVo vo, String cate, String type) {
		// 로그인 여부 확인
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		//System.out.println("no : "+vo.getNo());
		//System.out.println("cate : "+cate);
		//System.out.println("type : "+type);
		
		service.updateArticle(vo);
		
		return "redirect:/board/list?cate="+cate+"&type="+type;
	}
	
	//delete
	@GetMapping("/board/delete")
	public String delete(@ModelAttribute("sessUser") UserVo sessUser, String cate, String type, int no, int fid, String nName) {
		// 로그인 여부 확인
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
//		System.out.println("no : "+no);
		//System.out.println("fid : "+fid);
		//System.out.println("nName"+nName);
		
		if(fid == 0) {
			service.deleteArticle(no);
		}else {
			service.deleteArticle(no);
			service.deleteFile(fid);
			service.deleteAttachments(nName);
		}
		
		return "redirect:/board/list?cate="+cate+"&type="+type;
	}
	
	
	
	@GetMapping("/board/filedownload")
	public void filedownload(int fid, HttpServletResponse resp) {
		// 파일 다운로드 카운트 +1
		
		// 파일 다운로드 정보조회
		FileVo fvo = service.selectFile(fid);
		
		// 파일 다운로드
		service.fileDownload(resp, fvo);
	}
}