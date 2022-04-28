package kr.co.farmstory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.farmstory.dao.BoardDao;
import kr.co.farmstory.service.BoardService;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.FileVo;

@SpringBootTest
class FarmstoryApplicationTests {

//	@Autowired
//	private BoardDao dao;
//	
//	@Test
//	public void insertAttached() {
//		ArticleVo av = new ArticleVo();
//		av.setType("abcdefg");
//		av.setTitle("test");
//		av.setContent("test");
//		av.setFile(1);
//		av.setUid("1234");
//		av.setRegip("123.0.0.23.04");
//		av.setRdate("22-04-28 12:33:02");
//		
//		dao.insertArticle(av);
//		int no = av.getNo();
//		System.out.println("no : "+ no);
//		
//	}
//	
	
	
//	@Autowired
//	private BoardService service;
//	private BoardDao dao;
//	
//	@Test
//	public void fileUplode() {
//		FileVo fv = new FileVo();
//		fv.setFid(1);
//		fv.setParent(1);
//		fv.setOName("3");
//		fv.setNName("20220224041005_aaa1010.jpg");
//		fv.setDownload(1);
//		fv.setRdate("2022-02-24 13:10:05");
//		
//		dao.insertFile(fv);
//		System.out.println();
//	}

}
