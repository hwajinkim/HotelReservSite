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
package egovframework.sonorous.admin.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import egovframework.sonorous.common.service.DefaultVO;
import egovframework.sonorous.admin.service.RoomVO;
import egovframework.sonorous.admin.service.RoomService;
import egovframework.sonorous.admin.service.SonorousVO;
import egovframework.sonorous.common.util.FileUtils;
import egovframework.sonorous.file.model.FileItem;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
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

@Service("roomService")// extends EgovAbstractServiceImpl
public class RoomServiceImpl extends EgovAbstractServiceImpl implements RoomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

	/** SonorousDAO */
	// TODO ibatis 사용
	//@Resource(name = "roomMapper")
	//private SonorousDAO roomMapper;
	// TODO mybatis 사용
	@Resource(name="roomMapper")
	private RoomMapper roomMapper;
	
	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	@Autowired
	FileUtils fileUtils;
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SonorousVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	public int insertRoom(RoomVO vo, MultipartHttpServletRequest mRequest) throws Exception {
		LOGGER.debug(vo.toString());
		
		int updCnt = roomMapper.insertRoom(vo);
		System.out.println("@@@@updCnt : "+updCnt);
		List<FileItem> fileList = fileUtils.uploadFiles(vo, mRequest);
		System.out.println("@@@@fileList : "+fileList);
		if(updCnt != 0) {
			for(FileItem item : fileList) {
				roomMapper.insertFileItem(item);
			}
		}
		
		return updCnt;
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SonorousVO
	 * @return 
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public int updateRoom(RoomVO vo, MultipartHttpServletRequest mRequest) throws Exception {
		int updCnt = roomMapper.updateRoom(vo);
		System.out.println("@@@@updCnt : "+updCnt);
		List<FileItem> fileList = fileUtils.uploadFiles(vo, mRequest);
		System.out.println("@@@@fileList : "+fileList);
		if(updCnt != 0) {
			for(FileItem item : fileList) {
				roomMapper.insertFileItem(item);
			}
		}
		
		return updCnt;
	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SonorousVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteRoom(int roomId) throws Exception {
		roomMapper.deleteRoom(roomId);
	}
	
	/**
	 * 사진 일괄삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SonorousVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteImageAll(int roomId) throws Exception {
		roomMapper.deleteImageAll(roomId);
	}

	/**
	 * 사진을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SonorousVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteImage(int roomImageId) throws Exception {
		roomMapper.deleteImage(roomImageId);
	}
	
	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SonorousVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public Map selectRoom(int roomId) throws Exception {
		System.out.println("@@@roomId "+roomId);
		Map result = roomMapper.selectRoom(roomId);
		System.out.println("@@@result "+roomMapper.selectRoom(roomId));
		return result;
	}
	
	/**
	 * 사진을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SonorousVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public List<?> selectImage(int roomId) throws Exception {
		System.out.println("@@@22roomImage "+roomMapper.selectImage(roomId));
		return roomMapper.selectImage(roomId);
	}

	/**
	 * 글 목록을 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectRoomList(DefaultVO listVO) throws Exception {
		System.out.println("@@@2"+listVO);
		return roomMapper.selectRoomList(listVO);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	@Override
	public int selectRoomListTotCnt(DefaultVO listVO) {
		System.out.println("@@@3");
		return roomMapper.selectRoomListTotCnt(listVO);
	}

}
