package comgft.starterapi.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comgft.starterapi.model.auth.LoginRequest;
import comgft.starterapi.model.auth.TokenJwt;
import comgft.starterapi.service.TokenService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenJwt> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		try {
			Authentication authentication = authenticationManager.authenticate(loginRequest.convert());
			
			String token = tokenService.generateToken(authentication);
			
			return ResponseEntity.ok(new TokenJwt(token, loginRequest.getUsername()));
		
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	

}
