package comgft.starterapi.repository.query;

import java.util.List;

import comgft.starterapi.model.Desafio;
import comgft.starterapi.repository.filter.DesafioFilter;

public interface DesafioRepositoryQuery {
	
	public List<Desafio> filter(DesafioFilter filter);

}
