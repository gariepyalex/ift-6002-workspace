package projectH.domain.operation;

public class InvalidOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidOperationException(String description) {
		super(description);
	}

}
