package com.cos.blog.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	Optional<Board> findByTitle(String title);	
}
