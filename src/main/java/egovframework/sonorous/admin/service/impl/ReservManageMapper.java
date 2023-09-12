package egovframework.sonorous.admin.service.impl;

import java.util.ArrayList;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sonorous.admin.service.ReservManageVO;

@Mapper("reservManageMapper")
public interface ReservManageMapper {

	public int selectReservManageCount(Map<String, Object> paramMap) throws Exception;

	public ArrayList<ReservManageVO> selectReservManageList(Map<String, Object> paramMap) throws Exception;

	public ReservManageVO selectReserv(Map<String, Object> paramMap) throws Exception;

	public int updateReservManage(ReservManageVO reservManage) throws Exception;

}
