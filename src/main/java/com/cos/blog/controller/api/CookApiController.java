package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.CookDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.service.CookService;

@RestController
public class CookApiController {
	
	@Autowired
	private CookService cookService;
	
	@PostMapping("/api/cook")
	public ResponseDto<Integer> saveCook(@RequestBody CookDto cook, @AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("COOK-----API_Controller----CookDto : "+cook);
		//return new ResponseDto<Integer>(HttpStatus.OK,1);
		cookService.쿡등록(cook,principal.getUser());		//여기서 DB에 insert해준다
		System.out.println("CHECK-----------11");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
}
