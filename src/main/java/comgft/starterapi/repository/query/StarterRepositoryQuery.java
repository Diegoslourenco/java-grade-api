package comgft.starterapi.repository.query;

import java.util.List;

import comgft.starterapi.model.Starter;
import comgft.starterapi.repository.filter.StarterFilter;

public interface StarterRepositoryQuery {
	
	public List<Starter> filter(StarterFilter filter);

}
