package comgft.starterapi.model.auth;

/**
 * TokenJwt --- represents a JSON Web Token that corresponds to an authenticated client.
 * @author    Diego da Silva Lourenco
 */

public class TokenJwt {
	
	private String token;
	
	private String type = "Bearer";
	
	private String username;	
	
	public TokenJwt(String token, String username) {
		this.token = token;
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

}
