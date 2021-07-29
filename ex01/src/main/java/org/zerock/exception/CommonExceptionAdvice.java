package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

/*
 * 	 @ControllerAdvice : 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시하는용도.
 *   @ExceptionHandler : 해당 메서드가() 들어가는 예외타입을 처리한다는것 의미. 속송으로 Exception 클래스타입 지정가능.
 *   
 *   org.zerock.exception 패키지는 sevlet-context.xml에서 인식하지 x -> component-scan을 이용해 
 *   해당 패키지으 내용을 조사하도록 해야함.
 *   
 * */

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)    //모든 예외에대한 처리가 except()만을 이용해 처리.
	public String except(Exception ex, Model model) {
		log.error("Exception ........" + ex.getMessage());
		model.addAttribute("exception",ex);
		return "error_page";
	}
	
	// 404에러 처리
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handler404(NoHandlerFoundException ex) {
		return "custom404";
	}
}
