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

import comgft.starterapi.model.Nota;
import comgft.starterapi.model.metamodel.Nota_;
import comgft.starterapi.repository.filter.NotaFilter;

public class NotaRepositoryImpl implements NotaRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Nota> filter(NotaFilter filter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Nota> criteria = builder.createQuery(Nota.class);
		
		Root<Nota> root = criteria.from(Nota.class);
		
		Predicate[] predicates = createRestriction(filter, builder, root);;
		criteria.where(predicates);
		
		TypedQuery<Nota> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestriction(NotaFilter filter, CriteriaBuilder builder, Root<Nota> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		System.out.println("\n\n\n" + filter.getGradeCodeQuality() + "\n\n\n");
		System.out.println("\n\n\n" + filter.getGradeQuantityDelivered() + "\n\n\n");
			
		if (filter.getGradeCodeQuality() != 0) {
			predicates.add(
					builder.equal(root.get(Nota_.GRADE_CODE_QUALITY),
							filter.getGradeCodeQuality()));
		}
		else 
		
		if (filter.getGradeQuantityDelivered() != 0) {
			predicates.add(
					builder.equal(root.get(Nota_.GRADE_QUANTITY_DELIVERED),
							filter.getGradeQuantityDelivered()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
