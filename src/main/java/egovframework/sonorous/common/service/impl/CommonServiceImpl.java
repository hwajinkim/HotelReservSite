package egovframework.sonorous.common.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.sonorous.admin.service.impl.RoomMapper;
import egovframework.sonorous.common.service.CommonService;
import egovframework.sonorous.file.model.FileItem;

@Service
public class CommonServiceImpl implements egovframework.sonorous.common.service.CommonService{
	
	/*@Autowired
	FileItemDAO fileItemDao;*/
	//FileItemDAO fileItemDao = new FileItemDAO();
	
	@Resource(name="roomMapper")
	private RoomMapper roomMapper;
	
	@Override
	public FileItem getFileItem(HashMap<String, Object> paramMap) throws Exception {
		return roomMapper.selectFileItem(paramMap);
	}
}
