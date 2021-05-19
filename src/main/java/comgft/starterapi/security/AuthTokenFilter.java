package comgft.starterapi.security;

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
import org.springframework.web.filter.OncePerRequestFilter;

import comgft.starterapi.service.TokenService;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AutenticationService autenticationService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = parseToken(request);
			
			boolean tokenValid = tokenService.validate(token);
			
			if (tokenValid) {
				authenticateUser(token);
			}
		} catch(Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String parseToken(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
			return null;
		}
		
		return authHeader.substring(7, authHeader.length());
	}
	
	private void authenticateUser(String token) {
		String username = tokenService.getUsername(token);		
		UserDetails userDetails = autenticationService.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());	
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
