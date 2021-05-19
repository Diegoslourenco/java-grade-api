package comgft.starterapi.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import comgft.starterapi.security.UsuarioSistema;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
	
	@Value("${starter.jwt.expiration}")
	private Long expiration;
	
	@Value("${starter.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		
		UsuarioSistema usuarioLogged = (UsuarioSistema) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(usuarioLogged.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
		
		return false;		
	}

	public String getUsername(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
}
