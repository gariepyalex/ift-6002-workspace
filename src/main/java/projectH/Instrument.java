package projectH;

public class Instrument {

	public enum Status {
		USED_PATIENT, SOILED, UNUSED
	};

	private Status status;
	private String serial;
	private String typecode;

	public Instrument(String aTypecode, Status aStatus) {
		this.status = aStatus;
		this.typecode = aTypecode;
		this.serial = "";
	}

	public Instrument(String aTypecode, Status aStatus, String aSerialNumber) {
		this.status = aStatus;
		this.typecode = aTypecode;
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

	public boolean isAnonymous() {
		return serial == "";
	}

	public String getTypecode() {
		return typecode;
	}
}
