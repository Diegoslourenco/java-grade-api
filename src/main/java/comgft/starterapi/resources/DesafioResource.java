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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import comgft.starterapi.model.Desafio;
import comgft.starterapi.service.DesafioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/desafios")
@PreAuthorize("hasRole('INSTRUTOR')")
@Api(tags =  {"desafios"})
@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Resource not found")
})
public class DesafioResource {
	
	@Autowired
	DesafioService desafioService;
	
	@ApiOperation(value="Retorna uma lista de desafios")	
	@GetMapping
	public ResponseEntity<List<Desafio>> getAll() {
		return new ResponseEntity<List<Desafio>>(desafioService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value="Retorna um desafio único")
	@GetMapping("/{id}")
	public ResponseEntity<Desafio> getOne(@PathVariable Long id) {
		return new ResponseEntity<Desafio>(desafioService.getOne(id), HttpStatus.OK);
	}
	
	@ApiOperation(value="Cria um desafio")
	@ResponseStatus(value = HttpStatus.CREATED) // swagger library can get the 201 code instead of the 200 default one
	@PostMapping
	public ResponseEntity<Desafio> create(@Valid @RequestBody Desafio desafio, HttpServletResponse response) {
		return new ResponseEntity<Desafio>(desafioService.save(desafio, response), HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value="Deleta um desafio")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // swagger library can get the 204 code instead of the 200 default one
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		desafioService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Atualiza um desafio")
	@PutMapping("/{id}")
	public ResponseEntity<Desafio> update(@PathVariable Long id, @Valid @RequestBody Desafio desafio) {			
		return new ResponseEntity<Desafio>(desafioService.update(id, desafio), HttpStatus.OK);
	}

}
