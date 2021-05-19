package comgft.starterapi.model.starter;

import javax.persistence.Column;

/**
 * Language --- represents the code language for a Starter.
 * @author    Diego da Silva Lourenco
 */

public class Language {
	
	@Column(name = "language_name")
	private String languageName;

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}
