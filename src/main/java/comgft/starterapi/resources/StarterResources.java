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

import comgft.starterapi.model.Starter;
import comgft.starterapi.service.StarterService;

@RestController
@RequestMapping("/starters")
public class StarterResources {
	
	@Autowired
	StarterService starterService;
	
	@GetMapping
	public ResponseEntity<List<Starter>> getAll() {
		return new ResponseEntity<List<Starter>>(starterService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Starter> getById(@PathVariable Long id) {
		return new ResponseEntity<Starter>(starterService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Starter> create(@Valid @RequestBody Starter starter, HttpServletResponse response) {
		return new ResponseEntity<Starter>(starterService.save(starter, response), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		starterService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Starter> update(@PathVariable Long id, @Valid @RequestBody Starter starter) {			
		return new ResponseEntity<Starter>(starterService.update(id, starter), HttpStatus.OK);
	}
	
}
