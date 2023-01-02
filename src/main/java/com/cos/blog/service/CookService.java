package com.cos.blog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.CookDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Cook;
import com.cos.blog.model.CookSub;
import com.cos.blog.model.User;
import com.cos.blog.repository.CookRepository;
import com.cos.blog.repository.CookSubRepository;

@Service
public class CookService {
	@Autowired
	private CookRepository cookRepository;
	
	@Autowired
	private CookSubRepository cookSubRepository;
	
	@Transactional
	public void 쿡등록(CookDto cook, User user) {
		
		Cook cooks = new Cook();
		
		cooks.setCook_kind(cook.getCook_kind());
		cooks.setCook_name(cook.getCook_name());
		cooks.setUser(user);
		
		String gram_str = cook.getGram();
		String material_str = cook.getMaterial();
		
		String[] material_arr = material_str.split(",");
		String[] gram_arr =gram_str.split(",");
		int[] gram_arr_int = Arrays.stream(gram_arr).mapToInt(Integer::parseInt).toArray();
		
		for(int i=0; i < material_arr.length; i++) {
			CookSub cookSub = new CookSub();
			
			String material = material_arr[i];
			int gram = gram_arr_int[i];
			
			cookSub.setGram(gram);
			cookSub.setMaterial(material);
			cookSub.setCook(cooks);
			cooks.addCookSub(cookSub);
		}
			
		cookRepository.save(cooks);
		
	}
	
	@Transactional(readOnly = true)
	public Page<Cook> 쿡목록(Pageable pageable){
		//Page<Board> pagingboard =boardRepository.findAll(pageable);
		//return pagingboard;
		return cookRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Cook  쿡상세보기(int id){
		return cookRepository.findById(id)
				.orElseThrow(()->{
					return	new IllegalArgumentException("쿡 상세보기 실패 : 아이디를 찾을수 없습니다.");
				});
	}
}
