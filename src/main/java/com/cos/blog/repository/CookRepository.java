package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Cook;

public interface CookRepository extends JpaRepository<Cook, Integer> {
	//Optional<Cook> findByCookName(String cook_name);
}
