package egovframework.sonorous.common.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.sonorous.admin.service.RoomVO;
import egovframework.sonorous.file.model.FileItem;

@Component("fileUtils")
public class FileUtils {
	
//	public static final String filePath = "C:\\eGovFrameDev-3.9.0-64bit\\workspace\\eGovFrameTest\\src\\main\\webapp\\upload"; 
	public static final String filePath = "/Users/hwa.j/eclipse-workspace/eGovFrameTest/src/main/webapp/upload"; 
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	public List<FileItem> uploadFiles(RoomVO room,
			MultipartHttpServletRequest mRequest) throws Exception{
		
		List<FileItem> fileList = new ArrayList<FileItem>();
		List<MultipartFile> mfList = mRequest.getFiles("uploadFiles");
		File file = null;
		
		for(MultipartFile parts : mfList) {
			if(parts.isEmpty() == false) {//데이터가 있는 경우
				//저장할 파일이 있는지
				//FileItem 정보 생성
				FileItem fileItem = new FileItem();
				fileItem.setRoomImageId(room.getRoomId());//게시판의 글 번호
				fileItem.setRoomId(room.getRoomId());//게시판의 글 번호
				fileItem.setRoomImageName(parts.getOriginalFilename());//파일의 실제 이름
				//fileItem.setFile_save_name(UUID.randomUUID().toString()+"_"+parts.getOriginalFilename());
				
				fileItem.setRoomImagePath(dateFormat.format(new Date()));
				
				try {
					file = new File(filePath+"/"+fileItem.getRoomImagePath()+"/"+fileItem.getRoomImageName());
					if(file.exists() == false) {
						file.mkdirs();
					}
					parts.transferTo(file);//위에서 지정한 위치에 파일 저장
					
					//확장자 구하기
					String ext = parts.getOriginalFilename()
							.substring(parts.getOriginalFilename().lastIndexOf(".") + 1);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				fileList.add(fileItem);
			}
		}
		
		return fileList;
	}
	public static String getSaveName() {
		return UUID.randomUUID().toString().replaceAll("-","");
		
	}
	
	public static String getFancySize(long size) {
		String fancy = "";
		DecimalFormat decimalFormat = new DecimalFormat();
		
		if(size < 1024) {
			fancy = decimalFormat.format(size) + "bytes";
		}else if(size <(1024 * 1024)) {
			fancy = decimalFormat.format(size/1024.0) + "KB";
		}else {
			fancy = decimalFormat.format(size/1024.0*1024.0) + "MB";
		}
		return fancy;
	}
	
	/*public static String createThumbnail(String path, String fileName, String ext) {
		
		BufferedImage sourceImage;//원본 이미지를 메모리에 로딩
		
		String thumFileName; //썸네일 이미지의 파일 명
		
		try {
			//C:\\SpringUpload\\upload/GALLERY/날짜/
			sourceImage = ImageIO.read(new File(filePath + File.separator + path, fileName));		
			
			//이미지 축소
			//Scalr.Method.AUTOMATIC 은 원본비율 유지
			//원본이지미, 너비(비율유지여부), 높이, 너비나 높이 기준 px
			BufferedImage destImage = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 100);
			
			//썸네일 이미지 이름 생성
			//C:\\SpringUpload\\upload/GALLERY/날짜/파일명
			thumFileName = filePath + File.separator + path + File.separator + "thum_" + fileName;
			
			//썸네일 파일 생성
			File newFile = new File(thumFileName);
			
			ImageIO.write(destImage, ext, newFile);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thum_"+fileName;
	}	*/
}
