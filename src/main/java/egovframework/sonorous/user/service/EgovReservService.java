package egovframework.sonorous.user.service;

import java.util.ArrayList;

public interface EgovReservService {

	ArrayList<ReservVO> getReserv(ReservVO reservVO) throws Exception;

	int canselReserv(ReservVO reservVO);

}
