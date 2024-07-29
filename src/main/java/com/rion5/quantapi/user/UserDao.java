package com.rion5.quantapi.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import org.springframework.lang.NonNull;

@Repository
public class UserDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplete;
	@NonNull
	KeyHolder keyHolder = new GeneratedKeyHolder();

	public User newUser(User user) {
		String query = """
                INSERT INTO users (name,email,password,created_date,activation) \
                VALUES (:name,:email,:password,:created_date,:activation); \
                """;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", user.getName());
		param.addValue("email", user.getEmail());
		param.addValue("password", user.getPassword());
		param.addValue("created_date", user.getCreated_date());
		param.addValue("activation", user.isActivation());

		int affectedRow = namedParameterJdbcTemplete.update(query, param, keyHolder);
		System.out.println("Inserted " + affectedRow + "row(s).");

//		int id = (int) keyHolder.getKeys().get("id");
		int id = (int) extracted().get("id");
		user.setId(id);

		return user;
	}

	private Map<String, Object> extracted() {
		return keyHolder.getKeys();
	}

	public List<User> getUser(int id) {
		String query = "SELECT ID,name,email,password,created_date,activation FROM USERS WHERE ID = :id ; ";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);

		List<User> userList = namedParameterJdbcTemplete.query(query, param, new UserRowMapper());
		return userList;
	}

	public int deleteUser(int id) {
		String query = "DELETE FROM USERS WHERE ID = :id ; ";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);

		int affectedRow = namedParameterJdbcTemplete.update(query, param, keyHolder);
		System.out.println("Deleted " + affectedRow + "row(s).");
//		int deletedId = (int) keyHolder.getKeys().get("id");

		int deletedId = (int) extracted().get("id");
		return deletedId;
	}

	public int updateUser(User user) {
		String query = """
                UPDATE USERS SET NAME=:name,EMAIL=:email,PASSWORD=:password \
                ,CREATED_DATE=:created_date, ACTIVATION=:activation WHERE id=:id; \
                """;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", user.getId());
		param.addValue("name", user.getName());
		param.addValue("email", user.getEmail());
		param.addValue("password", user.getPassword());
		param.addValue("created_date", user.getCreated_date());
		param.addValue("activation", user.isActivation());

		int affectedRow = namedParameterJdbcTemplete.update(query, param, keyHolder);
		System.out.println("Updated " + affectedRow + "row(s).");
//		int updatedId = (int) keyHolder.getKeys().get("id");

		int updatedId = (int) extracted().get("id");
		return updatedId;
	}

	public User getUserOne(int id) {
		String query = "SELECT ID,name,email,password,created_date,activation FROM USERS WHERE ID = :id ; ";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);

		User user = (User) namedParameterJdbcTemplete.query(query, param, new UserRowMapper());
		return user;
	}

	public List<User> loginCheck(String email) {
		String query = "SELECT ID,name,email,password,created_date,activation FROM USERS WHERE email = :email ; ";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("email", email);

		List<User> userList = namedParameterJdbcTemplete.query(query, param, new UserRowMapper());
		return userList;
	}
}
