package comgft.starterapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Submissao --- represents a Starter's submission for a Desafio.
 * @author    Diego da Silva Lourenco
 */

@Entity
@Table(name = "submissoes")
@JsonIgnoreProperties("nota")
public class Submissao extends RepresentationModel<Submissao> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnoreProperties("links")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "starter_id")
	private Starter starter;
	
	@JsonIgnoreProperties("links")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "desafio_id")
	private Desafio desafio;
	
	@NotNull
	@Column(name = "repository_url")
	private String repositoryUrl;
	
	@OneToOne(mappedBy = "submissao", cascade = CascadeType.REMOVE)
	private Nota nota;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Starter getStarter() {
		return starter;
	}

	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}

	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submissao other = (Submissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
