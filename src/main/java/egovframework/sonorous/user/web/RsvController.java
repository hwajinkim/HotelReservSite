/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.sonorous.user.web;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import egovframework.sonorous.common.service.DefaultVO;
import egovframework.sonorous.admin.service.RoomVO;
import egovframework.sonorous.user.service.RsvVO;
import egovframework.sonorous.user.service.RsvService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
public class RsvController {

	/** SonorousService */
	@Resource(name = "rsvService")
	private RsvService rsvService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
/**
	 * 글 목록을 조회한다. (pageing)
	 * @param listVO - 조회할 정보가 담긴 DefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/rsvList.do")
	public String rsvList(@ModelAttribute("listVO") DefaultVO listVO, ModelMap model, 
			HttpSession session,
			HttpServletResponse response) throws Exception {

		if(session.getAttribute("LOGIN_USER")== null) {
			System.out.println("로그인 후 사용가능");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 예약 가능합니다.'); location.href='login/loginForm.do';</script>");
			out.flush();
			out.close();
		}
		
		/** EgovPropertyService.sample */
		listVO.setPageUnit(propertiesService.getInt("pageUnit"));
		listVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(listVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(listVO.getPageUnit());
		paginationInfo.setPageSize(listVO.getPageSize());

		listVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		listVO.setLastIndex(paginationInfo.getLastRecordIndex());
		listVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<?> roomTypeList = rsvService.selectRoomTypeList(listVO);
		model.addAttribute("roomTypeList", roomTypeList);
		
		List<?> roomList = rsvService.selectRoomList(listVO);
		model.addAttribute("roomList", roomList);
		System.out.println("@@@roomList : "+roomList);
		
		int totCnt = rsvService.selectRoomListTotCnt(listVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		System.out.println("@@@paginationInfo : "+paginationInfo);
		
		Map<String, String> searchInfo= new HashMap<>();
		searchInfo.put("searchRoomType", listVO.getSearchRoomType());
		searchInfo.put("searchCheckIn", listVO.getSearchCheckIn());
		searchInfo.put("searchCheckOut", listVO.getSearchCheckOut());
		searchInfo.put("searchPeople", listVO.getSearchPeople());
		System.out.println("$$$searchInfo "+searchInfo);
		model.addAttribute("searchInfo", searchInfo);
		return "user/rsvList";
	}
	
	/**
	 * 글을 조회한다.
	 * @param roomVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("roomVO") - 조회한 정보
	 * @exception Exception
	 */
	@RequestMapping(value = "/rsvView.do", method = RequestMethod.GET)
	public String rsvView(@RequestParam(value="roomId", required=true ) int roomId, Model model, HttpServletRequest request) throws Exception {
		String searchRoomType = request.getParameter("searchRoomType");
		String searchCheckIn = request.getParameter("searchCheckIn");
		String searchCheckOut = request.getParameter("searchCheckOut");
		String searchPeople = request.getParameter("searchPeople");
		
		Map<String, String> searchInfo= new HashMap<>();
		searchInfo.put("searchRoomType", searchRoomType);
		searchInfo.put("searchCheckIn", searchCheckIn);
		searchInfo.put("searchCheckOut", searchCheckOut);
		searchInfo.put("searchPeople", searchPeople);
		System.out.println("$$$searchInfo "+searchInfo);
		model.addAttribute("searchInfo", searchInfo);
		
		//RoomVO roomVO = new RoomVO();
		//roomVO.setRoomId(id);
		// 변수명은 CoC 에 따라 sonorousVO
		Map roomInfo = rsvService.selectRoom(roomId);
		System.out.println("@@@roomInfo : "+roomInfo);
		model.addAttribute("roomInfo", roomInfo);
		return "user/rsvView";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * @param listVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/rsvInsertPage.do", method = RequestMethod.GET)
	public String rsvInsertPage(@RequestParam(value="roomId", required=true ) int roomId, @ModelAttribute("listVO") DefaultVO listVO, Model model, HttpServletRequest request) throws Exception {
		String searchRoomType = request.getParameter("searchRoomType");
		String searchCheckIn = request.getParameter("searchCheckIn");
		String searchCheckOut = request.getParameter("searchCheckOut");
		String searchPeople = request.getParameter("searchPeople");
		
		Map<String, String> searchInfo= new HashMap<>();
		searchInfo.put("searchRoomType", searchRoomType);
		searchInfo.put("searchCheckIn", searchCheckIn);
		searchInfo.put("searchCheckOut", searchCheckOut);
		searchInfo.put("searchPeople", searchPeople);
		System.out.println("$$$searchInfo "+searchInfo);
		model.addAttribute("searchInfo", searchInfo);
		
		//객실정보
		Map roomInfo = rsvService.selectRoom(roomId);
		System.out.println("@@@roomInfo : "+roomInfo);
		model.addAttribute("roomInfo", roomInfo);
		
		RsvVO rsvVO = new RsvVO();
		model.addAttribute("rsvVO", rsvVO);
		
		System.out.println("@@@@@ "+request.getParameter("searchCheckIn"));
	       
        Date format1 = new SimpleDateFormat("yyyy/MM/dd").parse(searchCheckIn);
        Date format2 = new SimpleDateFormat("yyyy/MM/dd").parse(searchCheckOut);
        
        long diffSec = (format1.getTime() - format2.getTime()) / 1000; //초 차이
        long diffDays = diffSec / (24*60*60); //일자수 차이
        
        System.out.println(diffSec + "초 차이");
        System.out.println(diffDays + "일 차이");
        model.addAttribute("diffDays", diffDays);
		
		
		System.out.println("@@@@@1");
		return "user/rsvInsert";
	}
	
	/**
	 * 글을 등록한다.
	 * @param sonorousVO - 등록할 정보가 담긴 VO
	 * @param listVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception, method = RequestMethod.POST
	 * MultipartHttpServletRequest request, HttpServletResponse response,
	 */
	@RequestMapping(value = "/rsvInsert.do")
	public String rsvInsert(@ModelAttribute("listVO") DefaultVO listVO, RsvVO rsvVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {
		// Server-Side Validation
		beanValidator.validate(rsvVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("rsvVO", rsvVO);
			return "user/rsvInsert";
		}
		
		rsvService.insertRsv(rsvVO);
		status.setComplete();
		return "forward:/rsvList.do";
	}
	
	
	
	

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param listVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	/*@RequestMapping("/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") String id, @ModelAttribute("listVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SonorousVO sonorousVO = new SonorousVO();
		sonorousVO.setId(id);
		// 변수명은 CoC 에 따라 sonorousVO
		model.addAttribute(selectSample(sonorousVO, searchVO));
		return "sample/egovSampleRegister";
	}*/

	

	/**
	 * 글을 수정한다.
	 * @param sonorousVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	/*@RequestMapping("/updateSample.do")
	public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SonorousVO sonorousVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		beanValidator.validate(sonorousVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sonorousVO", sonorousVO);
			return "sample/egovSampleRegister";
		}

		sonorousService.updateSample(sonorousVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}*/

	/**
	 * 글을 삭제한다.
	 * @param sonorousVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	/*@RequestMapping("/deleteSample.do")
	public String deleteSample(SonorousVO sonorousVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status) throws Exception {
		sonorousService.deleteSample(sonorousVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}*/

	@RequestMapping(value = "/getPeopleNum.do")
	@ResponseBody
	public String getPeopleNum(
			@RequestParam(value="searchRoomType", required = true) String searchRoomType, 
			@RequestParam(value="searchCheckIn", required = true) String searchCheckIn, 
			@RequestParam(value="searchCheckOut", required = true) String searchCheckOut, 
			ModelMap model, 
			HttpSession session,
			HttpServletResponse response) throws Exception {
		System.out.println("searchRoomType:::"+searchRoomType);
		System.out.println("searchCheckIn:::"+searchCheckIn);
		System.out.println("searchCheckOut:::"+searchCheckOut);
		
		DefaultVO listVO = new DefaultVO();
		listVO.setSearchRoomType(searchRoomType);
		listVO.setSearchCheckIn(searchCheckIn);
		listVO.setSearchCheckOut(searchCheckOut);
		
		RoomVO roomVO = rsvService.selectPeopleNum(listVO);
		
		String peopleNum = roomVO.getPeopleNum();
		
		return peopleNum;
	}
}
