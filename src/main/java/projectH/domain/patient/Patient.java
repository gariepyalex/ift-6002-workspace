package projectH.domain.patient;

import java.util.Objects;

public class Patient {

	private final int number;

	public Patient(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Patient)) {
			return false;
		}

		Patient other = (Patient) obj;

		return Objects.equals(number, other.number);
	}

	@Override
	public String toString() {
		return "Patient #" + String.valueOf(number);
	}
}
