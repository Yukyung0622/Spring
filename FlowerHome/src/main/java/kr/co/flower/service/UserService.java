package kr.co.flower.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.flower.dao.UserDao;
import kr.co.flower.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public void insertUser(UserVo vo) {
		dao.insertUser(vo);
	}
	
	public UserVo selectUser(UserVo vo) {
		return dao.selectUser(vo);
	}

}
