package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//컨트롤에서 세션을 어떻게 찾는지?
	//@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"","/"})	
	public String index(Model model,@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards",boardService.글목록(pageable));		//모델의 정보를 index페이지로 들고 이동한다.
		//model.addAttribute("boardsPage",boardService.글페이징(pageable));
		//머지 테스트222
		return "index";	//viewResolver 작동!!
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		boardService.글상세보기(id);
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		boardService.글수정보기(id);
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/mainForm")
	public String mainForm(Model model,@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards",boardService.글목록(pageable));
		return "/board/mainForm";
	}
	
}
