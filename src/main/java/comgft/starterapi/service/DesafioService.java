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
import comgft.starterapi.model.Desafio;
import comgft.starterapi.repository.DesafioRepository;
import comgft.starterapi.resources.DesafioResource;

@Service
public class DesafioService {
	
	@Autowired
	private DesafioRepository desafios;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Desafio> getAll() {
		return mapToResourceCollection(desafios.findAll());
	}
	
	public Desafio getOne(Long id) {
		return mapToResource(getById(id));
	}

	public Desafio save(Desafio desafio, HttpServletResponse response) {

		Desafio desafioSaved = desafios.save(desafio);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, desafio.getId()));
		
		return mapToResource(desafioSaved);
	}

	public void delete(Long id) {
		desafios.deleteById(id);
	}

	public Desafio update(Long id, Desafio desafio) {

		Desafio desafioSaved = getById(id);
		
		BeanUtils.copyProperties(desafio, desafioSaved, "id");
				
		return mapToResource(desafios.save(desafioSaved));
	}
	
	public Desafio getById(Long id) {
		
		Optional<Desafio> desafioSaved = desafios.findById(id);
		
		if (desafioSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return desafioSaved.get();
	}
	
	private Desafio mapToResource(Desafio desafio) {
		
		desafio.add(linkTo(methodOn(DesafioResource.class)
				.getOne(desafio.getId()))
				.withSelfRel());
		
		desafio.add(linkTo(methodOn(DesafioResource.class)
				.update(desafio.getId(), desafio))
				.withRel("update"));
		
		desafio.add(linkTo(methodOn(DesafioResource.class)
				.delete(desafio.getId()))
				.withRel("delete"));
		
		desafio.add(linkTo(methodOn(DesafioResource.class)
				.getAll())
				.withRel("Lista de Desafios"));
		
		return desafio;
	}
	
	private List<Desafio> mapToResourceCollection(List<Desafio> allDesafios) {
		
		for (Desafio desafio : allDesafios) {
			
			desafio.add(linkTo(methodOn(DesafioResource.class)
					.getOne(desafio.getId()))
					.withSelfRel());
			
			desafio.add(linkTo(methodOn(DesafioResource.class)
					.update(desafio.getId(), desafio))
					.withRel("update"));
			
			desafio.add(linkTo(methodOn(DesafioResource.class)
					.delete(desafio.getId()))
					.withRel("delete"));
		}	

		return allDesafios;
	}

}
