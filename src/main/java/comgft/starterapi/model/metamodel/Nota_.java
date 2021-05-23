package comgft.starterapi.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import comgft.starterapi.model.Nota;
import comgft.starterapi.model.Submissao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Nota.class)
public abstract class Nota_ {

	public static volatile SingularAttribute<Nota, Integer> gradeQuantityDelivered;
	public static volatile SingularAttribute<Nota, Integer> gradeCodeQuality;
	public static volatile SingularAttribute<Nota, Long> id;
	public static volatile SingularAttribute<Nota, Submissao> submissao;

	public static final String GRADE_QUANTITY_DELIVERED = "gradeQuantityDelivered";
	public static final String GRADE_CODE_QUALITY = "gradeCodeQuality";
	public static final String ID = "id";
	public static final String SUBMISSAO = "submissao";

}

