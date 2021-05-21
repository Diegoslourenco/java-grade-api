package comgft.starterapi.exceptionhandler;

/**
 * Error --- represents a error that occurs during a request.
 * @author    Diego da Silva Lourenco
 */

public class Error {
	
	private String message;
	private String description;
		
	public Error(String message, String description) {
		this.message = message;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}
	
	public String getDescription() {
		return description;
	}	
	
}
