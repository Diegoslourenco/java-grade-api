package comgft.starterapi.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import comgft.starterapi.event.ResourceCreatedEvent;
import comgft.starterapi.exceptionhandler.SubmissaoNotEqualException;
import comgft.starterapi.exceptionhandler.SubmissaoNotUniqueException;
import comgft.starterapi.model.Nota;
import comgft.starterapi.model.Submissao;
import comgft.starterapi.repository.NotaRepository;
import comgft.starterapi.repository.filter.NotaFilter;
import comgft.starterapi.resources.NotaResource;

@Service
public class NotaService {
	
	@Autowired
	private NotaRepository notas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Nota> search(NotaFilter filter) {
		return mapToResourceCollection(notas.filter(filter));
	}
	
	public Nota getOne(Long id) {
		return mapToResource(getById(id));
	}

	public Nota save(Nota nota, HttpServletResponse response) {
		
		if (!checkUniqueSubmissao(nota.getSubmissao())) {
			throw new SubmissaoNotUniqueException();
		}
		
		Nota notaSaved = notas.save(nota);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, notaSaved.getId()));
		
		return mapToResource(notaSaved);
	}

	public void delete(Long id) {
		notas.deleteById(id);
	}

	public Nota update(Long id, Nota nota) {
		
		Nota notaSaved = getById(id);
		
		if (!notaSaved.getSubmissao().equals(nota.getSubmissao())) {
			throw new SubmissaoNotEqualException();
		}
		
		BeanUtils.copyProperties(nota, notaSaved, "id");
		
		return mapToResource(notas.save(notaSaved));
	}
	
	public Nota getById(Long id) {
		
		Optional<Nota> notaSaved = notas.findById(id);
		
		if (notaSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return notaSaved.get();
	}
	
	public boolean checkUniqueSubmissao(Submissao submissao) {
		
		List<Nota> allNotas = notas.findAll();
		
		for (Nota nota : allNotas) {
			if (nota.getSubmissao().equals(submissao)) {			
				return false;
			}
		}
		
		return true;
	}
	
	private Nota mapToResource(Nota nota) {
		
		nota.add(linkTo(methodOn(NotaResource.class)
				.getOne(nota.getId()))
				.withSelfRel());
		
		nota.add(linkTo(methodOn(NotaResource.class)
				.update(nota.getId(), nota))
				.withRel("update"));
		
		nota.add(linkTo(methodOn(NotaResource.class)
				.delete(nota.getId()))
				.withRel("delete"));
		
		nota.add(linkTo(methodOn(NotaResource.class)
				.search(null))
				.withRel("Lista de Notas"));
		
		return nota;
	}
	
	private List<Nota> mapToResourceCollection(List<Nota> allNotas) {
		
		for (Nota nota : allNotas) {
			
			nota.add(linkTo(methodOn(NotaResource.class)
					.getOne(nota.getId()))
					.withSelfRel());
			
			nota.add(linkTo(methodOn(NotaResource.class)
					.update(nota.getId(), nota))
					.withRel("update"));
			
			nota.add(linkTo(methodOn(NotaResource.class)
					.delete(nota.getId()))
					.withRel("delete"));
		}	

		return allNotas;
	}

}
