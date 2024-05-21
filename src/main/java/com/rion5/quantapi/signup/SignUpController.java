package com.rion5.quantapi.signup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rion5.quantapi.user.User;

@RestController

public class SignUpController {

	@Autowired
	private SignUpService signUpService;

	@PostMapping(value = "signup")
	public User saveSignUp(@RequestBody User user) {
		return signUpService.newUser(user);
	}

	@GetMapping(value = "signup/{id}/select")
	public List<User> getSignUp(@PathVariable int id) {
		return signUpService.getUser(id);
	}

	@GetMapping(value = "signup/{id}")
	public User getSignUpOne(@PathVariable int id) {
		return signUpService.getUserOne(id);
	}

	@GetMapping(value = "email/{email}")
	public Boolean emailDuplicateCheck(@PathVariable String email) {
		return signUpService.emailDuplicateCheck(email);
	}
}
