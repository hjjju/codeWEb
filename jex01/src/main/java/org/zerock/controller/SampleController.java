package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/sample/*")	//현재 클래스의 모든 메서드 들의 기본적인 URL경로가 됨.
//RequestMapping 은 1. 클래스 선언, 2. 메서드 선언에 사용 할 수 있습니다.
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET,RequestMethod.POST})
	public void basicGet() {
		log.info("basic get...............................");
	}
	
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get ....................................");
	}

	
	@GetMapping("/ex04")
	//기본 자료형의 경우 파라미터로 선언하더라도 화면까지 전달되지는 않는다.
	// @ModelAttribute가 붙은 파라미터는 타입에 관계없이 무조건 Model에 담아서 전달됨.
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "ex04";
	}
	
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload..............");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file ->{
			log.info("-------------------------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
	}
	
	
}
