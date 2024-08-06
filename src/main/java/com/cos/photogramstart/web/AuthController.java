package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 필드를 DI(Dependency Injection)할때 사용

@Controller // 1. Ioc  
				  // 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
		private static final Logger log = LoggerFactory.getLogger(AuthController.class);
		
		/*
		 * AuthService 객체를 사용하기 위해 DI(Dependency Injection) 해서 불러온다. DI(Dependency
		 * Injection)을 해서 불러올때 2가지 방법이 있다. 1. @Autowired 어노테이션을 사용한다. 2. AuthController의
		 * 생성자를 만든다.
		 */
		
		// 1. @Autowired
		//private AuthService authService;
		
		
		// 2. 생성자
		// private AuthService authService;
		// public AuthController(AuthService authService) {
		//	this.authService = authService;
		// }
		
		/*
		 * 자바에서 전역변수에 final을 사용하면 오류가 나는데 
		 * final 필드는 무조건 생성자나 객체를 실행할때 초기화를 해줘야한다.
		 * @RequiredArgsConstructor을 어노테이션을 사용하면 
		 * final 필드가 명시되어 있는 모든 부분에 대해 생성자를 만들어준다.
		 */
		 
		private final AuthService authService;
		
		@GetMapping("/auth/signin")
		public String signinForm() {
			
			return "auth/signin";
		}
		
		@GetMapping("/auth/signup")
		public String signupForm() {
			
			return "auth/signup";
		}
		
		// 회원가입 -> /auth/signup -> /auth/signin
		@PostMapping("/auth/signup")
		public String signup(SignupDto signupDto) {
			
			/*
			 * signup.jsp 파일에서 회원가입 form 태그 내 action과 method 속성 action = "/auth/signup"
			 * method = "post" username, password, email, name이라는 name 값이 key가 되고 input box에
			 * 입력된 값은 value가 된다. 데이터가 {key : value} 형식으로 들어오게 되는데 이 방식을
			 * x-www-form-url-encoded라고 한다.
			 */
		
			log.info(signupDto.toString());
			// 문자열만 받을 수 있어서 toString()으로 형변환을 해준다.
			
			
		     
			
			//System.out.println("siginup 살행됨?");
			
			User user = signupDto.toEntity();
			log.info(user.toString());
			
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			
			return "auth/signin";
			// 회원가입을 성공하면 로그인 화면으로 이동
		}
		
	

}

/*
 * 스프링은 IoC 컨테이너로 빈을 관리한다. 스프링은 DI를 사용한다. DI 방법에는 생성자 주입, setter 주입, 필드 주입 등이
 * 있다.
 */
