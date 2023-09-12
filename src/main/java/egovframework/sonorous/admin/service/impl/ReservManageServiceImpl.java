package egovframework.sonorous.admin.service.impl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.sonorous.admin.service.ReservManageService;
import egovframework.sonorous.admin.service.ReservManageVO;

@Service("reservManageService")
public class ReservManageServiceImpl implements ReservManageService{

	@Resource(name = "reservManageMapper")
	private ReservManageMapper reservManageDAO;
	
	@Override
	public int getReservManageCount(Map<String, Object> paramMap) throws Exception{
		return reservManageDAO.selectReservManageCount(paramMap);
	}

	@Override
	public ArrayList<ReservManageVO> getReservManageList(Map<String, Object> paramMap) throws Exception{
		return reservManageDAO.selectReservManageList(paramMap);
	}

	@Override
	public ReservManageVO getReserv(Map<String, Object> paramMap)throws Exception {
		return reservManageDAO.selectReserv(paramMap);
	}

	@Override
	public int updateReservManage(ReservManageVO reservManage)throws Exception {
		return reservManageDAO.updateReservManage(reservManage);
	}

}
