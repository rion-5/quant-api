package com.rion5.quantapi.jwt;

import java.util.Date;

import com.rion5.quantapi.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JwtTokenProvider {

	private static final String SECRET = "When-you-believe-in-a-thing,-believe-in-it-all-the-way,-implicitly-and-unquestionable."; //over 32bit																								
	private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

	public String createToken(User user) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 24); // 24시간
		return Jwts.builder()
				.setSubject(user.getName())
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SECRET_KEY) // 키와 알고리즘을 명시적으로 지정
				.compact();
	}

	// public Authentication getAuthentication(String token) {
	// Claims claims =
	// Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
	//
	// UserDetails userDetails =
	// User.builder().username(claims.getSubject()).build();
	//
	// return new UsernamePasswordAuthenticationToken(userDetails, "",
	// userDetails.getAuthorities());
	// }

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder()
					.setSigningKey(SECRET_KEY)
					.build()
					.parseClaimsJws(token);

			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			// 토큰이 유효하지 않은 경우 false 반환
			return false;
		}
	}

}
