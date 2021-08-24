package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;



@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	// BoardController는 BoardService에 대해 의존적이므로 @AllARgsConstructor를 이용해서 생성자를 만들고 
	// 자동으로 주입하도록 함.
	
	//만일 생성자를 만들지 않을경우 에는 @Setter(onMethod_= {@Autowired})를 이용해서 처리한다.
	
	private BoardService service;
	
	
	
	/*
	 * list()는 나중에 게시물의 목록을 전달해야하므로 Model을 파라미터로 지저하고, 이를 통해
	 * BoardServiceImpl객체의 getList()결과를 담아 전달한다.(addAttribute) 
	 * */
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		
		model.addAttribute("list",service.getList());
	}
	
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		
		log.info("register: " +board);
		
		
		service.register(board);
		
		rttr.addFlashAttribute("result",board.getBno());	//일회성보관 , 얘로 경고창이나 모달창보여주기 처리
		
		return "redirect:/board/list";
	}
	
	//게시물 조회
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {	//화면쪽으로 해당번호의 게시무을 전달해야하므로 Model을 파라미터로 지정.
		
		log.info("/get or /modify");
		model.addAttribute("board",service.get(bno));
	}

	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify :" + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove......" + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
	
	//p238 등록 입력 페이지와 등록처리
	
	
	
	
	/**
	
	  * @Method Name : register
	
	  * @작성일 : 2021. 8. 24.
	
	  * @작성자 : yuju
	
	  * @변경이력 : 
	
	  * @Method 설명 : 입력페이지를 보여주는 역할
	
	
	  */
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	
	
}
