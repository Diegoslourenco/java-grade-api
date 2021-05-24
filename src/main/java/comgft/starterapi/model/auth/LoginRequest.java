package comgft.starterapi.model.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * LoginRequest --- represents a login request from a client.
 * @author    Diego da Silva Lourenco
 */

public class LoginRequest {
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}

}
