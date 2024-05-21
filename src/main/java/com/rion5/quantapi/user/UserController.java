package com.rion5.quantapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rion5.quantapi.jwt.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;

//@CrossOrigin(origins = { "https://rion5.kro.kr", "http://localhost:4200" }, allowedHeaders = "*")

@RestController
@RequestMapping("/user")
public class UserController {
	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

//	@PutMapping(value = "/update")
//	public int updateUser(@RequestBody User user) {
//		return userService.updateUser(user);
//	}
//
//	@DeleteMapping(value = "/{id}")
//	public int deleteUser(@PathVariable int id) {
//		return userService.deleteUser(id);
//	}

	@PutMapping(value = "/update")
	public ResponseEntity<Integer> updateUser(@RequestBody User user, HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")
				&& jwtTokenProvider.validateToken(bearerToken.substring(7))) {
			return ResponseEntity.ok(userService.updateUser(user));

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable int id, HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")
				&& jwtTokenProvider.validateToken(bearerToken.substring(7))) {
			return ResponseEntity.ok(userService.deleteUser(id));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
