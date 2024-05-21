package com.rion5.quantapi.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rion5.quantapi.user.User;
import com.rion5.quantapi.user.UserDao;

@Service
public class LoginService {
	@Autowired
	UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Login loginCheck(String email, String password) {
		Login login = new Login();

		try {
			List<User> userList = userDao.loginCheck(email);
			User user = userList.get(0);

			if (passwordEncoder.matches(password, user.getPassword())) {
				login.setSuccess(true);
				login.setMessage("Login was successful.");
			} else {
				login.setSuccess(false);
				login.setMessage("Password dose not match!");
			}
			user.setPassword(null);
			login.setUser(user);

			return login;
		} catch (IndexOutOfBoundsException ie) {
			login.setSuccess(false);
			login.setMessage("Login failed.");
//			login.setMessage(ie.toString());
			return login;
		} catch (Exception e) {
			login.setSuccess(false);
			login.setMessage(e.toString());
			return login;
		}
	}
}
