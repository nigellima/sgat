package integra.exceptions;

public class ValidationDataException extends RuntimeException {
	public ValidationDataException() {}
	
	public ValidationDataException(String message) {
		super(message);
	}

}
