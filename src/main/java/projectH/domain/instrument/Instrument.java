package projectH.domain.instrument;

public class Instrument {

	private InstrumentStatus status;
	private final String serial;
	private final String typecode;

	public Instrument(String typecode, InstrumentStatus status) {
		this(typecode, status, "");
	}

	public Instrument(String typecode, InstrumentStatus status, String serialNumber) {
		if (typecode == null || typecode.equals(""))
			throw new IllegalArgumentException("Typecode cannot be empty");
		if (serialNumber == null)
			throw new IllegalArgumentException("Serial number cannot be 'null'");
		this.status = status;
		this.typecode = typecode;
		this.serial = serialNumber;
	}

	public InstrumentStatus getStatus() {
		return status;
	}

	public void setStatus(InstrumentStatus anotherStatus) {
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
