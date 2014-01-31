package projectH.domain.prescription;

public class InvalidPrescriptionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPrescriptionException(String string) {
		super(string);
	}

}