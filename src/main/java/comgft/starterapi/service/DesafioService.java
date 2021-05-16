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
import comgft.starterapi.model.Desafio;
import comgft.starterapi.repository.DesafioRepository;

@Service
public class DesafioService {
	
	@Autowired
	private DesafioRepository desafios;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Desafio> getAll() {
		return desafios.findAll();
	}

	public Desafio getById(Long id) {
		
		Optional<Desafio> desafioSaved = desafios.findById(id);
		
		if (desafioSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return desafioSaved.get();
	}

	public Desafio save(Desafio desafio, HttpServletResponse response) {

		Desafio desafioSaved = desafios.save(desafio);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, desafio.getId()));
		
		return desafioSaved;
	}

	public void delete(Long id) {
		desafios.deleteById(id);
	}

	public Desafio update(Long id, Desafio desafio) {

		Desafio desafioSaved = getById(id);
		
		BeanUtils.copyProperties(desafio, desafioSaved, "id");
				
		return desafios.save(desafioSaved);
	}

}
