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

import comgft.starterapi.model.Nota;
import comgft.starterapi.repository.filter.NotaFilter;
import comgft.starterapi.service.NotaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/notas")
@PreAuthorize("hasRole('INSTRUTOR')")
@Api(tags =  {"notas"})
@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Resource not found")
})
public class NotaResource {
	
	@Autowired
	private NotaService notaService;
	
	@ApiOperation(value="Retorna uma lista de notas")
	@GetMapping
	public ResponseEntity<List<Nota>> search(NotaFilter filter) {
		return new ResponseEntity<List<Nota>>(notaService.search(filter), HttpStatus.OK);
	}
	
	@ApiOperation(value="Retorna uma nota única")
	@GetMapping("/{id}")
	public ResponseEntity<Nota> getOne(@PathVariable Long id) {
		return new ResponseEntity<Nota>(notaService.getOne(id), HttpStatus.OK);
	}
	
	@ApiOperation(value="Cria uma nota")
	@ResponseStatus(value = HttpStatus.CREATED) // swagger library can get the 201 code instead of the 200 default one
	@PostMapping
	public ResponseEntity<Nota> create(@Valid @RequestBody Nota nota, HttpServletResponse response) {
		return new ResponseEntity<Nota>(notaService.save(nota, response), HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Deleta uma nota")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // swagger library can get the 204 code instead of the 200 default one
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		notaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Atualiza uma nota")
	@PutMapping("/{id}")
	public ResponseEntity<Nota> update(@PathVariable Long id, @Valid @RequestBody Nota nota) {			
		return new ResponseEntity<Nota>(notaService.update(id, nota), HttpStatus.OK);
	}
	
}
	
