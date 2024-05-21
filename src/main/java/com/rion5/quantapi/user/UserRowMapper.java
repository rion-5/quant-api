package com.rion5.quantapi.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

public class UserRowMapper implements RowMapper<User> {

	@Override
	@NonNull
	public User mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));

		user.setCreated_date(rs.getDate("created_date").toLocalDate());
		user.setActivation(rs.getBoolean("activation"));

		return user;
	}

}
