package egovframework.sonorous.user.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.sonorous.user.service.EgovMemberService;
import egovframework.sonorous.user.service.MemberVO;

@Service("memberService")
public class EgovMemberServiceImpl implements EgovMemberService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMemberServiceImpl.class);
	
	@Resource(name = "memberMapper")
	private MemberMapper memberDAO;

	@Override
	public MemberVO getMember(MemberVO memberVO) throws Exception {
		MemberVO vo = memberDAO.selectMember(memberVO);
		return vo;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws Exception {
		return memberDAO.insertMember(memberVO);
	}

	@Override
	public MemberVO getLogin(Map<String, Object> paramMap) throws Exception {
		return memberDAO.loginMember(paramMap);
	}
	
}
