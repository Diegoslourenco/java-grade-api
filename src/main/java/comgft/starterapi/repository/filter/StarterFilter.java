package comgft.starterapi.repository.filter;

/**
 * StarterFilter --- represents a filter for the resource Starter.
 * @author    Diego da Silva Lourenco
 */

public class StarterFilter {
	
	private String name;
	
	private String email;
	
	private String username;
	
	private String language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
