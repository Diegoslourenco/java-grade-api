package comgft.starterapi.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import comgft.starterapi.model.Perfil;
import comgft.starterapi.model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile SingularAttribute<Usuario, Perfil> role;
	public static volatile SingularAttribute<Usuario, String> name;
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> username;

	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

