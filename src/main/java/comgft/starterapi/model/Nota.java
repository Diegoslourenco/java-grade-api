package comgft.starterapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * Nota --- represents a Starter's grade in a Submissao for a Desafio.
 * @author    Diego da Silva Lourenco
 */

@Entity
@Table(name = "notas")
public class Nota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "submissao_id")
	private Submissao submissao;
	
	@NotNull
	@Column(name = "grade_code_quality")
	@Range(min = 1, max = 3)
	private int gradeCodeQuality;
	
	@NotNull
	@Column(name = "grade_quantity_delivered")
	@Range(min = 1, max = 3)
	private int gradeQuantityDelivered;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Submissao getSubmissao() {
		return submissao;
	}

	public void setSubmissao(Submissao submissao) {
		this.submissao = submissao;
	}

	public int getGradeCodeQuality() {
		return gradeCodeQuality;
	}

	public void setGradeCodeQuality(int gradeCodeQuality) {
		this.gradeCodeQuality = gradeCodeQuality;
	}

	public int getGradeQuantityDelivered() {
		return gradeQuantityDelivered;
	}

	public void setGradeQuantityDelivered(int gradeQuantityDelivered) {
		this.gradeQuantityDelivered = gradeQuantityDelivered;
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
		Nota other = (Nota) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
