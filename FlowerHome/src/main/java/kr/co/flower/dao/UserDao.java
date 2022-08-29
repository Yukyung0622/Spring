package kr.co.flower.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.flower.vo.UserVo;

@Mapper
@Repository
public interface UserDao {
	
	public void insertUser(UserVo vo);
	public UserVo selectUser(UserVo vo);


}
