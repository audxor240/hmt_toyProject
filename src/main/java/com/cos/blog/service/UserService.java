package com.cos.blog.service;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IOC를 해준다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();	//원문 패스워드
		String encPassword = encoder.encode(rawPassword);	//해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
	}
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();	//빈 객체 전달
		});
		return user;
	}
	
	@Transactional
	public void 회원정보수정(int id, User requestUser) {
		User user = userRepository.findById(id)
				.orElseThrow(()->{
					return	new IllegalArgumentException("회원 정보 찾기 실패 : 아이디를 찾을수 없습니다.");
				});	//영속화 완료 (DB의 데이터를 가져온다)
		if(requestUser.getOauth() == null || requestUser.getOauth().equals("")) {
			String rawPassword = requestUser.getPassword();	//원문 패스워드
			String encPassword = encoder.encode(rawPassword);	//해쉬
			user.setPassword(encPassword);
			user.setEmail(requestUser.getEmail());
		}
		
	
	}
	
}
