package comgft.starterapi.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import comgft.starterapi.model.Address;
import comgft.starterapi.model.Starter;
import comgft.starterapi.model.Submissao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Starter.class)
public abstract class Starter_ {

	public static volatile SingularAttribute<Starter, Address> address;
	public static volatile SingularAttribute<Starter, String> phone;
	public static volatile ListAttribute<Starter, Submissao> submissoes;
	public static volatile SingularAttribute<Starter, String> name;
	public static volatile SingularAttribute<Starter, String> language;
	public static volatile SingularAttribute<Starter, Long> id;
	public static volatile SingularAttribute<Starter, String> email;
	public static volatile SingularAttribute<Starter, String> username;

	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String SUBMISSOES = "submissoes";
	public static final String NAME = "name";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

