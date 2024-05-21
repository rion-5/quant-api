package com.rion5.quantapi.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rion5.quantapi.jwt.JwtTokenProvider;
import com.rion5.quantapi.user.User;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

// @PostMapping(value = "login")
//	public Login loginCheck(@RequestBody LoginParam param) {
//		return loginService.loginCheck(param.getEmail(), param.getPassword());
//	}

//	@PostMapping("/login")
//	public ResponseEntity<TokenDto> loginCheck(@RequestBody LoginParam param) {
//		Login login = loginService.loginCheck(param.getEmail(), param.getPassword());
//		User user = login.getUser();
//
//		if (login.getSuccess()) {
//
//			String token = jwtTokenProvider.createToken(user);
//			System.out.println(token);
//			TokenDto tokenDto = new TokenDto();
//			tokenDto.setToken(token);
//			return ResponseEntity.ok(tokenDto);
//		} else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//
//	}

	@PostMapping("/login")
	public Login loginCheck(@RequestBody LoginParam param) {
		Login login = loginService.loginCheck(param.getEmail(), param.getPassword());
		User user = login.getUser();
		if (login.getSuccess()) {
			String token = jwtTokenProvider.createToken(user);
			login.setToken(token);
		}
		return login;
	}
}
