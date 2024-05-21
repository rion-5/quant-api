package com.rion5.quantapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserDao userDao;

	public int deleteUser(int id) {
		return userDao.deleteUser(id);
	}

	public int updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userDao.updateUser(user);
	}
}
