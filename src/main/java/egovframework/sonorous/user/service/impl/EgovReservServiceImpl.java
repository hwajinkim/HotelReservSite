package egovframework.sonorous.user.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.sonorous.user.service.EgovReservService;
import egovframework.sonorous.user.service.MemberVO;
import egovframework.sonorous.user.service.ReservVO;


@Service("reservService")
public class EgovReservServiceImpl implements EgovReservService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMemberServiceImpl.class);
	
	@Resource(name = "reservMapper")
	private ReservMapper reservDAO;

	@Override
	public ArrayList<ReservVO> getReserv(ReservVO reservVO) throws Exception {
		ArrayList<ReservVO> vo = reservDAO.selectReserv(reservVO);
		System.out.println(vo);
		return vo;
	}

	@Override
	public int canselReserv(ReservVO reservVO) {
		return reservDAO.canselReserv(reservVO);
	}
}
