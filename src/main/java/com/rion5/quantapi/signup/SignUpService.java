package com.rion5.quantapi.signup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rion5.quantapi.user.User;
import com.rion5.quantapi.user.UserDao;

@Service
public class SignUpService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserDao userDao;

	public User newUser(User signUp) {
		System.out.println("raw:" + signUp.getPassword());
		signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
//
		System.out.println("encode:" + signUp.getPassword());

		return userDao.newUser(signUp);
	}

	public List<User> getUser(int id) {
		return userDao.getUser(id);
	}

	public User getUserOne(int id) {
		return userDao.getUserOne(id);
	}

	public Boolean emailDuplicateCheck(String email) {

		try {
			List<User> userList = userDao.loginCheck(email);
			System.out.println(userList.get(0).getEmail());
			return (userList.get(0).getEmail().equals(email)) ? true : false;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
