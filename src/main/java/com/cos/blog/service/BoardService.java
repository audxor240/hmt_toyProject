package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

// 스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IOC를 해준다.
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	
	@Transactional
	public void 글쓰기(Board board, User user) {	//title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
		
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		//Page<Board> pagingboard =boardRepository.findAll(pageable);
		//return pagingboard;
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board  글상세보기(int id){
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return	new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을수 없습니다.");
				});
	}
	
	@Transactional(readOnly = true)
	public Board  글수정보기(int id){
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return	new IllegalArgumentException("글 수정 보기 실패 : 아이디를 찾을수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return	new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을수 없습니다.");
				});	//영속화 완료 (DB의 데이터를 가져온다)
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수로 종료시(Service)가 종료될때 트랜잭션이 종료된다. 이떄 더티체킹 - 자동업데이트가 flush
	}
/*
	public Page<Board> 글페이징(Pageable pageable){
		Page<Board> pagingboard =boardRepository.findAll(pageable);
		return pagingboard;
	}
*/	
}
