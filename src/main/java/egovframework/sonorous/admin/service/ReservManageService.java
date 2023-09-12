package egovframework.sonorous.admin.service;

import java.util.ArrayList;
import java.util.Map;

public interface ReservManageService {

	public int getReservManageCount(Map<String, Object> paramMap) throws Exception;

	public ArrayList<ReservManageVO> getReservManageList(Map<String, Object> paramMap) throws Exception;

	public ReservManageVO getReserv(Map<String, Object> paramMap) throws Exception;

	public int updateReservManage(ReservManageVO reservManage) throws Exception;

}
