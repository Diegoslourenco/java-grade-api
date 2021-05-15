package comgft.starterapi.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import comgft.starterapi.event.ResourceCreatedEvent;
import comgft.starterapi.model.Starter;
import comgft.starterapi.repository.StarterRepository;

@Service
public class StarterService {
	
	@Autowired
	StarterRepository starters;
	
	private ApplicationEventPublisher publisher;
	
	public List<Starter> getAll() {
		return starters.findAll();
	}
	
	public Starter getById(Long id) {
		
		Optional<Starter> starterSaved = starters.findById(id);
		
		if (starterSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return starterSaved.get();
	}

	public Starter save(Starter starter, HttpServletResponse response) {
		
		Starter starterSaved = starters.save(starter);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, starterSaved.getId()));
		
		return starterSaved;
	}

	public void delete(Long id) {
		starters.deleteById(id);
	}

	public Starter update(Long id, Starter starter) {
		
		Starter starterSaved = getById(id);
		
		/* Pass the data from person that comes from client to personSaved that comes from database
		 * ignoring the id that is the same in the url
		 */
		BeanUtils.copyProperties(starter, starterSaved, "id");
		
		return starters.save(starterSaved);
	}


}