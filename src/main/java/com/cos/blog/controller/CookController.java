package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.CookService;

@Controller
public class CookController {
	
	@Autowired
	private CookService cookService;
	
	@GetMapping("/cook/mainForm")
	public String mainForm(Model model,@PageableDefault(size = 3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("cooks",cookService.쿡목록(pageable));
		return "/cook/mainForm";
	}
	
	@GetMapping("/cook/cookWriteForm")
	public String saveForm() {
		return "/cook/cookWriteForm";
	}
	
	@GetMapping("/cook/{id}")
	public String findById(@PathVariable int id, Model model) {
		cookService.쿡상세보기(id);
		model.addAttribute("cooks",cookService.쿡상세보기(id));
		return "cook/detail";
	}
	
}
