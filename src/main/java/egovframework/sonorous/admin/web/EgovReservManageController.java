package egovframework.sonorous.admin.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.sonorous.admin.service.ReservManageService;
import egovframework.sonorous.admin.service.ReservManageVO;
import egovframework.sonorous.common.util.PagingUtil;

@Controller
public class EgovReservManageController {
	
	@Resource(name = "reservManageService")
	ReservManageService reservManageService;
	
	
	@RequestMapping(value = "/reservManageList.do")
	public String reservManageList(
			@RequestParam(value="searchWord", required=false, defaultValue="") String searchWord,
			@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize,
			Model model
			)throws Exception{
		
		int pageCount = 5;
		int totalCount = 0;
		
		ArrayList<ReservManageVO> reservManageList = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if(!StringUtils.isBlank(searchWord)) {
			paramMap.put("searchWord", searchWord);
		}
		totalCount = reservManageService.getReservManageCount(paramMap);
		System.out.println("totalCount:::"+totalCount);
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount);
		
		System.out.println("getStartRow:::"+pagingUtil.getStartRow());
		System.out.println("getEndRow:::"+pagingUtil.getEndRow());
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		reservManageList = reservManageService.getReservManageList(paramMap);
		
		model.addAttribute("reservManageList", reservManageList);
		model.addAttribute("pagingUtil", pagingUtil);
		
		return "admin/reserv/reservList";
	}
	
	@RequestMapping(value = "/reservManageForm.do", method=RequestMethod.GET)
	public String reservManageForm(
			@RequestParam(value="reqSeq", required=true )int reqSeq,
			Model model
			) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("reqSeq", reqSeq);
		
		ReservManageVO reserv = reservManageService.getReserv(paramMap);
		model.addAttribute("reserv",reserv);
		
		return "admin/reserv/reservForm";
	}
	
	@RequestMapping(value="reservManageUpdate.do", method=RequestMethod.POST)
	public String reservManageUpdate(ReservManageVO reservManage, Model model
			) throws Exception{
		System.out.println("resSeq:::"+reservManage.getReqSeq());
		
		boolean isError = false;
		
		try {
			int  updCnt = reservManageService.updateReservManage(reservManage);
			System.out.println("updCnt:::"+updCnt);
			if(updCnt == 0) {
				isError = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isError = true;
		}
		String viewPage = "common/message";
		
		String message="예약 정보가 수정되었습니다.";
		
		if(isError) {
			message="예약 정보 수정에 실패하였습니다.";
		}else {
			model.addAttribute("locationURL","reservManageForm.do?reqSeq="+reservManage.getReqSeq());
		}
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
	
		return viewPage;
	} 
	
}
