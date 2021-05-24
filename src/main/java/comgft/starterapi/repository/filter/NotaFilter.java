package comgft.starterapi.repository.filter;

/**
 * NotaFilter --- represents a filter for the resource Nota.
 * @author    Diego da Silva Lourenco
 */

public class NotaFilter {
	
	private String gradeCodeQuality;
	
	private String gradeQuantityDelivered;

	public String getGradeCodeQuality() {
		return gradeCodeQuality;
	}

	public void setGradeCodeQuality(String gradeCodeQuality) {
		this.gradeCodeQuality = gradeCodeQuality;
	}

	public String getGradeQuantityDelivered() {
		return gradeQuantityDelivered;
	}

	public void setGradeQuantityDelivered(String gradeQuantityDelivered) {
		this.gradeQuantityDelivered = gradeQuantityDelivered;
	}

}
