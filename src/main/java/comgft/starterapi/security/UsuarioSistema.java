package comgft.starterapi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSistema extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String email;
	
	public UsuarioSistema(String name, String email, String username, String password,
							Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
		this.name = name;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
}
