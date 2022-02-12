package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> saveBoard(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.글쓰기(board,principal.getUser());		//여기서 DB에 insert해준다
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	/*
	@PostMapping("/api/board_delete")
	public ResponseDto<Integer> deleteBoard(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.글삭제(board,principal.getUser());		//여기서 DB에 insert해준다
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	*/
}
