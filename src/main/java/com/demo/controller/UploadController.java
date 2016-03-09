package com.demo.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@RequestMapping("/img")
	public ModelAndView upload(@RequestParam("pic") MultipartFile file) throws Exception{
		String originalFilename = file.getOriginalFilename();
		String newFileName = null;
		if (file != null && originalFilename !=null ) {
			String path = "D:\\Tomcat\\temp\\";
			newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf('.'));
			File newFile = new File(path+newFileName);
			//将内存中的数据写入磁盘
			file.transferTo(newFile);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", newFileName);
		mv.setViewName("image");
		return mv;
	}
}
