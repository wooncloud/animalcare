package com.pet.care.comm;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class FileUpload {
 private static final String SAVE_PATH = "C:\\Users\\CHO\\workspace_Spring\\PetCare\\src\\main\\webapp\\img\\";//원하는 파일 경로 지정
	 
	 @RequestMapping(value ="/test/upload.do", method = RequestMethod.POST)
	 public String upload(
		 @RequestParam(value="file1", required = false) MultipartFile mf) {
		 ModelAndView mv = new ModelAndView("uploadView");       
	 
		 String originalFileName = mf.getOriginalFilename();
		 long fileSize = mf.getSize();
		 String saveFile = SAVE_PATH + System.currentTimeMillis() + originalFileName;
	 
	            
		 	try {
		 		mf.transferTo(new File(saveFile));
	 
		 		} catch (IllegalStateException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		} catch (IOException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		 	return "/";
	 }
}
