package kr.co.ch06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch06.persistence.MemberDao;
import kr.co.ch06.vo.MemberVo;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao dao;
	
	public void insertMember(MemberVo vo) {
		dao.insertMember(vo);
	}
	public void selectMember() {}
	public List<MemberVo> selectMembers() {
		return dao.selectMembers();
	}
	public void updateMember() {}
	public void deleteMember() {}

}
