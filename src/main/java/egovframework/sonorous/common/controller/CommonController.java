package egovframework.sonorous.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/*import org.apache.commons.io.IOUtils;*/
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.sonorous.common.service.CommonService;
import egovframework.sonorous.common.service.impl.CommonServiceImpl;
import egovframework.sonorous.common.util.FileUtils;
import egovframework.sonorous.file.model.FileItem;

@Controller
public class CommonController {
	@Autowired
	CommonService commonService;
	
	/*@RequestMapping(value="/common/download")
	public void fileDownload(
		@RequestParam(value="file_seq_no", required=true) String fileSeqNo, HttpServletResponse response) throws Exception{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("file_seq_no", fileSeqNo);
			
			FileItem fileItem = commonService.getFileItem(paramMap);
			
			if(fileItem == null) {
				throw new RuntimeException("해당 첨부파일이 존재하지 않습니다.");
			}
			
			byte fileByte[] = 
					org.apache.commons.io.FileUtils.readFileToByteArray(
					new File(FileUtils.filePath + "/"+
			fileItem.getFile_path()+"/"+
			fileItem.getFile_save_name()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			response.setHeader
			("Content-Disposition", "attachment; fileName=\""
			+URLEncoder.encode(fileItem.getFile_name(),"utf-8"));
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();				
	}*/
}
