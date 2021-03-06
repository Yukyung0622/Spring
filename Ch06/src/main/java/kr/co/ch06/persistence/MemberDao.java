package kr.co.ch06.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.ch06.vo.MemberVo;

@Mapper
@Repository
public interface MemberDao {
	
	public void insertMember(MemberVo vo);
	public MemberVo selectMember(String uid);
	public List<MemberVo> selectMembers();
	public void updateMember(MemberVo vo);
	public void deleteMember(String uid);

}
