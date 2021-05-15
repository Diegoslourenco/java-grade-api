package comgft.starterapi.exceptionhandler;

/**
 * Error --- represents a error that occurs during a request.
 * @author    Diego da Silva Lourenco
 */

public class Error {
	
	private String messageUser;
	private String messageDeveloper;
		
	public Error(String messageUser, String messageDeveloper) {
		this.messageUser = messageUser;
		this.messageDeveloper = messageDeveloper;
	}

	public String getMessageUser() {
		return messageUser;
	}
	
	public String getMessageDeveloper() {
		return messageDeveloper;
	}	
}
