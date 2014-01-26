package projectH;

public class Instrument {

	public enum Status {
		USED_PATIENT, SOILED, UNUSED
	};

	private Status status;
	private String serial;

	public Instrument(Status aStatus) {
		this.status = aStatus;
		this.serial = "";
	}

	public Instrument(Status aStatus, String aSerialNumber) {
		this.status = aStatus;
		this.serial = aSerialNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status anotherStatus) {
		status = anotherStatus;
	}

	public String getSerial() {
		return serial;
	}
}
