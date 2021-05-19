package comgft.starterapi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import comgft.starterapi.model.Usuario;
import comgft.starterapi.repository.UsuarioRepository;

@Component
public class AutenticationService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = usuarios.findByUsername(username);
		
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		Usuario usuarioSaved = usuario.get();
		
		return new UsuarioSistema(usuarioSaved.getName(), usuarioSaved.getEmail(), usuarioSaved.getUsername(), usuarioSaved.getPassword(), auth(usuarioSaved));
	}

	private Collection<? extends GrantedAuthority> auth(Usuario usuario) {
		
		Collection<GrantedAuthority> auths = new ArrayList<>();
		
		auths.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().getAuthority()));
		
		return auths;	
	}

}
