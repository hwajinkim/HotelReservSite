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
package egovframework.sonorous.admin.web;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import egovframework.sonorous.common.service.DefaultVO;
import egovframework.sonorous.admin.service.RoomVO;
import egovframework.sonorous.admin.service.RoomService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class RoomController {

	/** SonorousService */
	@Resource(name = "roomService")
	private RoomService roomService;

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
	@RequestMapping(value = "/roomList.do")
	public String roomList(@ModelAttribute("listVO") DefaultVO listVO, ModelMap model) throws Exception {

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
		System.out.println("@@@");
		List<?> roomList = roomService.selectRoomList(listVO);
		model.addAttribute("roomList", roomList);
		System.out.println("@@@roomList : "+roomList);
		int totCnt = roomService.selectRoomListTotCnt(listVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		System.out.println("@@@paginationInfo : "+paginationInfo);
		return "admin/roomList";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * @param listVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/roomInsertPage.do", method = RequestMethod.GET)
	public String roomInsertPage(@ModelAttribute("listVO") DefaultVO listVO, Model model) throws Exception {
		RoomVO roomVO = new RoomVO();
		model.addAttribute("roomVO", roomVO);
		System.out.println("@@@@@1");
		return "admin/roomInsert";
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
	@RequestMapping(value = "/roomInsert.do")
	public String roomInsert(@ModelAttribute("listVO") DefaultVO listVO, RoomVO roomVO, MultipartHttpServletRequest mRequest, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {
		System.out.println("@@@@@");
		MultipartFile file = mRequest.getFile("file");
		// Server-Side Validation
		beanValidator.validate(roomVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("roomVO", roomVO);
			return "admin/roomInsert";
		}
		System.out.println("@@@@@222");
		
		roomService.insertRoom(roomVO, mRequest);
		status.setComplete();
		return "forward:/roomList.do";
	}
	

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param listVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/roomUpdatePage.do")
	public String roomUpdatePage(@RequestParam("roomId") int roomId, @ModelAttribute("listVO") DefaultVO searchVO, Model model) throws Exception {
		//객실정보 조회
		Map roomInfo = roomService.selectRoom(roomId);
		model.addAttribute("roomInfo", roomInfo);
		System.out.println("@@@roomInfo : "+roomInfo);
		//객실사진정보 조회
		List<?> imgList = roomService.selectImage(roomId);
		model.addAttribute("imgList", imgList);
		System.out.println("@@@imgList : "+imgList);
		return "admin/roomUpdate";
	}

	/**
	 * 글을 조회한다.
	 * @param roomVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("roomVO") - 조회한 정보
	 * @exception Exception
	 */
	@RequestMapping(value = "/roomView.do", method = RequestMethod.GET)
	public String roomView(@RequestParam(value="roomId", required=true ) int roomId, Model model, HttpServletRequest request) throws Exception {
		//객실정보 조회
		Map roomInfo = roomService.selectRoom(roomId);
		model.addAttribute("roomInfo", roomInfo);
		System.out.println("@@@roomInfo : "+roomInfo);
		//객실사진정보 조회
		List<?> imgList = roomService.selectImage(roomId);
		model.addAttribute("imgList", imgList);
		System.out.println("@@@imgList : "+imgList);
		return "admin/roomView";
	}

	/**
	 * 글을 수정한다.
	 * @param sonorousVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/roomUpdate.do")
	public String updateSample(@RequestParam(value="roomId", required=true ) int roomId, RoomVO roomVO, MultipartHttpServletRequest mRequest, @ModelAttribute("searchVO") DefaultVO searchVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {
		System.out.println("@@@@@333");
		MultipartFile file = mRequest.getFile("file");
		// Server-Side Validation
		beanValidator.validate(roomVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("roomVO", roomVO);
			return "admin/roomUpdate";
		}
		System.out.println("@@@@@444");
		
		roomService.updateRoom(roomVO, mRequest);
		status.setComplete();
		return "forward:/roomList.do";
	}

	/**
	 * 글을 삭제한다.
	 * @param sonorousVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/roomDelete.do")
	public String roomDelete(@RequestParam(value="roomId", required=true ) int roomId, RoomVO roomVO, @ModelAttribute("searchVO") DefaultVO searchVO, SessionStatus status) throws Exception {
		roomService.deleteRoom(roomId);
		roomService.deleteImageAll(roomId);
		status.setComplete();
		return "forward:/roomList.do";
	}
	
	/**
	 * 사진을 삭제한다.
	 * @param sonorousVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/roomImageDelete.do")
	public String roomImageDelete(@RequestParam(value="roomImageId", required=true ) int roomImageId, RoomVO roomVO, @ModelAttribute("searchVO") DefaultVO searchVO, SessionStatus status) throws Exception {
		roomService.deleteImage(roomImageId);
		status.setComplete();
		return "forward:/roomList.do";
	}

}
