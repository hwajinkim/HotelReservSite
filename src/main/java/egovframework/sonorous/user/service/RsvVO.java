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
package egovframework.sonorous.user.service;

import egovframework.sonorous.common.service.DefaultVO;

/**
 * @Class Name : RsvVO.java
 * @Description : RsvVO Class
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
public class RsvVO extends DefaultVO {

	private static final long serialVersionUID = 1L;

	/* 예약번호 */
	private int reqSeq;
	
	/* 회원ID */
	private String mId;
	
	/* 객실ID */
	private int roomId;
	
	/* 예약등록일 */
	private String resInsDate;
	
	/* 체크인날짜 */
	private String checkInDate;
	
	/* 체크아웃날짜 */
	private String checkOutDate;
	
	/* 예약상태 */
	private String resState;
	
	/* 추가요청사항 */
	private String addReq;
	
	/* 예약상태변경일 */
	private String resStateModDate;

	/* 예약상태변경일 */
	private int rsvPrice;
	
	public int getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(int reqSeq) {
		this.reqSeq = reqSeq;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getResInsDate() {
		return resInsDate;
	}

	public void setResInsDate(String resInsDate) {
		this.resInsDate = resInsDate;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getResState() {
		return resState;
	}

	public void setResState(String resState) {
		this.resState = resState;
	}

	public String getAddReq() {
		return addReq;
	}

	public void setAddReq(String addReq) {
		this.addReq = addReq;
	}

	public String getResStateModDate() {
		return resStateModDate;
	}

	public void setResStateModDate(String resStateModDate) {
		this.resStateModDate = resStateModDate;
	}
	
	public int getRsvPrice() {
		return rsvPrice;
	}

	public void setRsvPrice(int rsvPrice) {
		this.rsvPrice = rsvPrice;
	}

}
