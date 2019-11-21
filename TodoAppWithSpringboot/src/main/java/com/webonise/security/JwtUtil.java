package com.webonise.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import com.webonise.TodoAppProperties;
import com.webonise.exception.ExpiredJwtFoundExcpetion;
import com.webonise.exception.InvalidTokenFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	@Autowired
	private TodoAppProperties properties;
	
	private Logger log = LoggerFactory.getLogger(JwtUtil.class);
		
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(properties.getSigningKey()).parseClaimsJws(token).getBody();
		} catch (MalformedJwtException ex) {
			log.error("Invalid token found.");
			throw new InvalidTokenFoundException("Invalid token found.");
		} catch (ExpiredJwtException ex) {
			log.error("Expired jwt found");
			throw new ExpiredJwtFoundExcpetion("Expired jwt found.");
		}
		return claims;
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subjet) {
		return Jwts.builder().setClaims(claims).setSubject(subjet).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, properties.getSigningKey()).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
