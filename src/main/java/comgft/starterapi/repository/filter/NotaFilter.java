package comgft.starterapi.repository.filter;

/**
 * NotaFilter --- represents a filter for the resource Nota.
 * @author    Diego da Silva Lourenco
 */

public class NotaFilter {
	
	private int gradeCodeQuality;
	
	private int gradeQuantityDelivered;

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

}
