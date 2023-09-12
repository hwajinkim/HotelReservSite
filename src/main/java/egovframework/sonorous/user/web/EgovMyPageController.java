package egovframework.sonorous.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.sonorous.user.service.EgovMemberService;
import egovframework.sonorous.user.service.EgovReservService;
import egovframework.sonorous.user.service.MemberVO;
import egovframework.sonorous.user.service.ReservVO;

@Controller
public class EgovMyPageController {
	
	/** EgovMemberService */
	@Resource(name = "memberService")
	private EgovMemberService memberService;
	
	/** EgovReservService */
	@Resource(name = "reservService")
	private EgovReservService reservService;
	
	@RequestMapping(value = "/memberInfo.do")
	public String memberInfo(@ModelAttribute("memberVO") MemberVO memberVO,
			Model model,
			HttpSession session) throws Exception {
		
		MemberVO vo = (MemberVO) session.getAttribute("LOGIN_USER");
		
		MemberVO member = memberService.getMember(vo);
		
		model.addAttribute("result", member);
		
		return "user/myPage/memInfo";
	}
	
	@RequestMapping(value="/reservInfo.do")
	public String reservInfo(@ModelAttribute("memberVO") MemberVO memberVO,
			@ModelAttribute("reservVO") ReservVO reservVO,
			Model model,
			HttpSession session) throws Exception {
		
		MemberVO vo = (MemberVO) session.getAttribute("LOGIN_USER");
		System.out.println("vo.getmId()"+vo.getmId());
		reservVO.setmId(vo.getmId());
		ArrayList<ReservVO> reserv = reservService.getReserv(reservVO);
		
		model.addAttribute("reservList", reserv);
		
		return "user/myPage/reservInfo";
	}
	
	@RequestMapping(value="/reservCansel.do")
	public String reservCansel(@ModelAttribute("reservVO") ReservVO reservVO,
			Model model,
			HttpSession session) throws Exception {
		System.out.println("reqSeq:::"+reservVO.getReqSeq());
		
		int updCnt = reservService.canselReserv(reservVO);
		System.out.println("updCnt:::"+updCnt);
		return "redirect:/reservInfo.do";
	}
	
}
