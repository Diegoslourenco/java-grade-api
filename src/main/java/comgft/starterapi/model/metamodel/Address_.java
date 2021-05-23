package comgft.starterapi.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import comgft.starterapi.model.Address;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> number;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> district;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, String> complement;
	public static volatile SingularAttribute<Address, String> cep;

	public static final String NUMBER = "number";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String DISTRICT = "district";
	public static final String STATE = "state";
	public static final String COMPLEMENT = "complement";
	public static final String CEP = "cep";

}

