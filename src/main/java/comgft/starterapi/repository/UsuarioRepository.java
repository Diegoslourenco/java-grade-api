package comgft.starterapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import comgft.starterapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByUsername(String username);

}
