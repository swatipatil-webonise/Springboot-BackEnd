package com.webonise.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.webonise.exception.UnauthorizedUserFoundException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	private Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authenticationHeader = request.getHeader("Authorization");
		if ((request.getRequestURL().toString().matches("(.*)/todo-jobs/[0-9](.*)") || request.getRequestURL().toString().matches("(.*)/todo-jobs/(.*)")) && authenticationHeader == null && !request.getMethod().contains("OPTIONS")) {
				log.error("Unauthorized user found.");
				throw new UnauthorizedUserFoundException("Unauthorized user found.");
		} else {
			if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
				final int JWT_TOKEN_START = 7;
				String jwt = authenticationHeader.substring(JWT_TOKEN_START), username = jwtUtil.extractUsername(jwt);
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
					if (jwtUtil.validateToken(jwt, userDetails)) {
						UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
								null, userDetails.getAuthorities());
						token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(token);
					}
				}
			}
			filterChain.doFilter(request, response);
		}
	}
}
