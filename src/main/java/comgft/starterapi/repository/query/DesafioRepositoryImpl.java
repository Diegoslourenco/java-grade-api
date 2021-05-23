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

import comgft.starterapi.model.Desafio;
import comgft.starterapi.model.metamodel.Desafio_;
import comgft.starterapi.repository.filter.DesafioFilter;

public class DesafioRepositoryImpl implements DesafioRepositoryQuery {


	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Desafio> filter(DesafioFilter filter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Desafio> criteria = builder.createQuery(Desafio.class);
		
		Root<Desafio> root = criteria.from(Desafio.class);
		
		Predicate[] predicates = createRestriction(filter, builder, root);;
		criteria.where(predicates);
		
		TypedQuery<Desafio> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestriction(DesafioFilter filter, CriteriaBuilder builder, Root<Desafio> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
			
		if (!StringUtils.isEmpty(filter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get(Desafio_.NAME)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
