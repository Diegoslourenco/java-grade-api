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
import comgft.starterapi.model.Submissao;
import comgft.starterapi.repository.SubmissaoRepository;

@Service
public class SubmissaoService {
	
	@Autowired
	private SubmissaoRepository submissoes;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Submissao> getAll() {
		return submissoes.findAll();
	}
	
	public Submissao getById(Long id) {
		
		Optional<Submissao> submissaoSaved = submissoes.findById(id);
		
		if (submissaoSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return submissaoSaved.get();
	}

	public Submissao save(Submissao submissao, HttpServletResponse response) {
		
		Submissao submissaoSaved = submissoes.save(submissao);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, submissaoSaved.getId()));
		
		return submissaoSaved;
	}

	public void delete(Long id) {
		submissoes.deleteById(id);
	}

	public Submissao update(Long id, Submissao submissao) {
		
		Submissao submissaoSaved = getById(id);
		
		/* Pass the data from person that comes from client to personSaved that comes from database
		 * ignoring the id that is the same in the url
		 */
		BeanUtils.copyProperties(submissao, submissaoSaved, "id");
		
		return submissoes.save(submissaoSaved);
	}

}
