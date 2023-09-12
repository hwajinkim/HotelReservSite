package egovframework.sonorous.user.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.sonorous.user.service.EgovMemberService;
import egovframework.sonorous.user.service.MemberVO;

@Controller
public class EgovLoginController {

	@Autowired
	private EgovMemberService memberService;
	
	@RequestMapping(value="/main.do")
	public String main() throws Exception{
		System.out.println(1111);
		return "main";
	}
	@RequestMapping(value="login/loginForm.do")
	public String loginForm() throws Exception{
		return "login/loginForm";
	}
	
	@RequestMapping(value="login/login.do")
	public String login(
			@RequestParam(value="mId")String mId,
			@RequestParam(value="mPw")String mPw,
			Model model,
			HttpSession session) throws Exception{
		
		String message="";
		boolean isError = true;
		
		String viewPage = "common/message";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mId", mId);
		paramMap.put("mPw", mPw);
		
		MemberVO member = memberService.getLogin(paramMap);
		if(member != null) {
			//세션의 LOGIN_USER가 null이 아니면 로그인한 상태 
			session.setAttribute("LOGIN_USER", member);
			message = member.getmId() + "님 환영합니다.";
			isError = false;
		}else {
			message = "회원정보가 없습니다. 아이디나 비밀번호를 확인해주세요.";
			isError = true;
		}		
		model.addAttribute("isError",isError);
		model.addAttribute("message",message);
		model.addAttribute("locationURL","/main.do");
		
		return viewPage;
	}
	
	@RequestMapping(value="login/logout.do")
	public String logout(Model model,
	HttpSession session, HttpServletResponse response) throws Exception{
		
		session.removeAttribute("LOGIN_USER");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그아웃 되었습니다.'); location.href='/main.do';</script>");
		out.flush();
		out.close();
		
		return null;
	}
}
