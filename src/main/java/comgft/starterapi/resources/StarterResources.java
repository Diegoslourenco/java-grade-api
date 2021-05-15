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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import comgft.starterapi.model.Starter;
import comgft.starterapi.service.StarterService;

@RestController
@RequestMapping("/starters")
public class StarterResources {
	
	@Autowired
	StarterService starterService;
	
	@GetMapping
	public List<Starter> getAll() {
		return starterService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Starter> getById(@PathVariable Long id) {
		return ResponseEntity.ok(starterService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Starter> create(@Valid @RequestBody Starter starter, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.CREATED).body(starterService.save(starter, response));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		starterService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Starter> update(@PathVariable Long id, @Valid @RequestBody Starter starter) {			
		return ResponseEntity.ok(starterService.update(id, starter));
	}
}
