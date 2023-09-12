/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.sonorous.common.service.DefaultVO;
import egovframework.sonorous.admin.service.RoomVO;
import egovframework.sonorous.user.service.RsvVO;
import egovframework.sonorous.file.model.FileItem;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * sample에 관한 데이터처리 매퍼 클래스
 *
 * @author  표준프레임워크센터
 * @since 2014.01.24
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2014.01.24        표준프레임워크센터          최초 생성
 *
 * </pre>
 */
@Mapper("rsvMapper")
public interface RsvMapper {
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 RsvVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public int insertRsv(RsvVO vo) throws Exception;

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 RsvVO
	 * @return void형
	 * @exception Exception
	 */
	void updateSample(RsvVO vo) throws Exception;

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 RsvVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteSample(RsvVO vo) throws Exception;

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 RoomVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public Map selectRoom(int roomId) throws Exception;

	/**
	 * 객실타입을 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<?> selectRoomTypeList(DefaultVO listVO) throws Exception;
	
	/**
	 * 글 목록을 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<?> selectRoomList(DefaultVO listVO) throws Exception;

	/**
	 * 글 총 갯수를 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	int selectRoomListTotCnt(DefaultVO listVO);

	//첨부파일 목록 조회
	public ArrayList<FileItem> selectFileItemList(HashMap<String, Object> paramMap) throws Exception;
	
	//첨부 파일 1건 조회
	public FileItem selectFileItem(HashMap<String, Object> paramMap) throws Exception;
	
	//첨부파일 등록(업로드 내용 저장)
	public int insertFileItem(FileItem fileItem) throws Exception;
	
	//첨부파일 삭제
	public int deleteFileItem(HashMap<String, Object> paramMap) throws Exception;

	/**
	 * 최대 인원 수를 조회한다.
	 * @param listVO - 조회할 정보가 담긴 VO
	 * @return 인원 수 
	 * @exception Exception
	 */
	public RoomVO selectPeopleNum(DefaultVO listVO) throws Exception;
	
}
