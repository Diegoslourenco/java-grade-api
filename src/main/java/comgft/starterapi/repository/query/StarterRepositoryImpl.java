package comgft.starterapi.repository.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import comgft.starterapi.model.Starter;
import comgft.starterapi.model.metamodel.Starter_;
import comgft.starterapi.repository.filter.StarterFilter;

public class StarterRepositoryImpl implements StarterRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Starter> filter(StarterFilter filter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Starter> criteria = builder.createQuery(Starter.class);
		
		Root<Starter> root = criteria.from(Starter.class);
		
		Predicate[] predicates = createRestriction(filter, builder, root);;
		criteria.where(predicates);
		
		TypedQuery<Starter> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestriction(StarterFilter filter, CriteriaBuilder builder, Root<Starter> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
			
		if (!StringUtils.isEmpty(filter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get(Starter_.NAME)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getUsername())) {
			predicates.add(builder.like(
					builder.lower(root.get(Starter_.USERNAME)), "%" + filter.getUsername().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get(Starter_.EMAIL)), "%" + filter.getEmail().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getLanguage())) {
			predicates.add(builder.like(
					builder.lower(root.get(Starter_.LANGUAGE)), "%" + filter.getLanguage().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
