package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		System.out.println("board : "+board);
		boardService.글쓰기(board,principal.getUser());		//여기서 DB에 insert해준다
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		
		boardService.글수정하기(id, board);		//여기서 DB에 update해준다
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.글삭제(id);		//여기서 DB에 delete해준다
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
}
