package comgft.starterapi.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comgft.starterapi.model.Submissao;
import comgft.starterapi.service.SubmissaoService;

@RestController
@RequestMapping("/submissoes")
public class SubmissaoResource {
	
	@Autowired
	private SubmissaoService submissaoService;
	
	@GetMapping
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<List<Submissao>> getAll() {
		return new ResponseEntity<List<Submissao>>(submissaoService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<Submissao> getOne(@PathVariable Long id) {
		return new ResponseEntity<Submissao>(submissaoService.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Submissao> create(@Valid @RequestBody Submissao submissao, HttpServletResponse response) {
		return new ResponseEntity<Submissao>(submissaoService.save(submissao, response), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		submissaoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<Submissao> update(@PathVariable Long id, @Valid @RequestBody Submissao submissao) {			
		return new ResponseEntity<Submissao>(submissaoService.update(id, submissao), HttpStatus.OK);
	}
	
}
