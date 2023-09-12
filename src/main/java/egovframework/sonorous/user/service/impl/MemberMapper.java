package egovframework.sonorous.user.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sonorous.user.service.MemberVO;

@Mapper("memberMapper")
public interface MemberMapper {

	MemberVO selectMember(MemberVO memberVO) throws Exception;

	public int insertMember(MemberVO memberVO)throws Exception;

	MemberVO loginMember(Map<String, Object> paramMap)throws Exception;
	

}
