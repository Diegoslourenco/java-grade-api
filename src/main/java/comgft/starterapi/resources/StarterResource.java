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

import comgft.starterapi.model.Starter;
import comgft.starterapi.service.StarterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/starters")
@PreAuthorize("hasRole('INSTRUTOR')")
@Api(tags =  {"starters"})
public class StarterResource {
	
	@Autowired
	private StarterService starterService;
	
	@ApiOperation(value="Retorna uma lista de starters")
	@GetMapping
	public ResponseEntity<List<Starter>> getAll() {
		return new ResponseEntity<List<Starter>>(starterService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value="Retorna um starter único")
	@GetMapping("/{id}")
	public ResponseEntity<Starter> getOne(@PathVariable Long id) {
		return new ResponseEntity<Starter>(starterService.getOne(id), HttpStatus.OK);
	}
	
	@ApiOperation(value="Cria um starter")
	@PostMapping
	public ResponseEntity<Starter> create(@Valid @RequestBody Starter starter, HttpServletResponse response) {
		return new ResponseEntity<Starter>(starterService.save(starter, response), HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Deleta um starter")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		starterService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Atualiza um starter")
	@PutMapping("/{id}")
	public ResponseEntity<Starter> update(@PathVariable Long id, @Valid @RequestBody Starter starter) {			
		return new ResponseEntity<Starter>(starterService.update(id, starter), HttpStatus.OK);
	}
	
}
