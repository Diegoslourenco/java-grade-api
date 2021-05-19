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
import comgft.starterapi.exceptionhandler.SubmissaoNotUniqueException;
import comgft.starterapi.model.Nota;
import comgft.starterapi.model.Submissao;
import comgft.starterapi.repository.NotaRepository;

@Service
public class NotaService {
	
	@Autowired
	private NotaRepository notas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Nota> getAll() {
		return notas.findAll();
	}
	
	public Nota getById(Long id) {
		
		Optional<Nota> notaSaved = notas.findById(id);
		
		if (notaSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return notaSaved.get();
	}

	public Nota save(Nota nota, HttpServletResponse response) {
		
		if (!checkUniqueSubmissao(nota.getSubmissao())) {
			throw new SubmissaoNotUniqueException();
		}
		
		Nota notaSaved = notas.save(nota);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, notaSaved.getId()));
		
		return notaSaved;
	}

	public void delete(Long id) {
		notas.deleteById(id);
	}

	public Nota update(Long id, Nota nota) {
		
		if (!checkUniqueSubmissao(nota.getSubmissao())) {
			throw new SubmissaoNotUniqueException();
		}
		
		Nota notaSaved = getById(id);
		
		/* Pass the data from person that comes from client to personSaved that comes from database
		 * ignoring the id that is the same in the url
		 */
		BeanUtils.copyProperties(nota, notaSaved, "id");
		
		return notas.save(notaSaved);
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

}
