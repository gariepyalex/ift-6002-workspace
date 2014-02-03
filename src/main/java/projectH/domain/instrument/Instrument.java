package projectH.domain.instrument;


public class Instrument {

	public enum Status {
		USED_PATIENT, SOILED, UNUSED
	};

	private Status status;
	private final String serial;
	private final String typecode;

	public Instrument(String typecode, Status status) {
		this(typecode, status, "");
	}

	public Instrument(String typecode, Status status, String serialNumber) {
		this.status = status;
		this.typecode = typecode;
		this.serial = serialNumber;
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
		return serial.isEmpty();
	}

	public String getTypecode() {
		return typecode;
	}

	@Override
	public boolean equals(Object obj) {
		Instrument instrument = (Instrument) obj;
		return !isAnonymous() && this.serial.equals(instrument.serial);
	}
}
