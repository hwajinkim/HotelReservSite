package egovframework.sonorous.user.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.sonorous.user.service.EgovMemberService;
import egovframework.sonorous.user.service.MemberVO;

@Controller
public class EgovMemberController {
	
	/** EgovMemberService */
	@Resource(name = "memberService")
	private EgovMemberService memberService;

	/**
	 * 글 등록 화면을 조회한다.
	 * @param memberVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "mem_form"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addMember.do")
	public String addMemberView(@ModelAttribute("memberVO") MemberVO memberVO , Model model) throws Exception {
		model.addAttribute("memberVO", new MemberVO());
		return "user/member/memberForm";
	}
	
	@RequestMapping(value="/memberInsert.do")
	public String memberInsert(@ModelAttribute("memberVO") MemberVO memberVO , Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int updCnt = memberService.insertMember(memberVO);
		
		System.out.println("updCnt"+updCnt);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(updCnt > 0) {
			out.println("<script>alert('회원 가입에 성공하였습니다.'); location.href='/main.do';</script>");
		}else {
			out.println("<script>alert('회원 가입에 실패하였습니다.'); history.go(-1);</script>");
		}
		out.flush();
		out.close();
		return null;
	}
	
	
	@RequestMapping(value="/memberExists.do")
	@ResponseBody
	public Map<String, Object> memberExists(
			@RequestParam(value="mId", required = true) String mId,
			@ModelAttribute("memberVO") MemberVO memberVO
			) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		memberVO.setmId(mId);
		MemberVO member = memberService.getMember(memberVO);
		
		if(member != null){ //id가 존재 
			resultMap.put("result", "true");
		}else{
			resultMap.put("result", "false");
		}
		
		return resultMap;
	}
	
}
