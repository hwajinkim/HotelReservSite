package egovframework.sonorous.user.service;

import java.util.Map;

public interface EgovMemberService {

	//회원 정보 조회(1건)
	MemberVO getMember(MemberVO memberVO) throws Exception;

	public int insertMember(MemberVO memberVO) throws Exception;

	MemberVO getLogin(Map<String, Object> paramMap)throws Exception;

}
