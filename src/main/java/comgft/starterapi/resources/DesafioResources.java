package comgft.starterapi.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comgft.starterapi.model.Desafio;
import comgft.starterapi.service.DesafioService;

@RestController
@RequestMapping("/desafios")
public class DesafioResources {
	
	@Autowired
	DesafioService desafioService;
	
	@GetMapping
	public ResponseEntity<List<Desafio>> getAll() {
		return new ResponseEntity<List<Desafio>>(desafioService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Desafio> getById(@PathVariable Long id) {
		return new ResponseEntity<Desafio>(desafioService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Desafio> create(@Valid @RequestBody Desafio desafio, HttpServletResponse response) {
		return new ResponseEntity<Desafio>(desafioService.save(desafio, response), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		desafioService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Desafio> update(@PathVariable Long id, @Valid @RequestBody Desafio desafio) {			
		return new ResponseEntity<Desafio>(desafioService.update(id, desafio), HttpStatus.OK);
	}

}
