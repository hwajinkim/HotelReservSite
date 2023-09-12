package egovframework.sonorous.common.service;

import java.util.HashMap;

import egovframework.sonorous.file.model.FileItem;

public interface CommonService {
	public FileItem getFileItem(HashMap<String, Object> paramMap) throws Exception;
}
