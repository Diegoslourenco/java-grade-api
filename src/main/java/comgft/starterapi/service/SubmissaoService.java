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
import comgft.starterapi.model.Submissao;
import comgft.starterapi.repository.SubmissaoRepository;
import comgft.starterapi.resources.SubmissaoResource;

@Service
public class SubmissaoService {
	
	@Autowired
	private SubmissaoRepository submissoes;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Submissao> getAll() {
		return mapToResourceCollection(submissoes.findAll());
	}
	
	public Submissao getOne(Long id) {
		return mapToResource(getById(id));
	}
	
	public Submissao save(Submissao submissao, HttpServletResponse response) {
		
		Submissao submissaoSaved = submissoes.save(submissao);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, submissaoSaved.getId()));
		
		return mapToResource(submissaoSaved);
	}

	public void delete(Long id) {
		submissoes.deleteById(id);
	}

	public Submissao update(Long id, Submissao submissao) {
		
		Submissao submissaoSaved = getById(id);

		BeanUtils.copyProperties(submissao, submissaoSaved, "id");
		
		return mapToResource(submissoes.save(submissaoSaved));
	}
	
	public Submissao getById(Long id) {
		
		Optional<Submissao> submissaoSaved = submissoes.findById(id);
		
		if (submissaoSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return submissaoSaved.get();
	}
	
	private Submissao mapToResource(Submissao submissao) {
		
		submissao.add(linkTo(methodOn(SubmissaoResource.class)
				.getOne(submissao.getId()))
				.withSelfRel());
		
		submissao.add(linkTo(methodOn(SubmissaoResource.class)
				.update(submissao.getId(), submissao))
				.withRel("update"));
		
		submissao.add(linkTo(methodOn(SubmissaoResource.class)
				.delete(submissao.getId()))
				.withRel("delete"));
		
		submissao.add(linkTo(methodOn(SubmissaoResource.class)
				.getAll())
				.withRel("Lista de Submissões"));
		
		return submissao;
	}
	
	private List<Submissao> mapToResourceCollection(List<Submissao> allSubmissoes) {
		
		for (Submissao submissao : allSubmissoes) {
			
			submissao.add(linkTo(methodOn(SubmissaoResource.class)
					.getOne(submissao.getId()))
					.withSelfRel());
			
			submissao.add(linkTo(methodOn(SubmissaoResource.class)
					.update(submissao.getId(), submissao))
					.withRel("update"));
			
			submissao.add(linkTo(methodOn(SubmissaoResource.class)
					.delete(submissao.getId()))
					.withRel("delete"));
		}	

		return allSubmissoes;
	}

}
