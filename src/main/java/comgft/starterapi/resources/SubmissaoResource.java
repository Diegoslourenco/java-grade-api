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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/submissoes")
@Api(tags =  {"submissoes"})
public class SubmissaoResource {
	
	@Autowired
	private SubmissaoService submissaoService;
	
	@ApiOperation(value="Retorna uma lista de submissões")
	@GetMapping
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<List<Submissao>> getAll() {
		return new ResponseEntity<List<Submissao>>(submissaoService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value="Retorna ums submissão única")
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<Submissao> getOne(@PathVariable Long id) {
		return new ResponseEntity<Submissao>(submissaoService.getOne(id), HttpStatus.OK);
	}
	
	@ApiOperation(value="Cria uma submissão")
	@PostMapping
	public ResponseEntity<Submissao> create(@Valid @RequestBody Submissao submissao, HttpServletResponse response) {
		return new ResponseEntity<Submissao>(submissaoService.save(submissao, response), HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Deleta uma submissão")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		submissaoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Atualiza uma submissão")
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('INSTRUTOR')")
	public ResponseEntity<Submissao> update(@PathVariable Long id, @Valid @RequestBody Submissao submissao) {			
		return new ResponseEntity<Submissao>(submissaoService.update(id, submissao), HttpStatus.OK);
	}
	
}
