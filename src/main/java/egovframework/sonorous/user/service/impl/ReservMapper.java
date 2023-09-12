package egovframework.sonorous.user.service.impl;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sonorous.user.service.MemberVO;
import egovframework.sonorous.user.service.ReservVO;

@Mapper("reservMapper")
public interface ReservMapper {

	ArrayList<ReservVO> selectReserv(ReservVO reservVO) throws Exception;

	int canselReserv(ReservVO reservVO);

}
