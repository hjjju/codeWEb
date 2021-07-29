package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*") // 현재 클래스의 모든 메서드 들의 기본적인 URL경로가 됨.
//RequestMapping 은 1. 클래스 선언, 2. 메서드 선언에 사용 할 수 있습니다.
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {

	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("basic get...............................");
	}

	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get ....................................");
	}

	// SampleController의 경로가 sample/* 이므로 ex01() 메서드를 호출 하는 경로는 sample/ex01 이다.
	// 메서드에는 @GetMapping 이 사용 되었으므로, 필요한 파라미터를 URL 뒤에 '?name=AAA?&age = 10' 과 같은 형태로
	// 추가해서 호출 할 수 있다.
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {

		log.info("" + dto); // 실행결과 INFO : org.zerock.controller.SampleController - SampleDTO(name=AAA,
							// age=10)

		return "ex01";
	}

	// Controller가 파라미터를 수집하는 방식은 파라미터 타입에 따라 자동으로 변환하는 방식을 이용한다.
	// 예를 들면 SampleDto에는 int타입으로 선언된 age가 자동으로 숫자로 변환됨.

	@GetMapping("/ex02")

	/*
	 * @RequestParam은은 파라미터로 사용된 변수의 이름과 전달되는 파라미터의이름이 다른 경우에 유용하게 사용 된다.
	 * 
	 */
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age: " + age);

		return "ex02";

	}
	/*
	 * 동일한 이름의 파라미터가 여러개 전달되는 경우에는 ArrayList<>등을 이용해서 처리가능 스프링은 파라미터의 타입을 보고 객체를 생성
	 * 하므로 파라미터의 타입은 List<>와같이 실제클래스타입으로 지정 ids라는 이름의 파라미터가 여러개 전달 되더라도
	 * ArrayList<String> 이 생성되어 자동으로 수집됨.
	 */

	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {

		log.info("ids: " + ids);
		return "ex02List";
	}

	// 배열
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {

		log.info("array ids : " + Arrays.toString(ids));
		return "ex02Array";
	}

	// DTO타입이용
	// sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=BBB&list%5B2%5D.name=CCC
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}

	// 바인딩
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//
//	}

	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
	}
	
	public String home(Model model) {
		
		model.addAttribute("servetTime", new java.util.Date());
		
		return "home";
	}
	
	
	@GetMapping("/ex04")
	//기본 자료형의 경우 파라미터로 선언하더라도 화면까지 전달되지는 않는다.
	// @ModelAttribute가 붙은 파라미터는 타입에 관계없이 무조건 Model에 담아서 전달됨.
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "ex04";
	}
	
	
	// 메서드의 리턴타입을 void로 지정하는경우 해당URL의 경로를 그대로 jsp파일의 이름으로 사용하게 됨.
	@GetMapping("/ex05")
	public void ex05() {
		log.info("ex05");
	}
	
	/*
	 * 리턴타입을 VO나 DTO 타입등 복합적 데이터가 들어간 객체타입으로 지정할수 있는데
	 * 이 경우는 주로  JSON 데이터를 만들어 내는 용도로 사용.
	 * */
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06...........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍기동");
		
		return dto;
	}
	
	/*
	 * ResponseEntity를 통해 원하는 해더 정보나 데이터를 전달 할 수 있다.
	 * ResponseEntity는 HttpHeaders 객체를 같이 전달 할수 있고, 원하는 HTTP헤더메세지 가공가능.
	 * */
	 
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07..........");
		
//		{"name" : "홍길동"}
		String msg = "{\"name\" : \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg,header,HttpStatus.OK);
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
