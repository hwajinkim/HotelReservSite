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
package egovframework.sonorous.user.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.sonorous.common.service.DefaultVO;
import egovframework.sonorous.admin.service.RoomVO;
import egovframework.sonorous.user.service.RsvVO;
import egovframework.sonorous.user.service.RsvService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

@Service("rsvService")// extends EgovAbstractServiceImpl
public class RsvServiceImpl extends EgovAbstractServiceImpl implements RsvService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RsvServiceImpl.class);

	/** SonorousDAO */
	// TODO ibatis 사용
	//@Resource(name = "rsvMapper")
	//private SonorousDAO rsvMapper;
	// TODO mybatis 사용
	@Resource(name="rsvMapper")
	private RsvMapper rsvMapper;
	
	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SonorousVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	public int insertRsv(RsvVO vo) throws Exception {
		LOGGER.debug(vo.toString());

		/** ID Generation Service */
		/*String roomId = egovIdGnrService.getNextStringId();
		System.out.println("@@@roomId : "+roomId);
		vo.setRoomId(roomId);
		LOGGER.debug(vo.toString());

		rsvMapper.insertRoom(vo);*/
		int updCnt = rsvMapper.insertRsv(vo);
		System.out.println("@@@@updCnt : "+updCnt);
		System.out.println("@@@");
		
		return updCnt;
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SonorousVO
	 * @return void형
	 * @exception Exception
	 */
	/*@Override
	public void updateSample(SonorousVO vo) throws Exception {
		rsvMapper.updateSample(vo);
	}*/

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SonorousVO
	 * @return void형
	 * @exception Exception
	 */
	/*@Override
	public void deleteSample(SonorousVO vo) throws Exception {
		rsvMapper.deleteSample(vo);
	}*/

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SonorousVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public Map selectRoom(int roomId) throws Exception {
		System.out.println("@@@roomId "+roomId);
		Map result = rsvMapper.selectRoom(roomId);
		System.out.println("@@@result "+rsvMapper.selectRoom(roomId));
		return result;
	}

	/**
	 * 글 목록을 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectRoomTypeList(DefaultVO listVO) throws Exception {
		return rsvMapper.selectRoomTypeList(listVO);
	}
	
	/**
	 * 글 목록을 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectRoomList(DefaultVO listVO) throws Exception {
		System.out.println("@@@listVO "+listVO);
		return rsvMapper.selectRoomList(listVO);
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
		return rsvMapper.selectRoomListTotCnt(listVO);
	}

	/**
	 * 최대 인원 수를 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 인원 수 
	 * @exception Exception
	 */
	@Override
	public RoomVO selectPeopleNum(DefaultVO listVO) throws Exception{
		return rsvMapper.selectPeopleNum(listVO);
	}

}
