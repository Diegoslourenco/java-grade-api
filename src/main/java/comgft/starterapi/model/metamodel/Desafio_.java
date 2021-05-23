package comgft.starterapi.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import comgft.starterapi.model.Desafio;
import comgft.starterapi.model.Submissao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Desafio.class)
public abstract class Desafio_ {

	public static volatile ListAttribute<Desafio, Submissao> submissoes;
	public static volatile SingularAttribute<Desafio, String> name;
	public static volatile SingularAttribute<Desafio, Long> id;

	public static final String SUBMISSOES = "submissoes";
	public static final String NAME = "name";
	public static final String ID = "id";

}

