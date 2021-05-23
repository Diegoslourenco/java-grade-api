package comgft.starterapi.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import comgft.starterapi.model.Desafio;
import comgft.starterapi.model.Nota;
import comgft.starterapi.model.Starter;
import comgft.starterapi.model.Submissao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Submissao.class)
public abstract class Submissao_ {

	public static volatile SingularAttribute<Submissao, Starter> starter;
	public static volatile SingularAttribute<Submissao, Desafio> desafio;
	public static volatile SingularAttribute<Submissao, Long> id;
	public static volatile SingularAttribute<Submissao, Nota> nota;
	public static volatile SingularAttribute<Submissao, String> repositoryUrl;

	public static final String STARTER = "starter";
	public static final String DESAFIO = "desafio";
	public static final String ID = "id";
	public static final String NOTA = "nota";
	public static final String REPOSITORY_URL = "repositoryUrl";

}

