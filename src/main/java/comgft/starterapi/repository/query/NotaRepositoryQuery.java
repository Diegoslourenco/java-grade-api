package comgft.starterapi.repository.query;

import java.util.List;

import comgft.starterapi.model.Nota;
import comgft.starterapi.repository.filter.NotaFilter;

public interface NotaRepositoryQuery {

	public List<Nota> filter(NotaFilter filter);
	
}
