package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model; //model 인터페이스를 위한.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service //@Service를 적용한 Class는 비지니스 로직이 들어가는 Service로 등록이 된다. 
@Controller //해당 클래스를 웹 요청을 처리하는 컨트롤러로 사용한다.
@Log4j //log.info 사용 가능하게 해줌.
@RequestMapping("/board/*")  //기본 url 경로를 지정해줌.
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만들어줌.
public class BoardController {

	private BoardService service;
	
	@GetMapping("/list") 
	public void list(Criteria cri, Model model) {
		
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));
		//Model addAttribute(String name, Object value)

		//
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = service.getTotal(cri);
		
		log.info("total: "+total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr ) {
	
		
		
		log.info("register: " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		//RedirectAttributes rttr.addFlashAttribute(이름, 값 )
		//화면에 한번만 사용하고 다음에는 사용되지 않는 데이터를 전달하기 위해 사용.
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		//@RequestParam("가져올 데이터의 이름") 은 파라미터의 값을 가져온다.
		//@ModelAttribute("name") name으로 클래스의 객채를 자동생성, 자동으로 Model 객체에 추가 되고 뷰(view)단으로 전달된다.
		//Model model 객체는 JSP Servlet 에서 처럼 request or session 내장객체에 정보를 담아 넘겨주는것과 비슷한 역할.
		
		
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno)); //Model 객체를 사용하여 view 에 데이터 넘기기.
		// ex) model.addAttribute("key", "value")
		//model.addAttribute("value", "hi")라고 하면 
		//getAttribute("key") 
		//view(jsp)에서 <%= reqeust.getAttribute("value") %>로 받을수 있음.
	}
	
	//수정을 위한 
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("modify:" + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri ,RedirectAttributes rttr) {
		
		log.info("remove...." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
}
