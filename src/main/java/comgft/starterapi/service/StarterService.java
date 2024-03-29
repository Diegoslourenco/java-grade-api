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
import comgft.starterapi.exceptionhandler.StarterEmailNotUniqueException;
import comgft.starterapi.exceptionhandler.StarterUsernameNotUniqueException;
import comgft.starterapi.model.Starter;
import comgft.starterapi.repository.StarterRepository;
import comgft.starterapi.repository.filter.StarterFilter;
import comgft.starterapi.resources.StarterResource;

@Service
public class StarterService {
	
	@Autowired
	private StarterRepository starters;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Starter> search(StarterFilter filter) {	
		return mapToResourceCollection(starters.filter(filter));
	}
	
	public Starter getOne(Long id) {
		return mapToResource(getById(id));
	}

	public Starter save(Starter starter, HttpServletResponse response) {
		
		if (!checkUniqueUsername(starter)) {
			throw new StarterUsernameNotUniqueException();
		}
		
		if (!checkUniqueEmail(starter)) {
			throw new StarterEmailNotUniqueException();
		}
			
		Starter starterSaved = starters.save(starter);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, starterSaved.getId()));
		
		return mapToResource(starterSaved);
	}

	public void delete(Long id) {
		starters.deleteById(id);
	}

	public Starter update(Long id, Starter starter) {
		
		if (!checkUniqueUsername(starter)) {
			throw new StarterUsernameNotUniqueException();
		}
		
		if (!checkUniqueEmail(starter)) {
			throw new StarterEmailNotUniqueException();
		}
		
		Starter starterSaved = getById(id);
		
		/* Pass the data from starter that comes from client to starterSaved that comes from database
		 * ignoring the id that is the same in the url
		 */
		BeanUtils.copyProperties(starter, starterSaved, "id");
		
		return mapToResource(starters.save(starterSaved));
	}
	
	private boolean checkUniqueEmail(Starter novoStarter) {
		List<Starter> allStarters = starters.findAll();
		
		for (Starter starter : allStarters) {
			
			if (starter.getEmail().equals(novoStarter.getEmail())) {
				return false;
			}	
		}
		
		return true;	
	}
	
	private boolean checkUniqueUsername(Starter novoStarter) {
		List<Starter> allStarters = starters.findAll();
		
		for (Starter starter : allStarters) {
			
			if (starter.getUsername().equals(novoStarter.getUsername())) {
				return false;
			}	
		}
		
		return true;	
	}
	
	private Starter getById(Long id) {
		
		Optional<Starter> starterSaved = starters.findById(id);
		
		if (starterSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return starterSaved.get();
	}
	
	private Starter mapToResource(Starter starter) {
		
		starter.add(linkTo(methodOn(StarterResource.class)
				.getOne(starter.getId()))
				.withSelfRel());
		
		starter.add(linkTo(methodOn(StarterResource.class)
				.update(starter.getId(), starter))
				.withRel("update"));
		
		starter.add(linkTo(methodOn(StarterResource.class)
				.delete(starter.getId()))
				.withRel("delete"));
		
		starter.add(linkTo(methodOn(StarterResource.class)
				.search(null))
				.withRel("Lista de Starters"));
		
		return starter;
	}
	
	private List<Starter> mapToResourceCollection(List<Starter> allStarters) {
		
		for (Starter starter : allStarters) {
			
			starter.add(linkTo(methodOn(StarterResource.class)
					.getOne(starter.getId()))
					.withSelfRel());
			
			starter.add(linkTo(methodOn(StarterResource.class)
					.update(starter.getId(), starter))
					.withRel("update"));
			
			starter.add(linkTo(methodOn(StarterResource.class)
					.delete(starter.getId()))
					.withRel("delete"));
		}	

		return allStarters;
	}

}
