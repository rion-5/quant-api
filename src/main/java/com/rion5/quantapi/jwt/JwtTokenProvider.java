package com.rion5.quantapi.jwt;

import java.util.Date;

import com.rion5.quantapi.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenProvider {

	private static final String SECRET = "Thanks-for-being-you.";
//	private static final String TOKEN_PREFIX = "Bearer ";
//	private static final String AUTHORIZATION_HEADER = "Authorization";

	public String createToken(User user) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 24); // 24시간 
		return Jwts.builder().setSubject(user.getName()).setIssuedAt(now).setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
	}

//	public Authentication getAuthentication(String token) {
//		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
//
//		UserDetails userDetails = User.builder().username(claims.getSubject()).build();
//
//		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token);

			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

}
